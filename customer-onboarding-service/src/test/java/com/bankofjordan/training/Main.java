package com.bankofjordan.training;

import com.bankofjordan.training.usecases.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {
        OpenQuickAccountInput input = createInput();

//        OpenQuickAccountHandler handler = new OpenQuickAccountHandler(null);
//        OpenQuickAccountOutput output = handler.open(input);
//        System.out.println(output);
    }

    private static OpenQuickAccountInput createInput() {
        Birth birth = new Birth(LocalDate.of(1985, 10, 17), "KW");
        ResidenceAddress residenceAddress = new ResidenceAddress("Khalifa Street", "Al-Mansoura", "Jordan", "Jordan", "12345");
        ContactInfo contactInfo = new ContactInfo("0799409461", "mohammad.s.abdellatif@gmail.com");
        WealthSource wealthSource = new WealthSource(BigDecimal.valueOf(1000), Currency.getInstance("JOD"), "salary");
        OpenQuickAccountInput input = new OpenQuickAccountInput("9851017721",
                "NID",
                "mohammad shawkat abdullah abdellatif",
                "male",
                "JOR",
                birth,
                residenceAddress,
                contactInfo,
                wealthSource);
        return input;
    }
}
