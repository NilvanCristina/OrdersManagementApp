package bll.validators;

import model.OrderManagement;

public class OrderQuantityValidator implements Validator<OrderManagement> {
    @Override
    public void validate(OrderManagement order) {
        QuantityValidator quantityValidator = new QuantityValidator();
        quantityValidator.validate(order.getQuantity());
    }
}
