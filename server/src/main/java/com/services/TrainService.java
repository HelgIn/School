package com.services;


import com.entity.Train;

import javax.persistence.*;

public class TrainService {

    public EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();

    public void addTrain(Train train) {
        em.getTransaction().begin();
        em.merge(train);
        em.getTransaction().commit();
    }

}
