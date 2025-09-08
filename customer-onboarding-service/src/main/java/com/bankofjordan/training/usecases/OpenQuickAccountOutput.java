package com.bankofjordan.training.usecases;

public class OpenQuickAccountOutput {

    private final String cif;
    private final String accountNumber;
    private final String iban;

    public OpenQuickAccountOutput(String cif, String accountNumber, String iban) {
        this.cif = cif;
        this.accountNumber = accountNumber;
        this.iban = iban;
    }

    public String getCif() {
        return cif;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getIban() {
        return iban;
    }
}
