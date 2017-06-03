package model;


public class Ticket {

    private int ticketId;
    private int trainId;
    private double price;
    private int quantity;
    private int placeNumber;

    public Ticket(){}

    public Ticket(int ticketId, int trainId, double price, int quantity, int placeNumber) {
        this.ticketId = ticketId;
        this.trainId = trainId;
        this.price = price;
        this.quantity = quantity;
        this.placeNumber = placeNumber;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }
}
