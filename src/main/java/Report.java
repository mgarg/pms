/**
 * Created by SG0224789 on 3/4/2016.
 */
public class Report {
  String fileName;

  public Report(String fileName) {
    this.fileName = fileName;
  }

  int total,
      processed,
      updated,
      rejected;

  public void setUpdated(int updated) {
    this.updated = updated;
  }
  public void setProcessed(int processed) {
    this.processed = processed;
  }
  public void setTotal(int total) {
    this.total = total;
  }
  public void setRejected(int rejected) {
    this.rejected = rejected;
  }

  public int getUpdated() {
    return updated;
  }

  public int getRejected() {
    return rejected;
  }

  @Override
  public String toString() {
    return
      fileName + ":" +
      "Total " + total + "lines : "+
      processed + "Lines Processed : "+
      updated + " successfully inserted/updated to DB: "+
      rejected + "rejected";

  }
}
