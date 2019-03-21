package com.odinrossy.banksystem.controllers;

import com.odinrossy.banksystem.configuration.Roles;
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
@RequestMapping("/bankServices")
public class ActionController {

    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public ActionController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping
    public String render(Model model) {
        try {
            userService.checkUserAuthorization(session);
            model.addAttribute("user",(User) session.getAttribute("user"));
            model.addAttribute("role", (String) Roles.ADMIN);
            return "bankServices";
        } catch (UserNotFoundException | UserNotAuthorizedException e) {
            return "redirect:/user/signIn";
        }
    }
}
