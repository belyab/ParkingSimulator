package com.company;

import java.util.ArrayList;
import java.util.Random;

public class ParkingLotManager {
    Parking parking;
    Random random = new Random();
    private int counterRemoved;
    private boolean isFoundEmptyPlacesCar;
    private boolean isFoundEmptyPlacesTruck;
    private int uniqueNumbCar = 0;
    private int uniqueNumTruck = 0;


    public ParkingLotManager(Parking parking1) {
        this.parking = parking1;
    }

    public void info() {
        System.out.println(this.parking + "\n");
    }


    public void fillParkingPlaces() {
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Car> trucks = new ArrayList<>();
        Car currentCar;
        Car currentTruck;

        for (int car = 0; car < Math.abs(random.nextInt((parking.getPlacesCar().size() + parking.getPlacesTruck().size()) / 3)); car++) {
            uniqueNumbCar += 1;
            currentCar = new Car("Car", uniqueNumbCar);
            cars.add(currentCar);
        }
        for (int truck = 0; truck < Math.abs(random.nextInt((parking.getPlacesTruck().size() +
                parking.getPlacesCar().size()) / 3)); truck++) {
            uniqueNumbCar += 1;
            currentTruck = new Car("Truck", uniqueNumTruck);
            trucks.add(currentTruck);
        }
        System.out.println(cars.size() + "cars  and " + trucks.size() + " trucks arrived");
        fillCarPlaces(cars);
        fillTruckPlace(trucks);
    }

    private void fillTruckPlace(ArrayList<Car> trucks) {
        int counterEmptyPlaces = countingEmptyPlacesTruck();
        int counterOfNotFitTrucks = trucks.size() - counterEmptyPlaces;
        if (parking.getPlacesTruck().contains(null))
            if (counterEmptyPlaces >= trucks.size()) {
                for (Car truck : trucks) {
                    for (int place = 0; place < parking.getPlacesTruck().size(); place++) {
                        if (parking.getPlacesTruck().get(place) == null) {
                            parking.getPlacesTruck().set(place, truck);
                            break;
                        }
                    }
                }
                System.out.println("Places on truck parking are full " + "\n");
            } else {
                for (int c = 0; c < counterEmptyPlaces; c++) {
                    for (int place = 0; place < parking.getPlacesTruck().size(); place++) {
                        if (parking.getPlacesTruck().get(place) == null) {
                            parking.getPlacesTruck().set(place, trucks.get(c));
                            break;
                        }
                    }
                    trucks.set(c, null);
                }
                System.out.println("Driven" + counterEmptyPlaces + "truck.The other were sent to the parking for car ");//
                int counterTwoFreePlacesForTruck = countingTwoFreePlacesForTruck();
                if (counterTwoFreePlacesForTruck >= counterOfNotFitTrucks) {
                    fillingPassPlacesWithTrucks(trucks);
                    System.out.println(counterTwoFreePlacesForTruck + "trucks entered the Parking places for cars .The rest didn't fit");
                }
            }
        else {
            int counterTwoFreePlacesForTruck = countingTwoFreePlacesForTruck();
            if (counterTwoFreePlacesForTruck >= counterOfNotFitTrucks) {
                fillingPassPlacesWithTrucks(trucks);
            } else {
                System.out.println("no parking places");
            }
        }
    }

    private void fillingPassPlacesWithTrucks(ArrayList<Car> trucks) {
        for (int car = 0; car < trucks.size(); car++) {
            if (trucks.get(car) != null) {
                for (int place = 0; place < parking.getPlacesCar().size() - 1; place++) {
                    if (parking.getPlacesCar().get(place) == null && parking.getPlacesCar().get(place + 1) == null) {
                        parking.getPlacesCar().set(place, trucks.get(car));
                        parking.getPlacesCar().set(place + 1, trucks.get(car));
                        break;
                    }
                }
            }
        }
    }

    private int countingTwoFreePlacesForTruck() {
        int counter = 0;
        for (int i = 0; i < parking.getPlacesCar().size() - 1; i++) {
            if (parking.getPlacesCar().get(i) == null && parking.getPlacesCar().get(i + 1) == null) {
                counter += 1;
            }
        }
        return counter;
    }

