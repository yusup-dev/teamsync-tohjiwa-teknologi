package com.tohjiwa.teamsync.server.controller.dashboard.pws;

import com.tohjiwa.teamsync.server.model.SecurityUser;
import com.tohjiwa.teamsync.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("Dashboard-Main1")
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String index(Model model, Authentication authentication, @Nullable @CookieValue("pwsId") Long pwsId) {
		if(pwsId == null) {
			return "redirect:" + "/dashboard/workspaces";
		} else {
			return "redirect:" + "/dashboard/pws/" + pwsId + "/dashboard/dashboard-1";
		}
	}

	@GetMapping("/pws/{pwsId}/dashboard/dashboard-1")
	public String dashboard(Model model, Authentication authentication, @PathVariable Long pwsId) {
		var securityUser = (SecurityUser) authentication.getPrincipal();
		Long userId = securityUser.user().getId();

		var userRsl = userService.getUser(userId);

		model.addAttribute("user", userRsl.get());
		return "dashboard/pws/dashboard/index";
	}
	
	@GetMapping("/pws/{pwsId}/dashboard/dashboard-analytics")
	public String dashboard_analytics() {
		return "dashboard/pws/dashboard/analytics";
	}
	
	@GetMapping("/dashboard-crm")
	public String dashboard_crm() {
		return "dashboard/dashboard/crm";
	}
	
	@GetMapping("/dashboard-crypto")
	public String dashboard_crypto() {
		return "dashboard/dashboard/crypto";
	}
	
	@GetMapping("/dashboard-projects")
	public String dashboard_projects() {
		return "dashboard/dashboard/projects";
	}
	
	@GetMapping("/dashboard-nft")
	public String dashboard_nft() {
		return "dashboard/dashboard/nft";
	}
	
	@GetMapping("/dashboard-job")
	public String dashboard_job() {
		return "dashboard/dashboard/job";
	}
}
