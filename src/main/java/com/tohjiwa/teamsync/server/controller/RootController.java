package com.tohjiwa.teamsync.server.controller;

import com.tohjiwa.teamsync.server.model.User;
import com.tohjiwa.teamsync.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/")
public class RootController {
    @Autowired
    UserService userService;

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(WebRequest request, Model model) {
        var user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));

        try {
            userService.register(user);
        } catch (Exception ex) {
            model.addAttribute("success", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "register";
        }

        model.addAttribute("success", true);
        return "register";
    }
}
