package com.atguigu.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import com.atguigu.crowd.entity.RoleExample.Criteria;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleMapper roleMapper;

	/**
	 * 根据关键字查询Role对象
	 */
	@Override
	public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
		// 1.开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		// 2.执行查询,因为PageHelper是一个非侵入式的工具，所以正常返回即为Page类型结果
		List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
		// 3.封装为PageInfo对象并返回
		return new PageInfo<Role>(roleList);
	}

	@Override
	public void saveRole(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public void removeRole(List<Integer> roleIdList) {
		RoleExample example = new RoleExample();
		
		Criteria criteria = example.createCriteria();
		// delete from t_role where id in (5,8,12);
		criteria.andIdIn(roleIdList);
		
		roleMapper.deleteByExample(example);
	}

	
	/**
	 * 获取用户被分配到的角色
	 */
	@Override
	public List<Role> getAssignedRole(Integer adminId) {
		return roleMapper.selectAssignedRole(adminId);
	}

	/**
	 * 获取用户没被分配的角色
	 */
	@Override
	public List<Role> getUnAssignedRole(Integer adminId) {
		return roleMapper.selectUnAssignedRole(adminId);
	}

}
