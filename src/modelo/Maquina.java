package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Maquina {
	//La maquina tiene una coleccion de premios
	private int numeroCasillas;
	private float recaudacion;
	private float recaudacionMinima;
	private float precioJugada;
	private float credito;
	private int idMaquina;
	private float ultima_jugada;
	private boolean open;
	private String[] ultimaCombinacion;
	private Collection<Premio> premios;
	
	public static int idProximaMaquina = 1;
	
	public final static String[] frutas = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	public final static int frutasCant = 6;
	
	public Maquina(int numeroCasillas, float recaudacion, float recaudacionMinimo, float precioJugada, float credito) {
		premios = new ArrayList<Premio>();
		this.numeroCasillas = numeroCasillas;
		this.recaudacion = recaudacion;
		this.recaudacionMinima = recaudacionMinimo;
		this.precioJugada = precioJugada;
		this.credito = credito;
		this.idMaquina = idProximaMaquina++;
	}
	
	public float ultima_jugada() {
		return this.ultima_jugada;
	}
	
	public String[] ultimaCombinacion() {
		return this.ultimaCombinacion;
	}
	
	public boolean estaAbierta() {
		return open;
	}
	
	public void abrir() {
		this.open = true;
	}
	
	public void cerrar() {
		this.open = false;
	}
	
	public boolean jugar() {
		if (!puedeJugar())
			return false;
		
		ultimaCombinacion = generarCombinacion();
		Premio p = obtenerPremio(ultimaCombinacion);	
		
		if (p != null) {
			reducirRecaudacion(p.valorPremio());
			if (this.recaudacion < 0) {
				aumentarCredito(p.valorPremio() + this.recaudacion);
				this.ultima_jugada = p.valorPremio() + this.recaudacion;
				this.recaudacion = 0;
			}else {
				aumentarCredito(p.valorPremio());
				this.ultima_jugada = p.valorPremio();
			}
			
		}else {
			aumentarRecaudacion(this.precioJugada);
			reducirCredito(this.precioJugada);
			this.ultima_jugada = 0;
		}
		return true;
	}
	
	public void modificaRecaudacion(float valor) {
		this.recaudacion = valor;
	}
	
	public void modificaPrecioJugada(float valor) {
		this.precioJugada = valor;
	}
	
	public boolean puedeJugar() {
		if (this.credito >= this.precioJugada && premios.size() > 0)
			return true;
		return false;
	}
	
	private String[] generarCombinacion() {
		String[] jugada = new String[this.numeroCasillas];
		Random rand = new Random();
		for (int i=0; i<this.numeroCasillas; i++) {
			jugada[i] = Maquina.frutas[rand.nextInt(frutasCant)];
			//jugada[i] = "banana";		//Descomentar para ganar siempre con bananas
		}
		
		return jugada;
	}

	private void reducirCredito(float valor) {
		this.credito -= valor;
	}
	
	private void aumentarCredito(float valor) {
		this.credito += valor;
	}
	
	public float agregarCredito(float valor) {
		aumentarCredito(valor);
		return valor;
	}
	
	public void quitarCredito(float valor) {
		reducirCredito(valor);
	}
	
	private void reducirRecaudacion(float valor) {
		this.recaudacion -= valor;
	}
	
	private void aumentarRecaudacion(float valor) {
		this.recaudacion += valor;
	}
	
	public float consultarCredito() {
		return this.credito;
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
	
	public Premio obtenerPremio(String[] combinacion) {	//Devuelve el objeto premio que coincide con la combinacion
		for (Premio p : premios)
			if (p.soyEsePremio(combinacion))
				return p;

		return null;
	}
	
	public float valorPremio(String[] combinacion) {
		for (Premio p : premios)
			if (p.soyEsePremio(combinacion))
				return p.valorPremio();

		return 0;
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
	
	public float recaudacionMinima() {
		return this.recaudacionMinima;
	}
	
	public int numeroCasillas() {
		return this.numeroCasillas;
	}
	
	public float recaudacion() {
		return this.recaudacion;
	}
	
	public boolean soyEsaMaquina(int idMaquina) {
		return this.idMaquina == idMaquina;
	}
	
	public float creditoMaquina() {
		return this.credito;
	}
	
	public float precioJugada() {
		return this.precioJugada;
	}
	
	public int idMaquina() {
		return this.idMaquina;
	}
	
	public int cantidadPremios() {
		return premios.size();
	}
	
	public String[][] listarPremios() {
		String[][] listadoPremios = new String[premios.size()][];
		int i = 0;
		for (Premio p : premios) {
			listadoPremios[i++] = p.combinacion();
		}
		return listadoPremios;
	}
	
}
