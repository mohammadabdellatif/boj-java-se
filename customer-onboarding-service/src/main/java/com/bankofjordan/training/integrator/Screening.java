package com.bankofjordan.training.integrator;

// Functional interface
public interface Screening {

    public static final Screening ALWAYS_ACCEPT = (input) -> { };

    void screen(Input input);

    // Transfer Object
    class Input {
        private final String nationalId;
        private final String customerName;

        public Input(String nationalId, String customerName) {
            this.nationalId = nationalId;
            this.customerName = customerName;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getNationalId() {
            return nationalId;
        }
    }
}
