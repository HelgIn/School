package com.services;


import com.dto.AddJourneyInfo;
import com.dto.AddRouteInfo;
import entity.Journey;
import entity.Route;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddJourneyService {
    private EntityManager em;
    private AddJourneyInfo addJourneyInfo;

    public AddJourneyService(AddJourneyInfo addJourneyInfo, EntityManager em) {
        this.addJourneyInfo = addJourneyInfo;
        this.em = em;
    }

    public boolean checkJourneyNumber(long number) {
        Query checkJourney = em.createNativeQuery("select count(id) from journey where number = :number");
        checkJourney.setParameter("number", number);
        Integer count = ((BigInteger) checkJourney.getSingleResult()).intValue();
        if(count == 0) {
            return true;
        }
        return false;
    }

    public AddJourneyInfo addJourney() {
        try {
            em.getTransaction().begin();
            if(checkJourneyNumber(addJourneyInfo.getNumber())) {
                Query findRoute = em.createNativeQuery("select * from route where name = :name", Route.class);
                findRoute.setParameter("name", addJourneyInfo.getRouteName());
                Route route = (Route) findRoute.getSingleResult();

                Journey journey = new Journey(addJourneyInfo.getAvailableSeats(), addJourneyInfo.getArrivalTime());
                journey.setRoute(route);
                journey.setNumber(addJourneyInfo.getNumber());

                em.persist(journey);
                addJourneyInfo.setMessage("Поезд номер " + addJourneyInfo.getNumber() + " успешно добавлен");
            } else {
                addJourneyInfo.setMessage("Поезд номер " + addJourneyInfo.getNumber() + " уже существует");
            }

        } finally {
            em.getTransaction().commit();
        }
        return addJourneyInfo;
    }


}
