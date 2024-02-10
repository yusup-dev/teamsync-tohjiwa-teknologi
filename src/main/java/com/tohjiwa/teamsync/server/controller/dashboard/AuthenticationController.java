package com.tohjiwa.teamsync.server.controller.dashboard;

import com.tohjiwa.teamsync.server.model.User;
import com.tohjiwa.teamsync.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Service("Dashboard-Auth")
@Controller
@RequestMapping("/dashboard/authentication")
public class AuthenticationController {
	@Autowired
	UserService userService;
	
	@GetMapping("/auth-signin")
	public String auth_signin() {
		return "dashboard/authentication/auth-signin";
	}
	
	@GetMapping("/auth-signup")
	public String auth_signup() {
		return "dashboard/authentication/auth-signup";
	}

	@PostMapping("/auth-signup")
	public String postRegister(WebRequest request, Model model) {
		var user = new User();
		user.setEmail(request.getParameter("email"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));

		try {
			userService.register(user);
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("errorMessage", ex.getMessage());
			return "dashboard/authentication/auth-signup";
		}

		model.addAttribute("success", true);
		return "dashboard/authentication/auth-success-msg";
	}
	
	@GetMapping("/auth-pass-reset")
	public String auth_pass_reset() {
		return "dashboard/authentication/auth-pass-reset";
	}
	
	@GetMapping("/auth-pass-change")
	public String auth_pass_change() {
		return "dashboard/authentication/auth-pass-change";
	}
	
	@GetMapping("/auth-lockscreen")
	public String auth_lockscreen() {
		return "dashboard/authentication/auth-lockscreen";
	}
	
	@GetMapping("/auth-logout")
	public String auth_logout() {
		return "dashboard/authentication/auth-logout";
	}

	@GetMapping("/auth-success-msg")
	public String auth_success_msg() {
		return "dashboard/authentication/auth-success-msg";
	}
	
	@GetMapping("/auth-twostep")
	public String auth_twostep() {
		return "dashboard/authentication/auth-twostep";
	}
	
	@GetMapping("/auth-404")
	public String auth_not_found_cover() {
		return "dashboard/authentication/auth-404";
	}
	
	@GetMapping("/auth-500")
	public String auth_server_error() {
		return "dashboard/authentication/auth-500";
	}
	
	@GetMapping("/auth-offline")
	public String auth_offline() {
		return "dashboard/authentication/auth-offline";
	}
}






