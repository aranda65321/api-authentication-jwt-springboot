package com.test.carvajal.commons.core.managers.structural;

import com.test.carvajal.commons.domain.entity.User;

public interface IUserManager {
    public User findUserByUsername(String username);

    public boolean existUser(User user);

    public User saveUser(User user);
}
