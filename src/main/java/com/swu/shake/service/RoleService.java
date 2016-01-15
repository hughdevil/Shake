package com.swu.shake.service;

import java.util.List;

import com.swu.shake.model.Role;

public interface RoleService {

	/** 注册新的角色 */
	public Role register(Role role);

	/** 修改角色 */
	public boolean modify(Role role);

	/** 删除角色 */
	public boolean delete(String rid);

	/** 获得角色 */
	public Role getRole(String rid);

	/** 获得所有角色 */
	public List<Role> finall();

	/** 获得所有角色 分页 */
	public List<Role> findall(int start, int end);

	/** 通过名称，获得所有角色 */
	public List<Role> getRoleByName(String rname);

	/** 通过名称，获得所有角色 分页 */
	public List<Role> getRoleByName(String rname, int start, int end);

	/** 根据角色等级，查找下级角色 */
	public List<Role> getRoleByLevel(int rlevel);

	/** 获得当前角色的总和 */
	public int getCount();

}
