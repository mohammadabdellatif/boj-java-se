package com.bankofjordan.services.open;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class NewQuickAccountEvent {
    private String cif;
}
