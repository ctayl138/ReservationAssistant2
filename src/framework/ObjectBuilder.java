package framework;

import java.sql.ResultSet;
import java.sql.SQLException;

import DataObject.Reservation;

public class ObjectBuilder {
	private static ObjectBuilder objBuilder = null;
	private ObjectBuilder(){};
	public static ObjectBuilder getObjectBuilder(){
		if (objBuilder == null){
			objBuilder = new ObjectBuilder();
		}
		return objBuilder;
	}
	
	public Reservation createCustomer(ResultSet rs){
		Reservation r = null;
		try{
			String resName = rs.getString(DatabaseConstants.COLUMN_RES_NAME);
			String resAcc = rs.getString(DatabaseConstants.COLUMN_RES_ACC);
			int partySize = rs.getInt(DatabaseConstants.COLUMN_PARTY_SIZE);
                        String hour   = rs.getString(DatabaseConstants.COLUMN_HOUR);
                        String tableName = rs.getString(DatabaseConstants.COLUMN_TABLE_NAME);
                        String month = rs.getString(DatabaseConstants.DATABASE_MONTH);
			r = new Reservation(resName, resAcc, partySize, month, hour, tableName);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return r;
	}
}

