package com.nackademin.webshopbackend.listener;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-07
 * Time:  21:58
 * Project: webshop-backend
 * Copyright: MIT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO {
	@JsonProperty("reference")
	private String reference;
	@JsonProperty("paymentId")
	private String paymentId;
	@JsonProperty("status")
	private String status;
	@JsonCreator
	public PaymentDTO(@JsonProperty("reference") String reference, @JsonProperty("paymentId") String paymentId,
	               @JsonProperty("status") String status) {
		this.reference = reference;
		this.paymentId = paymentId;
		this.status = status;
	}
	public String getReference() {
		return reference;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getStatus() {
		return status;
	}
}
