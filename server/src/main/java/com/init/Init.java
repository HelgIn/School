package com.init;

import entity.Route;
import entity.Schedule;
import entity.Station;

import javax.persistence.EntityManager;

public class Init {
    public EntityManager em;
    public Init(EntityManager em) {
        this.em = em;
        Route redArrow = new Route("Красная Стрела из СПб");

        // Stations
        Station spb = new Station("Санкт-Петербург");
        Station bologoe = new Station("Бологое");
        Station tver = new Station("Тверь");
        Station moscow = new Station("Москва");

        // Shedule
        Schedule spb_bologoe = new Schedule(120, 1);
        spb_bologoe.setRoute(redArrow);
        spb_bologoe.setStationFrom(spb);
        spb_bologoe.setStationTo(bologoe);

        Schedule bologoe_tver = new Schedule(90, 2);
        bologoe_tver.setRoute(redArrow);
        bologoe_tver.setStationFrom(bologoe);
        bologoe_tver.setStationTo(tver);

        Schedule tver_msk = new Schedule(150, 3);
        tver_msk.setRoute(redArrow);
        tver_msk.setStationFrom(tver);
        tver_msk.setStationTo(moscow);


        em.getTransaction().begin();

        em.persist(redArrow);

        em.persist(spb);
        em.persist(bologoe);
        em.persist(tver);
        em.persist(moscow);

        em.persist(spb_bologoe);
        em.persist(bologoe_tver);
        em.persist(tver_msk);

        em.getTransaction().commit();

    }

}
