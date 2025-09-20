package com.bankofjordan.training;

import com.bankofjordan.training.repository.CustomerRepository;
import com.bankofjordan.training.repository.JDBCCustomerRepository;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.Duration;

public class CustomerRepoTest {

    public static void main(String[] args) throws SQLException {
//        OracleDataSource dataSource = new OracleDataSource();
//        dataSource.setURL("jdbc:oracle:thin:@192.168.1.142:1521:XE");
//        dataSource.setUser("pps");
//        dataSource.setPassword("pps");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@192.168.1.142:1521:XE");
        dataSource.setUsername("pps");
        dataSource.setPassword("pps");
        dataSource.setMaxTotal(20);
        dataSource.setMinIdle(8);
        dataSource.setMaxConn(Duration.ofSeconds(30));


        CustomerRepository customerRepository = new JDBCCustomerRepository(dataSource);

        System.out.println(customerRepository.isRegistered("' or '1' = '1", "' or '1' = '1"));
    }
}
