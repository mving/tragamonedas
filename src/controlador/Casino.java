package controlador;

import java.util.Scanner;
import java.util.Collection;
import modelo.Maquina;

public class Casino {
	
	private Collection<Maquina> maquina;
	public void crearUnaMaquina() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Ingrese el numero de casillas que tendra la maquina");
		int numeroCasillas = input.nextInt();
		
		System.out.println("Ingrese la recaudacion inicial que tendra la maquina");
		float recaudacion = input.nextFloat();
		
		System.out.println("Ingrese el precio por jugada que tendra la maquina");
		float precioJugada = input.nextFloat();
		
		Maquina maquina = new Maquina(numeroCasillas, recaudacion, 0/*recaudacion minima*/, precioJugada, 0/*credito*/);
		maquina.add(maquina);
	}
	
	public void agregarPremio() {
		//Tengo que modificar la recaudacion minima
		
	}
	
	public void borrarPremio() {
		//Tengo que modificar la recaudacion minima
	}
	
	public void jugar() {
		
	}
	
	public void cargarCredito() {
		
	}
	
	public String pedirTicket() {
		return "codigo";
	}
	
}
