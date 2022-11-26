package com.example.demo.login.store.service;

import java.util.List;

import com.example.demo.login.store.entity.User;



public interface UserService {
	public List<User> findAll();

	public User findByUsernameEquals(String username);

	public List<User> getAdministrators();
}
