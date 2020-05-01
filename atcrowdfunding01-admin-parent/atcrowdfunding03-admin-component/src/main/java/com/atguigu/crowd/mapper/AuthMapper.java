package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    /**
     * 自定义方法：根据roleId查询所对应的权限id
     * @param roleId
     * @return 
     */
	List<Integer> selectAssignedAuthIdByRoleId(Integer roleId);
	/**
	 * 自定义方法：根据roleId删除其所有旧权限
	 * @param roleId
	 */
	void deleteOldRelationship(Integer roleId);
	/**
	 * 自定义方法：为角色赋予各类权限
	 * @param roleId
	 * @param authIdList
	 */
	void insertRelationship(@Param("roleId") Integer roleId,@Param("authIdList") List<Integer> authIdList);
}