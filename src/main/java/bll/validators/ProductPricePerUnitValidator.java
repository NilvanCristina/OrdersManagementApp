package bll.validators;

import model.Product;

public class ProductPricePerUnitValidator implements Validator<Product> {
    @Override
    public void validate(Product product) {
        PriceValidator priceValidator = new PriceValidator();
        priceValidator.validate(product.getPricePerUnit());
    }
}
