import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by SG0224789 on 3/1/2016.
 */
public class Validator {
    final static String DATE_FORMAT = "MM/dd/yyyy";

    boolean length(String str, int min, int max) {
        int x = str.length();
        return x >= min && x <= max;
    }

    boolean mandatory(String str) {
      return (str!=null ||str.length() > 0);
    }

    boolean numeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }

    boolean alphaNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && !Character.isAlphabetic(str.charAt(i)))
                return false;
        }
        return true;
    }

    boolean alpha(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isAlphabetic(str.charAt(i)))
                return false;
        }
        return true;
    }

    boolean date(String str) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    boolean decimal(String str) {
        String ss[] = str.split("\\.");
        switch (ss.length) {
            case 1:
                return (numeric(ss[0]) && length(ss[0], 0, 9));
            case 2:
                return (ss.length == 2 && numeric(ss[0]) && length(ss[0], 0, 9) && numeric(ss[1]) && length(ss[1], 0, 2));
            default:
                return false;
        }
    }

    boolean address(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isDigit(ch) && !Character.isAlphabetic(ch) && ch != ' ' && ch != '-' && ch != ',' && ch != '#' && ch != '&' && ch != '*')
                return false;
        }
        return true;
    }

//    public static void main(String[] args) {
//        Validator v = new Validator();
//        System.out.println(v.address("*123456789-12"));
//    }
}
