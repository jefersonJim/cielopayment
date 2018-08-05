package br.com.ecommerce.cieloapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.cieloapi.config.constants.CartaoTipo;
import br.com.ecommerce.cieloapi.model.PaymentRequest;
import br.com.ecommerce.cieloapi.model.PaymentResponse;
import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.CieloEcommerce;
import cieloecommerce.sdk.ecommerce.Environment;
import cieloecommerce.sdk.ecommerce.Payment;
import cieloecommerce.sdk.ecommerce.Sale;
import cieloecommerce.sdk.ecommerce.SaleResponse;
import cieloecommerce.sdk.ecommerce.request.CieloRequestException;

@Service
public class PagamentoService {
	@Autowired
	private Merchant merchant;

	public PaymentResponse pagamentoCartao(PaymentRequest payRequest) throws IOException, CieloRequestException {
		Sale sale = new Sale(payRequest.getMerchantOrderId());
		
		sale.customer(payRequest.getClient());
		
		//Valor
		Payment payment = sale.payment(payRequest.getAmount());
		
		if(CartaoTipo.DEBITO.getTipo().equals(payRequest.getTipo()) ) {
			payment.debitCard(payRequest.getSecurityCode(), payRequest.getBrand())
				   .setExpirationDate(payRequest.getExpirationDate())
				   .setCardNumber(payRequest.getCardNumber())
				   .setHolder(payRequest.getHolder()); //TITULAR
			
		}else if(CartaoTipo.CREDITO.getTipo().equals(payRequest.getTipo())) {
			payment.creditCard(payRequest.getSecurityCode(), payRequest.getBrand())
			       .setExpirationDate(payRequest.getExpirationDate())
			       .setCardNumber(payRequest.getCardNumber())
			       .setHolder(payRequest.getHolder()); //TITULAR
		}
		
	    sale = new CieloEcommerce(merchant, Environment.SANDBOX).createSale(sale);

	    //DADOS RETORNADOS PELA CIELO
	    Payment paymentCielo = sale.getPayment();

	    SaleResponse saleResponse = new CieloEcommerce(merchant, Environment.SANDBOX).captureSale(paymentCielo.getPaymentId(), payRequest.getAmount(), 0);
		return new PaymentResponse(saleResponse,paymentCielo); 
	}
}
