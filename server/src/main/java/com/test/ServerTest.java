package com.test;

import com.entity.*;
import com.init.Init;
import com.services.JourneyService;
import com.services.PassengerService;
import com.services.RouteService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class ServerTest {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();

        RouteService rs = new RouteService(em);

        Journey redArr = new Journey(500, "10:00");
        redArr.setRoute(rs.getRouteBiId(1));

        JourneyService js = new JourneyService(em);
        js.addJourney(redArr);

    }
}
