package com.odinrossy.banksystem.repositories;

import com.odinrossy.banksystem.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User getUser(String username);

    User getUser(String username, String password);

    User createUser(User user);
}
