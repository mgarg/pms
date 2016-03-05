import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by SG0224789 on 3/4/2016.
 */
public class Worker implements Runnable{
  String file;
  Db db;
  public Worker(String file,Db db) {
    this.file = file;
    this.db = db;
  }

  @Override
  public void run() {
//    System.out.println("starting worker with: " + file);
    int updated = 0;
    Report report = new Report(file);
    Parser p = new Parser(file, db, report);
    try {
      List<Record> records = p.parse();
      RatePlanDb ratePlanDb = new RatePlanDb(db);
      for(Record r: records){
        try {
          if(ratePlanDb.transaction(r))
            updated++;
        } catch (SQLException | ParseException e) {
          e.printStackTrace();
        }
      }
      report.setUpdated(updated);
      report.setProcessed(report.getUpdated()+report.getRejected());
      System.out.println(report);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
