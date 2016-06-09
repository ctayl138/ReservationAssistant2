package framework;

import DataObject.Reservation;

public class StoredProcedureTester {

	public void initialize(){
		/**
		 * This method just tests a series of stored procedures
		 * provided by the DatabaseController
		 */
		DatabaseController dbc = DatabaseController.getDBController();
		
		//Test the getCarByID() method
	//	System.out.println("Testing getCarByID() stored procedure");
		//dbc.printResultSet(dbc.getCarByID("EQV536NJH730J"));
	//	System.out.println("");
		
		//Test the getCarsByPriceRange(lowVal, highVal method
		//System.out.println("Testing getCarsByPriceRange(lowVal, highVal) stored procedure");
		//dbc.printResultSet(dbc.getCarsByPriceRange(9000, 11000));
		//System.out.println("");
		//Test the addCustomer() method
		System.out.println("Testing addCustomer() stored procedure");
		Reservation r1 = new Reservation("Taylor", "Baby chair", 8, "June", "9", "8AM", "Outside 1"); 
		dbc.makeReservation(r1);

		//Test the getCustomerByID() method
		

		//Test the updateCustomer() method
		

		//Test the deleteCustomer() method
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StoredProcedureTester spt = new StoredProcedureTester();
		spt.initialize();
	}

}
