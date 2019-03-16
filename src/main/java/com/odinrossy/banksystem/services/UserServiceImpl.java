package com.odinrossy.banksystem.services;

import com.odinrossy.banksystem.exceptions.UserNotAuthorizedException;
import com.odinrossy.banksystem.exceptions.UserNotFoundException;
import com.odinrossy.banksystem.models.User;
import com.odinrossy.banksystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkUserAuthorization(HttpSession session) throws UserNotAuthorizedException, UserNotFoundException{
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null)
            throw new UserNotAuthorizedException("User is not authorized");
        authorizeUser(currentUser.getUsername(), currentUser.getPassword(), session);
    }

    public void authorizeUser(String username, String password, HttpSession session) throws UserNotFoundException {
        User user = userRepository.getUser(username, password);
        session.setAttribute("user", user);
    }
}
