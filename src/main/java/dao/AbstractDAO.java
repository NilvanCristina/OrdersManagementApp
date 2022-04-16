package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor<?>[] declaredConstructors = type.getDeclaredConstructors();
        Constructor<?> constructor = null;

        for (Constructor<?> auxiliaryConstructor : declaredConstructors) {
            constructor = auxiliaryConstructor;
            if (constructor.getGenericParameterTypes().length == 0)
                break;
        }

        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T instance = (T)constructor.newInstance();

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }

                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException |
                InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }

        return list;
    }

    protected String createSelectWhereQuery(String field) {
        return "SELECT " +
                "*" +
                " FROM " +
                type.getSimpleName() +
                " WHERE " + field + " = ?";
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("id");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    private String createSelectQuery() {
        return "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName();
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    private String createInsertQuery() {
        StringBuilder query = new StringBuilder();
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();

        query.append("INSERT INTO ");
        query.append(type.getSimpleName());
        fields.append(" (");
        values.append("VALUES(");

        for (int i = 0; i < type.getDeclaredFields().length; i++) {
            fields.append(type.getDeclaredFields()[i].getName());
            values.append("?");

            if (i == type.getDeclaredFields().length - 1) {
                fields.append(") ");
                values.append(")");
            }
            else {
                fields.append(",");
                values.append(",");
            }
        }

        query.append(fields);
        query.append(values);

        return query.toString();
    }

    public void insert(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            for (int i = 0; i < type.getDeclaredFields().length; i++) {
                Field field = type.getDeclaredFields()[i];
                field.setAccessible(true);
                statement.setObject((i + 1), field.get(object));
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private String createUpdateQuery(String toSetColumn, String conditionColumn) {
        return "UPDATE " +
                type.getSimpleName() +
                " SET " +
                toSetColumn + " = ?" +
                " WHERE " +
                conditionColumn + " = ?";
    }

    public void update(String toSetColumn, String toSetValue, String conditionColumn, String conditionValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(toSetColumn, conditionColumn);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setObject(1, toSetValue);
            statement.setObject(2, conditionValue);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private String createDeleteQuery(String conditionColumn) {
        return "DELETE FROM " +
                type.getSimpleName() +
                " WHERE " +
                conditionColumn + " = ?";
    }

    public void delete(String conditionColumn, String conditionValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(conditionColumn);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setObject(1, conditionValue);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
