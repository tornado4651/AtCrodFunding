package com.atguigu.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atguigu.spring.entity.Employee;

@Configuration
public class MyAnnotationConfiguration {
	
	@Bean
	public Employee getEmployee() {
		
		return new Employee();
	}

}
