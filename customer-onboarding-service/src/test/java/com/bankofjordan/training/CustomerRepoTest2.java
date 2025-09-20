package com.bankofjordan.training;

import com.bankofjordan.training.repository.Customer;
import com.bankofjordan.training.repository.CustomerRepository;
import com.bankofjordan.training.repository.JDBCCustomerRepository;
import com.bankofjordan.training.usecases.open.Birth;
import com.bankofjordan.training.usecases.open.ResidenceAddress;
import com.bankofjordan.training.usecases.open.WealthSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Currency;

public class CustomerRepoTest2 {

    public static void main(String[] args) throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@192.168.1.142:1521:XE");
        dataSource.setUsername("pps");
        dataSource.setPassword("pps");
        dataSource.setMaxTotal(20);
        dataSource.setMinIdle(8);
        dataSource.setMaxConn(Duration.ofSeconds(30));


        CustomerRepository customerRepository = new JDBCCustomerRepository(dataSource);

        Birth birth = new Birth(LocalDate.of(1985, 10, 10), "Jordan");
        ResidenceAddress address = new ResidenceAddress("ammand", "amman", "amman", "Jordan", "111101");
        WealthSource wealthSource = new WealthSource(BigDecimal.valueOf(1000), Currency.getInstance("JOD"),
                "salary");
        Customer customer = new Customer("9851016621",
                "NID",
                "Mohammad Abdellatif",
                "Jordanian",
                "MALE",
                "7897797",
                birth,
                address,
                wealthSource);
        customerRepository.save(customer);
    }
}
