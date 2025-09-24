package com.bankofjordan.services.open;

import com.bankofjordan.services.commons.Handler;
import com.bankofjordan.services.integrator.CivilDepIntegrator;
import com.bankofjordan.services.integrator.Screening;
import com.bankofjordan.services.repository.Customer;
import com.bankofjordan.services.repository.CustomerRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class OpenQuickAccountHandler implements Handler {

    // To get the logger with the name or create a new one
    private static final Logger LOGGER = Logger.getLogger("open_quick_account");

    static {
        try {
            Path logPath = Paths.get(".", "logs", "app.log");
            if (Files.notExists(logPath.getParent())) {
                Files.createDirectories(logPath.getParent());
            }
            FileHandler handler = new FileHandler(logPath.toString());
            Formatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
            LOGGER.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
        LOGGER.info("Opening Quick Account with inputs: " + input);
        validate(input);
        LOGGER.info("Valid Request, generate CIF");
        String cif = cifGenerator.generate();
        LOGGER.info("Generated CIF: " + cif);
        Account account = accountProducer.produce(cif);
        LOGGER.info("Generated Account: " + account);
        saveCustomer(input, cif);
        LOGGER.info("Done");
        return new OpenQuickAccountOutput(cif, account.getNumber(), account.getIban());
    }

    private void validate(OpenQuickAccountInput input) {
        LOGGER.info("Validating customer");
        validateCustomerExistence(input);
        if (isAdult(input))
            throw new IllegalArgumentException("Customer should be 18+ years old");
        validateCitizen(input);
        screenCustomer(input);
    }

    private void validateCitizen(OpenQuickAccountInput input) {
        LOGGER.info("Validating citizenship");
        civilDepIntegrator.validateCitizen(input.getCustomerId(), input.getCustomerIdType());
    }

    private void screenCustomer(OpenQuickAccountInput input) {
        LOGGER.info("Screen customer");
        Screening.Input screeningInput = new Screening.Input(input.getCustomerId(), input.getName());
        screening.screen(screeningInput);
    }

    private boolean isAdult(OpenQuickAccountInput input) {
        Birth birth = input.getBirth();
        LocalDate dateOfBirth = birth.getDateOfBirth();
        return dateOfBirth.isAfter(LocalDate.now().minusYears(18));
    }

    private void validateCustomerExistence(OpenQuickAccountInput input) {
        LOGGER.info("Checking if customer already registered");
        if (customerRepository.isRegistered(input.getCustomerId(), input.getCustomerIdType()))
            throw new IllegalStateException("Customer already registered");
    }

    private void saveCustomer(OpenQuickAccountInput input, String cif) {
        LOGGER.info("Saving customer");
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

