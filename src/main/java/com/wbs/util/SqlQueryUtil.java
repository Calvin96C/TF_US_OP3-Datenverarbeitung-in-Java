package com.wbs.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SqlQueryUtil {

    // Encapsulate resources in a single class
    public static class QueryResult{
        private final Connection connection;
        private final Statement statement;
        private final ResultSet resultSet;

        public QueryResult(Connection connection, Statement statement, ResultSet resultSet) {
            this.connection = connection;
            this.statement = statement;
            this.resultSet = resultSet;
        }

        public Connection getConnection() {
            return connection;
        }

        public Statement getStatement() {
            return statement;
        }

        public ResultSet getResultSet() {
            return resultSet;
        }
    }

    public static QueryResult getQueryResult(String databaseName, String queryString) throws SQLException {
        String connectionString = DBConnectionUtil.getDbUrl(databaseName);
        String userName = DBConnectionUtil.getDbUsername();
        String password = DBConnectionUtil.getDbPassword();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Create connection and statement
            connection = DriverManager.getConnection(connectionString, userName, password);
            statement = connection.createStatement();

            // Execute query
            resultSet = statement.executeQuery(queryString);

            // Return a query object, which contains
            return new QueryResult(connection, statement, resultSet);

        } catch (SQLException e) {
            // Ensure resources are closed in case of failure
            closeResources(connection, statement, resultSet);
            throw e;
        }
    }

    public static void closeQueryResult(QueryResult queryResult) {
        if (queryResult != null) {
            closeResources(queryResult.getConnection(), queryResult.getStatement(), queryResult.getResultSet());
        }
    }

    /*
    public static <T> List<T> resultSetToList(String databaseName, Class<?> classProperty)
    {
        String tableName = classProperty.getSimpleName().toLowerCase();
        Field[] fields = classProperty.getDeclaredFields();
        List<T> resultList = new ArrayList<>();
        String queryString = "select * from " + tableName;

        QueryResult queryResult = null;

        try
        {

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeQueryResult(queryResult);
        }
    }*/

    public static <T> List<T> resultSetToList(String databaseName, Class<T> classProperty) {
        String tableName = classProperty.getSimpleName().toLowerCase();
        Field[] fields = classProperty.getDeclaredFields();
        List<T> resultList = new ArrayList<>();
        String queryString = "SELECT * FROM " + tableName;

        QueryResult queryResult = null;

        try
        {
            queryResult = getQueryResult(databaseName, queryString);
            ResultSet resultSet = queryResult.getResultSet();

            while (resultSet.next())
            {
                T instance;
                try {
                    // Create a new instance of the class dynamically
                    instance = classProperty.getDeclaredConstructor().newInstance();
                } catch (ReflectiveOperationException | SecurityException e) {
                    throw new RuntimeException("Error creating instance of " + classProperty.getName(), e);
                }

                for (Field field : fields) {
                    field.setAccessible(true); // Make private fields accessible
                    String fieldName = field.getName();
                    Class<?> fieldType = field.getType();

                    try {
                        // Match field type and set value dynamically
                        if (fieldType == int.class || fieldType == Integer.class) {
                            field.set(instance, resultSet.getInt(fieldName));
                        } else if (fieldType == String.class) {
                            field.set(instance, resultSet.getString(fieldName));
                        } else if (fieldType == double.class || fieldType == Double.class) {
                            field.set(instance, resultSet.getDouble(fieldName));
                        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                            field.set(instance, resultSet.getBoolean(fieldName));
                        } else if (fieldType == long.class || fieldType == Long.class) {
                            field.set(instance, resultSet.getLong(fieldName));
                        } else if (fieldType == float.class || fieldType == Float.class) {
                            field.set(instance, resultSet.getFloat(fieldName));
                        } else if (fieldType == short.class || fieldType == Short.class) {
                            field.set(instance, resultSet.getShort(fieldName));
                        } else if (fieldType == java.sql.Date.class) {
                            field.set(instance, resultSet.getDate(fieldName));
                        } else if (fieldType == java.sql.Timestamp.class) {
                            field.set(instance, resultSet.getTimestamp(fieldName));
                        } else {
                            // Handle other types if needed
                            throw new UnsupportedOperationException("Field type not supported: " + fieldType);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to access or set the field '" + fieldName + "' in class '"
                                + classProperty.getName() + "'. Ensure the field is accessible and correctly named.", e);
                    } catch (SQLException e) {
                        throw new RuntimeException("Error retrieving value for field '" + fieldName + "' from the ResultSet.", e);
                    }
                }

                resultList.add(instance); // Add the populated object to the result list
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error querying database", e);
        } catch (RuntimeException e) {
            throw e; // Re-throw runtime exceptions for debugging
        } finally
        {
            closeQueryResult(queryResult);
        }

        return resultList;
    }

    public static void printClassProperties(Class<?> clazz) {
        // Get all declared fields of the class
        Field[] fields = clazz.getDeclaredFields();
        System.out.println(clazz.getSimpleName().toLowerCase());
        // Loop through the fields and print their types and names
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName(); // For simple name like "int", "String"
            // Or field.getType().getName(); // For fully qualified name like "java.lang.String"

            System.out.println("Property Name: " + fieldName + ", Property Type: " + fieldType);
        }
    }

    private static void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

