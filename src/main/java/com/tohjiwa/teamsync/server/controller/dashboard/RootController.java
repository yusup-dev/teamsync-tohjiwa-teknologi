package com.tohjiwa.teamsync.server.controller.dashboard;

import com.tohjiwa.teamsync.server.model.SecurityUser;
import com.tohjiwa.teamsync.server.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Service("Dashboard-Main")
@Controller
@RequestMapping("/dashboard")
public class RootController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView index() {
        return new ModelAndView("redirect:" + "/dashboard/");
    }

    @GetMapping("/workspaces")
    public String workspaces(Model model, Authentication authentication) {
        var securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.user().getId();

        var userRsl = userService.getUser(userId);

        model.addAttribute("user", userRsl.get());
        return "dashboard/workspaces";
    }

    @PostMapping(value = "/workspaces")
    public String workspaces(HttpServletResponse res, Long pwsId) {
        // create a cookie
        Cookie cookie = new Cookie("pwsId", pwsId.toString());
        cookie.setPath("/dashboard/");
        // cookie.setMaxAge((int)TimeUnit.HOURS.toHours(1));

        // add a cookie to the response
        res.addCookie(cookie);

        return "redirect:" + "/dashboard/pws/" + pwsId + "/dashboard/dashboard-1";
    }

}
