package com.example.demo.user_admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.reponsitory.UserRepository;
import com.example.demo.user_admin.service.UserService;
@Service
public class UserServiceIplm implements UserService{
	@Autowired
	UserRepository rep;

	@Override
	public void save(User u) {
		rep.save(u);
	}
}
