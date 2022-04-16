package presentation;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UsedFunctions {
    public void cleanPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    @NotNull
    public JPanel getPanelWithTextField(JTextField field, String labelText) {
        JPanel newPanel = new JPanel();
        JLabel newLabel = new JLabel(labelText);

        newPanel.add(newLabel);
        newPanel.add(field);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JPanel getPanelWithButton(JButton button, String labelText) {
        JPanel newPanel = new JPanel();
        JLabel newLabel = new JLabel(labelText);

        newPanel.add(newLabel);
        newPanel.add(button);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JPanel getPanelWithComboBox(JComboBox<String> comboBox, JButton button) {
        JPanel newPanel = new JPanel();

        newPanel.add(button);
        newPanel.add(comboBox);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JFrame setFrameDetails(String frameName) {
        JFrame frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        return frame;
    }

    @NotNull
    public void getResultFrame(String frameName, JScrollPane pane, JButton goBack) {
        JFrame resultFrame = setFrameDetails(frameName);
        JPanel resultPanel = new JPanel();

        resultPanel.add(pane);
        JPanel goBackPanel = getPanelWithButton(goBack, "");
        resultPanel.add(goBackPanel);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        resultFrame.setContentPane(resultPanel);
        resultFrame.setVisible(true);
    }

    public String[] getFields(Object object) {
        List<String> columnNames = new ArrayList<>();

        for(Field field:object.getClass().getDeclaredFields())
            columnNames.add(field.getName());

        return columnNames.toArray(new String[0]);
    }

    public <T> JScrollPane getTable(List<T> objects) {
        DefaultTableModel model = new DefaultTableModel();
        boolean firstIteration = true;

        for(Object object : objects) {
            List<String> rowData = new ArrayList<>();

            for(Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;

                try {
                    if (firstIteration)
                        model.addColumn(field.getName());

                    value = field.get(object);
                    rowData.add(value.toString());
                }
                catch(IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            model.addRow(rowData.toArray());
            firstIteration = false;
        }

        JTable table = new JTable(model);

        return new JScrollPane(table);
    }
}
