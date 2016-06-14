package framework;
import java.sql.*;

public class Conn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url="jdbc:sqlserver://reservations.database.windows.net:1433;database=Reservation;user=********@reservations;password=********;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
				try
				{
				//Loading the driver...
				Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
				System.out.println("Found the driver class!");
				}
				catch( java.lang.ClassNotFoundException e )
				{
					System.out.println(e);
				}

				try
				{
				 Connection m_Conn = DriverManager.getConnection(url);
				 if (m_Conn !=null){
					 System.out.println("Successfully connected to the database!");
				 }
				}catch( java.sql.SQLException e ){
					System.out.println(e);
				}
		}

}
