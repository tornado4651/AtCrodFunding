package com.atguigu.crowd.mvc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;

@Controller
public class TestHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping("/test/ssm.html")
	public String testSsm(Model model) {

		List<Admin> adminList = adminService.getAll();
		model.addAttribute("adminList", adminList);
		
		System.out.println(10/0);

		return "target";
	}

	@ResponseBody
	@RequestMapping("/send/array/one.html")
	public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {
		for (Integer number : array) {
			System.out.println("number = " + number);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("/send/array/two.html")
	public String testReceiveArrayTwo(@RequestParam("array") List<Integer> array) {
		for (Integer number : array) {
			System.out.println("number = " + number);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("/send/array/three.html")
	public String testReceiveArrayThree(@RequestBody List<Integer> array) {

		for (Integer number : array) {
			System.out.println("This number = " + number);
		}

		return "success";
	}


}