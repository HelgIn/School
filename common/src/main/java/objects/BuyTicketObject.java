package objects;


import java.io.Serializable;

public class BuyTicketObject implements Serializable {
    private String name;
    private String surname;
    private java.util.Calendar date;
    private long journeyID;


    private boolean result;
    private String message;



    public BuyTicketObject(String name, String surname, java.util.Calendar date, long journeyID) {
        result = false;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.journeyID = journeyID;
        message = null;
    }

    public String getName() {
        return  name;
    }
    public String getSurname() {
        return  surname;
    }
    public long getJourneyID() {
        return journeyID;
    }
    public java.util.Calendar getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Ticket { " + getName() + ", " + getSurname() + ", " + getDate() + ", " + getJourneyID() + " }";
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
    public boolean getResult() {
        return result;
    }
}
