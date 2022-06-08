package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.StaffMember;
import com.awin.coffeebreak.repository.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffMemberServiceImpl implements StaffMemberService {

    @Autowired
    StaffMemberRepository memberRepository;

    @Override
    public Optional<StaffMember> findById(int id) {
        return memberRepository.findById(id);
    }
}
