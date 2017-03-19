package com.github.apro.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @NotNull
    private Double amount;
    @NotNull
    private Long timeStamp;
}
