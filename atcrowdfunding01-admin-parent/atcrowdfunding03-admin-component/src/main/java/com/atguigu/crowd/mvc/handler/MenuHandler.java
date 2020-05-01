package com.atguigu.crowd.mvc.handler;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.service.api.MenuService;
import com.atguigu.crowd.util.ResultEntity;

@Controller
public class MenuHandler {
	
	@Autowired
	MenuService menuService;

	
	/**
	 * 得到所有菜单树形结构的方法
	 * @return 一个根节点的Menu对象
	 */
	@ResponseBody
	@RequestMapping("/menu/get/whole/tree.json")
	public ResultEntity<Menu> getWholeTree(){
		// 1.查找全部的Menu对象
		List<Menu> menuList = menuService.getAll();
		// 2.声明一个变量存储跟节点
		Menu root = null;
		// 3.创建一个Map对象，存储所有Menu对象，方便根据pid、id查找父节点
		HashMap<Integer, Menu> menuMap = new HashMap<>();
		// 4.先把所有menu对象遍历取到map中
		for (Menu menu : menuList) {
			menuMap.put(menu.getId(), menu);
		}
		// 5.再次遍历menuList，查找跟节点并借助menuMap组装
		for (Menu menu : menuList) {
			Integer pid = menu.getPid();
			// 3.1 如果是根节点，连给root
			if(pid == null) {
				root = menu;
			}else {
				Menu fatherMenu = menuMap.get(pid);
				fatherMenu.getChildren().add(menu);
			}
		}
		return ResultEntity.successWithData(root);
	}
	
	
	/**
	 * 保存树形结构节点
	 */
	@ResponseBody
	@RequestMapping("/menu/save.json")
	public ResultEntity<String> saveMenu(Menu menu){
		
		menuService.saveMenu(menu);
		
		return ResultEntity.successWithoutData();
	}
	
	/**
	 * 删除目录节点
	 * @param menuId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu/remove.json")
	public ResultEntity<Menu> removeMenu(Integer menuId){
		
		menuService.removeMenu(menuId);
		
		return ResultEntity.successWithoutData();
	}
	
	
	/**
	 * 更新目录节点
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu/update.json")
	public ResultEntity<Menu> updateMenu(Menu menu){
		
		menuService.updateMenu(menu);
		
		return ResultEntity.successWithoutData();
	}
}
