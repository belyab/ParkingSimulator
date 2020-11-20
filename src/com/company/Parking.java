package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Parking {
    private ArrayList<Car> placesCar;
    private ArrayList<Car> placesTruck;


    public Parking(int numbPlacesCar,int numbPlacesTruck) {
        this.placesCar = new ArrayList<>(Collections.nCopies(numbPlacesCar, null));
        this.placesTruck = new ArrayList<>(Collections.nCopies(numbPlacesTruck,null));
        this.placesCar.trimToSize();
        this.placesTruck.trimToSize();


    }


    public ArrayList<Car> getPlacesCar() {
        return placesCar;
    }
    public ArrayList<Car> getPlacesTruck() {
        return placesTruck;
    }


    public void setPlacesCar(ArrayList<Car> placesCar) {
        this.placesCar = placesCar;
    }
    public void setPlacesTruck(ArrayList<Car> placesTruck) {
        this.placesTruck = placesTruck;
    }


}
