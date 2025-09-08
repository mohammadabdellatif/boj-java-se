package com.bankofjordan.training.integrator;

public class AllowAllScreening implements Screening {
    @Override
    public void screen(Input input) {
        // always accept
    }
}
