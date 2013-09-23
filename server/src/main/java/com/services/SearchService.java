package com.services;


import com.dto.SearchInfo;
import com.entity.Route;
import com.entity.Schedule;
import com.entity.Station;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class SearchService {
    private EntityManager em;
    SearchInfo searchInfo;

    public SearchService(SearchInfo searchInfo, EntityManager em) {
        this.em = em;
        this.searchInfo = searchInfo;


    }
    public List<Station> search() {
        em.getTransaction().begin();

        // get station's id's
        TypedQuery<Long> queryFrom = em.createQuery("SELECT s.id FROM Station s WHERE name = '" + searchInfo.getFrom() + "'", Long.class);
        long fromId = queryFrom.getSingleResult();

        TypedQuery<Long> queryTo = em.createQuery("SELECT s.id FROM Station s WHERE name = '" + searchInfo.getTo() + "'", Long.class);
        long toId = queryTo.getSingleResult();

        // get schedule
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Schedule> criteriaQuery = cb.createQuery(Schedule.class);

        Root<Schedule> s = criteriaQuery.from(Schedule.class);
        criteriaQuery.where(cb.equal(s.get("id"), fromId));

        List<Schedule> results = em.createQuery(criteriaQuery).getResultList();

        for (Schedule result : results) {
            System.out.println(result);
        }
//        CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
//        Root<Object[]> c = q.from(Object[].class);
//        q.select(c);
//
//        List<Object[]> results = em.createQuery(q).getResultList();
//
//        for (Object result : results) {
//            System.out.println(result);
//        }

//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Station> criteriaQuery = builder.createQuery(Station.class);
//
//        Root<Station> s = criteriaQuery.from(Station.class);
//        criteriaQuery.select(s);
//        ParameterExpression<Long> pi = builder.parameter(Long.class);
//        criteriaQuery.where(builder.equal(s.get("name"), searchInfo.getFrom())).;
//
//
//        System.out.println(searchInfo.getFrom());
//        System.out.println(searchInfo.getTo());

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        Root<Station> emp = c.from(Station.class);
//
//        Subquery<Integer> sq = c.subquery(Integer.class);
//        Root<Station> project = sq.from(Station.class);
//        Join<Station, Station> sqEmp = project.join(Station_.stations)

//        String query =
//                "select 'route_id' from(select 'route_id' from Schedule where 'stationFrom_id'='1')f join (select 'route_id' from Schedule where 'stationTo_id'='4')t on 'f.route_id' = 't.route_id';";


       // List<Station> results = em.createQuery(query).getResultList();

        em.getTransaction().commit();
        //return results;
        return null;
    }
}
