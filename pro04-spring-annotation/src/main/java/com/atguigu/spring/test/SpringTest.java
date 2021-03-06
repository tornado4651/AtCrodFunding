package com.atguigu.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.spring.MyAnnotationConfiguration;
import com.atguigu.spring.entity.Employee;

public class SpringTest {
	public static void main(String[] args) {
//		new ClassPathXmlApplicationContext();
		
		// 基于注解的IOC容器
		AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(MyAnnotationConfiguration.class);
		
		// 从IOC中获取bean
		Employee employee = context.getBean(Employee.class);
		System.out.println(employee);
		
		// 关闭IOC容器
		context.close();
	}
}
