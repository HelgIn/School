package com.services;

import com.dto.StationAnswer;
import com.dto.StationInfo;
import com.dto.TicketInfo;
import entity.Route;
import entity.Schedule;
import entity.Station;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class StationService {
    private EntityManager em;
    StationInfo stationInfo;

    public StationService(StationInfo stationInfo, EntityManager em) {
        this.em = em;
        this.stationInfo = stationInfo;
    }

    public StationAnswer search() {
        em.getTransaction().begin();
        StationAnswer stationAnswer = null;
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
            stationAnswer = new StationAnswer(route);

        } finally {
            em.getTransaction().commit();
        }
        return stationAnswer;

    }

    public  Station getStation(String name) {
        String sqlQuery = "SELECT * FROM Station s WHERE s.name=:st";
        Query q = em.createNativeQuery(sqlQuery, Station.class);
        q.setParameter("st", name);
        Station results = (Station) q.getSingleResult();
        return results;
    }


}
