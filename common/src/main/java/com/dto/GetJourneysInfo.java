package com.dto;


import objects.GetJourneyObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetJourneysInfo implements Serializable {

    private List<GetJourneyObject> allJourneys;
    public GetJourneysInfo() {
        allJourneys = new ArrayList<GetJourneyObject>();
    }
    public List<GetJourneyObject> getJourneyObjects() {
        return  allJourneys;
    }

    public void print() {
        System.out.println("*************************");

        if(allJourneys.size() > 0) {
            for(GetJourneyObject o : allJourneys) {
                System.out.println(o);
            }
        } else {
            System.out.println("Поезда не найдены");
        }
        System.out.println("*************************");

    }
}
