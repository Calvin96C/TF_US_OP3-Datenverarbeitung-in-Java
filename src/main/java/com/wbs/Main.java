package com.wbs;

import com.wbs.util.DBConnectionUtil;
import com.wbs.util.SqlQueryUtil;

import java.sql.*;

public class Main {

    private void testMethod() throws SQLException, Exception{
        if(0 == 0){
            throw new SQLException("This comparison makes no sense");
        }
    }


    public static void main(String[] args)
    {

        SqlQueryUtil.QueryResult queryResult = null;

        try
        {
            //region old connection
            /*
            // 1 retrieve the connection string
            //String connectionString = "jdbc:mysql://localhost:3306/schulungsdatenbank";
            String connectionString = DBConnectionUtil.getDbUrl("onlineshop");
            String userName = DBConnectionUtil.getDbUsername();
            String password = DBConnectionUtil.getDbPassword();

            // 2 create the connection object
            Connection connection = DriverManager.getConnection(connectionString, userName, password);

            //region alternative connection
            // Connection connection2 = DriverManager.getConnection(
            // DBConnectionUtil.getDbUrl("onlineshop"),
            // DBConnectionUtil.getDbUsername(),
            // DBConnectionUtil.getDbPassword());
            //endregion

            // 3 create the statement object
            Statement statement = connection.createStatement();

            // 4 create the resultset object
            ResultSet rs = statement.executeQuery("select * from adresse");

            // 5
            //connection.close();

            */
            //endregion

            // Get the query result
            queryResult = SqlQueryUtil.getQueryResult("onlineshop", "select * from adresse");

            // Get the ResultSet from QueryResult
            ResultSet rs = queryResult.getResultSet();

            while (rs.next())
            {
                String sb =
                        rs.getInt("id") + " -- " +
                        //rs.getInt("kunde") +
                        rs.getString("ort") + " -- " +
                        rs.getString("plz") + " -- " +
                        rs.getString("straßeNr");

                System.out.println(sb);
            }

            /*statement.execute("drop table if exists animal;\n" +
                    "drop table if exists species;\n" +
                    "\n" +
                    "create table species(\n" +
                    "\tspecies_ID int primary key auto_increment unique not null,\n" +
                    "    species_name varchar(50) not null,\n" +
                    "\tnum_arcs decimal\n" +
                    "\t);\n" +
                    "\n" +
                    "create table if not exists animal(\n" +
                    "\tanimal_ID int primary key auto_increment unique not null,\n" +
                    "    animal_name varchar(50) not null,\n" +
                    "    species_ID int,\n" +
                    "    date_born timestamp not null,\n" +
                    "    foreign key (species_ID) references species (species_ID) on update cascade on delete restrict\n" +
                    "    );");*/

            /*
            // 4
            statement.executeUpdate("drop table if exists animal;");
            statement.executeUpdate("drop table if exists species;");

            statement.executeUpdate("create table species(\n" +
                    "\tspecies_ID int primary key auto_increment unique not null,\n" +
                    "\tspecies_name varchar(50) not null,\n" +
                    "\tnum_arcs decimal\n" +
                    "\t);");

            statement.executeUpdate("create table if not exists animal(\n" +
                    "\tanimal_ID int primary key auto_increment unique not null,\n" +
                    "    animal_name varchar(50) not null,\n" +
                    "    species_ID int,\n" +
                    "    date_born timestamp not null,\n" +
                    "    foreign key (species_ID) references species (species_ID) on update cascade on delete restrict\n" +
                    "    );");
             */


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
        // Close all resources
        SqlQueryUtil.closeQueryResult(queryResult);
    }
    }
}