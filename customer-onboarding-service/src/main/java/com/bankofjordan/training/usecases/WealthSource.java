package com.bankofjordan.training.usecases;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class WealthSource {
    private final List<String> INCOME_TYPES = Arrays.asList("salary", "self_employed");

    private final BigDecimal amount;
    private final Currency currency;
    private final String incomeType;

    public WealthSource(BigDecimal amount, Currency currency, String incomeType) {
        if(!isValidAmount(amount))
            throw new IllegalArgumentException("Invalid amount");
        if(currency == null)
            throw new IllegalArgumentException("Invalid currency");
        if(!INCOME_TYPES.contains(incomeType))
            throw new IllegalArgumentException("Invalid income type");
        this.amount = amount;
        this.currency = currency;
        this.incomeType = incomeType;
    }

    private static boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getIncomeType() {
        return incomeType;
    }
}
