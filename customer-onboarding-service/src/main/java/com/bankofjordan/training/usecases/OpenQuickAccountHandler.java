package com.bankofjordan.training.usecases;

import com.bankofjordan.training.integrator.CivilDepIntegrator;
import com.bankofjordan.training.integrator.Screening;
import com.bankofjordan.training.repository.Customer;
import com.bankofjordan.training.repository.CustomerRepository;

import java.time.LocalDate;

public class OpenQuickAccountHandler {
    // Facade Design pattern
    //  interface for complex business (logic)

    private final CustomerRepository customerRepository;
    private final CivilDepIntegrator civilDepIntegrator;
    private final Screening screening;
    private final CIFGenerator cifGenerator;
    private final AccountProducer accountProducer;

    public OpenQuickAccountHandler(CustomerRepository customerRepository,
                                   CivilDepIntegrator civilDepIntegrator,
                                   Screening screening,
                                   CIFGenerator cifGenerator,
                                   AccountProducer accountProducer) {
        this.customerRepository = customerRepository;
        this.civilDepIntegrator = civilDepIntegrator;
        this.screening = screening;
        this.cifGenerator = cifGenerator;
        this.accountProducer = accountProducer;
    }

    public OpenQuickAccountOutput open(OpenQuickAccountInput input) {
        validate(input);
        String cif = cifGenerator.generate();
        Account account = accountProducer.produce(cif);
        saveCustomer(input, cif);
        return new OpenQuickAccountOutput(cif, account.getNumber(), account.getIban());
    }

    private void validate(OpenQuickAccountInput input) {
        validateCustomerExistence(input);
        if (isAdult(input))
            throw new IllegalArgumentException("Customer should be 18+ years old");
        validateCitizen(input);
        screenCustomer(input);
    }

    private void validateCitizen(OpenQuickAccountInput input) {
        civilDepIntegrator.validateCitizen(input.getCustomerId(), input.getCustomerIdType());
    }

    private void screenCustomer(OpenQuickAccountInput input) {
        Screening.Input screeningInput = new Screening.Input(input.getCustomerId(), input.getName());
        screening.screen(screeningInput);
    }

    private boolean isAdult(OpenQuickAccountInput input) {
        Birth birth = input.getBirth();
        LocalDate dateOfBirth = birth.getDateOfBirth();
        return dateOfBirth.isAfter(LocalDate.now().minusYears(18));
    }

    private void validateCustomerExistence(OpenQuickAccountInput input) {
        if (customerRepository.isRegistered(input.getCustomerId(), input.getCustomerIdType()))
            throw new IllegalStateException("Customer already registered");
    }

    private void saveCustomer(OpenQuickAccountInput input, String cif) {
        Customer customer = new Customer(input.getCustomerId(),
                input.getCustomerIdType(),
                input.getName(),
                input.getNationality(),
                input.getGender(),
                cif,
                input.getBirth(),
                input.getResidenceAddress(),
                input.getWealthSource()
        );
        customerRepository.save(customer);
    }
}

