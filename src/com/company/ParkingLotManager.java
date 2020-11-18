package com.company;

import java.util.Arrays;
import java.util.Random;

public class ParkingLotManager {
    Parking parking;
    Random random = new Random();
    private int counterRemoved;
    boolean isFoundEmptyPlacesCar;
    boolean isFoundEmptyPlacesTruck;
    private int uniqueNumbCar=0;
    private int uniqueNumTruck=0;


    public ParkingLotManager(Parking parking1) {
        this.parking = parking1;
    }

    public void info() {
        System.out.println(this.parking );
    }



    public void fillParkingPlaces() {
        Car[] claimingCars = new Car[Math.abs(random.nextInt((parking.getPlacesCar().length+parking.getPlacesTruck().length) / 3))];
        Car[] claimingTrucks = new Car[Math.abs(random.nextInt((parking.getPlacesTruck().length+parking.getPlacesTruck().length) / 3))];
        Car currentCar;
        Car currentTruck;
        System.out.println(Arrays.toString(claimingCars) +"cars  and "+ Arrays.toString(claimingTrucks) +" trucks arrived");

        for (int car = 0; car < claimingCars.length; car++) {
            uniqueNumbCar+=1;
            currentCar = new Car("Car",uniqueNumbCar);
            claimingCars[car] = currentCar;
        }
        for (int truck = 0; truck < claimingTrucks.length; truck++) {
            uniqueNumbCar+=1;
            currentTruck = new Car("Truck",uniqueNumTruck);
            claimingTrucks[truck]=currentTruck;
        }
        fillCarPlaces(claimingCars);
        fillTruckPlace(claimingTrucks);
    }


    public int countingEmptyPlacesCar() {
        findEmptyCarPlaces();
        int counterEmptyPlaces=0;
        for (Object car:parking.getPlacesCar()){
            if(car==null){
                counterEmptyPlaces+=1;
            }
        }
        return counterEmptyPlaces;
    }
    public int countingEmptyPlacesTruck() {
        findEmptyTruckPlaces();
        int counterEmptyPlaces=0;
        for (Object car:parking.getPlacesTruck()){
            if(car==null){
                counterEmptyPlaces+=1;
            }
        }
        return counterEmptyPlaces;
    }

    public void findEmptyTruckPlaces() {
        boolean isFound = false;
        for (int car = 0; !isFound; car++) {
            if (parking.getPlacesTruck()[car] == null) {
                isFoundEmptyPlacesTruck = true;
                isFound = true;
            } else {
                isFoundEmptyPlacesTruck = false;
            }
            if (car == parking.getPlacesTruck().length - 1 && !isFound) {
                isFound = true;
            }
        }
    }


    public void findEmptyCarPlaces() {
        boolean isFound = false;
        for (int car = 0; !isFound; car++) {
            if (parking.getPlacesCar()[car] == null) {
                isFoundEmptyPlacesCar = true;
                isFound = true;
            } else {
                isFoundEmptyPlacesCar = false;
            }
            if (car == parking.getPlacesCar().length - 1 && !isFound) {
                isFound = true;
            }
        }
    }



        public void minusMove() {
            this.counterRemoved = 0;
            int counterRemovedCar = 0;
            int counterRemovedTruck = 0;
            for (int car = 0; car < parking.getPlacesCar().length; car++) {
                if (parking.getPlacesCar()[car] != null && parking.getPlacesCar()[car].getType().equals("truck")) {
                    parking.getPlacesCar()[car].setDurationMoves(parking.getPlacesCar()[car].getDurationMoves() - 1);
                    if (parking.getPlacesCar()[car].getDurationMoves() == 0) {
                        parking.getPlacesCar()[car] = null;
                        parking.getPlacesCar()[car + 1] = null;
                        this.counterRemoved += 1;
                        counterRemovedCar += 1;
                    }
                    car += 1;
                } else if (parking.getPlacesCar()[car] != null && parking.getPlacesCar()[car].getType().equals("car")) {
                    parking.getPlacesCar()[car].setDurationMoves(parking.getPlacesCar()[car].getDurationMoves() - 1);
                    if (parking.getPlacesCar()[car].getDurationMoves() == 0) {
                        parking.getPlacesCar()[car] = null;
                        parking.getPlacesCar()[car + 1] = null;
                        this.counterRemoved += 1;
                        counterRemovedCar += 1;
                    }
                }
            }
            for (int car = 0; car < parking.getPlacesTruck().length; car++) {
                if (parking.getPlacesTruck()[car] != null) {
                    parking.getPlacesTruck()[car].setDurationMoves(parking.getPlacesTruck()[car].getDurationMoves() - 1);
                    if (parking.getPlacesTruck()[car].getDurationMoves() == 0) {
                        parking.getPlacesTruck()[car] = null;
                        this.counterRemoved += 1;
                    }
                }
            }
            System.out.println(this.counterRemoved + " parking places were released ");
            System.out.println(counterRemovedCar + "  in car parking " + counterRemovedTruck + "  in truck parking");
}


