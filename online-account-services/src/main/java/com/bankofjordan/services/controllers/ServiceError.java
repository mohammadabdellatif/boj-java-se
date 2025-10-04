package com.bankofjordan.services.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceError {
    private String code;
    private String message;
}
