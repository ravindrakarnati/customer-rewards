package com.retailer.customer.rewards.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@RequiredArgsConstructor
public class CustomerTransaction {

    private final Integer customerId;
    private final LocalDate transactionDate;
    private final Integer transactionAmount;

}