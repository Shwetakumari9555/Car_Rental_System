package com.RentalSystem;

import java.util.Scanner;

public class MainMenu {
	public static void main(String[] args) {
		
		
		CarRentalSystem rentalSystem = new CarRentalSystem();

	     Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
	     Car car2 = new Car("C002", "Honda", "Accord", 70.0);
	     Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
	     rentalSystem.addCar(car1);
	     rentalSystem.addCar(car2);
	     rentalSystem.addCar(car3);

		Scanner sc= new Scanner(System.in);
		int choice;
		do {
			System.out.println("*******************************");
            System.out.println("Welcome to Car Rental Management");
            System.out.println("*******************************");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("*******************************");
            System.out.print("Enter Your Choice (1-3): ");
            
            choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
            case 1: 
            	rentalSystem.RentCar();
            	break;
            	
            case 2:
            	rentalSystem.ReturnCarInfo();
            	break;
            case 3: System.out.println("\nThank you for using the system. Goodbye!");
            	break;
            default: System.out.println("\nInvalid choice. Please enter a number between 1 and 3.\n");
            	break;
            }
            
		}while(choice!=3);
		
		sc.close();
		
		
	}

	
	
	
}

