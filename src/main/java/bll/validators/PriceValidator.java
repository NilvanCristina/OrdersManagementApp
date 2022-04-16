package bll.validators;

public class PriceValidator implements Validator<Double> {
    @Override
    public void validate(Double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Not a valid price!");
        }
    }
}
