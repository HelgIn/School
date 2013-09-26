package com.test;

import com.services.JourneyService;
import com.services.RouteService;
import entity.Journey;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;


public class ServerTest {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();

        RouteService rs = new RouteService(em);

        Journey redArr = new Journey(500, new Date("10:00"));
        //redArr.setRoute(rs.getRouteBiId(1));

        JourneyService js = new JourneyService(em);
        js.addJourney(redArr);

    }
}
