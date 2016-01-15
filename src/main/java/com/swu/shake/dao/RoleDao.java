package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.Role;

/**
 * 用户访问数据对象接口
 * 
 */

public interface RoleDao {
	/** 角色类型注册 */
	public Role save(Role role);

	/** 删除角色类型 */
	public boolean delete(String rid);

	/** 修改角色类型 */
	public boolean update(Role role);

	/** 通过Id查找角色 */
	public Role getRoleById(String rid);

	/** 查询所有角色类型 */
	public List<Role> findall();

	/** 查询所有角色类型 分页 */
	public List<Role> findall(int start, int end);

	/** 根据角色名查找角色 */
	public List<Role> getRolesByName(String rname);

	/** 根据角色名查找角色 分页 */
	public List<Role> getRolesByName(String rname, int start, int end);
	
	/** 根据角色等级，查找下级角色 */
	public List<Role> getRolesByLevel(int rlevel);
	
	/** 获得角色的总和*/
	public int getCount();

}
