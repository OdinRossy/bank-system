package com.odinrossy.banksystem.services
import com.odinrossy.banksystem.models.User
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession
import java.util.List

@Service
interface UserService {

    List<User> asList();

    User getUserByIdPassport(String idPassport);

    User save(User user);

    User update(String idPassport, User user);

    void delete(String idPassport);

    void checkUserAuthorization(HttpSession session);

    void authorizeUser(User user, HttpSession session);

    void logOutUser(HttpSession session);

}
