import javax.validation.constraints.Digits;

/**
 * Created by SG0224789 on 3/1/2016.
 */
public class Room {
  final String roomTypeCode;
  final String ratePlanStartDate;
  final String ratePlanEndDate;

  @Override
  public String toString() {
    return "{" +
      roomTypeCode + "," +
      ratePlanStartDate + "," +
      ratePlanEndDate + "," +
      ratePlanSingleOccupancyRate + ","+
       ratePlanDoubleOccupancyRate +
      '}';
  }

  @Digits(integer = 9, fraction = 2)
  final double ratePlanSingleOccupancyRate;
  final double ratePlanDoubleOccupancyRate;

  public Room(String roomTypeCode, String ratePlanStartDate, String ratePlanEndDate, double ratePlanSingleOccupancyRate, double ratePlanDoubleOccupancyRate) {
    this.roomTypeCode = roomTypeCode;
    this.ratePlanStartDate = ratePlanStartDate;
    this.ratePlanEndDate = ratePlanEndDate;
    this.ratePlanSingleOccupancyRate = ratePlanSingleOccupancyRate;
    this.ratePlanDoubleOccupancyRate = ratePlanDoubleOccupancyRate;
  }
}
