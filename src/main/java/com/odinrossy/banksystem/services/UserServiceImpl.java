package com.odinrossy.banksystem.services;

import com.odinrossy.banksystem.exceptions.RoleNotFoundException;
import com.odinrossy.banksystem.exceptions.UserNotAuthorizedException;
import com.odinrossy.banksystem.exceptions.UserNotFoundException;
import com.odinrossy.banksystem.exceptions.WrongUserPasswordException;
import com.odinrossy.banksystem.models.Role;
import com.odinrossy.banksystem.models.User;
import com.odinrossy.banksystem.repositories.RoleRepository;
import com.odinrossy.banksystem.repositories.UserRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void checkUserAuthorization(HttpSession session) throws RuntimeException {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null)
            throw new UserNotAuthorizedException("User is not authorized");
        authorizeUser(currentUser, session);
    }

    public void authorizeUser(User user, HttpSession session) throws RuntimeException {
        if (user != null) {
            String password = user.getPassword();
            user = userRepository.findById(user.getIdPassport()).orElse(user);

            logger.log(Level.INFO,"User found. User information: " + user);

            if (user.getUsername() != null) {
                if (user.getPassword().equals(password)) {
                    session.setAttribute("user", user);
                    logger.log(Level.INFO,"User authorized in session. User info: " + user);
                }
                else
                    throw new WrongUserPasswordException("Invalid user passport. User information: " + user);
            } else
                throw new UserNotFoundException("User not found. User information: " + user);
        } else
            throw new RuntimeException("User is null");
    }

    public User saveUser(User user) throws RuntimeException {
        if (user != null) {
//            Role userRole = roleRepository.findById(user.getIdRole())
//                    .orElse(new Role(null));
//            if (userRole.getIdRole() == null)
//                throw new RoleNotFoundException("Role not found. Role details: " + user.getIdRole());
            user = userRepository.save(user);
            logger.log(Level.INFO,"User saved. User information: " + user);
            return user;
        } else
            throw new RuntimeException("User is null");
    }
}
