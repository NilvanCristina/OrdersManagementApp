package bill;

import model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Aceasta clasa genereaza un fisier *.txt ce contine detaliile unei comenzi.
 */
public class OrderBill {
    private Client client;
    private Product product;
    private OrderManagement order;

    public OrderBill(Client client, Product product, OrderManagement order) {
        this.client = client;
        this.product = product;
        this.order = order;

        generateBill();
    }

    private String generateContentBill() {
        return "----- ORDER -----\n" +
                "CLIENT : \n" + "   Name: " + client.getName() + "\n" + "   Email: " + client.getEmail() + "\n" +
                "PRODUCT : \n" + "   Name: " + product.getName() + "\n" + "   Price: " + product.getPricePerUnit() + "\n" +
                "QUANTITY : " + order.getQuantity() + "\n" +
                "TOTAL : " + order.getPrice();
    }

    private void generateBill() {
        int orderNumber = (int)Math.floor(Math.random() * (2500 - 1 + 1) + 1);
        String fileName = "Order" + orderNumber + ".txt";
        File file = new File(fileName);

        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(generateContentBill());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
