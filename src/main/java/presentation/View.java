package presentation;

import model.Client;
import model.OrderManagement;
import model.Product;

import javax.swing.*;
import java.awt.*;

public class View extends Component {
    // Main page buttons
    private JButton clientsButton = new JButton("Client");
    private JButton productsButton = new JButton("Product");
    private JButton orderButton = new JButton("Order");
    private JButton homeButton = new JButton("Home");

    // Panels
    private JPanel mainPanel = new JPanel();
    private JPanel clientsPanel = new JPanel();
    private JPanel productsPanel = new JPanel();
    private JPanel orderPanel = new JPanel();

    // Client page fields and buttons
    private JButton findClientButton = new JButton("Find By");
    private JButton insertClientButton = new JButton("Insert");
    private JButton updateClientButton = new JButton("Update");
    private JButton deleteClientButton = new JButton("Delete");
    private JButton showClientsButton = new JButton("Show all");
    private JComboBox<String> clientOptions;
    private JTextField findClientField = new JTextField(5);
    private JTextField clientNameField = new JTextField(15);
    private JTextField clientEmailField = new JTextField(15);
    private JTextField toSetColumnC = new JTextField(5);
    private JTextField toSetValueC = new JTextField(5);
    private JTextField conditionColumnC = new JTextField(5);
    private JTextField conditionValueC = new JTextField(5);
    private JTextField conditionColumnDelC = new JTextField(5);
    private JTextField conditionValueDelC = new JTextField(5);

    // Product page fields and buttons
    private JButton findProductButton = new JButton("Find By");
    private JButton insertProductButton = new JButton("Insert");
    private JButton updateProductButton = new JButton("Update");
    private JButton deleteProductButton = new JButton("Delete");
    private JButton showProductsButton = new JButton("Show all");
    private JComboBox<String> productOptions;
    private JTextField findProductField = new JTextField(5);
    private JTextField productNameField = new JTextField(5);
    private JTextField productMeasuringUnitField = new JTextField(5);
    private JTextField productPricePerUnitField = new JTextField(5);
    private JTextField productQuantityField = new JTextField(5);
    private JTextField toSetColumnP = new JTextField(5);
    private JTextField toSetValueP = new JTextField(5);
    private JTextField conditionColumnP = new JTextField(5);
    private JTextField conditionValueP = new JTextField(5);
    private JTextField conditionColumnDelP = new JTextField(5);
    private JTextField conditionValueDelP = new JTextField(5);

    // Order page fields and buttons
    private JButton findOrderButton = new JButton("Find By");
    private JButton insertOrderButton = new JButton("Insert");
    private JButton updateOrderButton = new JButton("Update");
    private JButton deleteOrderButton = new JButton("Delete");
    private JButton showOrdersButton = new JButton("Show all");
    private JComboBox<String> orderOptions;
    private JTextField findOrderField = new JTextField(5);
    private JTextField orderClientNameField = new JTextField(15);
    private JTextField orderProductNameField = new JTextField(10);
    private JTextField orderQuantityField = new JTextField(5);
    private JTextField toSetColumnO = new JTextField(5);
    private JTextField toSetValueO = new JTextField(5);
    private JTextField conditionColumnO = new JTextField(5);
    private JTextField conditionValueO = new JTextField(5);
    private JTextField conditionColumnDelO = new JTextField(5);
    private JTextField conditionValueDelO = new JTextField(5);

    Controller controller = new Controller(this);
    UsedFunctions functions = new UsedFunctions();

    public View() {
        setMainFrame();
    }

