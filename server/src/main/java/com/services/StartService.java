package com.services;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StartService {

    private EntityManager em;

    public StartService(EntityManager em) {
        this.em = em;

    }

    public List<String> getStations() {
        em.getTransaction().begin();
        TypedQuery<String> query = em.createQuery("SELECT s.name FROM Station s", String.class);
        em.getTransaction().commit();
        return query.getResultList();
    }
}
