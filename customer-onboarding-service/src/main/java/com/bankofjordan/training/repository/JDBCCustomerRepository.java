package com.bankofjordan.training.repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCCustomerRepository implements CustomerRepository {
    private static final String INSERT_CUSTOMER = "insert into CUSTOMER (ID, \n" +
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
            "values(CUST_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String FIND_CUSTOMER_BY_ID_AND_ID_TYPE = "select 1 from customer " +
            "where customerid=? " +
            "and customeridtype=?";
    // factory for constructing database connections, and manage the connections in a pool
    private final DataSource dataSource;

    public JDBCCustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isRegistered(String customerId, String customerIdType) {
        try (Connection conn = dataSource.getConnection()) {
            try (CallableStatement cb = conn.prepareCall("{call CHECK_CUSTOMER_ID_EXISTS(?,?,?)}")) {
                cb.setString(1, customerId);
                cb.setString(2, customerIdType);

                cb.registerOutParameter(3, Types.BOOLEAN);
                cb.execute();

                return cb.getBoolean(3);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
//        // closing of the connection means to return the connection to the pool, no actual closing
//        try (Connection conn = dataSource.getConnection()) {
//            try (PreparedStatement ps = conn.prepareStatement(FIND_CUSTOMER_BY_ID_AND_ID_TYPE)) {
//                ps.setString(1, customerId);
//                ps.setString(2, customerIdType);
//                try (ResultSet rs = ps.executeQuery()) {
//                    return rs.next();
//                }
//            }
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        }
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = dataSource.getConnection()) {
            // auto commit
            insertCustomer(customer, connection);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void save(List<Customer> customers) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER)) {
                for (Customer customer : customers) {
                    setInsertParameters(customer, ps);
                    ps.addBatch();
                }
                // the number of rows updated by each SQL statement in the batch
                ps.executeBatch();
            }
            connection.commit();
        } catch (BatchUpdateException e) {
            int[] updateCounts = e.getUpdateCounts();
            Set<Integer> failedSQLIndexes = new HashSet<>();
            for (int i = 0; i < updateCounts.length; i++) {
                if (updateCounts[i] == Statement.EXECUTE_FAILED ) {
                    failedSQLIndexes.add(updateCounts[i]);
                }
            }
            throw new IllegalStateException(e);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
//        try (Connection connection = dataSource.getConnection()) {
//            connection.setAutoCommit(false);
//            for (Customer customer : customers) {
//                insertCustomer(customer, connection);
//            }
//            connection.commit();
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        }
    }

    private void insertCustomer(Customer customer, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER)) {
            setInsertParameters(customer, ps);
            ps.executeUpdate();
        }
    }

    private void setInsertParameters(Customer customer, PreparedStatement ps) throws SQLException {
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
    }
}
