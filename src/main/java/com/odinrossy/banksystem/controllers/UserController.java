package com.odinrossy.banksystem.controllers;

import com.odinrossy.banksystem.exceptions.UserNotAuthorizedException;
import com.odinrossy.banksystem.exceptions.UserNotFoundException;
import com.odinrossy.banksystem.models.User;
import com.odinrossy.banksystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            return "redirect:/user/profile";
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @PostMapping
//    public String createUser(@RequestBody User user) {
//        try {
//            user = userService.saveUser(user);
//            userService.authorizeUser(user, session);
//            return "redirect:/profile";
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

    @PostMapping
    public String createUser(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam String middleName, @RequestParam String idPassport,
                             @RequestParam String username, @RequestParam String password,
                             @RequestParam String idRole) {
        try {
            Long role = Long.valueOf(idRole);
            User user =
                    new User(idPassport,firstName, middleName, lastName, username, password, role);
            user = userService.saveUser(user);
            userService.authorizeUser(user, session);
            return "redirect:/user/profile";
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("profile")
    public String render(Model model) {
        try {
            userService.checkUserAuthorization(session);
            model.addAttribute("user", (User) session.getAttribute("user"));
            return "profile";
        } catch (UserNotFoundException | UserNotAuthorizedException e) {
            return "redirect:/user/signIn";
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
