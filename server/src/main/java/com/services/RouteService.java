package com.services;

import entity.Route;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class RouteService {
    EntityManager em;

    public RouteService(EntityManager em) {
        this.em = em;
    }

    public Route getRouteBiId(long id) {
        em.getTransaction().begin();
        TypedQuery<Route> query = em.createQuery("FROM Route", Route.class);
        List<Route> ret = query.getResultList();
        em.getTransaction().commit();
        return ret.get(0);
    }
}
