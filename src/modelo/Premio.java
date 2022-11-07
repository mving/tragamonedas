package modelo;

import java.util.Arrays;

public class Premio {
	
	private float valor;
	private String[] combinacion; 
	
	
	public Premio(float valor, String[] combinacion) {
		this.valor = valor;
		this.combinacion = Arrays.copyOf(combinacion, 0);
		//Revisar
		//this.combinacion = combinacion;
	}
	
	public boolean soyEsePremio(String[] combinacion) {
		return this.combinacion == combinacion;
		//revisar esta comparacion de strings
	}
	
	
	public float valorPremio() {
		return this.valor;
	}
	
	
	
	
	
	
	
	
	
	
	
}
