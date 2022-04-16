package presentation;

import bll.*;
import model.*;
import bill.OrderBill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener {
    private View view;
    private UsedFunctions functions = new UsedFunctions();

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent clickEvent) {
        checkMainPageSources(clickEvent);
        checkClientPageSources(clickEvent);
        checkProductPageSources(clickEvent);
        checkOrderPageSources(clickEvent);
    }

    private void checkMainPageSources(ActionEvent clickEvent) {
        if (clickEvent.getSource() == view.getClientsButton())
            view.setClientFrame();

        if (clickEvent.getSource() == view.getProductsButton())
            view.setProductsFrame();

        if (clickEvent.getSource() == view.getOrderButton())
            view.setOrderFrame();

        if (clickEvent.getSource() == view.getHomeButton())
            view.setMainFrame();
    }

    private void checkClientPageSources(ActionEvent clickEvent) {
        ClientBLL clientBLL = new ClientBLL();
        List<Client> clients = new ArrayList<>();

        if (clickEvent.getSource() == view.getFindClientButton()) {
            String chosenOperation = String.valueOf(view.getClientOptions().getSelectedItem());

            if (chosenOperation.equals("id")) {
                clients.add(clientBLL.findClientById(Integer.parseInt(view.getFindClientField())));
            }

            if (chosenOperation.equals("name")) {
                clients.add(clientBLL.findClientByName(view.getFindClientField()));
            }

            if (chosenOperation.equals("email")) {
                clients.add(clientBLL.findClientByEmail(view.getFindClientField()));
            }

            JScrollPane jScrollPane = functions.getTable(clients);
            functions.getResultFrame("Find Client", jScrollPane, view.getClientsButton());
        }

        if (clickEvent.getSource() == view.getInsertClientButton()) {
            String name = view.getClientNameField();
            String email = view.getClientEmailField();
            Client newClient = new Client(name, email);

            clientBLL.insertClient(newClient);

            clients = clientBLL.findAllClients();
            JScrollPane jScrollPane = functions.getTable(clients);
            functions.getResultFrame("Current clients", jScrollPane, view.getClientsButton());
        }

        if (clickEvent.getSource() == view.getUpdateClientButton()) {
            String toSetColumn = view.getToSetColumnC();
            String toSetValue = view.getToSetValueC();
            String conditionColumn = view.getConditionColumnC();
            String conditionValue = view.getConditionValueC();

            try {
                clientBLL.updateClient(toSetColumn, toSetValue, conditionColumn, conditionValue);
            } catch (Exception e) {
                e.printStackTrace();
            }

            clients = clientBLL.findAllClients();
            JScrollPane jScrollPane = functions.getTable(clients);
            functions.getResultFrame("Current clients", jScrollPane, view.getClientsButton());
        }

        if (clickEvent.getSource() == view.getDeleteClientButton()) {
            String conditionColumn = view.getConditionColumnDelC();
            String conditionValue = view.getConditionValueDelC();

            clientBLL.deleteClient(conditionColumn, conditionValue);

            clients = clientBLL.findAllClients();
            JScrollPane jScrollPane = functions.getTable(clients);
            functions.getResultFrame("Current clients", jScrollPane, view.getClientsButton());
        }

        if (clickEvent.getSource() == view.getShowClientsButton()) {
            clients = clientBLL.findAllClients();
            JScrollPane jScrollPane = functions.getTable(clients);
            functions.getResultFrame("All clients", jScrollPane, view.getClientsButton());
        }
    }

    private void checkProductPageSources(ActionEvent clickEvent) {
        ProductBLL productBLL = new ProductBLL();
        List<Product> products = new ArrayList<>();

        if (clickEvent.getSource() == view.getFindProductButton()) {
            String chosenOperation = String.valueOf(view.getProductOptions().getSelectedItem());

            if (chosenOperation.equals("id")) {
                products.add(productBLL.findProductById(Integer.parseInt(view.getFindProductField())));
            }

            if (chosenOperation.equals("name")) {
                products.add(productBLL.findProductByName(view.getFindProductField()));
            }

            if (chosenOperation.equals("measuringUnit")) {
                products.addAll(productBLL.findProductByMeasuringUnit(view.getFindProductField()));
            }

            if (chosenOperation.equals("pricePerUnit")) {
                products.addAll(productBLL.findProductByPricePerUnit(Double.parseDouble(view.getFindProductField())));
            }

            if (chosenOperation.equals("quantity")) {
                products.addAll(productBLL.findProductByQuantity(Integer.parseInt(view.getFindProductField())));
            }

            JScrollPane jScrollPane = functions.getTable(products);
            functions.getResultFrame("Find Product", jScrollPane, view.getProductsButton());
        }

        if (clickEvent.getSource() == view.getInsertProductButton()) {
            String name = view.getProductNameField();
            String measuringUnit = view.getProductMeasuringUnitField();
            double pricePerUnit = Double.parseDouble(view.getProductPricePerUnitField());
            int quantity = Integer.parseInt(view.getProductQuantityField());

            Product newProduct = new Product(name, measuringUnit, pricePerUnit, quantity);
            productBLL.insertProduct(newProduct);

            products = productBLL.findAllProducts();
            JScrollPane jScrollPane = functions.getTable(products);
            functions.getResultFrame("Current products", jScrollPane, view.getProductsButton());
        }

        if (clickEvent.getSource() == view.getUpdateProductButton()) {
            String toSetColumn = view.getToSetColumnP();
            String toSetValue = view.getToSetValueP();
            String conditionColumn = view.getConditionColumnP();
            String conditionValue = view.getConditionValueP();

            try {
                productBLL.updateProduct(toSetColumn, toSetValue, conditionColumn, conditionValue);
            } catch (Exception e) {
                e.printStackTrace();
            }

            products = productBLL.findAllProducts();
            JScrollPane jScrollPane = functions.getTable(products);
            functions.getResultFrame("Current products", jScrollPane, view.getProductsButton());
        }

        if (clickEvent.getSource() == view.getDeleteProductButton()) {
            String conditionColumn = view.getConditionColumnDelP();
            String conditionValue = view.getConditionValueDelP();

            productBLL.deleteProduct(conditionColumn, conditionValue);

            products = productBLL.findAllProducts();
            JScrollPane jScrollPane = functions.getTable(products);
            functions.getResultFrame("Current products", jScrollPane, view.getProductsButton());
        }

        if (clickEvent.getSource() == view.getShowProductsButton()) {
            products = productBLL.findAllProducts();
            JScrollPane jScrollPane = functions.getTable(products);
            functions.getResultFrame("All products", jScrollPane, view.getProductsButton());
        }
    }

    private void checkOrderPageSources(ActionEvent clickEvent) {
        OrderManagementBLL orderBll = new OrderManagementBLL();
        List<OrderManagement> orders = new ArrayList<>();

        if (clickEvent.getSource() == view.getFindOrderButton()) {
            String chosenOperation = String.valueOf(view.getOrderOptions().getSelectedItem());

            if (chosenOperation.equals("id")) {
                orders.add(orderBll.findOrderById(Integer.parseInt(view.getFindOrderField())));
            }

            if (chosenOperation.equals("codeClient")) {
                orders.addAll(orderBll.findOrderByCodeClient(Integer.parseInt(view.getFindOrderField())));
            }

            if (chosenOperation.equals("codeProduct")) {
                orders.addAll(orderBll.findOrderByCodeProduct(Integer.parseInt(view.getFindOrderField())));
            }

            if (chosenOperation.equals("price")) {
                orders.addAll(orderBll.findOrderByPrice(Double.parseDouble(view.getFindOrderField())));
            }

            if (chosenOperation.equals("quantity")) {
                orders.addAll(orderBll.findOrderByQuantity(Integer.parseInt(view.getFindOrderField())));
            }

            JScrollPane jScrollPane = functions.getTable(orders);
            functions.getResultFrame("Find Order", jScrollPane, view.getOrderButton());
        }

        if (clickEvent.getSource() == view.getInsertOrderButton()) {
            String clientName = view.getOrderClientNameField();
            String productName = view.getOrderProductNameField();
            int quantity = Integer.parseInt(view.getOrderQuantityField());

            if (orderBll.insertOrder(clientName, productName, quantity)) {
                orders = orderBll.findAllOrders();
                JScrollPane jScrollPane = functions.getTable(orders);
                functions.getResultFrame("Current orders", jScrollPane, view.getOrderButton());
            } else {
                JOptionPane.showMessageDialog(view, "Not enough items in stock!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (clickEvent.getSource() == view.getUpdateOrderButton()) {
            String toSetColumn = view.getToSetColumnO();
            String toSetValue = view.getToSetValueO();
            String conditionColumn = view.getConditionColumnO();
            String conditionValue = view.getConditionValueO();

            try {
                orderBll.updateOrder(toSetColumn, toSetValue, conditionColumn, conditionValue);
            } catch (Exception e) {
                e.printStackTrace();
            }

            orders = orderBll.findAllOrders();
            JScrollPane jScrollPane = functions.getTable(orders);
            functions.getResultFrame("Current orders", jScrollPane, view.getOrderButton());
        }

        if (clickEvent.getSource() == view.getDeleteOrderButton()) {
            String conditionColumn = view.getConditionColumnDelO();
            String conditionValue = view.getConditionValueDelO();

            orderBll.deleteOrder(conditionColumn, conditionValue);

            orders = orderBll.findAllOrders();
            JScrollPane jScrollPane = functions.getTable(orders);
            functions.getResultFrame("Current orders", jScrollPane, view.getOrderButton());
        }

        if (clickEvent.getSource() == view.getShowOrdersButton()) {
            orders = orderBll.findAllOrders();
            JScrollPane jScrollPane = functions.getTable(orders);
            functions.getResultFrame("All orders", jScrollPane, view.getOrderButton());
        }
    }
}
