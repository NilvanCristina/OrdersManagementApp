package bll.validators;

import java.util.regex.Pattern;

public class NameValidator implements Validator<String> {
    private static final String NAME_PATTERN = "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+";

    @Override
    public void validate(String name) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(name).matches()) {
            throw new IllegalArgumentException("Not a valid name!");
        }
    }
}
