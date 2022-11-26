package com.example.demo.user_admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.entity.UserRole;
import com.example.demo.login.store.reponsitory.UserRepository;
import com.example.demo.login.store.reponsitory.UserRoleRepository;
import com.example.demo.user_admin.service.UserRoleService;
@Service
public class UserRoleServiceiplm implements UserRoleService{
	
	@Autowired
	UserRoleRepository userRoleRep;
	@Autowired
	UserRepository userRep;
	@Override
	public List<User> findAllAdminOrEmployees() {
		List<User> list= new ArrayList<User>();
		List<UserRole> listUserRole = userRoleRep.findAllAdminOrEmployees();
		Boolean check = true;
		for (UserRole userRole : listUserRole) {
//			for (User user : list) {
//				if(userRole.getUser().getId() == user.getId() ) {
//					
//					check= false;
//				}
//			}
			if(check && userRole.getUser().getStatus() == true) {
				list.add(userRole.getUser());
			}
		}
		return list;
	}
	@Override
	public List<User> findAllUsers() {
		List<User> list= new ArrayList<User>();
		List<UserRole> listUserRole = userRoleRep.findAllUsers();
		Boolean check = true;
		for (UserRole userRole : listUserRole) {
//			for (User user : list) {
//				if(userRole.getUser().getId() == user.getId()) {
//					
//					check= false;
//				}
//			}
			if(check && userRole.getUser().getStatus()==true) {
				list.add(userRole.getUser());
			}
		}
		return list;
	}
	@Override
	public User findById(int id) {
		return userRep.findById(id).get();
	}
	@Override
	public User update(User u) throws Exception {
		int id = u.getId();
		if(userRep.findById(id).isPresent()) {
			return userRep.save(u);
		} else {
			throw  new Exception();
		}
	}
	@Override
	public void save(UserRole u) {
		userRoleRep.save(u);
		
	}
}
