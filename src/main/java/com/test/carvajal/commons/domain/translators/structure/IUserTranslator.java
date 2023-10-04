package com.test.carvajal.commons.domain.translators.structure;

public interface IUserTranslator<A, U, R> {
    public U toUser(A authenticationUserRequest);

    public R toUserResponse(U user, String token);

    public R toUserResponse(U user);

    public R toUserResponse(String status, boolean ok);

    public R toUserResponse(String username, String token,  boolean ok);
}
