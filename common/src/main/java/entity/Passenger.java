package entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Passenger")
public class Passenger implements Serializable{
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
    @Temporal(TemporalType.DATE)
    private java.util.Calendar date;


    public Passenger(String name, String secondName, java.util.Calendar date) {

        this.name = name;
        this.secondName = secondName;
        this.date = date;

    }

    public  long getId() {
        return id;
    }

    public Passenger() {
    }

    public String getName() {
        return name;
    }
    public  String getSecondName() {
        return secondName;
    }

    public java.util.Calendar getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Passenger { " + getName() + ", " + getSecondName() + " }";
    }

}
