package com.swu.shake.dao;

import java.util.List;

import com.swu.shake.model.User;

/**
 * 用户访问数据对象接口
 * 
 */

public interface UserDao {
	/** 用户注册 */
	public User save(User user);

	/** 删除用户 */
	public boolean delete(int uid);

	/** 修改用户 */
	public boolean update(User user);

	/** 修改用户密码 */
	public boolean updatePwd(User user);

	/** 修改用户，将用户的角色置为空 */
	public boolean removeRole(String  rid);

	/** 用户登陆 */
	public User login(String name, String password);

	/** 检测用户名是否存在,存在返回true，否则返回false */
	public boolean checkUserName(String username);

	// 查询
	/** 根据用户id查询User */
	public User getUserById(int uid);

	/** 根据用户名查询UserList */
	public List<User> getUsersByNameNoLike(String username);

	/** 查询所有用户 */
	public List<User> findall();

	/** 根据用户名模糊查找用户 */
	public List<User> getUsersByName(String username);

	/** 分页显示所有用户 */
	public List<User> getUsersByPage(int start, int end);

	/** 分页显示根据用户名模糊查到的所有用户 */
	public List<User> getUsersByPage(String username, int start, int end);

}
