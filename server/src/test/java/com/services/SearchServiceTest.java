package com.services;


import com.dto.SearchInfo;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class SearchServiceTest {

    private SearchInfo searchInfo;
    private SearchService searchService;
    private EntityManager em;


    @Test(expected = NoResultException.class)
    public void searchTestException() {
        searchInfo = new SearchInfo("123", "123");
        em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        searchService = new SearchService(searchInfo, em);
        searchService.search();
    }

    @Test
    public void searchTest() {
        searchInfo = new SearchInfo("Санкт-Петербург", "Москва");
        em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        searchService = new SearchService(searchInfo, em);
        assert(searchService.search().getSearchObj().get(0).getName().equals("Красная Стрела из СПб"));
    }

}
