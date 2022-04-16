package model;

public class OrderManagement {
    private int id;
    private int codeClient;
    private int codeProduct;
    private double price;
    private int quantity;

    public OrderManagement() {

    }

    public OrderManagement(int codeClient, int codeProduct, double price, int quantity) {
        this.codeClient = codeClient;
        this.codeProduct = codeProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderManagement(int id, int codeClient, int codeProduct, double price, int quantity) {
        this.id = id;
        this.codeClient = codeClient;
        this.codeProduct = codeProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public int getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [codeOrder=" + id + ", codeClient=" + codeClient + ", codeProduct=" + codeProduct +
                ", price=" + price + ", quantity=" + quantity + "]";
    }
}
