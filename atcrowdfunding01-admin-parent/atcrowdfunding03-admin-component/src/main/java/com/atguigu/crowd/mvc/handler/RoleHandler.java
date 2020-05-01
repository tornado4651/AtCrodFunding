package com.atguigu.crowd.mvc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;

@Controller
public class RoleHandler {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	@ResponseBody//@ReseonpseBody注解是为了返回JSON数据，而不是传给viewResolvor
	@RequestMapping("/role/get/page/info.json")
	public ResultEntity<PageInfo<Role>> getPageInfo(
			@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
			@RequestParam(value="keyword", defaultValue="") String keyword){
				// 调用service方法获取分页数据
				try {
					PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
					return ResultEntity.successWithData(pageInfo);
				} catch (Exception e) {
					e.printStackTrace();
					return ResultEntity.failed(e.getMessage());
				}
	}
	
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	@ResponseBody//@ReseonpseBody注解是为了返回JSON数据，而不是传给viewResolvor
	@RequestMapping("/role/save.json")
	public ResultEntity<String> saveRole(Role role){
		
		roleService.saveRole(role);
		
		return ResultEntity.successWithoutData();
	}
	
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	@ResponseBody//@ReseonpseBody注解是为了返回JSON数据，而不是传给viewResolvor
	@RequestMapping("/role/update.json")
	public ResultEntity<String> updateRole(Role role){
		
		roleService.updateRole(role);
		return ResultEntity.successWithoutData();
	}
	
	/**
	 * 删除角色功能
	 * 传入值为List类型，所以统一实现了单条删除和多条删除
	 * 这里使用了@ResponseBody和@RequestBody两个json注解
	 * @param roleIdList
	 * @return
	 */
	@ResponseBody//@ReseonpseBody注解是为了返回JSON数据，而不是传给viewResolvor
	@RequestMapping("/role/remove/by/role/id/array.json")
	public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList){
		roleService.removeRole(roleIdList);
		return ResultEntity.successWithoutData();
	}
	
	
	
}
