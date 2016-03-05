import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by SG0224789 on 3/2/2016.
 */

public class RatePlanDb {
  final Db db;

  public RatePlanDb(Db db) {
    this.db = db;
  }

  Date getDate(String d) throws ParseException {
    SimpleDateFormat sd1 = new SimpleDateFormat("MM/dd/yyyy");
    java.util.Date dsd1 = sd1.parse(d);
    return new Date(dsd1.getTime());
  }
  // returns the id of the rateplans with same dates
  int existsSame(String startDate1,String endDate1,String startDate2,String endDate2) throws SQLException, ParseException {
    Connection con = db.connection();
    PreparedStatement stmt = con.prepareStatement("select id from rateplan where rp1startdate = ? and rp1enddate = ? and rp2startdate = ? and rp2enddate = ?");
    stmt.setDate(1,getDate(startDate1));
    stmt.setDate(2,getDate(endDate1));
    stmt.setDate(3,getDate(startDate2));
    stmt.setDate(4,getDate(endDate2));

    ResultSet rs = stmt.executeQuery();
    if (rs.next())
      return rs.getInt(1);
    return -1;

  }
  int existsOverlap(String startDate1,String endDate1,String startDate2,String endDate2) throws SQLException, ParseException {
    Connection con = db.connection();
    PreparedStatement stmt = con.prepareStatement("SELECT * FROM rateplan where rp1startdate>? or rp1startdate<? or rp1enddate>? or rp1enddate<?\n" +
            "or rp2startdate>? or rp2startdate<? or rp2enddate>? or rp2enddate<?");
    Date rmsStartDate1 = getDate(startDate1);
    Date rmsEndDate1 = getDate(endDate1);
    Date rmsStartDate2 = getDate(startDate2);
    Date rmsEndDate2 = getDate(endDate2);

    stmt.setDate(1,rmsStartDate1);
    stmt.setDate(2,rmsStartDate1);
    stmt.setDate(3,rmsEndDate1);
    stmt.setDate(4,rmsEndDate1);
    stmt.setDate(5,rmsStartDate2);
    stmt.setDate(6,rmsStartDate2);
    stmt.setDate(7,rmsEndDate2);
    stmt.setDate(8,rmsEndDate2);

    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      Date newStartDate1, newEndDate1, newStartDate2, newEndDate2;
      newStartDate1 = (rs.getDate(4).before(rmsStartDate1))?rs.getDate(4):rmsStartDate1;
      newEndDate1 = (rs.getDate(5).after(rmsEndDate1))?rs.getDate(5):rmsEndDate1;
      newStartDate2 = (rs.getDate(9).before(rmsStartDate2))?rs.getDate(9):rmsStartDate2;
      newEndDate2 = (rs.getDate(10).after(rmsStartDate2))?rs.getDate(10):rmsStartDate2;
      PreparedStatement stmt1 = con.prepareStatement("update rateplan set rp1startdate=?, rp1enddate=?, rp2startdate=?, rp2enddate =? where id = ?");
      stmt1.setDate(1,newStartDate1);
      stmt1.setDate(2,newEndDate1);
      stmt1.setDate(3,newStartDate2);
      stmt1.setDate(4,newEndDate2);
      stmt1.setInt(5,rs.getInt(1));
      return stmt1.executeUpdate();
    }
    return -1;
  }

  int updateRate(int id,double rp1single,double rp1double,double rp2single, double rp2double) throws SQLException, ParseException {
    Connection con = db.connection();
    PreparedStatement stmt1 = con.prepareStatement("update rateplan set rp1single = ? , rp1double = ?, rp2single = ?, rp2double = ? where id = ?");
    stmt1.setDouble(1,rp1single);
    stmt1.setDouble(2,rp1double);
    stmt1.setDouble(3,rp2single);
    stmt1.setDouble(4,rp2double);

    stmt1.setInt(5,id);
    stmt1.executeUpdate();
    int i = stmt1.executeUpdate();
    return i;
    //return rs.next();
  }
  boolean transaction(Record rec) throws SQLException, ParseException {
    int id1 = existsOverlap(rec.one.ratePlanStartDate,rec.one.ratePlanEndDate,rec.two.ratePlanStartDate,rec.two.ratePlanEndDate);
    if(id1!=-1){
      updateRate(id1, rec.one.ratePlanSingleOccupancyRate, rec.one.ratePlanDoubleOccupancyRate, rec.two.ratePlanSingleOccupancyRate, rec.two.ratePlanDoubleOccupancyRate);
      return true;
    }
    int id2 = existsSame(rec.one.ratePlanStartDate,rec.one.ratePlanEndDate,rec.two.ratePlanStartDate,rec.two.ratePlanEndDate);
    if(id2!=-1) {
      updateRate(id2, rec.one.ratePlanSingleOccupancyRate, rec.one.ratePlanDoubleOccupancyRate, rec.two.ratePlanSingleOccupancyRate, rec.two.ratePlanDoubleOccupancyRate);
      return true;
    }
    return false;

  }
}
