package com.dto;

import java.io.Serializable;

/*
 *
 *
 */
public class SearchInfo implements Serializable {
    //private String arrivalStation;
    //private String destinationStation;

    private long arrivalID;
    private long destinationID;

    private String from;

    SearchInfo(long arrivalID, long destinationID) {
        this.arrivalID = arrivalID;
        this.destinationID = destinationID;
    }
    public SearchInfo(String from) {
         this.from = from;
    }

    public long getArrivalID() {
        return arrivalID;
    }
    public long getDestinationID() {
        return destinationID;
    }
    public String getFrom() {
        return from;
    }


}
