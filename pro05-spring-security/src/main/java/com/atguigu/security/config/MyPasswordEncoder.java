package com.atguigu.security.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MyPasswordEncoder implements PasswordEncoder{

	private String privateEncode(CharSequence rawPassword) {
		try {
			// 1.创建MessageDigest对象
			String algorithm = "MD5";
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			// 2.获取rawPassword的字节数组
			byte[] input = ((String)rawPassword).getBytes();
			// 3.加密
			byte[] output = messageDigest.digest(input);
			// 4.转换为16进制的字符
			String encode = new BigInteger(1, output).toString(16).toUpperCase();
			return encode;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String encode(CharSequence rawPassword) {
		return privateEncode(rawPassword);
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// 1.先将原密码加密
		String formPassword = privateEncode(rawPassword);
		// 2.对比数据库中的加密密码
		return Objects.equals(formPassword, encodedPassword);
	}
}
