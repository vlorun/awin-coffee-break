package com.awin.coffeebreak.services

import com.awin.coffeebreak.entity.CoffeeBreakPreference
import com.awin.coffeebreak.entity.StaffMember
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class SlackNotifierSpec extends Specification {

    def "testStatusOfNotificationIsTrue"() {
        given:
        def staff = new StaffMember()
        staff.setSlackIdentifier("ABC123")
        def details = new HashMap();
        details.put("sugar", "yes");
        def preference = new CoffeeBreakPreference("drink", "coffee", staff, details)

        def notificationService = new SlackNotifier()

        when:
        def status = notificationService.notifyStaffMember(staff, [preference])

        then:
        assertThat(status).isTrue()
    }

    def "testThrowsExceptionWhenCannotNotify"() {
        given:
        def staff = new StaffMember()
        staff.setSlackIdentifier("");
        def details = new HashMap();
        details.put("sugar", "yes");
        def preference = new CoffeeBreakPreference("drink", "tea", staff, details)
        def notificationService = new SlackNotifier()

        when:
        def status = notificationService.notifyStaffMember(staff, [preference])

        then:
        thrown(RuntimeException.class)
    }
}
