package com.atguigu.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.MenuExample;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.service.api.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	public List<Menu> getAll(){
		return menuMapper.selectByExample(new MenuExample());
	}

	@Override
	public void saveMenu(Menu menu) {
		menuMapper.insert(menu);
	}

	@Override
	public void removeMenu(Integer menuId) {
		menuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public void updateMenu(Menu menu) {
		menuMapper.updateByPrimaryKeySelective(menu);
	}
}
