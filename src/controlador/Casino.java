package controlador;

import java.util.Scanner;


import java.util.Collection;
import modelo.Maquina;
import modelo.Ticket;
import modelo.TicketCaja;
import modelo.TicketMaquina;

public class Casino {
	
	private Collection<Maquina> maquinas;
	private Collection<Ticket> tickets;
	//private Collection<TicketCaja> ticketsCaja;
	//private Collection<TicketMaquina> ticketsMaquina;
	
	private static Casino instancia;
	
	private Casino(){ 
		
	}
	
	public static Casino getInstancia(){
		if(instancia == null)
			instancia = new Casino();
		return instancia;
	}
	
	public void crearUnaMaquina() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Ingrese el numero de casillas que tendra la maquina");
		int numeroCasillas = input.nextInt();
		
		System.out.println("Ingrese la recaudacion inicial que tendra la maquina");
		float recaudacion = input.nextFloat();
		
		System.out.println("Ingrese el precio por jugada que tendra la maquina");
		float precioJugada = input.nextFloat();
		
		input.close();
		
		Maquina maquina = new Maquina(numeroCasillas, recaudacion, 0/*recaudacion minima*/, precioJugada, 0/*credito*/);
		maquinas.add(maquina);
	}
	
	public void agregarPremio() {
		//Tengo que modificar la recaudacion minima
		
	}
	
	public void borrarPremio() {
		//Tengo que modificar la recaudacion minima
	}
	
	public void jugar() {
		
	}
	
	public void cargarCredito(int idMaquina, int codigos) {
		//el codigo al test
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el codigo del ticket");
		int codigo = input.nextInt();
		input.close();

		Ticket ticket = null;
		for (Ticket t : tickets) {
			if (t.soyEseTicket(codigo)) {
				ticket = t;
				break;
			}
		}
		//Preguntar que pasa si me devuelve un ticket del tipo Caja
		Maquina m = obtenerMaquina(codigos);
		
		m.agregarCredito(ticket.valorTicket());
		
	}
	
	public Maquina obtenerMaquina(int idMaquina) {
		for (Maquina m : maquinas) {
			if (m.soyEsaMaquina(idMaquina))
				return m;
		}
		return null;
	}
	
	//Genera el ticket del usuario para retirar su dinero por caja
	public int pedirTicket(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		
		TicketCaja t = new TicketCaja(m.creditoMaquina());
		
		tickets.add(t);
		
		return t.codigoTicket();
	}
	
}
