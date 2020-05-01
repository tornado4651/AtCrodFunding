package com.atguigu.crowd.mvc.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.crowd.exception.AccessForbiddenException;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseException;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;

/**
 * @ControllerAdvice表示当前类是SpringMVC的Controller增强类
 * 可以实现以下三个功能：
 * 全局异常处理、全局数据绑定、全局数据预处理
 * @author tornado4651
 *
 */

@ControllerAdvice
public class CrowdExceptionResolver {
	/**
	 * 通用方法：异常处理
	 * @param viewName
	 * @param exception
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private ModelAndView commonResolve(
			// 传入参数：异常处理完成后要去的页面
			String viewName, 
			// 传入参数：实际捕获到的异常类型
			Exception exception, 
			// 传入参数：当前请求对象
			HttpServletRequest request, 
			// 传入参数：当前响应对象
			HttpServletResponse response) throws IOException {
		
				// 1.判断当前请求类型
				boolean judgeResult = CrowdUtil.judgeRequestTypeIsAjax(request);
				
				// 2.如果是Ajax请求
				if(judgeResult) {
					
					// 3.创建ResultEntity对象
					ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
					
					// 4.创建Gson对象
					Gson gson = new Gson();
					
					// 5.将ResultEntity对象转换为JSON字符串
					String json = gson.toJson(resultEntity);
					
					// 6.将JSON字符串作为响应体返回给浏览器
					response.getWriter().write(json);
					
					// 7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
					return null;
				}
				
				// 8.如果不是Ajax请求,而是普通请求，则创建ModelAndView对象
				ModelAndView modelAndView = new ModelAndView();
				
				// 9.将Exception对象存入模型
				modelAndView.addObject("MyException", exception);
				
				// 10.设置对应的视图名称
				modelAndView.setViewName(viewName);
				
				// 11.返回modelAndView对象
				return modelAndView;
	}

		
	/**
	 * 处理 登陆失败 的异常：返回登陆界面
	 * @param exception
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws IOException
	 */
	// @ExceptionHandler将一个具体的异常类型和一个方法关联起来
	@ExceptionHandler(value = LoginFailedException.class)
	public ModelAndView resolveLoginFailedException(
			LoginFailedException exception,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String viewName = "admin-login";
		
		return commonResolve(viewName, exception, request, response);
	}
	
	
	/**
	 * 处理未登录禁止访问时的异常
	 * @param exception
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws IOException
	 */
	// @ExceptionHandler将一个具体的异常类型和一个方法关联起来
	@ExceptionHandler(value = AccessForbiddenException.class)
	public ModelAndView resolveAccessForbiddenException(
			AccessForbiddenException exception,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		
		String viewName = "admin-login";
		
		return commonResolve(viewName, exception, request, response);
	}
	
	
	/**
	 * 处理空指针异常
	 * @param exception
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws IOException
	 */
	// @ExceptionHandler将一个具体的异常类型和一个方法关联起来
	@ExceptionHandler(value = NullPointerException.class)
	public ModelAndView resolveNullPointerException(
			NullPointerException exception, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String viewName = "system-error";

		return commonResolve(viewName, exception, request, response);
	}
	
	
	/**
	 * 处理“新建”用户信息时用户名已存在的异常
	 * @param exception
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws IOException
	 */
	// @ExceptionHandler将一个具体的异常类型和一个方法关联起来
	@ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
	public ModelAndView resolveLoginAcctAlreadyInUseException(
			LoginAcctAlreadyInUseException exception, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String viewName = "admin-add";

		return commonResolve(viewName, exception, request, response);
	}
	
	
	/**
	 * 处理“更新”用户信息时用户名已存在的异常
	 * @param exception
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws IOException
	 */
	// @ExceptionHandler将一个具体的异常类型和一个方法关联起来
	@ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
	public ModelAndView resolveLoginAcctAlreadyInUseForUpdateException(
			LoginAcctAlreadyInUseForUpdateException exception, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String viewName = "system-error";

		return commonResolve(viewName, exception, request, response);
	}
	
}
