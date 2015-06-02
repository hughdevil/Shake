package com.swu.shake.service;

import java.util.List;
/**
 * 用户业务服务类
 */





import com.swu.shake.model.User;
import com.swu.shake.util.MsgException;

public interface UserService {
	/**新增用户*/
	public User register (User user)  throws MsgException;
	/**删除用户*/
	public boolean remove(String[] id);
	/**修改用户*/
	public boolean modify(User p);
	/**查询所有用户*/
	public List<User> getPersons();
	/**用户登陆*/
	public User login (String name,String password);
	/**检测用户名是否存在*/
	public boolean checkUserName(String username);
	/**检测用户id是否存在*/
	public boolean checkUserId(int uid);
	/**分页显示所有用户*/
	public List<User> getPersons(int start, int end);
	/**获得用户数量*/
	public int getCount();
}
