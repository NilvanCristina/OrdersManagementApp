package bll.validators;

import model.OrderManagement;

public class OrderPriceValidator implements Validator<OrderManagement> {
    @Override
    public void validate(OrderManagement order) {
        PriceValidator priceValidator = new PriceValidator();
        priceValidator.validate(order.getPrice());
    }
}
