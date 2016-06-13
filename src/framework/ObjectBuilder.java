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
	
	public Reservation createReservation(ResultSet rs){
                Reservation r = null;
		try{
			String resName = rs.getString(DatabaseConstants.COLUMN_RES_NAME);
			int partySize = rs.getInt(DatabaseConstants.COLUMN_PARTY_SIZE);
                        String day = rs.getString(DatabaseConstants.COLUMN_DAY);
			String hour = rs.getString(DatabaseConstants.COLUMN_HOUR);
			String tableName = rs.getString(DatabaseConstants.COLUMN_TABLE_NAME);
                        String resAcc = rs.getString(DatabaseConstants.COLUMN_RES_ACC);
                        String month = rs.getString(DatabaseConstants.COLUMN_MONTH);
			r = new Reservation(resName,partySize,month,day,hour,tableName,resAcc);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return r;
        }
}

