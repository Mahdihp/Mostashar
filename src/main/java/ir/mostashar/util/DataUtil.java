package ir.mostashar.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {

    public static boolean isValidePhoneNumber(String phone) {
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
}