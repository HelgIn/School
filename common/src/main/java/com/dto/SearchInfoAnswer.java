package com.dto;

import entity.Route;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SearchInfoAnswer  implements Serializable {
    private List<SearchInfoObject> searchObj;

    public SearchInfoAnswer() {
        searchObj = new ArrayList<SearchInfoObject>();
    }

    public SearchInfoAnswer(List<SearchInfoObject> searchObj) {
        this.searchObj = searchObj;
    }

    public void setSearchObj(List<SearchInfoObject> searchObj) {
        this.searchObj = searchObj;
    }

    public List<SearchInfoObject> getSearchObj() {
        return searchObj;
    }


    public List<Long> getIDs() {
        List<Long> ids = new ArrayList<Long>();
        for(SearchInfoObject search : getSearchObj()) {
            ids.add(search.getId());
        }
        return ids;
    }

    @Override
    public String toString() {
        String res = "id\tname\tarrival\tseats\n";
        for(SearchInfoObject search : getSearchObj()) {
            res += search.getId();
            res += "\t";
            res += search.getName();
            res += "\t";
            res += search.getArrivalTime();
            res += "\t";
            res +=  search.getAvailableSeats();
            res += "\n";
        }
        return res;
    }
}
