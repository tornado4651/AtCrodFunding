package com.atguigu.crowd.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.atguigu.crowd.constant.CrowdConstant;

public class CrowdUtil {
	
	/**
	 * 判断当前请求是否为Ajax请求
	 * @param request 请求对象
	 * @return
	 * 		true：当前请求是Ajax请求
	 * 		false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestTypeIsAjax(HttpServletRequest request) {
		
		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		
		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))
				
				||
				
				(xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
	}
	
	/**
	 * 通用工具方法：对明文字符串进行MD5加密
	 * @param source:传入的明文字符串
	 * @author tornado4651
	 */
	public static String md5(String source) {
		//1.判断source是否有效,考虑周全，安全机制
		if(source==null || source.length()==0) {
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		
		// 2.获取MessageDigest对象,jdk自带的加密机制
		String algorithm = "md5";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		
		// 3.获取明文字符串对应的字节数组
		byte[] input = source.getBytes();
		
		// 4.执行加密
		byte[] output = messageDigest.digest(input);
		
		// 5.创建BigInteger对象
		int signum = 1;//signum value: -1 for negative, 0 for zero, or 1 for positive
		BigInteger bigInteger = new BigInteger(signum , output);

		// 6. 按照16进制将bigInteger值转化为字符串
		int radix = 16;
		String encode = bigInteger.toString(radix).toUpperCase();
		
		return encode;
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
