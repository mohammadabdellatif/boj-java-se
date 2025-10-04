package com.bankofjordan.services.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenQuickAccountJsonResponse {
    private final String cif;
    private final String accountNumber;
    private final String iban;
}
