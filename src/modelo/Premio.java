package modelo;

import java.util.Arrays;

public class Premio {
	
	private float valor;
	private String[] combinacion; 
	
	
	public Premio(float valor, String[] combinacion) {
		this.valor = valor;
		this.combinacion = Arrays.copyOf(combinacion, 0);
		//this.combinacion = combinacion;
	}
	
	public boolean soyEsePremio(String[] combinacion) {
		return this.combinacion == combinacion;
	}
	
	
	public float valorPremio() {
		return this.valor;
	}
	
	
	
	
	
	
	
	
	
	
	
	//cada fruta tiene un valor numerico, se suma el valor de cada fruta y ese es el valor de la combinacion.
	//Las combinaciones pueden ocurrir en cualquier posicion.
}
