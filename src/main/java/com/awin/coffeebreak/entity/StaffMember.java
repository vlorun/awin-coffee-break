package com.awin.coffeebreak.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "staff_member")
public class StaffMember {

    @Id
    @GeneratedValue
    Integer id;

    @Column
    String name;

    @Column
    String email;

    @Column
    String slackIdentifier;

    @OneToMany
    List<CoffeeBreakPreference> coffeeBreakPreferences = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getSlackIdentifier() {
        return slackIdentifier;
    }

    public void setSlackIdentifier(final String slackIdentifier) {
        this.slackIdentifier = slackIdentifier;
    }

    public List<CoffeeBreakPreference> getCoffeeBreakPreferences() {
        return coffeeBreakPreferences;
    }

    public void setCoffeeBreakPreferences(
          final List<CoffeeBreakPreference> coffeeBreakPreferences
    ) {
        this.coffeeBreakPreferences = coffeeBreakPreferences;
    }
}
