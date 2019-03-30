package com.odinrossy.banksystem.services.security

import com.odinrossy.banksystem.models.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.servlet.http.HttpSession

@Service
class AuthorizationServiceImpl implements AuthorizationService {

    private final HttpSession session

    @Autowired
    AuthorizationServiceImpl(HttpSession session) {
        this.session = session
    }

    @Override
    User getUserFromSession() {
        return (User) session.getAttribute("user")
    }

    @Override
    void putUserInSession(User user) {
        session.setAttribute("user", user)
    }

    @Override
    void removeUserFromSession() {
        session.removeAttribute("user")
    }
}
