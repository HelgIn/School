package com.services;

import com.dto.AddRouteInfo;
import com.dto.AddStationInfo;
import entity.Route;
import entity.Station;

import javax.persistence.EntityManager;


public class AddRouteService {
    private EntityManager em;
    private AddRouteInfo addRouteInfo;

    public AddRouteService(AddRouteInfo addRouteInfo, EntityManager em) {
        this.addRouteInfo = addRouteInfo;
        this.em = em;


    }

    public void add() {
        em.getTransaction().begin();
        Route route = new Route(addRouteInfo.getName());
        em.persist(route);
        em.getTransaction().commit();
    }
}
