package modelo;

import java.util.Collection;
import java.util.Scanner;

public class Maquina {
	//La maquina tiene una coleccion de premios
	private int numeroCasillas;
	private float recaudacion;
	private float recaudacionMinimo;
	private float precioJugada;
	private float credito;
	private Collection<Premio> premios;
	
	String[] frutas = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	
	public Maquina(int numeroCasillas, float recaudacion, float recaudacionMinimo, float precioJugada, float credito) {
		this.numeroCasillas = numeroCasillas;
		this.recaudacion = recaudacion;
		this.recaudacionMinimo = recaudacionMinimo;
		this.precioJugada = precioJugada;
		this.credito = credito;
	}
	
	public void iniciarJugada() {
		
	}
	
	private boolean puedeJugar() {
		if (this.credito >= this.precioJugada && this.recaudacion >= this.recaudacionMinimo)
			return true;
		return false;
	}
	
/*	public array generarCombinacion() {
		
	}
*/
	private void reducirCredito(float valor) {
		this.credito -= valor;
	}
	
	private void aumentarCredito(float valor) {
		this.credito += valor;
	}
	
	public void agregarCredito(float valor) {
		
	}
	
	private void reducirRecaudacion(float valor) {
		this.recaudacion -= valor;
	}
	
	private void aumentarRecaudacion(float valor) {
		this.recaudacion += valor;
	}

	public void add(Maquina maquina) {
		// TODO Auto-generated method stub
		
	}
	
	public void crearPremio() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Ingrese el numero de casillas que tendra la maquina");
		int numeroCasillas = input.nextInt();
	}
	
	//public void eliminarPremio()
	
	//comprobarPremio
	
	
	
	
	
}
