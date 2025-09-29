package com.bankofjordan.services.configuration;

import com.bankofjordan.services.commons.TimeStampCIFGenerator;
import com.bankofjordan.services.entities.CustomerEntityRepository;
import com.bankofjordan.services.integrator.CivilDepIntegrator;
import com.bankofjordan.services.integrator.Screening;
import com.bankofjordan.services.open.AccountProducer;
import com.bankofjordan.services.open.CIFGenerator;
import com.bankofjordan.services.open.DigitalBranchAccountProducer;
import com.bankofjordan.services.repository.CustomerRepository;
import com.bankofjordan.services.repository.JPACustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class OASConfiguration {

    @Bean
    public CIFGenerator cifGenerator() {
        return new TimeStampCIFGenerator();
    }

    @Bean
    public AccountProducer accountProducer(
            @Value("${openAccount.bank.code:BLOM}") String bankCode,
            @Value("${openAccount.bank.branch}") String branchCode) {
        return new DigitalBranchAccountProducer(bankCode, branchCode);
    }

    @Bean
    public CivilDepIntegrator civilDepIntegrator1() {
        return (customerId, customerIdType) -> {
        };
    }

    @Bean
    public CustomerRepository customerRepository(CustomerEntityRepository customerEntityRepository) {
        return new JPACustomerRepository(customerEntityRepository);
    }


}
