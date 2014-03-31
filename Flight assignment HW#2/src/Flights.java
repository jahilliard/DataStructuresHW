/**
 * @author Justin Hilliard <Jhilliar>
 * @section A
 */

// YOU MAY NOT IMPORT ANY ADDITIONAL
// CLASSES OR PACKAGES

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Flights {
	
	public ArrayList<Flight> flights = new ArrayList<Flight>();
	
	// DO NOT CHANGE THIS METHOD
	public Flights() {
		loadFlights();
	}
	
	/**
	 * Loads the flight data using the given specification. You must use the specification
	 * provided in the write-up in order to receive full credit. You may need to create
	 * multiple copies of the `flights.txt` file. You may *not* modify the `flights.txt` file.
	 */
	private void loadFlights() {
		String filename = "flights.txt";
		
		try {
			Scanner sc = new Scanner(new File(filename));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				// you need to look up the String.split method
				// in the Java API and understand how it works!
				String[] splitLine = line.split("[|]");	
				Flight F = new Flight(splitLine[0],splitLine[1], splitLine[2],
						splitLine[3],splitLine[4], new Integer(splitLine[5]));
				flights.add(F);
				
				// write your code here for loadFlights
			}
		} catch(FileNotFoundException fnfe) {
			System.out.println("I could not load the file. Please make sure the file is in the correct directory.");
			System.exit(0);
		} catch(Exception e) {
			System.out.println("There was an error while creating the flights:");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * Returns the flights ArrayList
	 * @return ArrayList<Flight>
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	
	/**
	 * Finds and returns all Flights that depart from the given ICAO airport code. This method
	 * must run in O(n) time. For example (your data may be different):
	 *   Flights f = new Flights();
	 *   f.getFlightsDepartingFrom("PIT"); // could return [DL8273, WN2834, WN5243]
	 * @param airport - the ICAO airport code to search for
	 * @return - an ArrayList of Flight objects that depart from the given ICAO code
	 */
	public ArrayList<Flight> getFlightsDepartingFrom(String airport) {
		// write your code for getFlightsDepartingFrom here
		ArrayList<Flight> flightID = new ArrayList<Flight>();
		for (int search=0; search<flights.size();search++){
			Flight f = flights.get(search);
			if (f.getDepartureAirport().equals(airport)){
				flightID.add(f);
			}
		}
		return flightID; 
	}
	
	/**
	 * Finds and returns all Flights that arrive at the given ICAO airport code. This method
	 * must run in O(n) time. For example (your data may be different):
	 *   Flights f = new Flights();
	 *   f.getFlightsArriveAt("LAS"); // could return [WN2834, WN5243]
	 * @param airport - the ICAO airport code to search for
	 * @return - an ArrayList of Flight objects that arrive at the given ICAO code
	 */
	public ArrayList<Flight> getFlightsArrivingAt(String airport) {
		ArrayList<Flight> flightID = new ArrayList<Flight>();
		for (int search=0; search<flights.size();search++){
			Flight f = flights.get(search);
			if (f.getArrivalAirport().equals(airport)){
				flightID.add(f);
			}
		}
		return flightID;
	}
	
	/**
	 * Finds and returns an ArrayList<Flight> representing all direct Flights starting in
	 * startAirport and ending at endAirport.
	 * are grouped by their unique identifier. For example:
	 *   Flights f = new Flights();
	 *   f.getFlightsAlongPath("PIT", "LAS"); // could return [WN2834, WN5243]
	 * @param departureAirport - the ICAO code of the departure airport
	 * @param arrivalAirport - the ICAO code of the arrival airport
	 * @return - an ArrayList<Flight> that are non-stop
	 */
	public ArrayList<Flight> getNonStopFlights(String departureAirport, String arrivalAirport) {
		ArrayList<ArrayList<Flight>> fltarrangecopy = arrangedFlights();
		
		ArrayList<Flight> nohops = new ArrayList<Flight>();
		for (int search=0; search<fltarrangecopy.size();search++){
			ArrayList<Flight> Bee = fltarrangecopy.get(search);
			Flight f = Bee.get(0);
			Flight f1 = Bee.get(Bee.size()-1); 
			if (Bee.size()==2 && f1.getArrivalAirport().equals(arrivalAirport) && 
					f.getDepartureAirport().equals(departureAirport)){
				nohops.add(f);
			}
		}
		return nohops;
	}
		
	/**
	 * Cancels all flights that stop in the given airport. You must deepCopy the global flights
	 * ArrayList and then remove any flights that ever exist in at a given airport.
	 * 
	 * Recall from lecture that there is optional bonus in this method
	 * @param airport - the airport that has canceled all flights
	 * @return - an ArrayList<Flight> that are still able to fly
	 */
	public ArrayList<Flight> cancelFlights(String airport) {
		ArrayList<Flight> flightID = new ArrayList<Flight>();
		ArrayList<Flight> flightscopy = this.deepCopy(flights);
		for (int search=flights.size(); search>=0;search--){
			Flight f = flightscopy.get(search); 
			if (f.getArrivalAirport().equals(airport) && f.getDepartureAirport().equals(airport)){
				flightID.add(f);
				flightscopy.remove(search);
			}
		}
		return flightID;
	}
	
	/**
	 * Calculates the total distance traveled for all the given flightIdentifiers
	 * @param flightIdentifiers - an ArrayList<String> representing the flightIdentifiers to search for
	 * @return - the total distance (in miles) traveled
	 */
	public int getTotalDistance(ArrayList<String> flightIdentifiers) {
		int total=0;
		
		for (int search=0; search<flightIdentifiers.size();search++){
				String temp1=flightIdentifiers.get(search);
				for(int search1=0; search1<flights.size();search1++){
					Flight f=flights.get(search1);
					String temp2 = f.getIdentifier();
				//this is only going to return the first flight ID that matches in flight ids
					if(temp1.equals(temp2)){
						int x = f.getDistance();
						total+=x;
					}	
				}
		}
		return total;
	}
	
	/**
	 * Arranges the flights in an ArrayList<ArrayList<Flight>>. Each ArrayList<ArrayList> represents
	 * a unique flight path. Each ArrayList<ArrayList<Flight>> represents a collection of the flight
	 * path, in the same order as the input file. For a more detailed explanation, see the write-up.
	 * @return
	 */
	public ArrayList<ArrayList<Flight>> arrangedFlights() {
		ArrayList<ArrayList<Flight>> foo = new ArrayList<ArrayList<Flight>>();
		
		ArrayList<Flight> flights1 = this.deepCopy(flights);
		
		for(int counter=0; counter<flights1.size();counter++){
			Flight f = flights1.get(counter);
			String flightID = f.getIdentifier();
			
			for (int counter1=0; counter1<foo.size();counter1++){
				Flight f1 =flights1.get(counter1);
				String flightID1 = f1.getIdentifier();
				if (flightID.equals(flightID1)){
					foo.get(counter1).add(f1);
				}
			}
			
			ArrayList<Flight> tmp = new ArrayList<Flight>();
			tmp.add(f);
			foo.add(tmp);
		}
		return foo;
	}
	
	/**
	 * Returns an ArrayList<Flight> corresponding to all flights that start at departureAirport
	 * and end at arrivalArrival airport and have at least 1 stop in between. Return the FIRST 
	 * flight object along the full flight path.
	 * 
	 * HINT: You may find it helpful to use the arrangedFlights() method you just wrote.
	 * @param departureAirport - the ICAO code of the departure airport
	 * @param arrivalAirport - the ICAO code of the arrival airport
	 * @return - an organized list of all multi-hop flights
	 */
	public ArrayList<Flight> getMultiHopFlights(String departureAirport, String arrivalAirport) {
		ArrayList<ArrayList<Flight>> fltarrangecopy= arrangedFlights();
		ArrayList<Flight> multihops = new ArrayList<Flight>();
		for (int search=0; search<fltarrangecopy.size();search++){
			ArrayList<Flight> Bee = fltarrangecopy.get(search);
			if(Bee.size()>1){
				Flight f = Bee.get(0);
				Flight f1 = Bee.get(Bee.size()-1);
				//dont know how to make sure they are not nonstops 
				if (f1.getArrivalAirport().equals(arrivalAirport) && 
						f.getDepartureAirport().equals(departureAirport)){
					multihops.add(f);
				}
			}
		}
		return multihops;
	}

	
	/**
	 * deepCopies an ArrayList<Flight>
	 * @param theFlights
	 * @return - a new ArrayList<Flight> containing the same Flights in the same order as the given
	 * ArrayList<Flight>
	 */
	private ArrayList<Flight> deepCopy(ArrayList<Flight> theFlights) {
		ArrayList<Flight> deepcopy = new ArrayList<Flight>();
		for (int add=0; add<theFlights.size();add++){
			deepcopy.add(theFlights.get(add));
		}
		return deepcopy;
	}
}