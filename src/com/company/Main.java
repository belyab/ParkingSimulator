package com.company;
import com.company.Car;
import com.company.Parking;
import com.company.ParkingLotManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please,enter the size of Parking ");
        Parking size = new Parking(scanner.nextInt());
        int menu;
        boolean isWork = true;
        ParkingLotManager parkingLotManager = new ParkingLotManager(size);
        System.out.println("Welcome to parking simulator!");
        while (isWork) {
            parkingLotManager.fillParkingPlaces();
            System.out.println("Please choose command");

            System.out.println("1: Finish the move and go to the next one");
            System.out.println("2: Find out how many Parking places are occupied");
            System.out.println("3: Find out how many Parking places are available");
            System.out.println("4: Find out when a Parking place is available");
            System.out.println("5: Reset the Parking of all cars");
            System.out.println("6: Quite");

            System.out.print("Enter your choice: ");
            menu = scanner.nextInt();
            System.out.println();

            switch (menu) {
                case 1:
                    parkingLotManager.minusMove();
                    break;
                case 2:
                    System.out.println("Occupied place: " + (parkingLotManager.parking.getPlaces().length - parkingLotManager.countingEmptyPlaces()));
                    break;
                case 3:
                    System.out.println("Available place: " + parkingLotManager.countingEmptyPlaces());
                    break;
                case 4:
                 System.out.println("The shortest waiting time: "+parkingLotManager.Min());
                 break;
                case 5:
                    parkingLotManager.resetPlaces();
                    System.out.println("Parking places have been reset");
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    isWork=false;
                    break;
                default:
                    System.out.println("Unknown command! Try again");
                    break;


            }
        }
    }
}


