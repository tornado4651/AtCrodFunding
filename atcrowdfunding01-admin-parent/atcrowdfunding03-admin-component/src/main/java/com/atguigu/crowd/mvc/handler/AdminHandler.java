package com.atguigu.crowd.mvc.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminHandler {

	
	@Autowired
	private AdminService adminService;
	
	
	/**
	 * 用户登录处理
	 * @param loginAcct
	 * @param userPswd
	 * @param session
	 * @return
	 */
	@RequestMapping("/admin/do/login.html")
	public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd, HttpSession session) {
		
		// 调用Service的方法进行登陆检查，验证功能也交给Service方法来做
		Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
		
		// 将成功返回的admin对象存入Session域中
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
		
		return "redirect:/admin/to/main/page.html";
	}
	
	
	/**
	 * 用户登出处理
	 * @param session
	 * @return
	 */
	@RequestMapping("/admin/do/logout.html")
	public String doLogout(HttpSession session) {
		//用户退出，直接使session失效即可
		session.invalidate();
		return "redirect:/admin/to/login/page.html";
	}
	
	
	/**
	 * 用户列表查询，在传入参数中设置默认值，以便做到使该方法适配多种请求
	 * @param keyword
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/admin/get/page.html")
	public String getPageInfo(
			@RequestParam(value = "keyword", defaultValue="") String keyword,
			@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
			Model model
			) {
		
		// 调用Service方法获取PageInfo对象
		PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
		
		// 将pageInfo对象存入模型
		model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		
		return "admin-page";
	}
	
	
	/**
	 * 删除用户功能方法
	 * @param adminId
	 * @return
	 */
	@RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
	public String remove(
			@PathVariable("adminId") Integer adminId,
			@PathVariable("pageNum") Integer pageNum,
			@PathVariable("keyword") String keyword
			) {
		
		adminService.remove(adminId);
		
		// 页面跳转：回到分页页面
		
				// 尝试方案1：直接转发到admin-page.jsp会无法显示分页数据
				// return "admin-page";
				
				// 尝试方案2：转发到/admin/get/page.html地址，一旦刷新页面会重复执行删除浪费性能
				// return "forward:/admin/get/page.html";
				
				// 尝试方案3：重定向到/admin/get/page.html地址
				// 同时为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
				return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
	}
	
	
	/**
	 * 新增用户功能
	 * @param admin
	 * @return
	 */
	@RequestMapping("/admin/save.html")
	public String addAdmin(Admin admin){
		System.out.println(admin);
		adminService.saveAdmin(admin);
		
		return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
	}
	
	
	@RequestMapping("/admin/to/edit/page.html")
	public String toEditPage(
			@RequestParam("adminId") Integer adminId, 
			Model model) {
		
		Admin admin = adminService.getAdminById(adminId);
		model.addAttribute("admin", admin);
		
		return "admin-edit";
	}
	
	
	/**
	 * 更新用户功能
	 * @param admin
	 * @param pageNum
	 * @param keyword
	 * @return
	 */
	@RequestMapping("/admin/update.html")
	public String update(Admin admin, 
			@RequestParam("pageNum") Integer pageNum, 
			@RequestParam("keyword") String keyword) {
		
		adminService.update(admin);
		
		return "redirect:/admin/get/page.html?&pageNum="+pageNum+"&keyword="+keyword;
	}
	
}
