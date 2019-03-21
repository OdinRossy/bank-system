package com.odinrossy.banksystem.controllers;

import com.odinrossy.banksystem.exceptions.UserNotAuthorizedException;
import com.odinrossy.banksystem.exceptions.UserNotFoundException;
import com.odinrossy.banksystem.models.User;
import com.odinrossy.banksystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public ProfileController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping
    public String render(Model model) {
        try {
            userService.checkUserAuthorization(session);
            model.addAttribute("user", (User) session.getAttribute("user"));
            return "profile";
        } catch (UserNotFoundException | UserNotAuthorizedException e) {
            return "redirect:/user/signIn";
        }
    }
}
