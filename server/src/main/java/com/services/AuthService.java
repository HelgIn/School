package com.services;


import com.dto.AuthInfo;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AuthService {
    private EntityManager em;
    AuthInfo authInfo;

    public AuthService(AuthInfo authInfo, EntityManager em) {
        this.em = em;
        this.authInfo = authInfo;

    }

    public AuthInfo authorize() {
        em.getTransaction().begin();
        try {
            Query query = em.createNativeQuery("select password from user where login=:login");
            query.setParameter("login", authInfo.getLogin());

            String password = (String) query.getSingleResult();


            if(authInfo.getPassword().equals(password))  {
                authInfo.setAuth(true);
            }
        } finally {
            em.getTransaction().commit();
        }
        return authInfo;
    }


}
