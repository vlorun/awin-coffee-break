package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.StaffMember;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceFactoryTest {

    @Test
    void getServiceWillReturnEmailNotifierWhenSlackNotDefined() {
        StaffMember staffMember = new StaffMember();
        staffMember.setSlackIdentifier("");
        assertTrue(NotificationServiceFactory.getService(staffMember) instanceof EmailNotifier);
    }
}