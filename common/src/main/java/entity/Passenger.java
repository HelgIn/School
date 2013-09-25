package entity;


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
    private int id;
    @Column (name = "name")
    private String name;
    @Column(name="second_name")
    private String secondName;
    @Column(name="date")
    private String date;


    public Passenger(String name, String secondName, String date) {

        this.name = name;
        this.secondName = secondName;
        this.date = date;

    }

}
