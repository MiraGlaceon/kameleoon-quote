package mira.space.kameleoon.utils;

import mira.space.kameleoon.exceptions.PropertyBadRequestException;
import org.h2.util.StringUtils;

import java.util.regex.Pattern;

public class ValidUtils {

    /**
     * @throws PropertyBadRequestException if property null or empty
     */
    public static void checkValid(String property, String input) {
        if (StringUtils.isNullOrEmpty(input)) {
            throw new PropertyBadRequestException(property);
        }
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailRegex)
                .matcher(email)
                .matches();
    }
}
