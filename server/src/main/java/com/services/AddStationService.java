package com.services;


import com.dto.AddStationInfo;
import entity.Station;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;

public class AddStationService {
    private EntityManager em;
    private AddStationInfo addStationInfo;

    public AddStationService(AddStationInfo addStationInfo, EntityManager em) {
        this.addStationInfo = addStationInfo;
        this.em = em;


    }

    public AddStationInfo add() {
        em.getTransaction().begin();
        if(checkName()) {
            Station station = new Station(addStationInfo.getName());
            em.persist(station);
            addStationInfo.setMessage("Станция " + addStationInfo.getName() + " добавлена");
        } else {
            addStationInfo.setMessage("Станция " + addStationInfo.getName() + " существует");
        }
        em.getTransaction().commit();

        return  addStationInfo;
    }


    private boolean checkName() {
        Query check = em.createNativeQuery("select count(id) from station where name = :name");
        check.setParameter("name", addStationInfo.getName());
        Integer count = ((BigInteger)check.getSingleResult()).intValue();
        if(count > 0) {
            return false;
        }
        return true;
    }

}