    private void fillCarPlaces(ArrayList<Car> cars) {
        int counterEmptyPlaces = countingEmptyPlacesCar();
        if (parking.getPlacesCar().contains(null)) {
            if (counterEmptyPlaces >= cars.size()) {
                for (Car car : cars) {
                    for (int place = 0; place < parking.getPlacesCar().size(); place++) {
                        if (parking.getPlacesCar().get(place) == null) {
                            parking.getPlacesCar().set(place, car);
                            break;

                        }
                    }
                }
                System.out.println("Places on  parking are full " + "\n");
            } else {
                for (int c = 0; c < counterEmptyPlaces; c++) {
                    for (int place = 0; place < parking.getPlacesCar().size(); place++) {
                        if (parking.getPlacesCar().get(c) == null) {
                            parking.getPlacesCar().set(place, cars.get(c));
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


    public int countingEmptyPlacesCar() {
        int counterEmptyPlaces = 0;
        if (!parking.getPlacesCar().contains(null)) {
            return 0;
        } else {
            for (Object car : parking.getPlacesCar()) {
                if (car == null) {
                    counterEmptyPlaces += 1;
                }
            }
            return counterEmptyPlaces;
        }
    }

    public int countingEmptyPlacesTruck() {
        int counterEmptyPlaces = 0;
        if (!parking.getPlacesTruck().contains(null)) {
            return 0;
        } else {
            for (Object car : parking.getPlacesTruck()) {
                if (car == null) {
                    counterEmptyPlaces += 1;
                }
            }
            return counterEmptyPlaces;
        }
    }


    public void minusMove() {
        this.counterRemoved = 0;
        int counterRemovedCar = 0;
        int counterRemovedTruck = 0;
        for (int car = 0; car < parking.getPlacesCar().size(); car++) {
            if (parking.getPlacesCar().get(car) != null && parking.getPlacesCar().get(car).getType().equals("truck")) {
                parking.getPlacesCar().get(car).setDurationMoves(parking.getPlacesCar().get(car).getDurationMoves() - 1);
                if (parking.getPlacesCar().get(car).getDurationMoves() == 0) {
                    parking.getPlacesCar().set(car, null);
                    parking.getPlacesCar().set(car + 1, null);
                    this.counterRemoved += 1;
                    counterRemovedCar += 1;
                }
                car += 1;
            } else if (parking.getPlacesCar().get(car) != null && parking.getPlacesCar().get(car).getType().equals("car")) {
                parking.getPlacesCar().get(car).setDurationMoves(parking.getPlacesCar().get(car).getDurationMoves() - 1);
                if (parking.getPlacesCar().get(car).getDurationMoves() == 0) {
                    parking.getPlacesCar().set(car, null);
                    parking.getPlacesCar().set(car + 1, null);
                    this.counterRemoved += 1;
                    counterRemovedCar += 1;
                }
            }
        }
        for (int car = 0; car < parking.getPlacesTruck().size(); car++) {
            if (parking.getPlacesTruck().get(car) != null) {
                parking.getPlacesTruck().get(car).setDurationMoves(parking.getPlacesTruck().get(car).getDurationMoves() - 1);
                if (parking.getPlacesTruck().get(car).getDurationMoves() == 0) {
                    parking.getPlacesTruck().set(car, null);
                    this.counterRemoved += 1;
                    counterRemovedCar += 1;
                }
            }
        }
        System.out.println(this.counterRemoved + " parking places were released ");
        System.out.println(counterRemovedCar + "  in car parking " + counterRemovedTruck + "  in truck parking");
    }


    public void Min() {
        int minCar = 10;
        int minTruck = 10;
        for (int car = 0; car < parking.getPlacesCar().size(); car++) {
            if (parking.getPlacesCar().get(car) != null && minCar > parking.getPlacesCar().get(car).getDurationMoves()) {
                minCar = parking.getPlacesCar().get(car).getDurationMoves();
            }
        }
            for (int truck = 0; truck < parking.getPlacesCar().size(); truck++) {
                if (parking.getPlacesTruck().get(truck) != null && minTruck > parking.getPlacesTruck().get(truck).getDurationMoves()) {
                    minTruck = parking.getPlacesTruck().get(truck).getDurationMoves();
                }
            }
            System.out.println("The shortest   waiting time for a car: " + minCar);
            System.out.println("The shortest   waiting time for a truck: " + minTruck);
        }

    public void resetPlaces() {
        int carCounter = countingEmptyPlacesCar();
        int truckCounter = countingEmptyPlacesTruck();

        for (int car = 0; car < parking.getPlacesCar().size(); car++) {
            parking.getPlacesCar().set(car, null);
        }
        for (int truck = 0; truck < parking.getPlacesTruck().size(); truck++) {
            parking.getPlacesTruck().set(truck, null);
        }

        System.out.println(parking.getPlacesCar().size() - carCounter + " places were released in the parking for car");
        System.out.println(parking.getPlacesTruck().size() - truckCounter + " places were released in the parking for truck");
    }
}

