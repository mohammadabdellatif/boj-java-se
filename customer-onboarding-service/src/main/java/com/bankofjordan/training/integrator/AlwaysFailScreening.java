package com.bankofjordan.training.integrator;

public class AlwaysFailScreening implements Screening {
    @Override
    public void screen(Input input) {
        throw new IllegalStateException("The customer is terrorism suspect");
    }
}
