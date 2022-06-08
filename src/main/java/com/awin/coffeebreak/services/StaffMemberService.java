package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.StaffMember;

import java.util.Optional;

public interface StaffMemberService {
    public Optional<StaffMember> findById(int id);

}
