package com.bankofjordan.training;

import java.sql.*;

public class DBConnectivityTest {

    public static void main(String[] args) {
        // entry point to connect to a database
        // connection strings example by database engine type
        // jdbc:mysql://localhost:3306/customer_onboarding_service
        // jdbc:oracle:thin:@localhost:1521:XE
        // jdbc:postgresql://localhost:5432/customer_onboarding_service
        // jdbc:sqlserver://localhost:1433;databaseName=customer_onboarding_service
        // jdbc:h2:mem:testdb
        // jdbc:h2:file:~/testdb

//        String url = "jdbc:h2:tcp://localhost:9092/customer_db";
        String url = "jdbc:h2:file:./customer_db";
        String username = "sa";
        String password = "sa";
        // This is your connection to the DB engine and your session
        try {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("connected to database");
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getDatabaseProductName());
                System.out.println(metaData.getDatabaseProductVersion());

                try (Statement statement = connection.createStatement()) {
                    // CRUD operations
//                    statement.executeUpdate("ALTER table BOJ_CUSTOMERS rename column CUT_ID_TYPE to CUST_ID_TYPE");
//                    int affectedRecords = statement.executeUpdate("INSERT INTO BOJ_CUSTOMERS VALUES (5, '12345','nid')");
//                    System.out.println("updated: " + affectedRecords);
                    try (ResultSet resultSet = statement.executeQuery("SELECT ID, CUST_ID,CUST_ID_TYPE FROM BOJ_CUSTOMERS")) {
                        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                            System.out.print(resultSetMetaData.getColumnLabel(i) + " ");
                        System.out.println();
                        while (resultSet.next()) {
                            System.out.println(resultSet.getString(1) + " " + resultSet.getString("CUST_ID") + " " + resultSet.getString(3));
                        }
                    }
                }
            }
//            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL state: " + e.getSQLState() + ", error code: " + e.getErrorCode() + ", message: " + e.getMessage());
        }
    }
}
