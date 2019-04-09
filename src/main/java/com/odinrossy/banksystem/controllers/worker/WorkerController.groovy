package com.odinrossy.banksystem.controllers.worker

import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.security.AuthorizationService
import com.odinrossy.banksystem.services.worker.WorkerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

import java.text.SimpleDateFormat

@Controller
@RequestMapping("/worker")
class WorkerController {

    @Autowired
    WorkerService workerService

    @Autowired
    AuthorizationService authorizationService


    @RequestMapping("/authenticate")
    String authenticate(@RequestParam String username, @RequestParam String password) {
        try {
            workerService.findByUsernameAndPassword(username, password)
            return "redirect:/worker/profile"
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping('/profile')
    String index(Model model) {
        try {
            Worker worker = authorizationService.getWorkerFromSession()
            workerService.checkAuthorization()
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat('dd.MM.yyyy')

            Map<String, String> dates = new HashMap()
            dates.put('dateOfIssue', simpleDateFormat.format(worker.passport.dateOfIssue))
            dates.put('dateOfExpire', simpleDateFormat.format(worker.passport.dateOfExpire))
            dates.put('birthDate', simpleDateFormat.format(worker.passport.birthDate))

            model.addAllAttributes(dates)
            model.addAttribute("worker", (Worker) worker)
            return "worker/profile"
        } catch (RuntimeException e) {
            e.printStackTrace()
            return "redirect:/worker/logIn"
        }
    }

    @RequestMapping("logIn")
    String logIn() {
        return "worker/logIn"
    }

    @RequestMapping("logUp")
    String logUp() {
        return "worker/logUp"
    }

    @RequestMapping("logOut")
    String logOut() {
        authorizationService.removeWorkerFromSession()
        return "redirect:/"
    }
}
