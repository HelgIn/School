package com.services;


import com.dto.TicketInfo;
import entity.Passenger;
import objects.BuyTicketObject;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TicketTest {
    private Passenger passenger;
    private EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();;
    private TicketInfo ticketInfo;


    @Test
    public void checkPositiveTest() throws ParseException {
        Calendar date = new GregorianCalendar();
        ticketInfo = new TicketInfo();
        date.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("29-05-1988"));
        ticketInfo.addObject(new BuyTicketObject("L", "L", date, 1));
        ticketInfo.addObject(new BuyTicketObject("N", "N", date, 1));

        TicketService ticketService = new TicketService(ticketInfo, em);
        boolean result = ticketService.checkPassengers();
        assert(result == true);
    }

    @Test
     public void checkNegativeTest() throws ParseException {
        Calendar date = new GregorianCalendar();
        ticketInfo = new TicketInfo();
        date.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("29-05-1988"));
        ticketInfo.addObject(new BuyTicketObject("asd", "wqe", date, 1));
        ticketInfo.addObject(new BuyTicketObject("N", "L", date, 1));

        TicketService ticketService = new TicketService(ticketInfo, em);
        boolean result = ticketService.checkPassengers();
        assert(result == false);
    }

//    @Test
//    public void getTest() throws ParseException {
//        Calendar date = new GregorianCalendar();
//        ticketInfo = new TicketInfo();
//        date.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("29-05-1988"));
//        ticketInfo.addObject(new BuyTicketObject("asd", "wqe", date, 1));
//        ticketInfo.addObject(new BuyTicketObject("N", "L", date, 1));
//
//        TicketService ticketService = new TicketService(ticketInfo, em);
//        int result = ticketService.getSeats(1);
//        assert(result == 491);
//    }

}
