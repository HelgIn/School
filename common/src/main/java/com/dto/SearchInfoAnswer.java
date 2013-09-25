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
}
