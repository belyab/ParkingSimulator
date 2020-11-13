package com.company;

import java.util.Scanner;

public class Parking {
    Scanner scanner = new Scanner(System.in);
    public Parking() {
        int sizeParking = scanner.nextInt();
    }
    private Car[] places;

    public Parking(int numbPlaces) {
        this.places = new Car[numbPlaces];
    }


    public Car[] getPlaces() {
        return places;
    }

    public void setPlaces(Car[] places) {
        this.places = places;
    }






}
