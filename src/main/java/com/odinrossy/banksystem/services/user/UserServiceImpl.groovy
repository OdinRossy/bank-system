package com.odinrossy.banksystem.services.user

import com.odinrossy.banksystem.exceptions.user.UserAlreadyExistException
import com.odinrossy.banksystem.exceptions.user.UserNotAuthorizedException
import com.odinrossy.banksystem.exceptions.user.UserNotFoundException
import com.odinrossy.banksystem.exceptions.user.UserNullException
import com.odinrossy.banksystem.exceptions.user.WrongUserPasswordException
import com.odinrossy.banksystem.models.user.User
import com.odinrossy.banksystem.repositories.user.UserRepository
import com.odinrossy.banksystem.services.security.AuthorizationService
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository
    private final AuthorizationService authorizationService

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class)

    @Autowired
    UserServiceImpl(UserRepository userRepository, AuthorizationService authorizationService) {
        this.userRepository = userRepository
        this.authorizationService = authorizationService
    }

    @Override
    List<User> findAll() {
        checkAuthorization()
        return (List<User>) userRepository.findAll()
    }

    @Override
    User findByIdPassport(String idPassport) throws UserNotFoundException {
        User user = userRepository.findById(idPassport).orElse(null)
        if (user == null)
            throw new UserNotFoundException("User with idPassport " + idPassport + " not found")
        else
            logger.log(Level.INFO, "User found. User information: " + user)
        return user
    }

    @Override
    User findByIdPassportAndPassword(String idPassport, String password)
            throws UserNotFoundException, WrongUserPasswordException {

//        throws UserNotFoundException
        findByIdPassport(idPassport)

        User user = userRepository.findByIdPassportAndPassword(idPassport, password)

        if (user) {
            authorizationService.putUserInSession(user)
            logger.log(Level.INFO, "User authorized in session. User info: " + user)
            return user
        } else
            throw new WrongUserPasswordException("Invalid user passport. ")
    }

    @Override
    User save(User user) throws RuntimeException {
        if (user) {
            try {
                findByIdPassport(user.getIdPassport())
                throw new UserAlreadyExistException("User with idPassport [" + user.getIdPassport() + "] already exist.")
            } catch (UserNotFoundException ignored) {
                user = userRepository.save(user)
                logger.log(Level.INFO, "User saved. User information: " + user)
                authorizationService.putUserInSession(user)
                return user
            }
        } else
            throw new UserNullException()
    }

    @Override
    User update(String idPassport, User user) throws RuntimeException {
        if (user) {
            findByIdPassport(idPassport)
            user.setIdPassport(idPassport)
            user = userRepository.save(user)
            logger.log(Level.INFO, "User updated. User information: " + user)
            authorizationService.putUserInSession(user)
            return user
        } else
            throw new UserNullException()
    }

    @Override
    void delete(String idPassport) {
        checkAuthorization()
        User user = findByIdPassport(idPassport)
        userRepository.deleteById(idPassport)
        logger.log(Level.INFO, "User deleted. User information: " + user)
        if (user == authorizationService.getUserFromSession())
            authorizationService.removeUserFromSession()
    }

    @Override
    void checkAuthorization() throws RuntimeException {
        User currentUser = authorizationService.getUserFromSession()
        if (!currentUser)
            throw new UserNotAuthorizedException("User is not authorized.")
    }
}

