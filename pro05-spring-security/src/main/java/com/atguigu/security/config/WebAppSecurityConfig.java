package com.atguigu.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

@Configuration
@EnableWebSecurity // 启用web环境下的权限控制功能
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private MyPasswordEncoder myPasswordEncoder;
	
	
	/**
	 * 配置用户角色和权限
	 */
	@Override protected void configure(AuthenticationManagerBuilder builder) throws Exception { 
//		builder
//			.inMemoryAuthentication() 
//			.withUser("tom")
//			.password("123123") //设置账号密码
//			.roles("ADMIN", "学徒") //设置角色
//			.and() 
//			.withUser("jerry")
//			.password("456456")//设置另一个账号密码
//			.authorities("SAVE","内门弟子"); //设置权限}
		
		// 使用数据“记住我”
		builder
		.userDetailsService(userDetailsService)
		.passwordEncoder(myPasswordEncoder);
		;
	}
	
	/**
	 * 配置请求路径
	 */
	@Override protected void configure(HttpSecurity security) throws Exception {
		
		JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl(); 
		repository.setDataSource(dataSource);
		
		security
				.authorizeRequests()		//对请求进行授权
					// 放行一些小范围资源的允许权限
					.antMatchers("/layui/**","/index.jsp").permitAll()	
					.antMatchers("/level1/**").hasRole("学徒")
					.antMatchers("/level2/**").hasAuthority("内门弟子")
					.antMatchers("/level3/**").hasRole("宗师")
					.and()
				.authorizeRequests()		//对请求进行授权
					// 对其他大范围的资源的全部请求,需要认证
					.anyRequest()
					.authenticated()	
					.and() 
				.formLogin()				// 开启表单形式登录
					// 开启登录功能基础设置
					.loginPage("/index.jsp")
					.loginProcessingUrl("/do/login.html") 
					.usernameParameter("loginAcct") 
					.passwordParameter("userPswd") 
					.defaultSuccessUrl("/main.html") 
					.and()
				.logout()					// 开启退出功能							
					.logoutUrl("/do/logout.html")
					.logoutSuccessUrl("/index.jsp")
					.and()
				.exceptionHandling()		// 配置异常处理
					.accessDeniedPage("/to/no/auth/page.html")
					.accessDeniedHandler(new AccessDeniedHandler() {
						public void handle(HttpServletRequest request, HttpServletResponse response,
								AccessDeniedException accessDeniedException) throws IOException, ServletException {
							request.setAttribute("message", "抱歉，宁无法访问这里的资源~~~~~~~~~~~~~");
							request.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(request, response);;
						}
					})
					.and()
				// 开启记住我功能
				.rememberMe()
				;
	}
}