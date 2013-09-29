package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name="Journey")
public class Journey {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="id")
    private long id;

    @Column(name="available_seats")
    private int availableSeats;

    public Journey() {
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

//    @Column(name="arrival_time")
//    private Date arrivalTime;

    @Column(name="arrival_time")
    private String arrivalTime;

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Journey(int availableSeats, String arrivalTime) {
        this.availableSeats = availableSeats;
        this.arrivalTime = arrivalTime;
    }

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;
    public Route getRoute() {
        return route;
    }

    private int number;

    @Column(name="number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public void setRoute(Route id_route) {
        this.route = id_route;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Journey { " + getId() + ", " + getNumber() + ", " + getAvailableSeats() + ", " + getArrivalTime() + " }";
    }
}
