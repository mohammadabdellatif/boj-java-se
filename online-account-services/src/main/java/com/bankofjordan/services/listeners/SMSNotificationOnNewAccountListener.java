package com.bankofjordan.services.listeners;

import com.bankofjordan.services.open.NewQuickAccountEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class SMSNotificationOnNewAccountListener {

    @EventListener(NewQuickAccountEvent.class)
    @Async
    public void onNewQuickAccount(NewQuickAccountEvent newQuickAccountEvent) {
        log.info("Push SMS to the customer: " + newQuickAccountEvent.getCif());
    }
}
