package com.services;


import com.dto.BuyTicketObject;
import com.dto.SearchInfo;
import com.dto.TicketInfo;
import entity.Journey;
import entity.Passenger;
import entity.Ticket;

import javax.persistence.EntityManager;

public class TicketService {
    private EntityManager em;
    TicketInfo ticketInfo;

    public TicketService(TicketInfo ticketInfo, EntityManager em) {
        this.em = em;
        this.ticketInfo = ticketInfo;
    }

    public Boolean addTicket() {
        em.getTransaction().begin();

        for(BuyTicketObject to : ticketInfo.getTicketList())  {

            Passenger passenger = new Passenger(to.getName(), to.getSurname(), to.getDate());
            em.persist(passenger);

            Ticket ticket = new Ticket();
            ticket.setPassenger(passenger);
            JourneyService journey = new JourneyService(em);
            ticket.setJourney(journey.getByID(to.getJourneyID()));
            em.persist(ticket);
        }
        em.getTransaction().commit();
        return true;
    }



}
