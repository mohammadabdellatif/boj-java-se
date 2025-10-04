package com.bankofjordan.services.controllers;

import com.bankofjordan.services.entities.CustomerEntity;
import com.bankofjordan.services.open.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;

@RestController
@RequestMapping(path = "/api/v1/")
public class OpenAccountController {

    private final OpenQuickAccountHandler openQuickAccountHandler;

    public OpenAccountController(OpenQuickAccountHandler openQuickAccountHandler) {
        this.openQuickAccountHandler = openQuickAccountHandler;
    }

    @PostMapping(path = "/openAccount",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OpenQuickAccountJsonResponse openAccount(@RequestBody OpenAccountJsonRequest request) {
        return execute(request);
    }

    @PostMapping(path = "/openAccount",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public OpenQuickAccountJsonResponse openAccountXml(@RequestBody OpenAccountJsonRequest request) {
        return execute(request);
    }

    private OpenQuickAccountJsonResponse execute(OpenAccountJsonRequest request) {
        OpenQuickAccountOutput output = openQuickAccountHandler.open(mapJsonToHandlerRequest(request));
        return new OpenQuickAccountJsonResponse(output.getCif(), output.getAccountNumber(), output.getIban());
    }

    private static OpenQuickAccountInput mapJsonToHandlerRequest(OpenAccountJsonRequest request) {
        OpenQuickAccountInput openAccountInput = new OpenQuickAccountInput(
                request.getCustomerId(),
                request.getCustomerIdType(),
                request.getName(),
                request.getGender(),
                request.getNationality(),
                mapBirth(request),
                mapResidenceAddress(request),
                mapContactInfo(request),
                mapWealthSource(request));
        return openAccountInput;
    }

    private static WealthSource mapWealthSource(OpenAccountJsonRequest request) {
        WealthSource wealthSource = new WealthSource(
                request.getWealthSource().getAmount(),
                Currency.getInstance(request.getWealthSource().getCurrency()),
                request.getWealthSource().getIncomeType());
        return wealthSource;
    }

    private static ContactInfo mapContactInfo(OpenAccountJsonRequest request) {
        ContactInfo contactInfo = new ContactInfo(
                request.getContactInfo().getMobileNo(),
                request.getContactInfo().getEmail());
        return contactInfo;
    }

    private static Birth mapBirth(OpenAccountJsonRequest request) {
        Birth birth = new Birth(request.getBirth(), request.getBirthPlace());
        return birth;
    }

    private static ResidenceAddress mapResidenceAddress(OpenAccountJsonRequest request) {
        ResidenceAddress address = new ResidenceAddress(request.getResidenceAddress().getStreet(),
                request.getResidenceAddress().getRegion(),
                request.getResidenceAddress().getCity(),
                request.getResidenceAddress().getCountry(),
                request.getResidenceAddress().getPostalCode());
        return address;
    }
}
