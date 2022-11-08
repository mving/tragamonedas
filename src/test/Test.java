package test;

import java.util.Scanner;

import controlador.Casino;

public class Test {

	public static void main(String[] args) {
		
		Casino c = Casino.getInstancia();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Ingrese el numero de casillas que tendra la maquina");
		int numeroCasillas = input.nextInt();
		
		System.out.println("Ingrese la recaudacion inicial que tendra la maquina");
		float recaudacion = input.nextFloat();
		
		System.out.println("Ingrese el precio por jugada que tendra la maquina");
		float precioJugada = input.nextFloat();
		
		input.close();
		
		c.crearUnaMaquina(numeroCasillas, recaudacion, precioJugada);
		
		String[] combinacion = new String[3];
		
		c.agregarPremio(1, combinacion, 500);
		
		c.generarTicketMaquina(1000);
		
		c.cargarCredito(1, 1);
		
		
		
	}

}
