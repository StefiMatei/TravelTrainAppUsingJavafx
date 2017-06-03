package model;

import java.sql.Date;


public class Train {

    private int trainId;
    private int number;
    private Date dateOfDeparture;
    private Date dateOfArrival;
    private String leavesFrom;
    private String goesTo;
    private int places;

    public Train(){}
    public Train(int trainId, int number, Date dateOfDeparture, Date dateOfArrival, String leavesFrom, String goesTo) {
        this.trainId = trainId;
        this.number = number;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.leavesFrom = leavesFrom;
        this.goesTo = goesTo;

    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public String getLeavesFrom() {
        return leavesFrom;
    }

    public void setLeavesFrom(String leavesFrom) {
        this.leavesFrom = leavesFrom;
    }

    public String getGoesTo() {
        return goesTo;
    }

    public void setGoesTo(String goesTo) {
        this.goesTo = goesTo;
    }
}
