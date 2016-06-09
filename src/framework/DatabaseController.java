package framework;
import java.sql.*;
import java.util.ArrayList;

import DataObject.Reservation;

public class DatabaseController implements DatabaseConstants{
	/** VARIABLE DECLARATIONS *************************/
	private Connection dbConnection = null;
	private String userName = null;
	private String password = null;
	private String databaseName = null;
	private String databaseServerURL = null;
	private String driverClass = null;
	private String portNumber = null;
	private static DatabaseController DBController = null;
	private ObjectBuilder objectBuilder = null;
	private DatabaseMetaData dbMetaData = null;

	/** GETTERS AND SETTERS *************************/
	private ObjectBuilder  getObjectBuilder(){
		if (objectBuilder == null){
			objectBuilder = ObjectBuilder.getObjectBuilder(); 
		}
		return objectBuilder;
	}
	private Connection getDbConnection() {
		if (dbConnection == null){
			dbConnection = createDatabaseConnection();
		}
		return dbConnection;
	}
	private String getUserName() {
		if (userName == null){
			userName = "sa";
		}
		return userName;
	}
	private String getPassword() {
		if (password == null){
			password = "itcs3160";
		}
		return password;
	}
	public String getDatabaseName() {
		if (databaseName == null){
			databaseName = "AutoDealer";
		}
		return databaseName;
	}
	private String getDatabaseServerURL() {
		if (databaseServerURL == null){
			databaseServerURL = "localhost";
		}
		return databaseServerURL;
	}
	private String getDriverClass() {
		if (driverClass == null){
			driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		}
		return driverClass;
	}
	private String getPortNumber() {
		if (portNumber == null){
			portNumber = "57491";
		}
		return portNumber;
	}
	public void resetDbConnection() {
		this.dbConnection = null;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public void setDatabaseServerURL(String databaseServerURL) {
		this.databaseServerURL = databaseServerURL;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public static DatabaseController getDBController() {
		if (DBController == null){
			DBController = new DatabaseController();
		}
		return DBController;
	}
	private DatabaseMetaData getDBMetaData(){
		try {
			dbMetaData = getDbConnection().getMetaData();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return dbMetaData;
	}
	
	/** CONSTRUCTORS *************************/
	private DatabaseController(){
		/**
		 * This constructor is defined as private so no one 
		 * is able to create a new instance
		 * of this class.  The static method getDBController() 
		 * should be used instead.  This
		 * will ensure all classes share the same instance of 
		 * the database controller.
		 */
	}

	/** DATABASE CONNECTION CODE **********************/
	private Connection createDatabaseConnection() {
		Connection conn = null;
		String connectString = "jdbc:sqlserver://reservations.database.windows.net:1433;database=Reservations;user=opprobrious@reservations;password=13ANGels!!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
		//First, we make sure the Driver exists
		try {
			Class.forName(getDriverClass());
		} catch (java.lang.ClassNotFoundException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("No driver class found for: ");
			buf.append(getDriverClass());
			System.out.println(buf.toString());
			System.exit(0);
		}
		//Driver class exists, now try to open the connection
		try {
			conn = DriverManager.getConnection(connectString);
		} catch (SQLException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("There was a problem with the following connection string: ");
			buf.append(connectString);
			buf.append("\n\nHere is the exceptio:\n");
			buf.append(e.toString());
			System.out.println(buf.toString());
			System.exit(0);
		}
		return conn;
	}

	/** UTILITIES   ******************************************/
	public ArrayList<String> getDatabaseNames(){
		/**
		 * This method gets a list of all the databases on the server owned by dbo
		 */
	    ArrayList<String> databases = new ArrayList<String>();
		ResultSet res;
		try {
			res = getDBMetaData().getCatalogs();
		    while (res.next()) {
		    	databases.add(res.getString("TABLE_CAT"));
			}
			res.close();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return databases;
}
	public ArrayList<String> getTableNames(){
		/**
		 * This method gets a list of all tables in the currently active database
		 */
		ArrayList<String> tables = new ArrayList<String>();
		DatabaseMetaData dbmd = getDBMetaData();
		if (dbmd != null){
			try {
				ResultSet rs = dbmd.getTables(null, null, "%", null);
				while(rs.next()){
					if (rs.getString(2).equals("dbo")){
						tables.add(rs.getString(3));
					}
				}
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		return tables;
	}
	public void printResultSet(ResultSet rs){
		/**
		 * This method just prints the contents of a ResultSet
		 * to the output console.
		 */
		try{
			boolean columnHeadingsPrinted = false;
			while (rs.next()){
				if(! columnHeadingsPrinted){
					//print the table's column headings
					for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
						System.out.print(rs.getMetaData().getColumnLabel(i));
						System.out.print(":\t");
					}
					System.out.println();
					columnHeadingsPrinted = true;
				}
				//now print a row of data	
				for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
					System.out.print(rs.getString(i));
					System.out.print("\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void printQueryResults(String query) {
		/**
		 * This method attempts to execute the query string passed in
		 * and print out the results to the console.
		 */
		try {
			Statement s = getDbConnection().createStatement();
			ResultSet rs = s.executeQuery(query);
			printResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DatabaseController dbc = DatabaseController.getDBController();
		ArrayList<String> dbNames = dbc.getDatabaseNames();
		StringBuffer buf = new StringBuffer();
		buf.append("The database server has the following databases:\n");
		for (String dbName : dbNames){
			buf.append(dbName);
			buf.append("\n");
		}
		System.out.println(buf.toString());
		ArrayList<String> tables = dbc.getTableNames();
		buf = new StringBuffer();
		buf.append("Database ");
		buf.append(dbc.getDatabaseName());
		buf.append(" has the following tables:\n");
		for (String tableName : tables){
			buf.append(tableName);
			buf.append("\n");
		}
		System.out.println(buf.toString());
		
	}
	
	/** DATA ACCESS CODE **/
	
	
	public ResultSet executeQuery(String query){
		 //Executes the query passed in on the active database and returns a ResultSet
		Statement s = null;
		ResultSet rs = null;
		try {
			s = getDbConnection().createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet executeStoredProcedure(String procName, ArrayList<NameValuePair> nvpList) {
		/**
		 * Calls the stored procedure passed in, sending it
		 * the parameters passed in.  Returns the resulting ResultSet 
		 */
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();
		buf.append("{ call ");
		buf.append(procName);
		buf.append("(");
		for (int i=0; i<nvpList.size(); i++){
			if (i > 0) buf.append(", ");
			buf.append("?");
		}
		buf.append(") }");
		String sql = buf.toString();
		try {
			CallableStatement cs = getDbConnection().prepareCall(sql);
			for (int i=0; i<nvpList.size(); i++){
				cs.setString( nvpList.get(i).getName(), nvpList.get(i).getValue());
			}
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public void executeCUDStoredProcedure(String procName, ArrayList<NameValuePair> nvpList) {
		/**
		 * Calls the stored procedure passed in, sending it
		 * the parameters passed in.  Should only be used for stored procs that create, update,
		 * or delete records
		 */
		StringBuffer buf = new StringBuffer();
		buf.append("{ call ");
		buf.append(procName);
		buf.append("(");
		for (int i=0; i<nvpList.size(); i++){
			if (i > 0) buf.append(", ");
			buf.append("?");
		}
		buf.append(") }");
		String sql = buf.toString();
		try {
			CallableStatement cs = getDbConnection().prepareCall(sql);
			for (int i=0; i<nvpList.size(); i++){
				cs.setString( nvpList.get(i).getName(), nvpList.get(i).getValue());
			}
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateReservation(Reservation r){
		ArrayList<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		nvpList.add(new NameValuePair(COLUMN_RES_NAME, r.getResName()));
		nvpList.add(new NameValuePair(COLUMN_RES_ACC, r.getResAcc()));
		nvpList.add(new NameValuePair(COLUMN_PARTY_SIZE, Integer.toString(r.getPartySize())));
                nvpList.add(new NameValuePair(DATABASE_MONTH, r.getMonth()));
		nvpList.add(new NameValuePair(COLUMN_HOUR, r.getHour()));
                nvpList.add(new NameValuePair(COLUMN_TABLE_NAME, r.getTableName()));
		executeCUDStoredProcedure(SP_UPDATE_RESERVATION, nvpList);
	}

	public void makeReservation(Reservation r){
		ArrayList<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		nvpList.add(new NameValuePair(COLUMN_RES_NAME, r.getResName()));
		nvpList.add(new NameValuePair(COLUMN_RES_ACC, r.getResAcc()));
		nvpList.add(new NameValuePair(COLUMN_PARTY_SIZE, Integer.toString(r.getPartySize())));
                nvpList.add(new NameValuePair(DATABASE_MONTH, r.getMonth()));
		nvpList.add(new NameValuePair(COLUMN_HOUR, r.getHour()));
                nvpList.add(new NameValuePair(COLUMN_TABLE_NAME, r.getTableName()));
		executeCUDStoredProcedure(SP_MAKE_RESERVATION, nvpList);
	}

	public void deleteReservation(Reservation r){
                ArrayList<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		nvpList.add(new NameValuePair(COLUMN_RES_NAME, r.getResName()));
		nvpList.add(new NameValuePair(COLUMN_RES_ACC, r.getResAcc()));
		nvpList.add(new NameValuePair(COLUMN_PARTY_SIZE, Integer.toString(r.getPartySize())));
                nvpList.add(new NameValuePair(DATABASE_MONTH, r.getMonth()));
		nvpList.add(new NameValuePair(COLUMN_HOUR, r.getHour()));
                nvpList.add(new NameValuePair(COLUMN_TABLE_NAME, r.getTableName()));
		executeCUDStoredProcedure(SP_DELETE_RESERVATION, nvpList);
	}
        
        public void checkReservation(Reservation r){
                ArrayList<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		nvpList.add(new NameValuePair(COLUMN_RES_NAME, r.getResName()));
		nvpList.add(new NameValuePair(COLUMN_RES_ACC, r.getResAcc()));
		nvpList.add(new NameValuePair(COLUMN_PARTY_SIZE, Integer.toString(r.getPartySize())));
                nvpList.add(new NameValuePair(DATABASE_MONTH, r.getMonth()));
		nvpList.add(new NameValuePair(COLUMN_HOUR, r.getHour()));
                nvpList.add(new NameValuePair(COLUMN_TABLE_NAME, r.getTableName()));
		executeCUDStoredProcedure(SP_DELETE_RESERVATION, nvpList);
	}

}

