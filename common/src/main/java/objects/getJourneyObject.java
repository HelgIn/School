package objects;


import java.io.Serializable;

public class GetJourneyObject implements Serializable {
    private long id;
    private int number;
    private int availableSeats;
    private String name;


    public GetJourneyObject(long id, int number, int availableSeats, String name) {
        this.id = id;
        this.number = number;
        this.availableSeats = availableSeats;
        this.name = name;
    }


    @Override
    public String toString() {
        return id + ". " + number + " '" + name + "' " + availableSeats + " мест";
    }

}
