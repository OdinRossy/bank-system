package com.odinrossy.banksystem.controllers.profile

import com.odinrossy.banksystem.exceptions.user.UserNotAuthorizedException
import com.odinrossy.banksystem.exceptions.user.UserNotFoundException
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/profile")
class ProfileController {

    private final UserService userService
    private final AuthorizationService authorizationService

    @Autowired
    ProfileController(UserService userService, AuthorizationService authorizationService) {
        this.userService = userService
        this.authorizationService = authorizationService
    }

    @RequestMapping("authenticate")
    String authenticate(@RequestParam String idPassport, @RequestParam String password) {
        try {
            userService.findByIdPassportAndPassword(idPassport, password)
            return "redirect:/profile"
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping
    String index(Model model) {
        try {
            userService.checkAuthorization()
            model.addAttribute("user", authorizationService.getUserFromSession())
            return "profile"
        } catch (UserNotFoundException | UserNotAuthorizedException e) {
            e.printStackTrace()
            return "redirect:/profile/signIn"
        }
    }

    @RequestMapping("signIn")
    String signIn() {
        return "signIn"
    }

    @RequestMapping("signUp")
    String signUp() {
        return "signUp"
    }

    @RequestMapping("signOut")
    String logOut() {
        authorizationService.removeUserFromSession()
        return "redirect:/"
    }
}
