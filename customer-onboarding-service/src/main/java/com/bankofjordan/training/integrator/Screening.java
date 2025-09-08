package com.bankofjordan.training.integrator;

public interface Screening {

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
