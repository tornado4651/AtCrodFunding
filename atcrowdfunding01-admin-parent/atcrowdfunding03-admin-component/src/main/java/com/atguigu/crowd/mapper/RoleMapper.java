package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
     * 自定义接口：根据关键字查询
     */
    List<Role> selectRoleByKeyword(String keyword);
    
    /**
     * 自定义接口：查询当前用户已分配的角色
     */
	List<Role> selectAssignedRole(Integer adminId);
	
	/**
     * 自定义接口：查询当前用户未分配角色
     */
	List<Role> selectUnAssignedRole(Integer adminId);
}