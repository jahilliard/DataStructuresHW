import java.util.ArrayList;


public class FlightsTester {

	public static void main(String[] args) {
		FlightsTester tester = new FlightsTester();
		Flights f1 = new Flights();
		Flights f2 = new Flights();
		
		if (tester.flightObjectsAreEqual( f1, f2 )) {
			System.out.println("EQUAL");
		} else {
			System.out.println("NOT EQUAL");
		}
		
		Flight temp = f2.flights.get(0);
		String id = temp.getIdentifier();
		String arrPort = temp.getArrivalAirport();
		String arrTime = temp.getArrivalTime();
		String deptPort = temp.getDepartureAirport();
		String deptTime = temp.getDepartureTime();
		//int dist = temp.getDistance();
		
		Flight tempFlight = new Flight(id, deptPort, deptTime, arrPort, arrTime, 12);
		f2.flights.set(0, tempFlight);
		
		if (tester.flightObjectsAreEqual( f1, f2 )) {
			System.out.println("EQUAL");
		} else {
			System.out.println("NOT EQUAL");
		}
		
		//tester.printFlights(f1.flights);
	}
	
	private boolean flightObjectsAreEqual(Flights flights1, Flights flights2) {
		//flights arrays equal length
		if ( flights1.flights.size() != flights1.flights.size() ) {
			return false;
		}
		
		//every flight object in array equal
		for (int x = 0; x < flights1.flights.size(); x++) {
			Flight f1 = flights1.flights.get(x);
			Flight f2 = flights2.flights.get(x);
			
			String id1 = f1.getIdentifier();
			String arrPort1 = f1.getArrivalAirport();
			String arrTime1 = f1.getArrivalTime();
			String deptPort1 = f1.getDepartureAirport();
			String deptTime1 = f1.getDepartureTime();
			int dist1 = f1.getDistance();

			String id2 = f2.getIdentifier();
			String arrPort2 = f2.getArrivalAirport();
			String arrTime2 = f2.getArrivalTime();
			String deptPort2 = f2.getDepartureAirport();
			String deptTime2 = f2.getDepartureTime();
			int dist2 = f2.getDistance();
			
			if( !id1.equals(id1) || 
					!arrPort1.equals(arrPort2) || 	
					!arrTime1.equals(arrTime2) || 		
					!deptPort1.equals(deptPort2) || 		
					!deptTime1.equals(deptTime2) || 		
					dist1 != dist2
					) {
				return false;
			}
		}
		return true;
	}

	//DELETE BEFORE SUBMITTING
	public String printFlights(ArrayList<Flight> flights) {
		ArrayList<String> output = new ArrayList<String>();
		for (Flight aFlight : flights){
			output.add(aFlight.getIdentifier());
		}
		return output.toString();
	}
}
