package test;

import java.util.Scanner;

import gui.VentanaPrincipal;
import gui.Ventana;

public class Test {

	public static Scanner input; 

	
	public static void main(String[] args) throws InterruptedException {
		
	//	VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
	//	miVentanaPrincipal.setVisible(true);

		Ventana v1 = new Ventana(3);
		v1.setVisible(true);

		v1.random(3);
		v1.random(3);
	//	input = new Scanner(System.in);
	//	Casino c = Casino.getInstancia();
		
	//	System.out.println("Crear Maquina...");
	//	crearMaquina(c);
		
	//	System.out.println("Crear Premio...");
	//	crearPremio(c);
		
		//System.out.println("Generar Ticket Maquina...");
		//generarTicketMaquina(c);
		
		//System.out.println("�Donde quiere jugar?");
		//int idMaquina = seleccionarMaquina(c);
		
		//System.out.println("Cargar Credito...");
		//cargarCredito(c, idMaquina);
		
		/*System.out.println("Jugar...");
		if(!puedeJugar(c, idMaquina))
			System.out.println("Usted no puede jugar");
		else {
			if(jugar(c, idMaquina))
				System.out.println("Usted gano");
			else
				System.out.println("Usted perdio");
		}
		*/
		
	//	c.imitaJuego(1);
	//	v1.jugada(3, c, 1);
		
	//	String[][] listado = c.listadoPremiosMaquina(1);
	//	System.out.println(listado[0][0] + listado[0][1] + listado[0][2]);
		
	/*	System.out.println("Generar Ticket Caja...");
		generarTicketCaja(c, idMaquina);
		
		System.out.println("Borrar Premio...");
		borrarPremio(c);
	*/	
		//input.close();
	}
/*
	private static boolean jugar(Casino c, int idMaquina) {
		return c.jugar(idMaquina);
	}
	
	private static void crearMaquina(Casino c) {
		
		
		System.out.println("Ingrese el numero de casillas que tendra la maquina");
		int numeroCasillas = input.nextInt();
		
		System.out.println("Ingrese la recaudacion inicial que tendra la maquina");
		float recaudacion = input.nextFloat();
		
		System.out.println("Ingrese el precio por jugada que tendra la maquina");
		float precioJugada = input.nextFloat();
				
		c.crearUnaMaquina(numeroCasillas, recaudacion, precioJugada);
	}
	
	private static void crearPremio(Casino c) {
		int idMaquina = seleccionarMaquina(c);
		int cantidadCasillas = c.cantidadCasillasMaquina(idMaquina);
		
		
		System.out.println("Ingrese el valor que tendra el premio de la maquina " + idMaquina);
		float valor = input.nextFloat();		
		String[] combinacion = auxPremio(cantidadCasillas);
		
		c.agregarPremio(idMaquina, combinacion, valor);
	}
*/	
	/*private static void borrarPremio(Casino c) {
		int idMaquina = seleccionarMaquina(c);
		int cantidadCasillas = c.cantidadCasillasMaquina(idMaquina);
		String[] combinacion = auxPremio(cantidadCasillas);
		
		c.borrarPremio(idMaquina, combinacion);
	}
	*/
	
	/*
	private static void cargarCredito(Casino c, int idMaquina) {
		
		System.out.println("Ingrese el codigo del ticket Maquina");
		String codigo = input.next();		
		
		c.cargarCredito(idMaquina, codigo);
	}*/
	/*
	private static void generarTicketCaja(Casino c, int idMaquina) {
		//int idMaquina = seleccionarMaquina(c);
		String codigo = c.generarTicketCaja(idMaquina);
		System.out.println("El codigo de su ticket es: " + codigo);
	}
	*/
/*	private static void generarTicketMaquina(Casino c) {
		System.out.println("Indique que valor debe tener el ticketMaquina");
		float valor = input.nextFloat();
		String codigo = c.generarTicketMaquina(valor);
		
		System.out.println("El codigo del ticket es: " + codigo);
	}
*/
/*	private static boolean puedeJugar(Casino c, int idMaquina) {
		return c.puedeJugar(idMaquina);
	}*/
	//Metodos auxiliares
	
/*	private static int seleccionarMaquina(Casino c) {
		int[] listadoMaquinas = c.listarMaquinas();
		System.out.print("Seleccione una maquina");
		for(int i = 0; i < (listadoMaquinas.length); i++) {
			System.out.print(listadoMaquinas[i] + ", ");
		}
		int idMaquina = input.nextInt();
		return idMaquina;
	}
	*/
/*	private static String[] auxPremio(int cantidadCasillas) {
		String[] combinacion = new String[cantidadCasillas];
		
		
		System.out.println("Usted debe ingresar " + cantidadCasillas + " frutas en el orden deseado");
		
		for(int i = 0; i < cantidadCasillas ; i++) {
			System.out.println("Ingrese la fruta n�" + (i+1));
			combinacion[i] = input.next();
		}
		
		
		return combinacion;
	}

	*/
}


