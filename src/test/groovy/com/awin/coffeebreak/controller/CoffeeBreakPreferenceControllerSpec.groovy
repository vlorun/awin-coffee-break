package com.awin.coffeebreak.controller

import com.awin.coffeebreak.entity.CoffeeBreakPreference
import com.awin.coffeebreak.services.CoffeeBreakPreferenceService
import com.awin.coffeebreak.services.StaffMemberService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class CoffeeBreakPreferenceControllerSpec extends Specification {

    def "Get empty list of coffee break preferences for Today in default format"() {
        given: "The content format is null"
        def format = null

        and: "preferenceService will return an empty list"
        CoffeeBreakPreferenceService preferenceService = Mock(CoffeeBreakPreferenceService)
        StaffMemberService staffMemberService = Mock(StaffMemberService);
        preferenceService.getCurrentPreferences() >> List.of()

        and: "We construct the CoffeeBreakController"
        CoffeeBreakPreferenceController preferenceController = new CoffeeBreakPreferenceController(preferenceService, staffMemberService)

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

        and: "preferenceService will return a list of 1 CoffeeBreakPreference"
        CoffeeBreakPreferenceService preferenceService = Mock(CoffeeBreakPreferenceService)
        StaffMemberService staffMemberService = Mock(StaffMemberService)
        /*StaffMember staffMember = Stub(StaffMember)
        staffMember.getEmail() >> "t.smith@email.com"
        staffMember.getName() >> "t smith"
        staffMember.getId() >> 1
        staffMember.getSlackIdentifier() >> ""*/
        CoffeeBreakPreference breakPreference = Stub(CoffeeBreakPreference)
        /*breakPreference.setType("food")
        breakPreference.setSubType("sandwitch")
        HashMap<String, String> details = new HashMap<String, String>()
        breakPreference.setRequestedBy(staffMember)
        breakPreference.setRequestedDate( Instant.now())
        details.put("condiment", "ketchup")
        breakPreference.setDetails(details)*/
        breakPreference.getAsJson() >> "{}"
        preferenceService.getCurrentPreferences() >> List.of(breakPreference)

        and: "We construct the CoffeeBreakController"
        CoffeeBreakPreferenceController preferenceController = new CoffeeBreakPreferenceController(preferenceService, staffMemberService)

        when: "We call the today method"
        ResponseEntity<?> responseEntity = preferenceController.today(format)

        then: "We get a responses http status 200 "
        responseEntity.statusCode.value() == 200

        and: "returns html content type"
        responseEntity.headers.getFirst("content-type") == "application/json"

        and: "returns json response"
        responseEntity.getBody() == "{\"preferences\":[{}]}"
    }

    def "NotifyStaffMember"() {
    }
}
