package com.services;


import com.dto.GetJourneysInfo;
import entity.Journey;
import objects.GetJourneyObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GetJourneysService {

    private EntityManager em;
    private GetJourneysInfo getJourneysInfo;

    public GetJourneysService(GetJourneysInfo getJourneysInfo, EntityManager em) {
        this.getJourneysInfo = getJourneysInfo;
        this.em = em;
    }

    public GetJourneysInfo getJourneys() {
        em.getTransaction().begin();

        Query journeyQuery = em.createNativeQuery("select * from Journey", Journey.class);

        List<Journey> journeys = journeyQuery.getResultList();

        em.getTransaction().commit();

        for(Journey j : journeys) {
            getJourneysInfo.getJourneyObjects().add(new GetJourneyObject(j.getId(), j.getNumber(), j.getAvailableSeats(), j.getRoute().getName()));
        }

        return getJourneysInfo;
    }
}
