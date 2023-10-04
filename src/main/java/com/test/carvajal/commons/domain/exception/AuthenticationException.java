package com.test.carvajal.commons.domain.exception;

import lombok.Builder;

public class AuthenticationException extends Exception {
    private static final long serialVersionUID = 1L;

    @Builder
    public AuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }
}