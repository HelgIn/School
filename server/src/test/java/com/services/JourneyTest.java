package com.services;



import com.dto.AddJourneyInfo;
import com.dto.JourneyInfo;
import entity.Journey;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JourneyTest {
    private EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();


    @Test
    public void passPositiveTest() {
        JourneyInfo journeyInfo = new JourneyInfo(79);
        JourneyService journeyService = new JourneyService(journeyInfo, em);
        journeyService.getJourneyPassengers();

    }

    @Test
    public void passNegativeTest() {
        JourneyInfo journeyInfo = new JourneyInfo(50);
        JourneyService journeyService = new JourneyService(journeyInfo, em);
        journeyService.getJourneyPassengers();

    }

    @Test
    public void addJourneyPositiveTest() {
        AddJourneyService addRouteService = new AddJourneyService(new AddJourneyInfo("Сапсан", 77, 250, "450000"), em);
        addRouteService.addJourney();
    }

    @Test
    public void addJourneyNegativeTest() {
        AddJourneyService addJourneyService = new AddJourneyService(new AddJourneyInfo("qwe", 77, 250, "450000"), em);
        addJourneyService.addJourney();
    }


    @Test
    public void checkJourneyNumberTest() {
        AddJourneyService addJourneyService = new AddJourneyService(new AddJourneyInfo("Сапсан", 77, 250, "450000"), em);
        Boolean result = addJourneyService.checkJourneyNumber(77);
        assert (result == false);

    }

}
