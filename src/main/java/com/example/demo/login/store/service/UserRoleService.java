package com.example.demo.login.store.service;

import java.util.List;

import com.example.demo.login.store.entity.UserRole;



public interface UserRoleService {
	public List<UserRole> findAll();

	public List<UserRole> findAuthoritiesOfAdministrators();
}