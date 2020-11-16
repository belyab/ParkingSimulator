package com.company;

import java.util.Arrays;
import java.util.Random;

public class ParkingLotManager {
    Parking parking;
    boolean isFoundEmptyPlaces;
    Random random = new Random();
    private int counterRemovedCar;




    public ParkingLotManager(Parking parking1) {
        this.parking=parking1;
    }


    public void fillParkingPlaces() {
        Car[] claimingCars = new Car[Math.abs(random.nextInt(parking.getPlaces().length)) / 3];
        Car currentCar;

        for (int car = 0; car < claimingCars.length; car++) {
            currentCar = new Car();
            claimingCars[car] = currentCar;
        }
        int counterEmptyPlaces = countingEmptyPlaces();
        if (isFoundEmptyPlaces) {
            if (counterEmptyPlaces >= claimingCars.length) {
                for (Car claimingCar : claimingCars) {
                    for (int place = 0; place < parking.getPlaces().length; place++) {
                        if (parking.getPlaces()[place] == null) {
                            parking.getPlaces()[place] = claimingCar;
                            break;

                        }
                    }
                }
                System.out.println("Places  are full ");
                System.out.println(" ");
            } else {
                for (int car = 0; car < counterEmptyPlaces; car++) {
                    for (int place = 0; place < parking.getPlaces().length; place++) {
                        if (parking.getPlaces()[place] == null) {
                            parking.getPlaces()[place] = claimingCars[car];
                            break;
                        }
                    }
                }
                System.out.println("Driven" + counterEmptyPlaces + "cars.The other didn't fit");
            }
        } else {
            System.out.println("There are no free Parking places ");
        }
    }


    public  int countingEmptyPlaces() {
        isFoundEmptyPlaces = true;
        int counterEmptyPlaces = 0;
        for (Object car : parking.getPlaces()) {
            if (car == null) {
                isFoundEmptyPlaces = true;
                counterEmptyPlaces += 1;

            } else {
                isFoundEmptyPlaces = false;
            }
        }
        return counterEmptyPlaces;
    }


    public void minusMove() {
        this.counterRemovedCar = 0;
        for (Car car : parking.getPlaces()) {
            if (car != null) {
                car.setDurationMoves(car.getDurationMoves() - 1);
                deleteCar();
            }
        }
        System.out.println(this.counterRemovedCar +" cars left");
    }

    public void deleteCar() {
        for (int car = 0; car < parking.getPlaces().length; car++) {
            if (parking.getPlaces()[car] != null && parking.getPlaces()[car].getDurationMoves() == 0) {
                parking.getPlaces()[car] = null;
                this.counterRemovedCar += 1;
            }
        }
    }
    public int Min(){
        int min=10;
        for (int car=0;car <parking.getPlaces().length;car++ ) {
            if ((parking.getPlaces()[car] != null) && (min) >= parking.getPlaces()[car].getDurationMoves()) {
                min = parking.getPlaces()[car].getDurationMoves();
            }
        }
        return min;
    }
    public void resetPlaces(){
        Arrays.fill(parking.getPlaces(), null);
    }

}


