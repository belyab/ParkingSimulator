package com.company;
import com.company.Car;
import com.company.Parking;
import com.company.ParkingLotManager;

import java.util.Scanner;

public class Main {
    public static void  main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please,enter the size of Parking for cars");
        int parkingSizeCar = scanner.nextInt();
        System.out.println("Please,enter the size of Parking for truck");
        int parkingSizeTruck = scanner.nextInt();
        boolean isWork = true;
        Parking parking=new Parking(parkingSizeCar,parkingSizeTruck);
        ParkingLotManager parkingLotManager = new ParkingLotManager(parking);
        parkingLotManager.fillParkingPlaces();
        while (isWork) {

            System.out.println("Please choose command");

            System.out.println("1: Finish the move and go to the next one");
            System.out.println("2: Find out how many Parking places are occupied");
            System.out.println("3: Find out how many Parking places are available");
            System.out.println("4: Find out when a Parking place is available");
            System.out.println("5: Reset the Parking of all cars");
            System.out.println("6:Info");
            System.out.println("7: Quite");

            System.out.print("Enter your choice: ");
            int menu = scanner.nextInt();
            System.out.println();

            switch (menu) {
                case 1 -> parkingLotManager.minusMove();
                case 2 -> {
                    System.out.println("Occupied place in car parking: " + (parkingLotManager.parking.getPlacesCar().size() - parkingLotManager.countingEmptyPlacesCar()));
                    System.out.println("Occupied place in truck parking: " + (parkingLotManager.parking.getPlacesTruck().size() - parkingLotManager.countingEmptyPlacesTruck()));
                }
                case 3 -> {
                    System.out.println("Available place in car parking : " + parkingLotManager.countingEmptyPlacesCar());
                    System.out.println("Available place in truck parking: " + parkingLotManager.countingEmptyPlacesTruck());
                }
                case 4 -> parkingLotManager.Min();
                case 5 -> {
                    parkingLotManager.resetPlaces();
                    System.out.println("Parking places have been reset");
                }
                case 6 -> parkingLotManager.info();
                case 7 -> {
                    System.out.println("Goodbye!");
                    isWork = false;
                }
                default -> System.out.println("Unknown command! Try again");
            }
        }
    }
}


