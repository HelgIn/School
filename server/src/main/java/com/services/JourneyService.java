package com.services;


import com.entity.Journey;

import javax.persistence.EntityManager;

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
}
