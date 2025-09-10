package com.bankofjordan.training;

import com.bankofjordan.training.integrator.*;
import com.bankofjordan.training.repository.CustomerRepository;
import com.bankofjordan.training.repository.InMemoryCustomerRepository;
import com.bankofjordan.training.usecases.open.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {
        OpenQuickAccountHandler handler = createOpenQuickAccountHandler();
        OpenQuickAccountInput input = createInput();
        OpenQuickAccountOutput output = handler.open(input);
        System.out.println(output.getAccountNumber());
        System.out.println(output.getCif());
        System.out.println(output.getIban());
    }

    // factory method
    private static OpenQuickAccountHandler createOpenQuickAccountHandler() {
        // Polymorphism (dependency construction)
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        CivilDepIntegrator civilDepIntegrator = new AlwaysValidCivilDepIntegrator();
//        Screening screening = new AlwaysFailScreening();
        Screening screening = screening();
        CIFGenerator cifGenerator = new TimeStampCIFGenerator();
        AccountProducer accountProducer = new DigitalBranchAccountProducer("BJOR", "1101");

        OpenQuickAccountHandler handler = new OpenQuickAccountHandler(customerRepository,
                civilDepIntegrator,
                screening,
                cifGenerator,
                accountProducer);
        return handler;
    }

    private static OpenQuickAccountInput createInput() {
        Birth birth = new Birth(LocalDate.of(2005, 10, 17), "KW");
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

    // factory method
    public static Screening screening() {
        // anonymous class
        Screening screening = new Screening() {

            @Override
            public void screen(Input input) {
//                try {
//                    Thread.currentThread().sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                throw new IllegalStateException("The customer is terrorism suspect");
            }
        };
        return screening;
    }
}
