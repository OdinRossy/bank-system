package com.odinrossy.banksystem.services

import com.odinrossy.banksystem.exceptions.*
import com.odinrossy.banksystem.models.User
import com.odinrossy.banksystem.repositories.UserRepository
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.servlet.http.HttpSession
import java.util.List

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class)

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    List<User> asList() {
        return (List<User>) userRepository.findAll()
    }

    @Override
    User getUserByIdPassport(String idPassport) {
        User user = userRepository.findById(idPassport).orElse(null)
        if (user == null)
            throw new UserNotFoundException("User with idPassport " +  idPassport + " not found")
        else
            logger.log(Level.INFO, "User found. User information: " + user)
        return user
    }

    @Override
    User save(User user) throws RuntimeException {
        checkUser(user)
        try {
            getUserByIdPassport(user.getIdPassport())
            throw new UserAlreadyExistException("User with idPassport [" + user.getIdPassport() + "] already exist.")
        } catch (UserNotFoundException e) {
            user = userRepository.save(user)
            logger.log(Level.INFO, "User saved. User information: " + user)
            return user
        }
    }

    @Override
    User update(String idPassport, User user) throws RuntimeException {
        checkUser(user)
        getUserByIdPassport(idPassport)
        user.setIdPassport(idPassport)
        user = userRepository.save(user)
        logger.log(Level.INFO, "User updated. User information: " + user)
        return user
    }

    @Override
    void delete(String idPassport) {
        getUserByIdPassport(idPassport)
        userRepository.deleteById(idPassport)
    }

    @Override
    void checkUserAuthorization(HttpSession session) throws RuntimeException {
        User currentUser = (User) session.getAttribute("user")
        if (currentUser == null)
            throw new UserNotAuthorizedException("User is not authorized")
        authorizeUser(currentUser, session)
    }

    @Override
    void authorizeUser(User user, HttpSession session) throws RuntimeException {
        checkUser(user)
        String password = user.getPassword()
        user = getUserByIdPassport(user.getIdPassport())

        if (user.getPassword().equals(password)) {
            session.setAttribute("user", user)
            logger.log(Level.INFO, "User authorized in session. User info: " + user)
        } else
            throw new WrongUserPasswordException("Invalid user passport. User information: " + user)
    }

    @Override
    void logOutUser(HttpSession session) {
        User user = (User) session.getAttribute("user")
        session.removeAttribute("user")
        logger.log(Level.INFO, "User logged out. User details: " + user)
    }

    private void checkUser(User user) {
        if (user == null)
            throw new UserNullException()
    }
}

