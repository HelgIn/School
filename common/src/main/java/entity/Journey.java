package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name="arrival_time")
    private Date arrivalTime;
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Journey(int availableSeats, Date arrivalTime) {
        this.availableSeats = availableSeats;
        this.arrivalTime = arrivalTime;
    }

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route id_route) {
        this.route = id_route;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Journey { " + getId() + ", " + getAvailableSeats() + ", " + getArrivalTime() + " }";
    }
}
