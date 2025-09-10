package com.bankofjordan.training.usecases.open;

public class Account extends Object {
    private String number;
    private String iban;

    public Account(String number, String iban) {
        this.number = number;
        this.iban = iban;
    }

    public String getNumber() {
        return number;
    }

    public String getIban() {
        return iban;
    }

    @Override
    public String toString() {
        return "Account [number=" + number + ", iban=" + iban + "]";
    }
}
