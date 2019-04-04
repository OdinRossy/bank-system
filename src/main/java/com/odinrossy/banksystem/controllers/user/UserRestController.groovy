package com.odinrossy.banksystem.controllers.user

import com.odinrossy.banksystem.exceptions.user.UserNotFoundException
import com.odinrossy.banksystem.models.user.User
import com.odinrossy.banksystem.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
class UserRestController {

    private final UserService userService

    @Autowired
    UserRestController(UserService userService) {
        this.userService = userService
    }

    @GetMapping("/api/user")
    List<User> getUsersList() {
        try {
            return userService.findAll()
        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @GetMapping("/api/user/{idPassport}")
    User getUser(@PathVariable String idPassport) {
        try {
            return userService.findByIdPassport(idPassport)
        } catch (UserNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PostMapping("/api/user")
    User createUser(@RequestBody User user) {
        try {
            user = userService.save(user)
            return user

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @PutMapping("/api/user/{idPassport}")
    User updateUser(@RequestBody User user, @PathVariable String idPassport) {
        try {
            user = userService.update(idPassport, user)
            return user

        } catch (UserNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }

    @DeleteMapping("/api/user/{idPassport}")
    void deleteUser(@PathVariable String idPassport) {
        try {
            userService.delete(idPassport)

        } catch (UserNotFoundException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage())

        } catch (RuntimeException e) {
            e.printStackTrace()
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage())
        }
    }
}
