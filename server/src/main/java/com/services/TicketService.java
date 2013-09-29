package com.services;


import com.dto.JourneyInfo;
import entity.Journey;
import objects.BuyTicketObject;
import com.dto.TicketInfo;
import entity.Passenger;
import entity.Ticket;
import sun.rmi.runtime.Log;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.logging.Logger;

public class TicketService {
    private EntityManager em;
    TicketInfo ticketInfo;

    private static Logger log = Logger.getLogger(TicketService.class.getName());

    public TicketService(TicketInfo ticketInfo, EntityManager em) {
        this.em = em;
        this.ticketInfo = ticketInfo;
    }

    public TicketInfo addTicket() {

        em.getTransaction().begin();

        if(getSeats(ticketInfo.getJourneyId()) < ticketInfo.getTicketList().size()) {

            em.getTransaction().commit();
            log.info("checkPassenger(): Мест нет");
            ticketInfo.setMessage("Мест нет");
            ticketInfo.setResult(false);
            return ticketInfo;

        } else if(!checkPassengers()) {

            em.getTransaction().commit();
            log.info("checkPassenger(): Пассажир уже существует");
            ticketInfo.setMessage("Пассажир уже существует");
            ticketInfo.setResult(false);
            return ticketInfo;

        } else {

            for(BuyTicketObject ticketObject : ticketInfo.getTicketList())  {

                Passenger passenger = new Passenger(ticketObject.getName(), ticketObject.getSurname(), ticketObject.getDate());
                em.persist(passenger);

                Ticket ticket = new Ticket();
                ticket.setPassenger(passenger);
                JourneyService journey = new JourneyService(new JourneyInfo(0), em);

                ticket.setJourney(journey.getByID(ticketObject.getJourneyID()));
                em.persist(ticket);
                ticketObject.setResult(true);
                em.getTransaction().commit();

            }
            ticketInfo.setMessage("Покупка завершена");
            return ticketInfo;

        }
    }



//        if(checkPassengers()) {
//            for(BuyTicketObject ticketObject : ticketInfo.getTicketList())  {
//
//                if(getSeats(ticketObject.getJourneyID()) > 0) {
//                    Passenger passenger = new Passenger(ticketObject.getName(), ticketObject.getSurname(), ticketObject.getDate());
//                    em.persist(passenger);
//
//                    Ticket ticket = new Ticket();
//                    ticket.setPassenger(passenger);
//                    JourneyService journey = new JourneyService(new JourneyInfo(0), em);
//
//                    ticket.setJourney(journey.getByID(ticketObject.getJourneyID()));
//                    em.persist(ticket);
//                    ticketObject.setResult(true);
//                } else {
//                    log.info("checkPassenger(): Пассажир уже существует");
//                    ticketInfo.setMessage("Пассажир уже существует");
//                    break;
//                }
//            }
//        } else {
//            log.info("checkPassenger(): Пассажир уже существует");
//            ticketInfo.setMessage("Пассажир уже существует");
//        }


    public Integer getSeats(long journeyId) {

        Query avSeats = em.createNativeQuery("select Journey.available_seats from Journey where id=:id");
        avSeats.setParameter("id", journeyId);
        Integer availableSeats = (Integer) avSeats.getSingleResult();
        Query ticketsCount = em.createNativeQuery("select count(Ticket.id) from Ticket where journey_id =:id");
        ticketsCount.setParameter("id", journeyId);
        Integer saleSeats = ((BigInteger) ticketsCount.getSingleResult()).intValue();
        log.info("Result getSeats(): " + (availableSeats - saleSeats));
        return availableSeats - saleSeats;

    }

    public boolean checkPassengers() {

        boolean result = false;

        for(BuyTicketObject ticketObject : ticketInfo.getTicketList())  {
            Query check = em.createNativeQuery("select count(id) from Passenger where name=:name and second_name=:sec");
            check.setParameter("name", ticketObject.getName());
            check.setParameter("sec", ticketObject.getSurname());
            Integer pass = ((BigInteger) check.getSingleResult()).intValue();
            System.out.println("Passenger Check: " + pass);
            if(pass == 0) {
                result =  true;
            } else {
                ticketObject.setResult(false);
                ticketObject.setMessage("Пассажир " + ticketObject.getName() + " " + ticketObject.getSurname() + " уже зарегистрирован");
                break;
            }
        }
        log.info("Result checkPassengers(): " + result);
        return result;

    }



}
