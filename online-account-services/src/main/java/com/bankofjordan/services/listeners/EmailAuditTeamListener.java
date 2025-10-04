package com.bankofjordan.services.listeners;

import com.bankofjordan.services.open.NewQuickAccountEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

// subscriber to an event
@Slf4j
public class EmailAuditTeamListener {

    @EventListener(NewQuickAccountEvent.class)
    public void onNewQuickAccount(NewQuickAccountEvent event) {
        log.info("I am going to send an email to the audit team for this customer: " + event.getCif());
    }
}
