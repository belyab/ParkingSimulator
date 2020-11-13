package com.company;

import java.util.Random;

public class ParkingLotManager {
    public static Parking parking;
    private static boolean isFoundEmptyPlaces;
    Random random = new Random();
    int counterRemovedCar;


    public void fillParkingLot() {
        Car[] claimingCars = new Car[Math.abs(random.nextInt(parking.getPlaces().length)) / 2];
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
                System.out.println("Places are full ");
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
            System.out.println("There are no free Parking places");
        }
    }

    public static int countingEmptyPlaces() {
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
        System.out.println("Released " + this.counterRemovedCar + " Parking places.");
    }

    public void deleteCar() {
        int counterRemovedCar = 0;
        for (int car = 0; car < parking.getPlaces().length; car++) {
            if (parking.getPlaces()[car] != null && parking.getPlaces()[car].getDurationMoves() == 0) {
                parking.getPlaces()[car] = null;
                this.counterRemovedCar += 1;
            }
        }
    }
}


