package com.services;


import com.dto.AddJourneyInfo;
import com.dto.GetJourneysInfo;
import com.dto.JourneyInfo;
import entity.Journey;
import entity.Passenger;
import objects.GetJourneyObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class JourneyService {
    private EntityManager em;
    private JourneyInfo journeyInfo;


    public JourneyService(JourneyInfo journeyInfo, EntityManager em) {
        this.em = em;
        this.journeyInfo = journeyInfo;

    }

    public void addJourney(Journey journey) {
        em.getTransaction().begin();
        em.persist(journey);
        em.getTransaction().commit();
    }
    public Journey getByID(long id) {
        return em.find(Journey.class, id);
    }

    public void getJourneyPassengers() {
        em.getTransaction().begin();

        try {

            Query getJourney = em.createNativeQuery("select id  from Journey where number = :num");
            getJourney.setParameter("num", journeyInfo.getNumber());

            Integer id = ((BigInteger) getJourney.getSingleResult()).intValue();

            Journey journey = getByID(id);

            Query query = em.createNativeQuery("select passenger_id  from Ticket where journey_id = :id");
            query.setParameter("id", journey.getId());
            List<Long> users = query.getResultList();

            if(users.size() > 0) {
                Query passengers = em.createNativeQuery("select * from passenger where id in :ids", Passenger.class);
                passengers.setParameter("ids", users);
                List<Passenger> pass = passengers.getResultList();
                journeyInfo.setPassengerList(pass);
            }


        } finally {
            em.getTransaction().commit();
        }
    }




}
