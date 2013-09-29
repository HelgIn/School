package com.services;

import entity.Journey;
import objects.StationAnswerObject;
import com.dto.StationInfo;
import entity.Route;
import entity.Schedule;
import entity.Station;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;
import java.util.logging.Logger;


public class StationService {
    private EntityManager em;
    private StationInfo stationInfo;
    private Logger log = Logger.getLogger(StationService.class.getName());

    public StationService(StationInfo stationInfo, EntityManager em) {
        this.em = em;
        this.stationInfo = stationInfo;
    }

    public StationInfo search() {
        em.getTransaction().begin();
        try{
            Station station = getStation(stationInfo.getStation());
            String sqlQuery = "SELECT * FROM Schedule s WHERE s.stationFrom_id=:from OR stationTo_id=:to";
            Query q = em.createNativeQuery(sqlQuery, Schedule.class);
            q.setParameter("from", station.getId());
            q.setParameter("to", station.getId());
            List<Schedule> result = q.getResultList();

            List<Route> route = new ArrayList<Route>();
            for(Schedule r : result) {
                if(!route.contains(r.getRoute())) {
                    route.add(r.getRoute());
                }
            }

            for(Route r : route) {
                //log.info(String.valueOf(r));
                //log.info(String.valueOf(station.getId()));
                Query timeDelay = em.createNativeQuery("select sum(delayTime) from schedule where route_id=:route and stationTo_id<=:to");

                timeDelay.setParameter("route", r.getId());
                timeDelay.setParameter("to", station.getId());

                // время до станции поиска
                Long delay = ((BigDecimal) timeDelay.getSingleResult()).longValue();

                Query timeStart = em.createNativeQuery("select * from journey where route_id=:route order by arrival_time asc", Journey.class);
                timeStart.setParameter("route", r.getId());
                List<Journey> times = timeStart.getResultList();

                for(Journey time : times) {
                    System.out.println(delay);
                    System.out.println(time.getArrivalTime());



                    String[] timeAr = time.getArrivalTime().split(":");

                    Calendar c = new GregorianCalendar();//Calendar.getInstance();
                    c.setTimeZone(TimeZone.getDefault());
                    c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeAr[0]));
                    c.set(Calendar.MINUTE, Integer.parseInt(timeAr[1]));
                    c.set(Calendar.SECOND, 0);
                    c.setTimeInMillis(c.getTime().getTime() + delay * 60000);
                    String displayTime;
                    displayTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
                    stationInfo.add(new StationAnswerObject(r.getName(), displayTime, time.getNumber()));
                }

                r.setTimeToStation(delay);
            }


        } finally {
            em.getTransaction().commit();
        }
        return stationInfo;

    }

    public  Station getStation(String name) {
        String sqlQuery = "SELECT * FROM Station s WHERE s.name=:st";
        Query q = em.createNativeQuery(sqlQuery, Station.class);
        q.setParameter("st", name);
        Station results = (Station) q.getSingleResult();
        return results;
    }




}
