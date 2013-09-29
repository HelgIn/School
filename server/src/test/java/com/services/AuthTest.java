package com.services;

import com.dto.AuthInfo;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class AuthTest {

    private EntityManager em;
    private AuthInfo authInfo;
    private AuthService authService;

    @Test
    public void authPositiveTest() {
        em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        authInfo = new AuthInfo("admin", "admin");
        authService = new AuthService(authInfo, em);
        AuthInfo result = authService.authorize();
        assert(result.getAuth() == true);
    }

    @Test(expected = NoResultException.class)
    public void authNegativeTest() {
        em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        authInfo = new AuthInfo("asd", "asd");
        authService = new AuthService(authInfo, em);
        AuthInfo result = authService.authorize();
    }

}
