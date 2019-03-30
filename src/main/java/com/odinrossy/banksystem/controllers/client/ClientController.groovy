package com.odinrossy.banksystem.controllers.client

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/client")
class ClientController {
    @RequestMapping
    String index() {
        return "client/index"
    }
}
