package bll.validators;

import model.OrderManagement;

public class QuantityValidator implements Validator<Integer> {
    @Override
    public void validate(Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Not a valid quantity!");
        }
    }
}