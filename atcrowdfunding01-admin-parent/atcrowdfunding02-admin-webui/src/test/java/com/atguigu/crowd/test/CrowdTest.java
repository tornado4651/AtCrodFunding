package com.atguigu.crowd.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.AdminService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {
	/**
	 * 装配对象
	 */
	@Autowired
	private DataSource dataSource;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleMapper roleMapper;
	
	
	/**
	 * 测试数据库的连接
	 * @throws SQLException
	 */
	@Test
	public void testConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}
	
	/**
	 * 测试MyBatis，用插入功能
	 */
	@Test
	public void testInsertAdmin() {
		Admin admin = new Admin(null, "tom", "123123", "汤姆", "tom@qq.com", null);
		int count = adminMapper.insert(admin);
		System.out.println(count);
	}

	/**
	 * 测试日志logback功能
	 */
	@Test
	public void testLog() {
		Logger logger = LoggerFactory.getLogger(CrowdTest.class);
		logger.debug("Debug level......");
		logger.debug("Debug level......");
		logger.debug("Debug level......");
		
		logger.info("Info level!!!");
		logger.info("Info level!!!");
		logger.info("Info level!!!");
		
		logger.warn("Warn level......");
		logger.warn("Warn level......");
		logger.warn("Warn level......");
		
		logger.error("Error level!!!");
		logger.error("Error level!!!");
		logger.error("Error level!!!");
	}
	
	/**
	 * 测试事务功能
	 */
	@Test
	public void testTx() {
		Admin admin = new Admin(null,"Jerry", "456456", "杰瑞", "jerry@qq.com", null);
		adminService.saveAdmin(admin);
	}
	
	
	/**
	 * 向数据库中加入假数据
	 */
	@Test
	public void insertData() {
		for (int i = 1; i < 234; i++) {
			int count = adminMapper.insert(new Admin(null, "loginAcct"+i, "userPswd"+i, "userName"+i, "email"+i, null));
			System.out.println("返回值：" + count);
		}
	}
	
	/**
	 * 向role表中插入数据
	 */
	@Test
	public void  insertRoleData() {
		for (int i = 13; i < 212; i++) {
			roleMapper.insert(new Role(null, "role"+i));
		}
	}
}
