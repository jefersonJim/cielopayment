package br.com.ecommerce.cieloapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cieloecommerce.sdk.Merchant;

@Configuration
public class CieloConfig {
	
	private final String MERCHANT_ID = "";
	private final String MERCHANT_KEY = "";

	@Bean
	public Merchant merchant(){
		return new Merchant(MERCHANT_ID, MERCHANT_KEY);
	}
}
