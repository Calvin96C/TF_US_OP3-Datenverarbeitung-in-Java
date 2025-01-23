package com.wbs.util;

import java.sql.*;

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

