package com.bankofjordan.training.repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class JDBCCustomerRepository implements CustomerRepository {
    // factory for constructing database connections, and manage the connections in a pool
    private final DataSource dataSource;

    public JDBCCustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isRegistered(String customerId, String customerIdType) {
        // closing of the connection means to return the connection to the pool, no actual closing
        try (Connection conn = dataSource.getConnection()) {
            String sql = "select 1 from customer " +
                    "where customerid=? " +
                    "and customeridtype=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, customerId);
                ps.setString(2, customerIdType);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
//            try (Statement statement = conn.createStatement()) {
//                // SQL injection
//                String query = "select 1 from customer" +
//                        " where customerid = '" + customerId + "' " +
//                        "and customeridtype = '" + customerIdType + "'";
//                try (ResultSet rs = statement.executeQuery(query)) {
//                    return rs.next();
//                }
//            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = dataSource.getConnection()) {
            String insert = "insert into CUSTOMER (ID, \n" +
                    "                      CUSTOMERID, \n" +
                    "                      CUSTOMERIDTYPE, \n" +
                    "                      CUSTOMERNAME,\n" +
                    "                      NATIONALITY,\n" +
                    "                      GENDER,\n" +
                    "                      CIF, \n" +
                    "                      DATEOFBIRTH,\n" +
                    "                      COUNTRY_OF_BIRTH, \n" +
                    "                      STREET,\n" +
                    "                      REGION,\n" +
                    "                      CITY,\n" +
                    "                      COUNTRY,\n" +
                    "                      POSTALCODE,\n" +
                    "                      AMOUNT, \n" +
                    "                      CURRENCY,\n" +
                    "                      INCOMETYPE)\n" +
                    "values((select max(id)+1 from customer),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(insert)) {
                ps.setString(1, customer.getCustomerId());
                ps.setString(2, customer.getCustomerIdType());
                ps.setString(3, customer.getCustomerName());
                ps.setString(4, customer.getNationality());
                ps.setString(5, customer.getGender());
                ps.setString(6, customer.getCif());
                ps.setDate(7, Date.valueOf(customer.getBirth().getDateOfBirth()));
                ps.setString(8, customer.getBirth().getCountry());
                ps.setString(9, customer.getAddress().getStreet());
                ps.setString(10, customer.getAddress().getRegion());
                ps.setString(11, customer.getAddress().getCity());
                ps.setString(12, customer.getAddress().getCountry());
                ps.setString(13, customer.getAddress().getPostalCode());
                ps.setBigDecimal(14, customer.getWealthSource().getAmount());
                ps.setString(15, customer.getWealthSource().getCurrency().getCurrencyCode());
                ps.setString(16, customer.getWealthSource().getIncomeType());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
