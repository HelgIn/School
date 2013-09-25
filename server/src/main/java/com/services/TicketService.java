package com.services;


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

    public boolean addTicket() {
        em.getTransaction().begin();

        Passenger passenger = new Passenger(ticketInfo.getName(), ticketInfo.getSurname(), ticketInfo.getDate());
        em.persist(passenger);

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        JourneyService journey = new JourneyService(em);
        ticket.setJourney(journey.getByID(ticketInfo.getJourneyID()));
        em.persist(ticket);
        em.getTransaction().commit();
        return true;
    }



}
