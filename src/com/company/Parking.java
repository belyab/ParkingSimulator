package com.company;

import java.util.Scanner;

public class Parking {
    private Car[] placesCar;
    private Car[] placesTruck;

    public Parking(int numbPlacesCar,int numbPlacesTruck) {
        this.placesCar = new Car[numbPlacesCar];
        this.placesTruck=new Car[numbPlacesTruck];

    }


    public Car[] getPlacesCar() {
        return placesCar;
    }
    public Car[] getPlacesTruck() {
        return placesTruck;
    }


    public void setPlacesCar(Car[] placesCar) {
        this.placesCar = placesCar;
    }
    public void setPlacesTruck(Car[] placesTruck) {
        this.placesTruck = placesTruck;
    }


}
