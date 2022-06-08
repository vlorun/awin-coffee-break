package com.awin.coffeebreak.controller;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import com.awin.coffeebreak.entity.StaffMember;
import com.awin.coffeebreak.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeBreakPreferenceController {

    public CoffeeBreakPreferenceService coffeeBreakPreferenceService;
    public StaffMemberService staffMemberService;

    public CoffeeBreakPreferenceController(
          CoffeeBreakPreferenceService coffeeBreakPreferenceService,
          StaffMemberService staffMemberService
    ) {
        this.coffeeBreakPreferenceService = coffeeBreakPreferenceService;
        this.staffMemberService = staffMemberService;
    }

    /**
     * Publishes the list of preferences in the requested format
     */
    @GetMapping(path = "/today")
    public ResponseEntity<?> today(@RequestParam("format") String format) {
        if (format == null) {
            format = "html";
        }

        List<CoffeeBreakPreference> t = coffeeBreakPreferenceService.getCurrentPreferences();

        String responseContent;
        String contentType = "text/html";

        switch (format) {
            case "json":
                responseContent = getJsonForResponse(t);
                contentType = "application/json";
                break;

            case "xml":
                responseContent = getXmlForResponse(t);
                contentType = "text/xml";
                break;

            default:
                String formattedPreferences = getHtmlForResponse(t);
                return ResponseEntity.ok().contentType(MediaType.valueOf(contentType))
                      .body(formattedPreferences);
        }

        return ResponseEntity.ok()
              .contentType(MediaType.valueOf(contentType))
              .body(responseContent);
    }

    @GetMapping("/notifyStaffMember")
    public ResponseEntity<Object> notifyStaffMember(@RequestParam("staffMemberId") int id) {
        Optional<StaffMember> staffMember = this.staffMemberService.findById(id);

        List<CoffeeBreakPreference> preferences = new ArrayList<>();


        NotificationService notifier = NotificationServiceFactory.getService(staffMember.get());
        boolean ok = notifier.notifyStaffMember(staffMember.get(), preferences);

        return ResponseEntity.ok(ok ? "OK" : "NOT OK");
    }

    private String getJsonForResponse(final List<CoffeeBreakPreference> list) {
        String responseJson = "{\"preferences\":[";

        for (final CoffeeBreakPreference p : list) {
            responseJson += p.getAsJson();
        }

        return responseJson += "]}";
    }

    private String getXmlForResponse(List<CoffeeBreakPreference> list) {
        String responseJson = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        responseJson += "<Preferences>";

        for (final CoffeeBreakPreference p : list) {
            responseJson += p.getAsXml();
        }

        responseJson += "</Preferences>";

        return responseJson;
    }

    private String getHtmlForResponse(final List<CoffeeBreakPreference> list) {
        String responseJson = "<ul>";

        for (final CoffeeBreakPreference p : list) {
            responseJson += p.getAsListElement();
        }

        return responseJson + "</ul>";
    }
}
