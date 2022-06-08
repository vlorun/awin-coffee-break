package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.StaffMember;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotificationServiceFactory {
    public static NotificationService getService(StaffMember staffMember) {
        if (!staffMember.getSlackIdentifier().isEmpty()) {
            return new SlackNotifier();
        }

        return new EmailNotifier();
    }
}
