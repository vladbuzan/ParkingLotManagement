package model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern bucharestPattern = Pattern.compile(" \\d{3} [A-Z]{3}");
    private static final Pattern countyPattern = Pattern.compile(" \\d{2} [A-Z]{3}");

    private static final String[] county = {"AB", "AG", "AR", "BC", "BH", "BN", "BR",
            "BT", "BV", "BZ", "CJ", "CL", "CS", "CT", "CV", //county codes except for "B"
            "DB", "DJ", "GJ", "GL", "GR", "HD", "HR", "IF",
            "IL", "IS", "MH", "MM", "MS", "NT", "OT", "PH",
            "SB", "SJ", "SM", "SV", "TL", "TM", "TR", "VL",
            "VN", "VS"};

    public static String calendarAsString(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH) + " " +
                (calendar.get(Calendar.MONTH) + 1) + " " +
                calendar.get(Calendar.YEAR) + " " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE);
    }

    public static String calendarAsString_seconds(Calendar calendar) {
        return calendarAsString(calendar) + ":" + calendar.get(Calendar.SECOND);
    }

    public static boolean isValidPlate(String plate) {
        Matcher matcher;
        if(Arrays.asList(county).contains(plate.substring(0,2))) {
            matcher = countyPattern.matcher(plate.substring(2));
            return matcher.matches();
        }
        if(plate.charAt(0) == 'B') {
            matcher = bucharestPattern.matcher(plate.substring(1));
            return matcher.matches();
        }
        return false;
    }


}
