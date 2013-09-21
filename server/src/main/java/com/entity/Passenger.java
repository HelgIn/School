package com.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Passenger")
public class Passenger {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment" )
    @Column (name = "id")
    private int id_reader;
    @Column (name = "name")
    private String name;
    @Column(name="second_name")
    private String secondName;
    @Column(name="date")
    private String date;

}
