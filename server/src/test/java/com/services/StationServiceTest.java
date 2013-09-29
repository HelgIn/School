package com.services;


import objects.StationAnswerObject;
import com.dto.StationInfo;
import entity.Station;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class StationServiceTest {

    private StationInfo stationInfo;
    private StationService stationService;
    private EntityManager em;

    @Test
    public void getStation() {
        stationInfo = new StationInfo("Тверь");
        em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        stationService = new StationService(stationInfo, em);
        Station get = stationService.getStation("Тверь");
        assert(get.getName().equals("Тверь"));
        assert(get.getId() == 3);
    }

    @Test(expected = NoResultException.class)
    public void searchNegativeTest() {
        stationInfo = new StationInfo("123");
        em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        stationService = new StationService(stationInfo, em);
        stationService.search();
    }

    @Test
    public void searchPositiveTest() {
        stationInfo = new StationInfo("Бологое");
        em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        stationService = new StationService(stationInfo, em);
        StationInfo stationInfo = stationService.search();
        System.out.println(stationInfo.getResult());

        String time = "18:56:46";
        Calendar c = new GregorianCalendar();//Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        c.set(Calendar.HOUR_OF_DAY, 17);
        c.set(Calendar.MINUTE, 50);
        c.set(Calendar.SECOND, 0);
        c.setTimeInMillis(c.getTime().getTime() + 210 * 60000);
        System.out.println(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));





        //assert(stationAnswerObject.getRoutes().get(0).getName().equals("Красная Стрела из СПб"));
    }

}
