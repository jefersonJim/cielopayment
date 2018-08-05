package br.com.ecommerce.cieloapi.config.constants;

public enum CartaoTipo {
	
	DEBITO(1),
	CREDITO(2);
	
	private Integer tipo;
	CartaoTipo(Integer tipo){
		this.tipo = tipo;
	}
	
	
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
	
}