package com.odinrossy.banksystem.controllers.github

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller(value = 'GitHubController')
@RequestMapping(value = '/github')
class GitHubController {

    @RequestMapping
    def github() {
        return 'redirect:https://github.com/OdinRossy/bank-system'
    }

}
