package com.services;


import com.dto.SearchInfo;
import com.dto.SearchInfoAnswer;
import com.dto.SearchInfoObject;
import entity.Journey;
import entity.Route;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private EntityManager em;
    SearchInfo searchInfo;

    public SearchService(SearchInfo searchInfo, EntityManager em) {
        this.em = em;
        this.searchInfo = searchInfo;


    }
    public SearchInfoAnswer search() {
        em.getTransaction().begin();
        SearchInfoAnswer sa = null;
        try {
            // get station's id's
            TypedQuery<Long> queryFrom = em.createQuery("SELECT s.id FROM Station s WHERE name = '" + searchInfo.getFrom() + "'", Long.class);
            long fromId = queryFrom.getSingleResult();

            TypedQuery<Long> queryTo = em.createQuery("SELECT s.id FROM Station s WHERE name = '" + searchInfo.getTo() + "'", Long.class);
            long toId = queryTo.getSingleResult();


            String sqlQuery = "select Route.id, Route.name from Route join Journey on Route.id = Journey.route_id where Route.id IN (select f.route_id from(select route_id from Schedule where stationFrom_id=?)f join (select route_id from Schedule where stationTo_id=?)t on f.route_id = t.route_id) order by Route.id asc;";
            Query q = em.createNativeQuery(sqlQuery, Route.class);
            q.setParameter(1, fromId);
            q.setParameter(2, toId);
            List<Route> results = q.getResultList();

            List<Journey> journeys = getJourney(results);

            sa = new SearchInfoAnswer();

            List<Long> routeIds = new ArrayList<Long>();
            for(int i=0; i<journeys.size(); i++) {

                if(results.get(i).getId() == journeys.get(i).getRoute().getId()) {
                    sa.getSearchObj().add(i, new SearchInfoObject(journeys.get(i).getId(), results.get(i).getName(), journeys.get(i).getArrivalTime(), journeys.get(i).getAvailableSeats() - (Integer) getAvailableSeats(journeys.get(i))));
                }

            }

            for(Route res : results) {
                System.out.println(res.getName());
            }
        } finally {
            em.getTransaction().commit();
        }
        return sa;

    }



    public List<Journey> getJourney(List<Route> route) {

        List<Long> ids = new ArrayList<Long>();
        for (int i=0; i<route.size(); i++) {
            ids.add(i,  route.get(i).getId());
        }

        Query query = em.createNativeQuery("SELECT * FROM Journey WHERE route_id IN (:ids)  order by Journey.route_id asc", Journey.class);
        query.setParameter("ids", ids);



        List<Journey> results = query.getResultList();
        for(Journey res : results) {
            System.out.println(res);
        }
        return results;
    }

    private Integer getAvailableSeats(Journey journey) {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM Ticket WHERE journey_id = :j");
        query.setParameter("j", journey.getId());
        Integer result = ((BigInteger) query.getSingleResult()).intValue();
        return  result;
    }

}
