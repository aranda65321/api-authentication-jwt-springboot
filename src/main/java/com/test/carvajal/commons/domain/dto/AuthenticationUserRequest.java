package com.test.carvajal.commons.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationUserRequest {
    private String username;
    private String password;
    private String token;
}
