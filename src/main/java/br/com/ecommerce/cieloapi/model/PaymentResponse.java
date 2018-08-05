package br.com.ecommerce.cieloapi.model;

import cieloecommerce.sdk.ecommerce.Payment;
import cieloecommerce.sdk.ecommerce.SaleResponse;
import cieloecommerce.sdk.ecommerce.request.CieloError;

public class PaymentResponse {
	
	public PaymentResponse() {}
	
	public PaymentResponse(SaleResponse response,Payment payment) {
		this.saleResponse = response;
		this.payment = payment;
	}
	
	public PaymentResponse(CieloError error) {
		this.cieloErro = error;
	}
	
	private SaleResponse saleResponse;
	private Payment payment;
	private CieloError cieloErro;
	
	public SaleResponse getSaleResponse() {
		return saleResponse;
	}

	public void setSaleResponse(SaleResponse saleResponse) {
		this.saleResponse = saleResponse;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public CieloError getCieloErro() {
		return cieloErro;
	}

	public void setCieloErro(CieloError cieloErro) {
		this.cieloErro = cieloErro;
	}

}
