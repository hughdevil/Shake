package com.swu.shake.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.swu.shake.AbstractTest;
import com.swu.shake.model.User;
import com.swu.shake.util.HibernateUtil;

public class BaseDaoTest extends AbstractTest{
	
	@Autowired
	HibernateUtil hibernateUtil;
	
	@Test
	public void test(){
		System.out.println("1111111111");
		String hql = "from User";
		List<User> users = hibernateUtil.exeQuery(hql);
		System.out.println(users.size());
	}

}
