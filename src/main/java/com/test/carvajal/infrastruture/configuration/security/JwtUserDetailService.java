package com.test.carvajal.infrastruture.configuration.security;

import com.test.carvajal.commons.domain.dto.AuthenticationUserRequest;
import com.test.carvajal.commons.domain.repository.impl.UserRepositoryImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Log4j2
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepositoryImpl userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Validando si el usuario existe");
        com.test.carvajal.commons.domain.entity.User user = userRepository.findUserByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), encoder.encode(user.getPassword()), new ArrayList<>());
        }
        log.error("No se encontro el usuario en base de datos - username: " + username);
        throw new UsernameNotFoundException("Usuario no encontrado username: " + username);
    }

    public UserDetails loadUserByCredentials(AuthenticationUserRequest credentials) {
        return null;
    }
}
