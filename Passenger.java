package airReservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Passenger {
	static ArrayList <Flight> list=new ArrayList ();
	static ArrayList <Reservation> reserv=new ArrayList();
	static Scanner scan =new Scanner (System.in);
	
	public static void main(String[] args) {

		list.add(new Flight (123, "pairs", 5));
		list.add(new Flight (7991, "Mumbai", 4));
		list.add(new Flight (6364, "Hyd", 2));
		while(true) {
			System.out.println("\n----AirLine Reservation System----");
			System.out.println("1. Display Available Seats");
			System.out.println("2. Book a Flight");
			System.out.println("3. View Reservation");
			System.out.println("4. Cancle Booking");
			System.out.println("5. Exit");
			System.out.println("choose an option");
			int choice = getvalidintegerInput();
			switch(choice) {
			case 1:{
				displayAvailableFlights();
				break;
			}
			case 2: {
				bookFlight();
				break;
			}
			case 3: {
				viewReservation();
				break;
			}
			case 4: {
				cancleBooking();
				break;
			}
			case 5: {
				System.out.println("Exiting The System");
				scan.close();
				return;
			}
			default : {
				System.out.println("Invalid Option, Please Try Agian");
			}
				
			
			}
		}

	}

	private static void cancleBooking() {

		System.out.println("Enter the Number of the Passenger to cancel the Flight");
		String PassengerName= scan.next();
		Reservation reservationTocancel=null;
		for(Reservation r: reserv) {
			if(r.getName().equalsIgnoreCase(PassengerName));{
				
			reservationTocancel=r;
			break;
			}
		  }
	if(reservationTocancel!=null) {
		Flight flight =reservationTocancel.getFlight();
		flight.setAvailableSeats(flight.getAvailableSeats()+1);
		reserv.remove(reservationTocancel);
		System.out.println("Reservation is Cancelled for the passenger");
	}
	else {
		System.out.println("No Reservation made yet with the name:"+ passengerName);
	}

	private static void viewReservation() {

		if(reserv.isEmpty()) {
			System.out.println("No Reservation Done yet!!!!");
		}
		else {
			System.out.println("Reservations-----");
			for(Reservation r: reserv) {
				System.out.println("PassengerName:"+r.getName());
				System.out.println("FlightNumber:"+r.getFlight().getFlightNumber());
				System.out.println("Designation:"+r.getFlight().getDesignation());
				System.out.println("-------------------");
			}
		}
		
	}

	private static void bookFlight() {
		displayAvailableFlights();
		System.out.println("Enter The Flight Number To Book a Flight");
		int flightNumber=getValidIntegerInput();
		Flight selectFlight =null;
		for(Flight flight: list) {
			if(flight.getFlightNumber()==flightNumber) {
				selectFlight=flight;
				break;
			}
		}
		if(selectFlight==null) {
			System.out.println("Invalid Flight Number. Try Agian");
			return;
		}
		if(selectFlight.getAvailableSeats()>0) {
			System.out.println("Enter The Your Name");
			String passengerName=scan.next();
			Reservation reservation = new Reservation (passengerName, selectFlight);
			reserv.add(reservation);
			selectFlight.decreaseAvailableSeats();
			System.out.println("Booking Successful!!!");
		}
		else {
			System.out.println("Sorry, Seats are Not Avaliable in the selected Flight");
		}

	}

	private static int getValidIntegerInput() {
		return 0;
	}

	private static void displayAvailableFlights() {

		System.out.println("\n----Avaliable flights----");
		for(Flight f: list) {
			System.out.println("FlightNumber:"+ f.getFlightNumber()+", Designation:"+ f.getDesignation()+",AvaliableSeats:"+f.getAvailableSeats());
		}
	}

	private static int getvalidintegerInput() {
		while(!scan.hasNextInt()) {
			System.out.println("Enter The Proper Number");
			scan.next();
		}
		return scan.nextInt();
	}
}
