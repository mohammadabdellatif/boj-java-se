package com.bankofjordan.services.open;

import org.apache.commons.lang3.StringUtils;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

public class DigitalBranchAccountProducer implements AccountProducer {

    private final String bankCode;
    private final String branch;

    public DigitalBranchAccountProducer(String bankCode, String branch) {
        this.bankCode = bankCode;
        this.branch = branch;
    }

    @Override
    public Account produce(String cif) {
        String account = cif + "500";
        // Using the Builder pattern
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.JO)
                .bankCode(bankCode)
                .accountNumber(StringUtils.leftPad(account, 18, "0"))
                .branchCode(branch)
                .build();
        return new Account(account, iban.toString());
    }
}
