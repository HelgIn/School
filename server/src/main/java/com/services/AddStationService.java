package com.services;


import com.dto.AddStationInfo;
import entity.Station;

import javax.persistence.EntityManager;

public class AddStationService {
    private EntityManager em;
    private AddStationInfo addStationInfo;

    public AddStationService(AddStationInfo addStationInfo, EntityManager em) {
        this.addStationInfo = addStationInfo;
        this.em = em;


    }

    public void add() {
        em.getTransaction().begin();
        Station station = new Station(addStationInfo.getName());
        em.persist(station);
        em.getTransaction().commit();
    }

}
