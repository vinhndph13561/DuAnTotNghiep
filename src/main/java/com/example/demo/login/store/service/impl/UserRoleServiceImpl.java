package com.example.demo.login.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.entity.UserRole;
import com.example.demo.login.store.reponsitory.UserRepository;
import com.example.demo.login.store.reponsitory.UserRoleRepository;
import com.example.demo.login.store.service.UserRoleService;



@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	UserRoleRepository userRoleDAO;
	@Autowired
	UserRepository userDAO;

	public List<UserRole> findAll() {
		return userRoleDAO.findAll();
	}

	public List<UserRole> findAuthoritiesOfAdministrators() {
		List<User> users = userDAO.getAdministrators();
		return userRoleDAO.authoritiesOf(users);
	}
}
