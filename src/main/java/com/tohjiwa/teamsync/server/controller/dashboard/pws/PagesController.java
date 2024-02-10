package com.tohjiwa.teamsync.server.controller.dashboard.pws;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("Dashboard-Page")
@Controller
@RequestMapping("/dashboard/pages")
public class PagesController {

	@GetMapping("/pages-starter")
	public String starter() {
		return "dashboard/pages/starter";
	}
	
	@GetMapping("/pages-team")
	public String team() {
		return "dashboard/pages/team";
	}
	
	@GetMapping("/pages-timeline")
	public String timeline() {
		return "dashboard/pages/timeline";
	}
	
	@GetMapping("/pages-faqs")
	public String faqs() {
		return "dashboard/pages/faqs";
	}
	
	@GetMapping("/pages-pricing")
	public String pricing() {
		return "dashboard/pages/pricing";
	}
	
	@GetMapping("/pages-gallery")
	public String gallery() {
		return "dashboard/pages/gallery";
	}
	
	@GetMapping("/pages-maintenance")
	public String maintenance() {
		return "dashboard/pages/maintenance";
	}
	
	@GetMapping("/pages-coming-soon")
	public String coming_soon() {
		return "dashboard/pages/coming-soon";
	}
	
	@GetMapping("/pages-sitemap")
	public String sitemap() {
		return "dashboard/pages/sitemap";
	}
	
	@GetMapping("/pages-search-results")
	public String search_results() {
		return "dashboard/pages/search-results";
	}
	
	@GetMapping("/pages-privacy-policy")
	public String privacy_policy() {
		return "dashboard/pages/privacy-policy";
	}
	
	@GetMapping("/pages-term-conditions")
	public String term_conditions() {
		return "dashboard/pages/term-conditions";
	}
	
	@GetMapping("/landing")
	public String landing() {
		return "dashboard/pages/landing";
	}
	
	@GetMapping("/nft-landing")
	public String nft_landing() {
		return "dashboard/pages/nft-landing";
	}
	
	@GetMapping("/job-landing")
	public String job_landing() {
		return "dashboard/pages/job-landing";
	}
	
}
