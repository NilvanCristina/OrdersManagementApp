package bll.validators;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public void validate(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Not a valid email!");
        }
    }
}
