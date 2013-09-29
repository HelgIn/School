package com.test;

import com.services.JourneyService;
import com.services.RouteService;
import entity.Journey;
import entity.Passenger;
import entity.Route;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ServerTest {

    private static boolean checkDate(String date) {
        Pattern p = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
        Matcher m = p.matcher(date);
        return  m.matches();
    }

    private static boolean timeCheck(String time) {
        Pattern p = Pattern.compile("[0-2][0-9]:[0-5][0-9]");
        Matcher m = p.matcher(time);
        return m.matches();
    }

    public static void main(String[] args) throws ParseException {
        EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();

        //System.out.println(timeCheck("15:00"));

//
        em.getTransaction().begin();

        Query time = em.createNativeQuery("select sum(delayTime) from schedule where route_id=:route and stationTo_id<=:to");
        time.setParameter("route", 1);
        time.setParameter("to", 3);

        Long delay = ((BigDecimal) time.getSingleResult()).longValue();

        System.out.println(delay);

        Query timeStart = em.createNativeQuery("select arrival_time from journey where route_id=:route");
        timeStart.setParameter("route", 1);
        List<Time> timeSt = timeStart.getResultList();

        for(Time timeDb : timeSt) {
            String timeStr = timeDb.toString();

            long timeLong = Long.valueOf(timeStr.replace(":", ""));
        }
//        String dt = new java.text.SimpleDateFormat("HHmmss").format(java.util.Calendar.getInstance().getTime());
//
//        System.out.println(dt);
//        Long ldt = Long.valueOf(dt) + 1000;
//        System.out.println(ldt);
//
//        em.getTransaction().begin();
//        Query q = em.createNativeQuery("SELECT * FROM Journey WHERE route_id IN (1, 2) and time(arrival_time) > :for  order by Journey.route_id asc", Journey.class);
//        q.setParameter("for", 140000);
//
//        List<Journey> journey = q.getResultList();
//
//        for(Journey j : journey) {
//            System.out.println(j);
//        }

//        System.out.println(journey);

//        String ttt = "14:00";
//        String nnn = ttt.replace(":", "");
//        System.out.println(Long.valueOf(nnn));


//        Calendar calendar = new GregorianCalendar();
//        java.sql.Time time = new java.sql.Time(System.currentTimeMillis());
//
//        java.util.Date date= new java.util.Date();
//        System.out.println(new Timestamp(date.getTime()).getTime());
//
//        //Date now = new Date();
//        System.out.println(time);
//        System.out.println(time.getTime());
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
////        Date res = simpleDateFormat.parse(String.valueOf(journey.getArrivalTime()));
//        System.out.println(journey);
//        System.out.println(journey.getTime());


//        Journey journey1 = new Journey(400, 175000);
//        RouteService routeService = new RouteService(em);
//        journey1.setNumber(52);
//        Route route = routeService.getRouteBiId(1);
//        journey1.setRoute(route);
//        em.persist(journey1);
//
//        System.out.println(passenger.getDate());
//
//        Calendar dob = Calendar.getInstance();

        //System.out.println(checkDate("29-11-1988"));



//        try {
//            dob.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("29-05-1988"));
//            System.out.println(dob);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Passenger pass = new Passenger("asd", "wqe", dob);
//
//        em.persist(pass);
        em.getTransaction().commit();

        //redArr.setRoute(rs.getRouteBiId(1));

        //JourneyService js = new JourneyService(em);
        //js.addJourney(redArr);

    }
}
