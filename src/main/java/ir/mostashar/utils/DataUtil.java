package ir.mostashar.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {

    public static boolean isValideMobileNumber(String phone) {
        if (phone.length() != 11)
            return false;

        String PhoneNo_PATTERN = "(0)?([ ]|,|-|[()]){0,2}9[0-9]([ ]|,|-|[()]){0,2}(?:[0-9]([ ]|,|-|[()]){0,2}){8}";
        if (phone == null || phone.length() <= 0)
            return false;
        Pattern p = Pattern.compile(PhoneNo_PATTERN);
        Matcher m = p.matcher(phone);
        if (m.matches())
            return true;
        else
            return false;
    }

    //    public static boolean isValidUUID(String uuid){
////        String uuidPattern = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";
//        String uuidPattern = "/^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i";
//        if (uuid == null || uuid.length() <= 0)
//            return false;
//
//        Pattern p = Pattern.compile(uuidPattern);
//        Matcher m = p.matcher(uuid);
//        if (m.matches())
//            return true;
//        else
//            return false;
//    }
    public static boolean isValidUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String genarateRandomNumber() {
        long round = Math.round(Math.random() * 100000);
        return String.valueOf(round);
    }
    public static String generateOffPackCode(int count) {
        return  RandomStringUtils.randomAlphanumeric(count);
    }
    public static String generateAlphaNumericRandomUserPass(int count) {
        String s = RandomStringUtils.randomAlphanumeric(count);
        return s;
    }

    public static String generateNumericRandomUserPass(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    public static int getTimeSpent(long start, long end) {
        DateTime timeStart = new DateTime(start);
        DateTime timeEnd = new DateTime(end);
        Duration duration = new Duration(timeStart, timeEnd);
        System.out.println("Log---getTimeSpent--------------------:" + duration.getStandardMinutes());
        return Math.abs(Math.toIntExact(duration.getStandardMinutes()));
    }
}
