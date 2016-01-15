package com.swu.shake.service;

import java.util.List;
/**
 * 用户业务服务类
 */

import com.swu.shake.model.User;
import com.swu.shake.util.MsgException;

public interface UserService {
	/** 新增用户 */
	public User register(User user) throws MsgException;

	/** 删除用户 */
	public boolean remove(int uid);

	/** 修改用户 */
	public boolean modify(User user);

	/** 修改用户密码 */
	public boolean modifyPwd(User user);

	/** 用户登陆 */
	public User login(String name, String password);

	/** 获得用户数量 */
	public int getCount();

	// 查询
	/** 通过用户id查询用户 */
	public User getUserById(int uid);

	/** 查询所有用户 */
	public List<User> getUsers();

	/** 查询所有用户 分页 */
	public List<User> getUsers(int start, int end);

	/** 通过用户名模糊查找得到若干用户 */
	public List<User> getUsers(String username);

	/** 通过用户名得到用户 分页 */
	public List<User> getUsers(String username, int start, int end);
}
