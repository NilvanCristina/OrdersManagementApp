package dao;

import connection.ConnectionFactory;
import model.OrderManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class OrderManagementDAO extends AbstractDAO<OrderManagement> {
    /** Aceasta metoda primeste id-ul unui client si cauta in tabel randurile ce corespund acestui id.
     * @param codeClient: id-ul clientului dupa care se face cautarea
     */
    public List<OrderManagement> findByCodeClient(int codeClient) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("codeClient");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, codeClient);

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderManagementDAO:findByCodeClient " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /** Aceasta metoda primeste id-ul unui produs si cauta in tabel randurile ce corespund acestui id.
     * @param codeProduct: id-ul produsului dupa care se face cautarea
     */
    public List<OrderManagement> findByCodeProduct(int codeProduct) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("codeProduct");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, codeProduct);

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderManagementDAO:findByCodeProduct " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /** Aceasta metoda primeste un pret si cauta in tabel randurile ce corespund acestui pret.
     * @param price: pretul dupa care se face cautarea
     */
    public List<OrderManagement> findByPrice(double price) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("price");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setDouble(1, price);

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderManagementDAO:findByPrice " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /** Aceasta metoda primeste o cantitate si cauta in tabel randurile ce corespund acestei cantitati.
     * @param quantity: cantitatea dupa care se face cautarea
     */
    public List<OrderManagement> findByQuantity(int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("quantity");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, quantity);

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderManagementDAO:findByQuantity " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }
}
