package com.RentalSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
	private String carId;
	private String brand;
	private String model;
	private double basePriceAtPerDay;
	private boolean isAvailable;
	
	
	public Car(String carId, String brand, String model, double basePriceAtPerDay) {
		super();
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.basePriceAtPerDay = basePriceAtPerDay;
		this.isAvailable = true;
	}


	public String getCarId() {
		return carId;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public double calulatePrice(int rentaldays) {
		return basePriceAtPerDay*rentaldays;
	}


	public boolean isAvailable() {
		return isAvailable;
	}
	public void rent() {
		isAvailable=false;
	}
	
	public void renturnCar() {
		isAvailable =true;
	}
	
	
}

class Customer{
	
	private String customerId;
	private String name;
	private String PhoneNo;
	private String Address;
	
	public Customer(String customerId, String name, String phoneNo, String address) {
		super();
		this.customerId = customerId;
		this.name = name;
		PhoneNo = phoneNo;
		Address = address;
	}



	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	
	public String getPhoneNo() {
		return PhoneNo;
	}

	

	public String getAddress() {
		return Address;
	}


	
}

class Rental{
	
	private Car car;
	private Customer customer;
	private int days;
	
	
	
	public Car getCar() {
		return car;
	}
	public Customer getCustomer() {
		return customer;
	}
	public int getDays() {
		return days;
	}
	public Rental(Car car, Customer customer, int days) {
		super();
		this.car = car;
		this.customer = customer;
		this.days = days;
	}
	
	
	
	
	
	
}


 class CarRentalSystem{
	private List<Car> cars;
	private List<Customer> customers;
	private List<Rental> rentals;
	
	
	public CarRentalSystem() {
		cars= new ArrayList<>();
		customers= new ArrayList<>();
		rentals= new ArrayList<>();
		
	}
	
	public void addCar(Car car) {
		cars.add(car);
	}
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	public void rentCar(Car car, Customer customer ,int days) {
		if(car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car, customer,days));
		}else {
			System.out.println("Sorry ! Car Is Not Available For Rent");
		}
	}
	
	public void ReturnCar(Car car) {
		car.renturnCar();
		Rental rentalToRemove=null;
		for(Rental rental:rentals) {
			if(rental.getCar()==car) {
				rentalToRemove=rental;
				break;
			}
		}
		
		if(rentalToRemove!=null) {
			rentals.remove(rentalToRemove);
		}else {
			System.out.println("Sorry! But The Car is Not Rented Kindly Check Details");
		}
	}
	
	Scanner sc= new Scanner (System.in);
	
	public void RentCar() {
		 System.out.println("\n== Rent a Car ==\n");
		 System.out.print("Enter your name: ");
		 String customerName = sc.nextLine();
		 System.out.print("Enter your PhoneNo: ");
		 String phone= sc.nextLine();
		 System.out.println("Enter your Address");
		 String address= sc.nextLine();
		 
		 System.out.println("\nAvailable Cars:");
		 for(Car car:cars) {
			 if(car.isAvailable()) {
				 System.out.println(car.getCarId()+"-" + car.getBrand()+" " + car.getModel());
			 }
		 }
		 
		 System.out.print("\nEnter the car ID you want to rent: ");
         String carId = sc.nextLine();
         
         System.out.println("Enter the number of Days for Rental: ");
         int rentalDays = sc.nextInt();
         
         sc.nextLine();
         
         Customer newCustomer = new Customer("CUS"+ (customers.size()+1 ), customerName,phone,address);
         addCustomer(newCustomer);
         
         Car selectedCar=null;
         for(Car car: cars) {
        	 if(car.getCarId().equals(carId) && car.isAvailable()) {
        		 selectedCar = car;
        		 break;
        	 }
         }
         
         if(selectedCar!=null) {
        	 double totalPrice= selectedCar.calulatePrice(rentalDays);
        	 System.out.println("\n== Rental Information ==\n");
        	 System.out.println("Customer Id: " + newCustomer.getCustomerId());
        	 System.out.println("Customer Name: " + newCustomer.getName());
        	 System.out.println("Customer PhoneNo: " + newCustomer.getPhoneNo());
        	 System.out.println("Customer Address: " + newCustomer.getAddress());
        	 System.out.println("Car:" +selectedCar.getCarId()+" "+ selectedCar.getBrand()+" ");
        	 System.out.println("Rental Days"+ rentalDays);
        	 System.out.printf("Total Price: $%.2f%n", totalPrice);
        	 
        	 
        	 System.out.println("Do you confirm the rental? (Type 'Y' for Yes or 'N' for No)");
        	 
        	 String confirm= sc.nextLine();
        	 
        	 if(confirm.equalsIgnoreCase("Y")) {
        		 rentCar(selectedCar, newCustomer, rentalDays);
        		 System.out.println("Car Rented Successfully");
        	 }else {
        		 System.out.println("Rental canceled. Exiting...");
        	 }
         }else {
        	 System.out.println("Unable to process rental information. Please check selected car and customer details.");
         }
		 
	}
	
	
	public void ReturnCarInfo() {
		 System.out.println("\n== Return a Car ==\n");
         System.out.print("Enter the car ID you want to return: ");
         String carId = sc.nextLine();
         
         Car carToReturn =null;
         for(Car car:cars) {
        	 if(car.getCarId().equals(carId) && !car.isAvailable()) {
        		 carToReturn=car;
        		 break;
        	 }
         }
         if(carToReturn!=null) {
        	 Customer customer=null;
        	 for(Rental rental:rentals) {
        		 if(rental.getCar()==(carToReturn)) {
        			 customer=rental.getCustomer();
        			 break;
        		 }
        	 }
        	 if(customer!=null) {
        		 ReturnCar(carToReturn);
        		 System.out.println("Car returned successfully by " + customer.getName());
        	 }else {
        		 System.out.println("Car was not rented or rental information is missing.");
        	 }
         }else {
        	 System.out.println("Invalid car ID or car is not rented.");
         }
	}
	
	 
	
	
	
	
}





