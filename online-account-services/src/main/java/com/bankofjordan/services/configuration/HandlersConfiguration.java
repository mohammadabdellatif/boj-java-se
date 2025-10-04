package com.bankofjordan.services.configuration;

import com.bankofjordan.services.integrator.CivilDepIntegrator;
import com.bankofjordan.services.integrator.Screening;
import com.bankofjordan.services.open.AccountProducer;
import com.bankofjordan.services.open.CIFGenerator;
import com.bankofjordan.services.open.OpenQuickAccountHandler;
import com.bankofjordan.services.repository.CustomerRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlersConfiguration {

    @Bean
    public OpenQuickAccountHandler openQuickAccountHandler(
            CustomerRepository customerRepository,
            Screening screening,
            CivilDepIntegrator civilDepIntegrator,
            CIFGenerator cifGenerator,
            AccountProducer accountProducer,
            ApplicationEventPublisher eventPublisher) {
        return new OpenQuickAccountHandler(customerRepository,
                civilDepIntegrator,
                screening,
                cifGenerator,
                accountProducer,
                eventPublisher);
    }
}
