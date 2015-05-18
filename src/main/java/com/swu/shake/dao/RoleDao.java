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
	public boolean delete(int id);

	/** 修改角色类型 */
	public boolean update(Role role);

	/** 通过Id查找角色 */
	public Role getRoleById(int id);

	/** 查询所有角色类型 */
	public List<Role> findall();

	public List<Role> findallByPage(int start, int end);

	/** 根据角色名查找角色 */
	public List<Role> getRolesByName(String str);

	public List<Role> getRolesByName(String str, int start, int end);

}
