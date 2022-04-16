package bll;

import bill.OrderBill;
import bll.validators.*;
import dao.OrderManagementDAO;
import model.Client;
import model.OrderManagement;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderManagementBLL {
    private OrderManagementDAO orderManagementDAO;
    private List<Validator<OrderManagement>> validators;

    public OrderManagementBLL() {
        orderManagementDAO = new OrderManagementDAO();
        validators = new ArrayList<>();

        validators.add(new OrderPriceValidator());
        validators.add(new OrderQuantityValidator());
    }

    public OrderManagement findOrderById(int id) {
        OrderManagement orderManagement = orderManagementDAO.findById(id);

        if (orderManagement == null) {
            throw new NoSuchElementException("The order with id = " + id + " was not found!");
        }

        return orderManagement;
    }

    public List<OrderManagement> findOrderByCodeClient(int codeClient) {
        List<OrderManagement> orderManagement = orderManagementDAO.findByCodeClient(codeClient);

        if (orderManagement == null) {
            throw new NoSuchElementException("No order with codeClient = " + codeClient + " was not found!");
        }

        return orderManagement;
    }

    public List<OrderManagement> findOrderByCodeProduct(int codeProduct) {
        List<OrderManagement> orderManagement = orderManagementDAO.findByCodeProduct(codeProduct);

        if (orderManagement == null) {
            throw new NoSuchElementException("No order with codeProduct = " + codeProduct + " was not found!");
        }

        return orderManagement;
    }

    public List<OrderManagement> findOrderByPrice(double price) {
        List<OrderManagement> orderManagement = orderManagementDAO.findByPrice(price);

        if (orderManagement == null) {
            throw new NoSuchElementException("No order with price = " + price + " was not found!");
        }

        return orderManagement;
    }

    public List<OrderManagement> findOrderByQuantity(int quantity) {
        List<OrderManagement> orderManagement = orderManagementDAO.findByQuantity(quantity);

        if (orderManagement == null) {
            throw new NoSuchElementException("No order with quantity = " + quantity + " was not found!");
        }

        return orderManagement;
    }

    public List<OrderManagement> findAllOrders() {
        List<OrderManagement> products = orderManagementDAO.findAll();

        if (products == null) {
            throw new NullPointerException("There are no orders in this table!");
        }

        return products;
    }

    private void validateOrder(OrderManagement order) {
        for (Validator<OrderManagement> validator: validators)
            validator.validate(order);
    }

    public boolean insertOrder(String clientName, String productName, int quantity) {
        ClientBLL clientBLL = new ClientBLL();
        Client client = clientBLL.findClientByName(clientName);

        ProductBLL productBLL = new ProductBLL();
        Product product = productBLL.findProductByName(productName);

        if (quantity > product.getQuantity()) {
            return false;
        }

        int newQuantity = product.getQuantity() - quantity;
        product.setQuantity(newQuantity);

        String toSetVal = newQuantity + "";
        String conditionVal = product.getId() + "";

        try {
            productBLL.updateProduct("quantity", toSetVal, "id", conditionVal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double price = quantity * product.getPricePerUnit();
        OrderManagement order = new OrderManagement(client.getId(), product.getId(), price, quantity);
        validateOrder(order);
        orderManagementDAO.insert(order);

        OrderBill bill = new OrderBill(client, product, order);

        return true;
    }

    private void validatePrice(double price) {
        PriceValidator priceValidator = new PriceValidator();
        priceValidator.validate(price);
    }

    private void validateQuantity(int quantity) {
        QuantityValidator quantityValidator = new QuantityValidator();
        quantityValidator.validate(quantity);
    }

    public void updateOrder(String toSetColumn, String toSetValue, String conditionColumn, String conditionValue) throws Exception {
        if (toSetColumn.equals("price"))
            validatePrice(Double.parseDouble(toSetValue));

        if (toSetColumn.equals("quantity"))
            validateQuantity(Integer.parseInt(toSetValue));

        orderManagementDAO.update(toSetColumn, toSetValue, conditionColumn, conditionValue);
    }

    public void deleteOrder(String conditionColumn, String conditionValue) {
        orderManagementDAO.delete(conditionColumn, conditionValue);
    }
}
