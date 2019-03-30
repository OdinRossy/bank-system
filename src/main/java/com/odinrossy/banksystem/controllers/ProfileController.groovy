package com.odinrossy.banksystem.controllers

import com.odinrossy.banksystem.exceptions.UserNotAuthorizedException
import com.odinrossy.banksystem.exceptions.UserNotFoundException
import com.odinrossy.banksystem.models.User
import com.odinrossy.banksystem.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/profile")
class ProfileController {

    private final UserService userService
    private final HttpSession session

    @Autowired
    ProfileController(UserService userService, HttpSession session) {
        this.userService = userService
        this.session = session
    }

    @RequestMapping("authenticate")
    String authenticate(@RequestParam String idPassport, @RequestParam String password) {
        try {
            userService.authorizeUser(new User(idPassport, password), session)
            return "redirect:/profile"
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping
    String render(Model model) {
        try {
            userService.checkUserAuthorization(session)
            model.addAttribute("user", (User) session.getAttribute("user"))
            return "profile"
        } catch (UserNotFoundException | UserNotAuthorizedException e) {
            return "redirect:/profile/signIn"
        }
    }

    @RequestMapping("signIn")
    String renderSignIn() {
        return "signIn"
    }

    @RequestMapping("signUp")
    String renderSignUp() {
        return "signUp"
    }

    @RequestMapping("signOut")
    String logOut(HttpSession session) {
        userService.logOutUser(session)
        return "redirect:/"
    }
}
