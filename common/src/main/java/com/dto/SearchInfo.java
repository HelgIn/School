package com.dto;

import java.io.Serializable;


public class SearchInfo implements Serializable {

    //static final long serialVersionUID = -2098940495326113907L;
    private String from;
    private String to;


    public SearchInfo(String from, String to) {
        this.from = from;
        this.to = to;
    }



    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return getFrom() + " - " + getTo();
    }

}
