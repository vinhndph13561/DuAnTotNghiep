package com.example.demo.login.store.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.login.store.common.GooglePojo;
import com.example.demo.login.store.common.GoogleUtils;
import com.example.demo.login.store.common.RestFB;
import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.entity.UserRole;
import com.example.demo.login.store.reponsitory.RoleRepository;
import com.example.demo.login.store.reponsitory.UserRepository;
import com.example.demo.login.store.reponsitory.UserRoleRepository;



@Controller
public class LoginAndRegisterController {
	@Autowired
	private GoogleUtils googleUtils;

	@Autowired
	private RestFB restFb;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	RoleRepository roleRep;

	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return "redirect:/login?google=error";
		}

		String accessToken = googleUtils.getToken(code);

		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
		UserDetails userDetail = googleUtils.buildUser(googlePojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	@RequestMapping("/login-facebook")
	public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return "redirect:/login?facebook=error";
		}
		String accessToken = restFb.getToken(code);

		com.restfb.types.User user = restFb.getUserInfo(accessToken);
		UserDetails userDetail = restFb.buildUser(user);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "login/403";
	}

	@RequestMapping("/admin/index")
	public String admin1() {
		return "admin/index";
	}

	@RequestMapping("/customer/index")
	public String customer() {
		return "customer/index";
	}

	@RequestMapping("/security/login")
	public String login(User user, Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "login/loginandregister";
	}

	@PostMapping("/security/register")
	public String register(@ModelAttribute("user") User u, Model model) {
		userRepository.save(u);
		UserRole ur = new UserRole();
		ur.setUser(u);
		ur.setRole(roleRep.findById(1).get());
		System.out.print(ur);
		userRoleRepository.save(ur);
		return "redirect:/security/register/success";
	}
	
	@RequestMapping("/security/register/success")
	public String registerSuccess(User user, Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		model.addAttribute("message1", "Đăng ký thành công!");
		return "login/loginandregister";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(User user,Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "login/loginandregister";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(User user,Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "redirect:/";
	}
//	
//	@CrossOrigin("*")
//	@ResponseBody
//	@RequestMapping("/rest/security/authentication")
//	public Object getAuthentication(HttpSession session) {
//		return session.getAttribute("authentication");
//	}
}
