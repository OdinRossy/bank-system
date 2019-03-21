package com.odinrossy.banksystem.controllers;

import com.fasterxml.jackson.annotation.JsonValue;
import com.odinrossy.banksystem.exceptions.UserNotFoundException;
import com.odinrossy.banksystem.models.User;
import com.odinrossy.banksystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public UserController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @PostMapping("authenticate")
    public String authenticate(@RequestParam String idPassport, @RequestParam String password) {
        try {
            userService.authorizeUser(new User(idPassport, password), session);
            return "redirect:/profile";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        try {
            user = userService.saveUser(user);
            userService.authorizeUser(user, session);
            return "redirect:/profile";
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping("signIn")
    public String renderSignIn() {
        return "signIn";
    }

    @RequestMapping("signUp")
    public String renderSignUp() {
        return "signUp";
    }

    @RequestMapping("signOut")
    public String logOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
