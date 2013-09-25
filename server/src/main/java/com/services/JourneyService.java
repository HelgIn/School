package com.services;


import entity.Journey;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JourneyService {
    private EntityManager em;
    public JourneyService(EntityManager em) {
        this.em = em;

    }

    public void addJourney(Journey journey) {
        em.getTransaction().begin();
        em.persist(journey);
        em.getTransaction().commit();
    }
    public Journey getByID(long id) {
        return em.find(Journey.class, id);
    }
}
