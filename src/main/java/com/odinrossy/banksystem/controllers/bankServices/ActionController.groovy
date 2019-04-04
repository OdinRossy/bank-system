package com.odinrossy.banksystem.controllers.bankServices

import com.odinrossy.banksystem.constants.Roles
import com.odinrossy.banksystem.exceptions.user.UserNotAuthorizedException
import com.odinrossy.banksystem.exceptions.user.UserNotFoundException
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException


@Controller
@RequestMapping("/bankServices")
class ActionController {

    private final UserService userService
    private final AuthorizationService authorizationService

    @Autowired
    ActionController(UserService userService, AuthorizationService authorizationService) {
        this.userService = userService
        this.authorizationService = authorizationService
    }

    @GetMapping
    String index(Model model) {
        try {
            userService.checkAuthorization()
            model.addAttribute("user", authorizationService.getUserFromSession())
            model.addAttribute("role", (String) Roles.ADMIN)
            return "dashboard/bankServices"
        } catch (UserNotAuthorizedException e) {
            e.printStackTrace()
            return "redirect:/profile/logIn"
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}
