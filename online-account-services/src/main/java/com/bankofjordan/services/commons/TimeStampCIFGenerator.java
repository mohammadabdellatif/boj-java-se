package com.bankofjordan.services.commons;


import com.bankofjordan.services.open.CIFGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampCIFGenerator implements CIFGenerator {

    @Override
    public String generate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
    }
}
