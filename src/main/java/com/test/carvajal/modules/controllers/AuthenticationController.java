package com.test.carvajal.modules.controllers;

import com.test.carvajal.commons.core.services.impl.AuthenticationService;
import com.test.carvajal.commons.domain.dto.AuthenticationUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/authentication")
@CrossOrigin("*")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthenticationUserRequest user) {
        log.info("Iniciando sesion");
        Map<String, Object> response = new HashMap<>();
        if (this.authenticationService.isValidJsonLogin(user) && this.authenticationService.authenticateUser(user)) {
            response.put("succes", this.authenticationService.generateToken(user, true));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
        response.put("succes", this.authenticationService.generateBadLogin());
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody AuthenticationUserRequest user) {
        log.info("Registrando usuario");
        Map<String, Object> response = new HashMap<>();
        response.put("sucess", this.authenticationService.registerUser(user));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/test")
    public String test(@RequestParam String token) {
        return "Token valido";
    }
}
