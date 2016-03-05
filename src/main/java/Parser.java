import javax.validation.*;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Double.parseDouble;

public class Parser {
  static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

  final String filename;
  final Validator validator;
  final Db db;
  Report report;

  public Parser(String filename, Db db, Report report) {
    this.filename = filename;
    this.db = db;
    this.report = report;
    validator = factory.getValidator();
  }

  List<Record> parse() throws IOException {
    List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
    List<Record> recs = new ArrayList<>();
    report.setTotal(lines.size());
    for (String l : lines) {
      String ss[] = l.split("; ");
      Room r1 = new Room(ss[6], ss[7], ss[8], parseDouble(ss[9]), parseDouble(ss[10]));
      Room r2 = new Room(ss[11], ss[12], ss[13], parseDouble(ss[14]), parseDouble(ss[15]));
      Record r = new Record(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5], r1, r2);
      if (valid(r)) {
        recs.add(r);
      }
    }

    report.setRejected(lines.size() - recs.size());
    return recs;
  }

  boolean valid(Record rec) {
    try {
      Set<ConstraintViolation<Record>> validate = validator.validate(rec);
      int n = validate.size();
      if (n > 0)
        System.out.println("size issue");
      boolean valid = ((n == 0) && new PropertyDb(db).exists(Integer.parseInt(rec.propertyId))
              && new ChainDb(db).exists(rec.chainCode)
              && new StateDb(db).exists(rec.stateCode)
              && new CityDb(db).exists(rec.cityName)
              && new CountryDb(db).exists(rec.countryCode)
      );
      return valid;
    } catch (Exception e) {
      return false;
    }
  }
}