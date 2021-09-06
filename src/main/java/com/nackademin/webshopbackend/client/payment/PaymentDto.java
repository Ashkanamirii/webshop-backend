package com.nackademin.webshopbackend.client.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDto {
    @JsonProperty(value = "reference")
    private String reference;
    @JsonProperty(value = "amount")
    private long amount;

    public PaymentDto() {
    }

    public PaymentDto(String reference, long amount) {
        this.reference = reference;
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public long getAmount() {
        return amount;
    }
}
