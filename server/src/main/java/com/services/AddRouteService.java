package com.services;

import com.dto.AddRouteInfo;
import com.dto.AddStationInfo;
import entity.Route;
import entity.Station;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;


public class AddRouteService {
    private EntityManager em;
    private AddRouteInfo addRouteInfo;

    public AddRouteService(AddRouteInfo addRouteInfo, EntityManager em) {
        this.addRouteInfo = addRouteInfo;
        this.em = em;


    }

    public AddRouteInfo add() {
        em.getTransaction().begin();
        if(checkName()) {
            Route route = new Route(addRouteInfo.getName());
            em.persist(route);
            addRouteInfo.setMessage("Маршрут " + addRouteInfo.getName() + " добавлен");
        } else {
            addRouteInfo.setMessage("Маршрут " + addRouteInfo.getName() + " уже существует");

        }
        em.getTransaction().commit();
        return addRouteInfo;
    }


    private boolean checkName() {
        Query check = em.createNativeQuery("select count(id) from route where name = :name");
        check.setParameter("name", addRouteInfo.getName());
        Integer count = ((BigInteger)check.getSingleResult()).intValue();
        if(count > 0) {
            return false;
        }
        return true;
    }
}
