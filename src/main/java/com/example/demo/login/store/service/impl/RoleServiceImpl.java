package com.example.demo.login.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.store.entity.Role;
import com.example.demo.login.store.reponsitory.RoleRepository;
import com.example.demo.login.store.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleDAO;

	public List<Role> findAll() {
		return roleDAO.findAll();
	}
}