    public void Min() {
        int minCar = 10;
        int minTruck = 10;
        for (int car = 0; car < parking.getPlacesCar().length; car++) {
            if (parking.getPlacesCar()[car] != null && minCar > parking.getPlacesCar()[car].getDurationMoves()) {
                minCar = parking.getPlacesCar()[car].getDurationMoves();
            }
        }
        for (int car = 0; car < parking.getPlacesCar().length; car++) {
            if (parking.getPlacesTruck()[car] != null && minTruck > parking.getPlacesTruck()[car].getDurationMoves()) {
                minTruck = parking.getPlacesTruck()[car].getDurationMoves();
            }
        }
        System.out.println("The shortest   waiting time for a car: " + minCar);
        System.out.println("The shortest   waiting time for a truck: " + minTruck);
    }


    public void resetPlaces(){
        int carCounter=countingEmptyPlacesCar();
        int truckCounter=countingEmptyPlacesTruck();

        Arrays.fill(parking.getPlacesCar(),null);
        Arrays.fill(parking.getPlacesTruck(),null);

        System.out.println(parking.getPlacesCar().length-carCounter+" places were released in the parking for car");
        System.out.println(parking.getPlacesTruck().length-truckCounter+" places were released in the parking for truck");
    }

    private void fillCarPlaces(Car[] claimingCars){
        int counterEmptyPlaces=countingEmptyPlacesCar();
        if (isFoundEmptyPlacesCar) {
            if (counterEmptyPlaces >= claimingCars.length) {
                for (Car cars : claimingCars) {
                    for (int place = 0; place < parking.getPlacesCar().length; place++) {
                        if (parking.getPlacesCar()[place] == null) {
                            parking.getPlacesCar()[place] = cars;
                            break;

                        }
                    }
                }
                System.out.println("Places on  parking are full ");
                System.out.println(" ");
            } else {
                for (int c = 0; c < counterEmptyPlaces; c++) {
                    for (int place = 0; place < parking.getPlacesCar().length; place++) {
                        if (parking.getPlacesCar()[place] == null) {
                            parking.getPlacesCar()[place] = claimingCars[c];
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

    private void fillTruckPlace(Car[] truck) {
        int counterEmptyPlaces = countingEmptyPlacesTruck();
        int counterOfNotFitTrucks = truck.length - counterEmptyPlaces;
        if (isFoundEmptyPlacesTruck) {
            if (counterEmptyPlaces >= truck.length) {
                for (Car trucks : truck) {
                    for (int place = 0; place < parking.getPlacesTruck().length; place++) {
                        if (parking.getPlacesTruck()[place] == null) {
                            parking.getPlacesTruck()[place] = trucks;
                            break;
                        }
                    }
                }
                System.out.println("Places on truck parking are full ");
                System.out.println(" ");
            } else {
                for (int c = 0; c < counterEmptyPlaces; c++) {
                    for (int place = 0; place < parking.getPlacesTruck().length; place++) {
                        if (parking.getPlacesTruck()[place] == null) {
                            parking.getPlacesTruck()[place] = truck[c];
                            break;
                        }
                    }
                    truck[c] = null;
                }
                System.out.println("Driven" + counterEmptyPlaces + "truck.The other were sent to the parking for car ");//
                int counterTwoFreePlacesForTruck = countingTwoFreePlacesForTruck();
                if (counterTwoFreePlacesForTruck >= (truck.length - counterEmptyPlaces)) {
                    fillingPassPlacesWithTrucks(counterOfNotFitTrucks,truck);
                    System.out.println(counterTwoFreePlacesForTruck + "trucks entered the Parking places for cars .The rest didn't fit");
                }
            }
        } else {
            int counterTwoFreePlacesForTruck = countingTwoFreePlacesForTruck();
            if (counterTwoFreePlacesForTruck >= counterOfNotFitTrucks) {
                fillingPassPlacesWithTrucks(counterOfNotFitTrucks, truck);
            } else {

                System.out.println(counterTwoFreePlacesForTruck + " trucks entered the parking for car, the rest did not fit");
            }
        }
    }

            private void fillingPassPlacesWithTrucks(int counterOfNotFitTrucks, Car[] trucks) {
                for (Car truck : trucks) {
                    if (truck != null) {
                        for (int place = 0; place < parking.getPlacesCar().length - 1; place++) {
                            if (parking.getPlacesCar()[place] == null && parking.getPlacesCar()[place + 1] == null) {
                                parking.getPlacesCar()[place] = truck;
                                parking.getPlacesCar()[place + 1] = truck;
                                break;
                            }
                        }
                    }
                }
            }

            private int countingTwoFreePlacesForTruck(){
        int counter=0;
        for (int i=0;i<parking.getPlacesCar().length-1;i++){
            if(parking.getPlacesCar()[i]==null&&parking.getPlacesCar()[i+1]==null){
                counter+=1;
            }
        }
        return counter;
    }

}


