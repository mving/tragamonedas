package controlador;



import java.util.ArrayList;
import java.util.Collection;

import modelo.Maquina;
import modelo.Ticket;
import modelo.TicketCaja;
import modelo.TicketMaquina;

public class Casino {
	
	private Collection<Maquina> maquinas;
	private Collection<Ticket> tickets;
	
	private static Casino instancia;
	
	private Casino(){ 
		maquinas = new ArrayList<Maquina>();
		tickets = new ArrayList<Ticket>();
	}
	
	public static Casino getInstancia(){
		if(instancia == null)
			instancia = new Casino();
		return instancia;
	}
	
	public int cantidadMaquinas() {
		return Maquina.idProximaMaquina - 1;
	}
	
	public void crearUnaMaquina(int numeroCasillas, float recaudacion, float precioJugada) {
		Maquina maquina = new Maquina(numeroCasillas, recaudacion, 0/*recaudacion minima*/, precioJugada, 0/*credito*/);
		maquinas.add(maquina);
	}
	
	public void agregarPremio(int idMaquina, String[] combinacion, float valor) {	
		Maquina m = obtenerMaquina(idMaquina);
		m.crearPremio(valor, combinacion);	
	}
	
	public void borrarPremio(int idMaquina, String[] combinacion) {		
		Maquina m = obtenerMaquina(idMaquina);				
		m.eliminarPremio(combinacion);		
	}
	
	//agregar estado
	public boolean jugar(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		if(m == null)
			return false;
		return m.jugar();
	}
	
	public void imitaJuego(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		m.imitaJuego();
	}
	
	public String ultimaJugada(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		if(m.ultima_jugada())
			return "Gano";
		else
			return "Perdio";
			
	}
	
	public String[] ultimaCombinacion(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		return m.ultimaCombinacion();
	}
	
	public boolean puedeJugar(int idMaquina) {
		return obtenerMaquina(idMaquina).puedeJugar();
	}
	
	//instanceof
	public void cargarCredito(int idMaquina, String codigo) {
		/*if(codigo.substring(0, 1).equals("M"))
			return;*/
			
		for (Ticket t : tickets) {
			if (t.soyEseTicket(codigo)) {
				Maquina m = obtenerMaquina(idMaquina);
				m.agregarCredito(t.valorTicket());
				break;
				}
			
		}			
	}
	
	private Maquina obtenerMaquina(int idMaquina) {
		for (Maquina m : maquinas) {
			if (m.soyEsaMaquina(idMaquina))
				return m;
		}
		return null;
	}
	
	//Genera el ticket del usuario para retirar su dinero por caja
	public String generarTicketCaja(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		
		TicketCaja t = new TicketCaja(m.creditoMaquina());
		tickets.add(t);
		
		m.quitarCredito(m.creditoMaquina());
		
		return t.codigoTicket();
	}
	
	//Genera el ticket para ser ingresado en una máquina (lo crea el casino)
	public String generarTicketMaquina(float valor) {
		
		TicketMaquina t = new TicketMaquina(valor);
		tickets.add(t);
		return t.codigoTicket();
	}
	
	public int[] listarMaquinas() {
		int[] listadoMaquinas = new int[maquinas.size()];
		int i = 0;
		for (Maquina m : maquinas) {
			listadoMaquinas[i++] = m.idMaquina();
		}
		return listadoMaquinas;
	}
	
	public int cantidadCasillasMaquina(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		return m.numeroCasillas();
	}
}