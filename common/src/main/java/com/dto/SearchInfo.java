package com.dto;

/*
 *
 *
 */
public class SearchInfo {
    //private String arrivalStation;
    //private String destinationStation;

    private long arrivalID;
    private long destinationID;

    SearchInfo(long arrivalID, long destinationID) {
        this.arrivalID = arrivalID;
        this.destinationID = destinationID;
    }

    public long getArrivalID() {
        return arrivalID;
    }
    public long getDestinationID() {
        return destinationID;
    }


}
