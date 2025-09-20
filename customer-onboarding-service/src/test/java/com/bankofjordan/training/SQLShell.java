package com.bankofjordan.training;

import java.sql.*;
import java.util.Scanner;

public class SQLShell {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@192.168.1.142:1521:XE";
        String username = "pps";
        String password = "pps";
        Scanner scanner = new Scanner(System.in);
        try {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                System.out.println("connected");
                while (true) {
                    System.out.print("SQL> ");
                    String sql = readNextSQL(scanner);
                    if (sql.equals("exit")) {
                        break;
                    }
                    if (sql.equals("start transaction")) {
                        connection.setAutoCommit(false);
                        continue;
                    }
                    if (sql.equals("commit")) {
                        connection.commit();
                        connection.setAutoCommit(true);
                        continue;
                    }
                    if (sql.equals("rollback")) {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        continue;
                    }
                    try (Statement statement = connection.createStatement()) {
                        //statement.executeUpdate() Create Delete Update or any DDL
                        // statement.executeQuery() execute query and return a result
                        try {
                            boolean isQuery = statement.execute(sql);
                            if (isQuery) {
                                displayQueryResult(statement);
                            } else {
                                System.out.println("updated: " + statement.getUpdateCount());
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getSQLState() + ":" + e.getErrorCode() + ":" + e.getMessage());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String readNextSQL(Scanner scanner) {
        StringBuilder sqlLines = new StringBuilder();
        while (!sqlLines.toString().endsWith(";")) {
            sqlLines.append(scanner.nextLine());
        }
        String sql = sqlLines.substring(0, sqlLines.length() - 1);
        return sql;
    }

    private static void displayQueryResult(Statement statement) throws SQLException {
        try (ResultSet resultSet = statement.getResultSet()) {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String column = rsmd.getColumnLabel(i);
                int size = rsmd.getColumnDisplaySize(i);
                System.out.print(String.format("%" + size + "s|", column));
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String value = resultSet.getString(i);
                    int size = rsmd.getColumnDisplaySize(i);
                    System.out.print(String.format("%" + size + "s|", value));
                }
                System.out.println();
            }
        }
    }
}
