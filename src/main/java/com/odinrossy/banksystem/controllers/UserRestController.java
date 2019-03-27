package com.odinrossy.banksystem.controllers;

import com.odinrossy.banksystem.models.User;
import com.odinrossy.banksystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public UserRestController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/api/user")
    public List<User> getUsersList() {
        try {
            return userService.asList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/api/user/{idPassport}")
    public User getUser(@PathVariable String idPassport) {
        try {
            return userService.getUserByIdPassport(idPassport);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        try {
            user = userService.save(user);
            userService.authorizeUser(user, session);
            return user;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/api/user/{idPassport}")
    public User updateUser(@RequestBody User user, @PathVariable String idPassport) {
        try {
            user = userService.update(idPassport, user);
            userService.authorizeUser(user, session);
            return user;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/api/user/{idPassport}")
    public void deleteUser(@PathVariable String idPassport) {
        try {
            userService.delete(idPassport);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
