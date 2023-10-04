package com.test.carvajal.commons.core.services.impl;

import com.test.carvajal.commons.core.managers.impl.UserManager;
import com.test.carvajal.commons.core.services.structural.IUserService;
import com.test.carvajal.commons.domain.dto.AuthenticationUserRequest;
import com.test.carvajal.commons.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserManager userManager;

    @Override
    public User findUserByUsername(String username) {
        return userManager.findUserByUsername(username);
    }

    @Override
    public boolean authenticateUser(AuthenticationUserRequest user) {
        log.info("Buscando usuario en db: " + user.getUsername());
        User userFound = this.findUserByUsername(user.getUsername());
        if (this.validUser(user, userFound)) {
            log.info("Usuario encontrado: " + user.getUsername() + "\n procedemos a autenticar");
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userFound.getUsername(), userFound.getPassword()));
            log.info("Autenticacion exitosa");
            return true;
        }
        log.info("Autenticacion Fallida");
        return false;
    }

    @Override
    public User registerUser(User user) {
        try {
            return userManager.saveUser(user);
        } catch (Exception e) {
            log.error("Usuario ya existe en db", e);
        }
        return null;
    }

    private boolean validUser(AuthenticationUserRequest user, User userFound) {
        if (userFound != null && user.getUsername().equalsIgnoreCase(userFound.getUsername())
                && user.getPassword().equals(userFound.getPassword())) {
            return true;
        }
        return false;
    }
}
