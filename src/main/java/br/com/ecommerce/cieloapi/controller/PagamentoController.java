package br.com.ecommerce.cieloapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.cieloapi.PagamentoService;
import br.com.ecommerce.cieloapi.model.PaymentRequest;
import br.com.ecommerce.cieloapi.model.PaymentResponse;
import cieloecommerce.sdk.ecommerce.request.CieloRequestException;

@RestController(value="/pay")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping(value="/cartao")
	public ResponseEntity<PaymentResponse> cartao(@RequestBody PaymentRequest payRequest) {
		try {
			return new ResponseEntity<PaymentResponse>(this.pagamentoService.pagamentoCartao(payRequest),HttpStatus.OK);
		} catch (CieloRequestException e) {
			return new ResponseEntity<>(new PaymentResponse(e.getError()),HttpStatus.PRECONDITION_FAILED);
		} catch (IOException e) {
			return new ResponseEntity<>(new PaymentResponse(),HttpStatus.PRECONDITION_FAILED);
		}
	}
}
