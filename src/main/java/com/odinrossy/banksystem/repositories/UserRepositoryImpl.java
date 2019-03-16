package com.odinrossy.banksystem.repositories;

import com.odinrossy.banksystem.exceptions.UserNotFoundException;
import com.odinrossy.banksystem.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<User> userList = new ArrayList<>();

    {
        userList.add(new User("gleebka47@gmail.com", "qwerty"));
        userList.add(new User("gleebka@icloud.com", "iCloud"));
        userList.add(new User("remniov.work@gmail.com", "work"));
    }

    private boolean checkForDuplicates(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    @Override
    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UserNotFoundException("Can't find user with username: " + username);
    }

    @Override
    public User getUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        throw new UserNotFoundException("Can't find user with username: " + username + " and password: " + password);
    }

    @Override
    public User createUser(User user) {
        if (user != null && !checkForDuplicates(user.getUsername())) {
            userList.add(user);
            return user;
        } else
            return null;
    }
}
