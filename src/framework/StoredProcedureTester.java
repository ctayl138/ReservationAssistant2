package framework;

import DataObject.Reservation;

public class StoredProcedureTester {

	public void initialize(){
		/**
		 * This method just tests a series of stored procedures
		 * provided by the DatabaseController
		 */
		DatabaseController dbc = DatabaseController.getDBController();
		
		
		System.out.println("Testing makeReservation() stored procedure");
		Reservation r1 = new Reservation("Taylor", 8, "9", "8AM", "Outside 1", "Baby Chair", "June"); 
		dbc.makeReservation(r1);
                
                System.out.println("Testing checkReservation() stored procedure"); 
		Reservation res = dbc.checkReservation(r1);
                System.out.println(res);
                
                System.out.println("Testing updateReservation() stored procedure");
		Reservation r2 = new Reservation("Jones", 10, "5", "11AM", "bar 1", "Extra seat", "June");
                if(dbc.checkReservation(r1) != null){
                    dbc.deleteReservation(r1);
                    dbc.makeReservation(r2);
                }else{
                    dbc.makeReservation(r2);
                }
		res = dbc.checkReservation(r2);
                System.out.println(res);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StoredProcedureTester spt = new StoredProcedureTester();
		spt.initialize();
	}

}
