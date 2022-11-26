package com.example.demo.user_admin.service;

import java.util.List;

import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.entity.UserRole;



public interface UserRoleService {

	List<User> findAllAdminOrEmployees();
	List<User> findAllUsers();
	public User findById(int id);
	public User update( User u) throws Exception;
	public void save(UserRole u);
}
