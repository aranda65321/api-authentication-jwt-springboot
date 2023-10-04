package com.test.carvajal.commons.core.services.structural;

import com.test.carvajal.commons.domain.dto.AuthenticationUserRequest;
import com.test.carvajal.commons.domain.dto.AuthenticationUserResponse;

public interface IAuthentication {
    boolean isValidJsonLogin(AuthenticationUserRequest user);

    boolean authenticateUser(AuthenticationUserRequest user);

    AuthenticationUserResponse generateToken(AuthenticationUserRequest user, boolean ok);

    AuthenticationUserResponse registerUser(AuthenticationUserRequest user);


}
