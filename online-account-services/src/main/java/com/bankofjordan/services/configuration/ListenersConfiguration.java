package com.bankofjordan.services.configuration;

import com.bankofjordan.services.listeners.EmailAuditTeamListener;
import com.bankofjordan.services.listeners.SMSNotificationOnNewAccountListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ListenersConfiguration {

    @Bean
    public EmailAuditTeamListener emailAuditTeamListener() {
        return new EmailAuditTeamListener();
    }

    @Bean
    public SMSNotificationOnNewAccountListener smsNotificationOnNewAccountListener() {
        return new SMSNotificationOnNewAccountListener();
    }
}
