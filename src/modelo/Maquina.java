package modelo;

import java.util.ArrayList;
import java.util.Random;


public class Maquina {
	//La maquina tiene una coleccion de premios
	private int numeroCasillas;
	private float recaudacion;
	private float recaudacionMinima;
	private float precioJugada;
	private float credito;
	private int idMaquina;	//Donde meto el ID
	private ArrayList<Premio> premios;
	
	private int idProximaMaquina = 1;
	
	public final static String[] frutas = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	public final static int frutasCant = 6;
	
	public Maquina(int numeroCasillas, float recaudacion, float recaudacionMinimo, float precioJugada, float credito) {
		this.numeroCasillas = numeroCasillas;
		this.recaudacion = recaudacion;
		this.recaudacionMinima = recaudacionMinimo;
		this.precioJugada = precioJugada;
		this.credito = credito;
		this.idMaquina = idProximaMaquina++;
	}
	
	public void jugar() {
		if (!puedeJugar())
			return;
		
		String[] jugada = generarCombinacion();
		Premio p = obtenerPremio(jugada);	
		
		if (p != null) {
			reducirRecaudacion(p.valorPremio());
			aumentarCredito(p.valorPremio());
		}else {
			aumentarRecaudacion(this.precioJugada);
			reducirCredito(this.precioJugada);
		}
			
	}
	
	private boolean puedeJugar() {
		if (this.credito >= this.precioJugada && this.recaudacion >= this.recaudacionMinima)
			return true;
		return false;
	}
	
	private String[] generarCombinacion() {
		String[] jugada = new String[this.numeroCasillas];
		Random rand = new Random();
		for (int i=0; i<this.numeroCasillas; i++)
			jugada[i] = Maquina.frutas[rand.nextInt(frutasCant)];
		
		return jugada;
	}

	private void reducirCredito(float valor) {
		this.credito -= valor;
	}
	
	private void aumentarCredito(float valor) {
		this.credito += valor;
	}
	
	public void agregarCredito(float valor) {
		this.credito += valor;
	}
	
	private void reducirRecaudacion(float valor) {
		this.recaudacion -= valor;
	}
	
	private void aumentarRecaudacion(float valor) {
		this.recaudacion += valor;
	}
	
	public void crearPremio(float valor,String[] combinacion) {
		
		Premio premio = new Premio(valor, combinacion);
		premios.add(premio);
		if (premio.valorPremio() > this.recaudacionMinima)
			actualizaRecaudacionMinima();
	}
	
	/*Da de baja un premio, y si ese es el de mayor valor
	debe actualizar el valor de recaudacionMinima*/
	public void eliminarPremio(String[] combinacion) {	
		
		Premio p = obtenerPremio(combinacion);
		
		float valor = p.valorPremio();
		
		premios.remove(p);
		
		if (valor == this.recaudacionMinima)
			actualizaRecaudacionMinima();
	}
	
	private Premio obtenerPremio(String[] combinacion) {	//Devuelve el objeto premio que coincide con la combinacion
		for (Premio p : premios)
			if (p.soyEsePremio(combinacion))
				return p;

		return null;
	}
	
	private void actualizaRecaudacionMinima() {	//La recaudación minima es el valor del premio mas grande
		for (Premio p : premios)
			if (p.valorPremio() > this.recaudacionMinima)
				this.recaudacionMinima = p.valorPremio();
	}
	
	public TicketCaja generaTicketCaja() {	//Cuando se entrega el ticket, es por el valor total del credito
		TicketCaja t = new TicketCaja(this.credito);
		this.credito = 0;
		return t;
	}
	
	public int numeroCasillas() {
		return this.numeroCasillas;
	}
	
	public boolean soyEsaMaquina(int idMaquina) {
		return this.idMaquina == idMaquina;
	}
	
	public float creditoMaquina() {
		return this.credito;
	}
	
}
