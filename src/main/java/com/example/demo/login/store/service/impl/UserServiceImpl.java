package com.example.demo.login.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.reponsitory.UserRepository;
import com.example.demo.login.store.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userDAO;

	public List<User> findAll() {
		return userDAO.findAll();
	}

	public User findById(Integer id) {
		return userDAO.findById(id).get();
	}

	public List<User> getAdministrators() {
		return userDAO.getAdministrators();
	}

	@Override
	public User findByUsernameEquals(String username) {
		return userDAO.findByUsernameEquals(username);
	}
}