package com.awin.coffeebreak.controller

import com.awin.coffeebreak.entity.CoffeeBreakPreference
import com.awin.coffeebreak.entity.StaffMember
import com.awin.coffeebreak.repository.CoffeeBreakPreferenceRepository
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.Instant

class CoffeeBreakPreferenceControllerSpec extends Specification {

    def "Get empty list of coffee break preferences for Today in default format"() {
        given: "The content format is null"
        def format = null

        and: "repository will return an empty list"
        CoffeeBreakPreferenceRepository repository = Mock(CoffeeBreakPreferenceRepository)
        repository.getPreferencesForToday() >> List.of()

        and: "We construct the CoffeeBreakController"
        CoffeeBreakPreferenceController preferenceController = new CoffeeBreakPreferenceController(repository)

        when: "We call the today method"
        ResponseEntity<?> responseEntity = preferenceController.today(format)

        then: "We get a responses http status 200 "
        responseEntity.statusCode.value() == 200

        and: "and returns html content type"
        responseEntity.headers.getFirst("content-type") == "text/html"
    }

    def "Get list of coffee break preferences for Today in json format"() {
        given: "The content format is null"
        def format = "json"

        and: "repository will return a list of 1 coffee break preference"
        CoffeeBreakPreferenceRepository repository = Mock(CoffeeBreakPreferenceRepository)
        StaffMember staffMember = Stub(StaffMember)
        staffMember.getEmail() >> "t.smith@email.com"
        staffMember.getName() >> "t smith"
        staffMember.getId() >> 1
        staffMember.getSlackIdentifier() >> ""
        CoffeeBreakPreference breakPreference = Stub(CoffeeBreakPreference)
        breakPreference.getType() >> "food"
        breakPreference.getSubType() >> "sandwitch"
        HashMap<String, String> details = new HashMap<String, String>()
        breakPreference.getDetails() >> details.put("condiment", "ketchup")
        breakPreference.getRequestedBy() >> staffMember
        breakPreference.getRequestedDate() >> Instant.now()
        repository.getPreferencesForToday() >> List.of(breakPreference)

        and: "We construct the CoffeeBreakController"
        CoffeeBreakPreferenceController preferenceController = new CoffeeBreakPreferenceController(repository)

        when: "We call the today method"
        ResponseEntity<?> responseEntity = preferenceController.today(format)

        then: "We get a responses http status 200 "
        responseEntity.statusCode.value() == 200

        and: "returns html content type"
        responseEntity.headers.getFirst("content-type") == "application/json"

        and: "returns json response"
        responseEntity.getBody() == "dsaf"
    }

    def "NotifyStaffMember"() {
    }
}
