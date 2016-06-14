/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataObject;

/**
 *
 * @author Leliel2
 */
public class Reservation {
    
    private String resName;
    private String resAcc;
    private int partySize;
    private String month;
    private String day;
    private String hour;
    private String tableName;
    private int ID;
    
    public Reservation (){
        resName = null;
        resAcc = null;
        partySize = 0;
        month = null;
        day = null;
        hour = null;
        tableName = null;
        ID = 0;
    }

    public Reservation(String resName, int partySize, String day, String hour, String tableName, String resAcc, String month, int ID) {
        this.resName = resName;
        this.resAcc = resAcc;
        this.partySize = partySize;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.tableName = tableName;
        this.ID = ID;
    }

    public String getResName() {
        return resName;
    }

    public String getResAcc() {
        return resAcc;
    }

    public int getPartySize() {
        return partySize;
    }

    public String getMonth() {
        return month;
    }

    public String getHour() {
        return hour;
    }

    public String getTableName() {
        return tableName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public void setResAcc(String resAcc) {
        this.resAcc = resAcc;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Reservation{" + "resName=" + resName + ", resAcc=" + resAcc + ", partySize=" + partySize + ", month=" + month + ", day=" + day + ", hour=" + hour + ", tableName=" + tableName + '}';
    }
}
