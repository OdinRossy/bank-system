package com.odinrossy.banksystem.services;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Service
public interface UserService {

    void checkUserAuthorization(HttpSession session);

    void authorizeUser(String username, String password, HttpSession session);

}
