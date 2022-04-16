package bll.validators;

import model.Product;

public class ProductQuantityValidator implements Validator<Product> {
    @Override
    public void validate(Product product) {
        QuantityValidator quantityValidator = new QuantityValidator();
        quantityValidator.validate(product.getQuantity());
    }
}
