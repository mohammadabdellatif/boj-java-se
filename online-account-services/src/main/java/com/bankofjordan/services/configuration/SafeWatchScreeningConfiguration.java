package com.bankofjordan.services.configuration;

import com.bankofjordan.services.integrator.Screening;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("safewatch")
public class SafeWatchScreeningConfiguration {

    @Bean
    public Screening safeWatchScreening() {
        return input -> System.out.println("integrating with safewatch");
    }
}
