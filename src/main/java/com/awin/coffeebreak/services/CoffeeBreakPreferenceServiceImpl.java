package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import com.awin.coffeebreak.repository.CoffeeBreakPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeBreakPreferenceServiceImpl implements CoffeeBreakPreferenceService {
    @Autowired
    CoffeeBreakPreferenceRepository repository;

    @Override
    public List<CoffeeBreakPreference> getCurrentPreferences() {
        return repository.getPreferencesForToday();
    }
}
