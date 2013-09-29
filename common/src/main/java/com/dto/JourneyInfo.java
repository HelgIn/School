package com.dto;


import entity.Passenger;
import objects.GetJourneyObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JourneyInfo implements Serializable{
    private int number;



    private List<Passenger> passengerList;

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public JourneyInfo(int number) {
        this.number = number;
        passengerList = new ArrayList<Passenger>();
    }

    public int getNumber() {
        return number;
    }

    public void print() {

        System.out.println("****************");

        if(passengerList.size() > 0) {
            for(Passenger p : passengerList) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String dob = sdf.format(p.getDate().getTime());
                System.out.println(p.getId() + " " +p.getName() + " " + p.getSecondName() + " " + dob);
            }
        } else {
            System.out.println("Ничего не найдено. Повторите поиск.");
        }
        System.out.println("****************");
    }
}
