package com.odinrossy.banksystem.controllers.client

import com.odinrossy.banksystem.exceptions.user.UserNotAuthorizedException
import com.odinrossy.banksystem.services.client.ClientService
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Controller
class ClientController {

    private final UserService userService
    private final AuthorizationService authorizationService
    private final ClientService clientService

    @Autowired
    ClientController(ClientService clientService, UserService userService, AuthorizationService authorizationService) {
        this.clientService = clientService
        this.userService = userService
        this.authorizationService = authorizationService
    }

    @GetMapping("/client")
    String index(Model model) {
        try {
            userService.checkAuthorization()
            model.addAttribute("clients", clientService.findAll())
            return "client/index"
        } catch (UserNotAuthorizedException e) {
            e.printStackTrace()
            return "redirect:/profile/logIn"
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }

    }

    @GetMapping("/client/{idPassport}")
    String getByIdPassport(@PathVariable String idPassport, Model model) {
        model.addAttribute("client", clientService.findByIdPassport(idPassport))
        return "client/show"
    }

    @GetMapping("/client/show")
    String getByIdPassportq(@RequestParam String idPassport, Model model) {
        try {
            userService.checkAuthorization()
            model.addAttribute("client", clientService.findByIdPassport(idPassport))
            return "client/show"
        } catch (UserNotAuthorizedException e) {
            e.printStackTrace()
            return "redirect:/profile/logIn"
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }

    }
}
