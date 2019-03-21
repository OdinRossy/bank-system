package com.odinrossy.banksystem.services;
import com.odinrossy.banksystem.models.User;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Service
public interface UserService {

    User saveUser(User user);

    void checkUserAuthorization(HttpSession session);

    void authorizeUser(User user, HttpSession session);

}
