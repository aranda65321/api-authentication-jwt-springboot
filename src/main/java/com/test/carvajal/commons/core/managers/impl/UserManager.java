package com.test.carvajal.commons.core.managers.impl;

import com.test.carvajal.commons.core.managers.structural.IUserManager;
import com.test.carvajal.commons.domain.entity.User;
import com.test.carvajal.commons.domain.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager implements IUserManager {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public boolean existUser(User user) {
        User userFound = userRepository.findUserByUsername(user.getUsername());
        if (user.getUsername().equalsIgnoreCase(userFound.getUsername())
                && user.getPassword().equals(userFound.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

}
