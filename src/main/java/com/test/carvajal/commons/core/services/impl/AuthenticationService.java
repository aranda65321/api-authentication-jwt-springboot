package com.test.carvajal.commons.core.services.impl;

import com.test.carvajal.commons.core.services.structural.IAuthentication;
import com.test.carvajal.commons.domain.dto.AuthenticationUserRequest;
import com.test.carvajal.commons.domain.dto.AuthenticationUserResponse;
import com.test.carvajal.commons.domain.translators.impl.UserTranslator;
import com.test.carvajal.infrastruture.configuration.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements IAuthentication {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserTranslator userTranslator;

    @Override
    public boolean isValidJsonLogin(AuthenticationUserRequest user) {
        return true;
    }

    @Override
    public boolean authenticateUser(AuthenticationUserRequest user) {
        return userService.authenticateUser(user);
    }

    @Override
    public AuthenticationUserResponse generateToken(AuthenticationUserRequest user, boolean ok) {
        return userTranslator.toUserResponse(user.getUsername(),
                jwtUtil.generateToken(new User(user.getUsername(),
                        user.getPassword(), new ArrayList<>())), ok);
    }

    @Override
    public AuthenticationUserResponse registerUser(AuthenticationUserRequest user) {
        com.test.carvajal.commons.domain.entity.User userRegister =
                userService.registerUser(userTranslator.toUser(user));
        if (userRegister != null) {
            AuthenticationUserResponse response = userTranslator.toUserResponse(userRegister);
            response.setStatus("Registro exitoso");
            response.setOK(true);
            return response;
        }
        return userTranslator.toUserResponse("Error ya existe un usuario: " + user.getUsername(), false);
    }

    public AuthenticationUserResponse generateBadLogin() {
        return userTranslator.toUserResponse("Error usuario no existe", false);
    }
}
