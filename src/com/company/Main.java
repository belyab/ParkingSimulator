package com.company;
import com.company.Car;
import com.company.Parking;
import com.company.ParkingLotManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu;
        System.out.println("Please,enter the amount of Parking ");
        int sizeParking = scanner.nextInt();

        boolean isWork = true;
        while (isWork) {
            System.out.println("Welcome to parking simulator!");

            System.out.println("1: Finish the move and go to the next one");
            System.out.println("2: Find out how many Parking places are occupied");
            System.out.println("3: Find out how many Parking places are available");
            //System.out.println("4: Find out when a Parking place is available");
            System.out.println("5: Сlear the Parking of all cars");

            System.out.print("Enter your choice: ");
            menu = scanner.nextInt();
            System.out.println();

            switch (menu) {
                case 1:
                    ParkingLotManager.minusMove();
                    break;
                case 2:
                    System.out.println("Occupied place: " + (ParkingLotManager.parking.getPlaces().length - ParkingLotManager.countingEmptyPlaces()));
                    break;
                case 3:
                    System.out.println("Available place: " + ParkingLotManager.countingEmptyPlaces());
                    break;
                //case 4:
                // System.out.println("The shortest wait time"
                case 5:

            }
        }
    }
}


