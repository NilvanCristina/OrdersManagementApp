package bll;

import bll.validators.*;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;
    private List<Validator<Product>> validators;

    public ProductBLL() {
        productDAO = new ProductDAO();
        validators = new ArrayList<>();

        validators.add(new ProductPricePerUnitValidator());
        validators.add(new ProductQuantityValidator());
    }

    public Product findProductById(int id) {
        Product product = productDAO.findById(id);

        if (product == null) {
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }

        return product;
    }

    public Product findProductByName(String name) {
        Product product = productDAO.findByName(name);

        if (product == null) {
            throw new NoSuchElementException("The product with name = " + name + " was not found!");
        }

        return product;
    }

    public List<Product> findProductByMeasuringUnit(String measuringUnit) {
        List<Product> product = productDAO.findByMeasuringUnit(measuringUnit);

        if (product == null) {
            throw new NoSuchElementException("No product with measuringUnit = " + measuringUnit + " was not found!");
        }

        return product;
    }

    public List<Product> findProductByPricePerUnit(double pricePerUnit) {
        List<Product> product = productDAO.findByPricePerUnit(pricePerUnit);

        if (product == null) {
            throw new NoSuchElementException("No product with pricePerUnit = " + pricePerUnit + " was not found!");
        }

        return product;
    }

    public List<Product> findProductByQuantity(int quantity) {
        List<Product> product = productDAO.findByQuantity(quantity);

        if (product == null) {
            throw new NoSuchElementException("No product with quantity = " + quantity + " was not found!");
        }

        return product;
    }

    public List<Product> findAllProducts() {
        List<Product> products = productDAO.findAll();

        if (products == null) {
            throw new NullPointerException("There are no clients in this table!");
        }

        return products;
    }

    private void validateProduct(Product product) {
        for (Validator<Product> validator: validators)
            validator.validate(product);
    }

    public void insertProduct(Product product) {
        validateProduct(product);
        productDAO.insert(product);
    }

    private void validatePrice(double price) {
        PriceValidator priceValidator = new PriceValidator();
        priceValidator.validate(price);
    }

    private void validateQuantity(int quantity) {
        QuantityValidator quantityValidator = new QuantityValidator();
        quantityValidator.validate(quantity);
    }

    public void updateProduct(String toSetColumn, String toSetValue, String conditionColumn, String conditionValue) throws Exception {
        if (toSetColumn.equals("pricePerUnit"))
            validatePrice(Double.parseDouble(toSetValue));

        if (toSetColumn.equals("quantity"))
            validateQuantity(Integer.parseInt(toSetValue));

        productDAO.update(toSetColumn, toSetValue, conditionColumn, conditionValue);
    }

    public void deleteProduct(String conditionColumn, String conditionValue) {
        productDAO.delete(conditionColumn, conditionValue);
    }
}
