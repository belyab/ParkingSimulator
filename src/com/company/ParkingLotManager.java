package com.company;

public class ParkingLotManager {
    private Car[] places;

    public void numbOfPlaces(int numberOfPlaces) {
        this.places = new Car[numberOfPlaces];
    }


    public Car[] getPlaces() {
        return places;
    }

    public void setPlaces(Car[] places) {
        this.places = places;
    }



}
