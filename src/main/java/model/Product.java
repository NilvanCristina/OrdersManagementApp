package model;

public class Product {
    private int id;
    private String name;
    private String measuringUnit;
    private double pricePerUnit;
    private int quantity;

    public Product() {

    }

    public Product(String name, String measuringUnit, double pricePerUnit, int quantity) {
        this.name = name;
        this.measuringUnit = measuringUnit;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
    }

    public Product(int id, String name, String measuringUnit, double pricePerUnit, int quantity) {
        this.id = id;
        this.name = name;
        this.measuringUnit = measuringUnit;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [codeProduct=" + id + ", name=" + name + ", measuringUnit=" + measuringUnit +
                ", pricePerUnit=" + pricePerUnit + ", quantity=" + quantity + "]";
    }
}
