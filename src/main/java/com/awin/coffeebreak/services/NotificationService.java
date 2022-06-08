package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import com.awin.coffeebreak.entity.StaffMember;

import java.util.List;

public interface NotificationService {
    public boolean notifyStaffMember(StaffMember staffMember, List<CoffeeBreakPreference> preferences);
}
