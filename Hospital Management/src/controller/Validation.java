package controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isPhoneValid(String phoneNumber) {
        // Phone number pattern
        Pattern pattern = Pattern.compile("^\\(?\\d{3}\\)?[-. ]?\\d{3}[-. ]?\\d{4}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isDateValid(Date date) {
        Pattern pattern = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher matcher = pattern.matcher(date.toString());
        return matcher.matches();
    }

    public static void isEmpty(String tmp, String type) {
        if (tmp.equals("")) {
            System.out.println(type+" cannot be empty.");
        }
    }
}
