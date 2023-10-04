package com.test.carvajal.commons.domain.translators.impl;

import com.test.carvajal.commons.domain.dto.AuthenticationUserRequest;
import com.test.carvajal.commons.domain.dto.AuthenticationUserResponse;
import com.test.carvajal.commons.domain.entity.User;
import com.test.carvajal.commons.domain.translators.structure.IUserTranslator;
import org.springframework.stereotype.Component;

@Component
public class UserTranslator implements IUserTranslator<AuthenticationUserRequest, User, AuthenticationUserResponse> {
    @Override
    public User toUser(AuthenticationUserRequest authenticationUserRequest) {
        return new User(null, authenticationUserRequest.getUsername(), authenticationUserRequest.getPassword());
    }

    @Override
    public AuthenticationUserResponse toUserResponse(User user, String token) {
        return AuthenticationUserResponse.builder()
                .username(user.getUsername())
                .token(token)
                .build();
    }

    @Override
    public AuthenticationUserResponse toUserResponse(User user) {
        return AuthenticationUserResponse.builder()
                .username(user.getUsername())
                .build();
    }

    @Override
    public AuthenticationUserResponse toUserResponse(String status, boolean ok) {
        return AuthenticationUserResponse.builder()
                .status(status)
                .isOK(ok)
                .build();
    }

    @Override
    public AuthenticationUserResponse toUserResponse(String username, String token, boolean ok) {
        return AuthenticationUserResponse.builder()
                .username(username)
                .token(token)
                .isOK(ok)
                .build();
    }
}
