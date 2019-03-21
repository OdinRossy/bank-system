package com.odinrossy.banksystem.services;

import com.odinrossy.banksystem.exceptions.UserNotAuthorizedException;
import com.odinrossy.banksystem.exceptions.UserNotFoundException;
import com.odinrossy.banksystem.models.User;
import com.odinrossy.banksystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        authorizeUser(currentUser, session);
    }

    public void authorizeUser(User user, HttpSession session) throws UserNotFoundException {
        String password = user.getPassword();
        user = userRepository.findById(user.getIdPassport()).orElse(user);
        if (user.getUsername() != null) {
            if (user.getPassword().equals(password))
                session.setAttribute("user", user);
        } else
            throw new UserNotFoundException(user + " not found in repository.");
    }

    public User saveUser(User user) {
        if (user != null) {
            return userRepository.save(user);
        } else
            throw new RuntimeException("User is null");
    }
}
