package com.bankofjordan.training;

import com.bankofjordan.training.usecases.open.Account;
import com.bankofjordan.training.usecases.open.DigitalBranchAccountProducer;

public class AccountProducerTest {

    public static void main(String[] args) {
        DigitalBranchAccountProducer producer = new DigitalBranchAccountProducer("BJOR", "1101");
        Account account = producer.produce("250908191000");
        System.out.println(account.getNumber());
        System.out.println(account.getIban());
    }
}