    public void setMainFrame() {
        functions.cleanPanel(mainPanel);

        JFrame frame = functions.setFrameDetails("Orders Management App");

        clientsButton.addActionListener(controller);
        JPanel clientsPanel = functions.getPanelWithButton(clientsButton, "See client operations: ");
        mainPanel.add(clientsPanel);

        productsButton.addActionListener(controller);
        JPanel productsPanel = functions.getPanelWithButton(productsButton, "See product operations: ");
        mainPanel.add(productsPanel);

        orderButton.addActionListener(controller);
        JPanel ordersPanel = functions.getPanelWithButton(orderButton, "See order operations: ");
        mainPanel.add(ordersPanel);

        homeButton.addActionListener(controller);
        JPanel backPanel = functions.getPanelWithButton(homeButton, "");
        mainPanel.add(backPanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void setClientFrame() {
        functions.cleanPanel(clientsPanel);
        JFrame frame = functions.setFrameDetails("Clients View");

        Client client = new Client();
        clientOptions = new JComboBox<>(functions.getFields(client));

        findClientButton.addActionListener(controller);
        JPanel findPanel = functions.getPanelWithComboBox(clientOptions, findClientButton);
        findPanel.add(new JLabel("Value :"));
        findPanel.add(findClientField);

        JPanel insertName = functions.getPanelWithTextField(clientNameField, "Name :");
        JPanel insertEmail = functions.getPanelWithTextField(clientEmailField, "Email :");

        insertClientButton.addActionListener(controller);
        JPanel insertPanel = functions.getPanelWithButton(insertClientButton, "");
        insertPanel.add(insertName);
        insertPanel.add(insertEmail);
        ;
        JPanel updateToSetCol = functions.getPanelWithTextField(toSetColumnC, "To update column:");
        JPanel updateToSetVal = functions.getPanelWithTextField(toSetValueC, "To update value:");
        JPanel updateCondCol = functions.getPanelWithTextField(conditionColumnC, "Condition column:");
        JPanel updateCondVal = functions.getPanelWithTextField(conditionValueC, "Condition value:");

        updateClientButton.addActionListener(controller);
        JPanel updatePanel = functions.getPanelWithButton(updateClientButton, "");
        updatePanel.add(updateToSetCol);
        updatePanel.add(updateToSetVal);
        updatePanel.add(updateCondCol);
        updatePanel.add(updateCondVal);

        JPanel deleteCondCol = functions.getPanelWithTextField(conditionColumnDelC, "Delete where:");
        JPanel deleteCondVal = functions.getPanelWithTextField(conditionValueDelC, "Value:");

        deleteClientButton.addActionListener(controller);
        JPanel deletePanel = functions.getPanelWithButton(deleteClientButton, "");
        deletePanel.add(deleteCondCol);
        deletePanel.add(deleteCondVal);

        showClientsButton.addActionListener(controller);
        JPanel showAllPanel = functions.getPanelWithButton(showClientsButton, "");
        JPanel backPanel = functions.getPanelWithButton(homeButton, "");

        clientsPanel.add(findPanel);
        clientsPanel.add(insertPanel);
        clientsPanel.add(updatePanel);
        clientsPanel.add(deletePanel);
        clientsPanel.add(showAllPanel);
        clientsPanel.add(backPanel);

        clientsPanel.setLayout(new BoxLayout(clientsPanel, BoxLayout.Y_AXIS));
        frame.setContentPane(clientsPanel);
        frame.setVisible(true);
    }

    public void setProductsFrame() {
        functions.cleanPanel(productsPanel);
        JFrame frame = functions.setFrameDetails("Products View");

        Product product = new Product();
        productOptions = new JComboBox<>(functions.getFields(product));

        findProductButton.addActionListener(controller);
        JPanel findPanel = functions.getPanelWithComboBox(productOptions, findProductButton);
        findPanel.add(new JLabel("Value :"));
        findPanel.add(findProductField);

        JPanel insertName = functions.getPanelWithTextField(productNameField, "Name :");
        JPanel insertMU = functions.getPanelWithTextField(productMeasuringUnitField, "Measuring Unit :");
        JPanel insertPricePerUnit = functions.getPanelWithTextField(productPricePerUnitField, "Price Per Unit :");
        JPanel insertQuantity = functions.getPanelWithTextField(productQuantityField, "Quantity :");

        insertProductButton.addActionListener(controller);
        JPanel insertPanel = functions.getPanelWithButton(insertProductButton, "");
        insertPanel.add(insertName);
        insertPanel.add(insertMU);
        insertPanel.add(insertPricePerUnit);
        insertPanel.add(insertQuantity);

        JPanel updateToSetCol = functions.getPanelWithTextField(toSetColumnP, "To update column:");
        JPanel updateToSetVal = functions.getPanelWithTextField(toSetValueP, "To update value:");
        JPanel updateCondCol = functions.getPanelWithTextField(conditionColumnP, "Condition column:");
        JPanel updateCondVal = functions.getPanelWithTextField(conditionValueP, "Condition value:");

        updateProductButton.addActionListener(controller);
        JPanel updatePanel = functions.getPanelWithButton(updateProductButton, "");
        updatePanel.add(updateToSetCol);
        updatePanel.add(updateToSetVal);
        updatePanel.add(updateCondCol);
        updatePanel.add(updateCondVal);

        JPanel deleteCondCol = functions.getPanelWithTextField(conditionColumnDelP, "Delete where:");
        JPanel deleteCondVal = functions.getPanelWithTextField(conditionValueDelP, "Value:");

        deleteProductButton.addActionListener(controller);
        JPanel deletePanel = functions.getPanelWithButton(deleteProductButton, "");
        deletePanel.add(deleteCondCol);
        deletePanel.add(deleteCondVal);

        showProductsButton.addActionListener(controller);
        JPanel showAllPanel = functions.getPanelWithButton(showProductsButton, "");
        JPanel backPanel = functions.getPanelWithButton(homeButton, "");

        productsPanel.add(findPanel);
        productsPanel.add(insertPanel);
        productsPanel.add(updatePanel);
        productsPanel.add(deletePanel);
        productsPanel.add(showAllPanel);
        productsPanel.add(backPanel);

        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        frame.setContentPane(productsPanel);
        frame.setVisible(true);
    }

    public void setOrderFrame() {
        functions.cleanPanel(orderPanel);
        JFrame frame = functions.setFrameDetails("Orders View");

        OrderManagement order = new OrderManagement();
        orderOptions = new JComboBox<>(functions.getFields(order));

        findOrderButton.addActionListener(controller);
        JPanel findPanel = functions.getPanelWithComboBox(orderOptions, findOrderButton);
        findPanel.add(new JLabel("Value :"));
        findPanel.add(findOrderField);

        JPanel insertClientName = functions.getPanelWithTextField(orderClientNameField, "Client name :");
        JPanel insertProductName = functions.getPanelWithTextField(orderProductNameField, "Product name :");
        JPanel insertQuantity = functions.getPanelWithTextField(orderQuantityField, "Quantity :");

        insertOrderButton.addActionListener(controller);
        JPanel insertPanel = functions.getPanelWithButton(insertOrderButton, "");
        insertPanel.add(insertClientName);
        insertPanel.add(insertProductName);
        insertPanel.add(insertQuantity);

        JPanel updateToSetCol = functions.getPanelWithTextField(toSetColumnO, "To update column:");
        JPanel updateToSetVal = functions.getPanelWithTextField(toSetValueO, "To update value:");
        JPanel updateCondCol = functions.getPanelWithTextField(conditionColumnO, "Condition column:");
        JPanel updateCondVal = functions.getPanelWithTextField(conditionValueO, "Condition value:");

        updateOrderButton.addActionListener(controller);
        JPanel updatePanel = functions.getPanelWithButton(updateOrderButton, "");
        updatePanel.add(updateToSetCol);
        updatePanel.add(updateToSetVal);
        updatePanel.add(updateCondCol);
        updatePanel.add(updateCondVal);

        JPanel deleteCondCol = functions.getPanelWithTextField(conditionColumnDelO, "Delete where:");
        JPanel deleteCondVal = functions.getPanelWithTextField(conditionValueDelO, "Value:");

        deleteOrderButton.addActionListener(controller);
        JPanel deletePanel = functions.getPanelWithButton(deleteOrderButton, "");
        deletePanel.add(deleteCondCol);
        deletePanel.add(deleteCondVal);

        showOrdersButton.addActionListener(controller);
        JPanel showAllPanel = functions.getPanelWithButton(showOrdersButton, "");
        JPanel backPanel = functions.getPanelWithButton(homeButton, "");

        orderPanel.add(findPanel);
        orderPanel.add(insertPanel);
        orderPanel.add(updatePanel);
        orderPanel.add(deletePanel);
        orderPanel.add(showAllPanel);
        orderPanel.add(backPanel);

        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        frame.setContentPane(orderPanel);
        frame.setVisible(true);
    }

    public JButton getClientsButton() {
        return clientsButton;
    }

    public JButton getProductsButton() {
        return productsButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getFindClientButton() {
        return findClientButton;
    }

    public JButton getInsertClientButton() {
        return insertClientButton;
    }

    public JButton getUpdateClientButton() {
        return updateClientButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public JButton getShowClientsButton() {
        return showClientsButton;
    }

    public JComboBox<String> getClientOptions() {
        return clientOptions;
    }

    public String getFindClientField() {
        return findClientField.getText();
    }

    public String getClientNameField() {
        return clientNameField.getText();
    }

    public String getClientEmailField() {
        return clientEmailField.getText();
    }

    public String getToSetColumnC() {
        return toSetColumnC.getText();
    }

    public String getToSetValueC() {
        return toSetValueC.getText();
    }

    public String getConditionColumnC() {
        return conditionColumnC.getText();
    }

    public String getConditionValueC() {
        return conditionValueC.getText();
    }

    public String getConditionColumnDelC() {
        return conditionColumnDelC.getText();
    }

    public String getConditionValueDelC() {
        return conditionValueDelC.getText();
    }

    public JButton getFindProductButton() {
        return findProductButton;
    }

    public JButton getInsertProductButton() {
        return insertProductButton;
    }

    public JButton getUpdateProductButton() {
        return updateProductButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    public JButton getShowProductsButton() {
        return showProductsButton;
    }

    public JComboBox<String> getProductOptions() {
        return productOptions;
    }

    public String getFindProductField() {
        return findProductField.getText();
    }

    public String getProductNameField() {
        return productNameField.getText();
    }

    public String getProductMeasuringUnitField() {
        return productMeasuringUnitField.getText();
    }

    public String getProductPricePerUnitField() {
        return productPricePerUnitField.getText();
    }

    public String getProductQuantityField() {
        return productQuantityField.getText();
    }

    public String getToSetColumnP() {
        return toSetColumnP.getText();
    }

    public String getToSetValueP() {
        return toSetValueP.getText();
    }

    public String getConditionColumnP() {
        return conditionColumnP.getText();
    }

    public String getConditionValueP() {
        return conditionValueP.getText();
    }

    public String getConditionColumnDelP() {
        return conditionColumnDelP.getText();
    }

    public String getConditionValueDelP() {
        return conditionValueDelP.getText();
    }

    public JButton getFindOrderButton() {
        return findOrderButton;
    }

    public JButton getInsertOrderButton() {
        return insertOrderButton;
    }

    public JButton getUpdateOrderButton() {
        return updateOrderButton;
    }

    public JButton getDeleteOrderButton() {
        return deleteOrderButton;
    }

    public JButton getShowOrdersButton() {
        return showOrdersButton;
    }

    public JComboBox<String> getOrderOptions() {
        return orderOptions;
    }

    public String getFindOrderField() {
        return findOrderField.getText();
    }

    public String getOrderClientNameField() {
        return orderClientNameField.getText();
    }

    public String getOrderProductNameField() {
        return orderProductNameField.getText();
    }

    public String getOrderQuantityField() {
        return orderQuantityField.getText();
    }

    public String getToSetColumnO() {
        return toSetColumnO.getText();
    }

    public String getToSetValueO() {
        return toSetValueO.getText();
    }

    public String getConditionColumnO() {
        return conditionColumnO.getText();
    }

    public String getConditionValueO() {
        return conditionValueO.getText();
    }

    public String getConditionColumnDelO() {
        return conditionColumnDelO.getText();
    }

    public String getConditionValueDelO() {
        return conditionValueDelO.getText();
    }
}
