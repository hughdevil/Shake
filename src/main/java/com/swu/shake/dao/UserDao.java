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
	public boolean delete(int id);

	/** 修改用户 */
	public boolean update(User user);

	/** 查询所有用户 */
	public List<User> findall();

	/** 用户登陆 */
	public User login(String name, String password);

	/** 检测用户名是否存在,存在返回true，否则返回false */
	public boolean checkUserName(String username);

	/** 检测用户id是否存在,存在返回true，否则返回false */
	public boolean checkUserId(int  uid);

	/** 分页显示所有用户 */
	public List<User> getPersons(int start, int end);

}
