package com.tohjiwa.teamsync.server.controller.dashboard;

import com.tohjiwa.teamsync.server.model.SecurityUser;
import com.tohjiwa.teamsync.server.model.UserProfile;
import com.tohjiwa.teamsync.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Service("Dashboard-Page1")
@Controller
@RequestMapping("/dashboard/pages")
public class PagesController {
    @Autowired
    UserService userService;

    @GetMapping("/pages-profile")
    public String profile(Model model, Authentication authentication) {
        var securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.user().getId();

        var userRsl = userService.getUserProfile(userId);

        model.addAttribute("user", userRsl.get());
        return "dashboard/pages/profile";
    }

    @GetMapping("/pages-profile-settings")
    public String profile_settings(Model model, Authentication authentication) {
        var securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.user().getId();

        var userRsl = userService.getUserProfile(userId);

        if (userRsl.isEmpty()) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("user", userRsl.get());
        }
        return "dashboard/pages/profile-settings";
    }

    @PostMapping("/pages-profile-settings")
    public String profile_settings(Model model, WebRequest request, Authentication authentication) {
        var securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.user().getId();

        var userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setPhoneNumberCountryCode(request.getParameter("phoneNumberCountryCode"));
        userProfile.setPhoneNumber(request.getParameter("phoneNumber"));
        userProfile.setFirstName(request.getParameter("firstName"));
        userProfile.setLastName(request.getParameter("lastName"));
        userProfile.setSkills(request.getParameter("skills"));
        userProfile.setCompany(request.getParameter("company"));
        userProfile.setDesignation(request.getParameter("designation"));
        userProfile.setUrl(request.getParameter("url"));
        userProfile.setCountry(request.getParameter("country"));
        userProfile.setCity(request.getParameter("city"));
        userProfile.setZipCode(request.getParameter("zipCode"));
        userProfile.setBio(request.getParameter("bio"));
        userProfile.setSocialUrlLinkedin(request.getParameter("socialUrlLinkedin"));
        userProfile.setSocialUrlGithub(request.getParameter("socialUrlGithub"));
        userProfile.setSocialUrlPinterest(request.getParameter("socialUrlPinterest"));
        userProfile.setSocialUrlDribble(request.getParameter("socialUrlDribble"));
        userProfile.setSocialUrlTwitter(request.getParameter("socialUrlTwitter"));
        userProfile.setSocialUrlFacebook(request.getParameter("socialUrlFacebook"));
        userProfile.setSocialUrlInstagram(request.getParameter("socialUrlInstagram"));

        UserProfile userProfileRsl;
        try {
            userProfileRsl = userService.updateUserProfile(userProfile);
        } catch (Exception ex) {
            model.addAttribute("success", false);
            model.addAttribute("message", ex.getMessage());
            return "dashboard/pages/profile-settings";
        }

        model.addAttribute("user", userProfileRsl);
        model.addAttribute("success", true);
        model.addAttribute("message", "Successfully update user profile");
        return "dashboard/pages/profile-settings";
    }

}
