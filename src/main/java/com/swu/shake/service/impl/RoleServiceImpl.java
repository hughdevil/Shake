package com.swu.shake.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swu.shake.dao.RoleDao;
import com.swu.shake.model.Role;
import com.swu.shake.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role register(Role role) {
		return roleDao.save(role);
	}

	@Override
	public boolean modify(Role role) {
		return roleDao.update(role);
	}

	@Override
	public boolean delete(String rid) {
		return roleDao.delete(rid);
	}

	@Override
	public List<Role> finall() {
		return roleDao.findall();
	}

	@Override
	public List<Role> findall(int start, int end) {
		return roleDao.findall(start, end);
	}

	@Override
	public List<Role> getRoleByName(String rname) {
		return roleDao.getRolesByName(rname);
	}

	@Override
	public List<Role> getRoleByName(String rname, int start, int end) {
		return roleDao.getRolesByName(rname, start, end);
	}

	@Override
	public List<Role> getRoleByLevel(int rlevel) {
		return roleDao.getRolesByLevel(rlevel);
	}

	@Override
	public int getCount() {
		return roleDao.getCount();
	}

}