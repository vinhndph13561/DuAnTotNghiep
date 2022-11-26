package com.example.demo.user_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.entity.UserRole;
import com.example.demo.login.store.reponsitory.RoleRepository;
import com.example.demo.user_admin.service.UserRoleService;
import com.example.demo.user_admin.service.UserService;

@Controller
@RequestMapping
public class UserController {

	@Autowired
	UserRoleService userRoleSer;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRep;
	@GetMapping("/userList")
	public String listUser(ModelMap map) {
		map.addAttribute("listUser", userRoleSer.findAllUsers());
		return "account/userList";
	}
	@GetMapping("/employeesList")
	public String listAdmin(ModelMap map) {
		map.addAttribute("listUser", userRoleSer.findAllAdminOrEmployees());
		return "account/employeesList";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Integer id) throws Exception {
		User u= userRoleSer.findById(id);
		u.setStatus(false);
		userRoleSer.update(u);
//		return "account/employeesList";
		return "redirect:/employeesList";
	}
	@GetMapping("/providedAccount")
	public String providedAccount(ModelMap map) {
		User user = new User();
		map.addAttribute("user",user);
		return "account/providedAccount";
	}
	@PostMapping("saveProvidedAccount")
	public String saveProvidedAccount(@ModelAttribute("user") User u) {
		u.setPassword("-");
		u.setUsername("-");
		u.setGender(false);
		u.setPhoneNumber("-");
		u.setStatus(true);
		userService.save(u);
		UserRole ur = new UserRole();
		ur.setUser(u);
		ur.setRole(roleRep.findById(3).get());
		System.out.print(ur);
		userRoleSer.save(ur);

		return "redirect:/employeesList";
	}
	
}
