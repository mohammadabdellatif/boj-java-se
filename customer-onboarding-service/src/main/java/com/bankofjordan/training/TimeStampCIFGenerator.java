package com.bankofjordan.training;

import com.bankofjordan.training.usecases.open.CIFGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampCIFGenerator implements CIFGenerator {

    @Override
    public String generate() {
        LocalDateTime now = LocalDateTime.now();
        String cif = now.format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        return cif;
    }
}
