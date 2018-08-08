package com.vectree.billing.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vectree.billing.utils.Constants.MAX_LENGTH_PASSWORD;
import static com.vectree.billing.utils.Constants.MIN_LENGTH_PASSWORD;

/**
 * Utils for strings.
 *
 * @version 0.1.
 */
public class Strings {

    public static final String EMAIL_PATTERN =
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])." +
            "{" + MIN_LENGTH_PASSWORD + "," + MAX_LENGTH_PASSWORD + "})";


    private Strings() {
    }

    public static boolean digitsAndCharactersOnly(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean emailAddress(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
