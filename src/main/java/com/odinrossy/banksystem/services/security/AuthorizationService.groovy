package com.odinrossy.banksystem.services.security

import com.odinrossy.banksystem.models.user.User

interface AuthorizationService {

    User getUserFromSession()

    void putUserInSession(User user)

    void removeUserFromSession()

}