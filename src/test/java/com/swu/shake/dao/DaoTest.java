package com.swu.shake.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.swu.shake.AbstractTest;import com.swu.shake.util.HibernateUtil;

public class DaoTest extends AbstractTest {
	@Autowired
	UserDao userDao;
	
//	@Test
	public void testSQLInject(){
		String userName = "' or 1=1 or ''='";
		System.out.println(userDao.getUsersByName(userName).size());
		userDao.getUsersByNameNoLike("T");
		userDao.login("213123", "123123123");
	}
	@Test
	public void testSQL(){
		System.out.println(userDao.login("%admi%","21232F297A57A5A743894A0E4A801FC3"));
	}
}
