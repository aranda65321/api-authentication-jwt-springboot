package com.test.carvajal.commons.core.services.structural;

import com.test.carvajal.commons.domain.dto.AuthenticationUserRequest;
import com.test.carvajal.commons.domain.entity.User;

public interface IUserService {
    public User findUserByUsername(String username);

    public boolean authenticateUser(AuthenticationUserRequest user);

    public User registerUser(User user);
}
