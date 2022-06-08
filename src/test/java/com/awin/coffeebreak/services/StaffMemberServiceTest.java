package com.awin.coffeebreak.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StaffMemberServiceTest {

    @Autowired
    StaffMemberService service;

    @Test
    void getCurrentPreferences() {
        assertNotNull(service.findById(1));
    }
}