package com.tohjiwa.teamsync.server.controller.admin;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("Admin-Main")
@Controller
@RequestMapping("/admin")
public class RootController {
    @GetMapping("/")
    public String index(@Nullable @CookieValue("pwsId") Long pwsId) {
        System.out.println(pwsId);
        return "admin/index.html";
    }
}
