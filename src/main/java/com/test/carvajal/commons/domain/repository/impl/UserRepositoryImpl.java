package com.test.carvajal.commons.domain.repository.impl;

import com.test.carvajal.commons.domain.entity.User;
import com.test.carvajal.commons.domain.repository.structure.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@Slf4j
public class UserRepositoryImpl {
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public User findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("Usuario con id: " + id + " fue encontrado");
            return user.get();
        }
        log.info("Usuario con id: " + id + "No fue encontrado en db");
        return null;
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
