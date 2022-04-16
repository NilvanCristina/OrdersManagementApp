package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

/** Aceasta clasa contine operatiile specifice unui produs.
 */
public class ProductDAO extends AbstractDAO<Product> {
    /** Aceasta metoda primeste un nume si cauta in tabel randul ce corespunde acestui nume.
     * @param name: numele dupa care se face cautarea
     */
    public Product findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("name");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /** Aceasta metoda primeste o unitate de masura si cauta in tabel randurile ce corespund acestei unitati de masura.
     * @param measuringUnit: unitatea de masura dupa care se face cautarea
     */
    public List<Product> findByMeasuringUnit(String measuringUnit) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("measuringUnit");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, measuringUnit);

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findByMeasuringUnit " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /** Aceasta metoda primeste un pret si cauta in tabel randurile ce corespund acestui pret.
     * @param pricePerUnit: pretul dupa care se face cautarea
     */
    public List<Product> findByPricePerUnit(double pricePerUnit) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("pricePerUnit");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setDouble(1, pricePerUnit);

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findByPricePerUnit " + e.getMessage());
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
    public List<Product> findByQuantity(int quantity) {
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
            LOGGER.log(Level.WARNING, "ProductDAO:findByQuantity " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }
}
