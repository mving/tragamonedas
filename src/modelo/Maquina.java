package modelo;

import java.util.Collection;
import java.util.Scanner;
import java.util.Random;


public class Maquina {
	//La maquina tiene una coleccion de premios
	private int numeroCasillas;
	private float recaudacion;
	private float recaudacionMinimo;
	private float precioJugada;
	private float credito;
	private Collection<Premio> premios;
	
	public final static String[] frutas = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	public final static int frutasCant = 6;
	
	public Maquina(int numeroCasillas, float recaudacion, float recaudacionMinimo, float precioJugada, float credito) {
		this.numeroCasillas = numeroCasillas;
		this.recaudacion = recaudacion;
		this.recaudacionMinimo = recaudacionMinimo;
		this.precioJugada = precioJugada;
		this.credito = credito;
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
		if (this.credito >= this.precioJugada && this.recaudacion >= this.recaudacionMinimo)
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
		
	}
	
	private void reducirRecaudacion(float valor) {
		this.recaudacion -= valor;
	}
	
	private void aumentarRecaudacion(float valor) {
		this.recaudacion += valor;
	}
	
	public void crearPremio() {
		System.out.println("Modo alta de premio");
		Scanner input = new Scanner(System.in);
		
		String[] combinacion = new String[this.numeroCasillas];
		
		System.out.println("Ingrese el valor que tendrá el premio");
		float premioValor = input.nextFloat();
		
		System.out.println("Usted debe ingresar " + this.numeroCasillas + " frutas en el orden deseado");
		
		for(int i = 1; i <= this.numeroCasillas; i++) {
			System.out.println("Ingrese la fruta nº" + i);
			combinacion[i] = input.next();
		}
		input.close();
		Premio premio = new Premio(premioValor, combinacion);
		premios.add(premio);
	}
	
	public void eliminarPremio() {
		System.out.println("Modo baja de premio");

		Scanner input = new Scanner(System.in);
		
		String[] combinacion = new String[this.numeroCasillas];
		
		System.out.println("Usted debe ingresar " + this.numeroCasillas + " frutas en el orden deseado");
		
		for(int i = 1; i <= this.numeroCasillas; i++) {
			System.out.println("Ingrese la fruta nº" + i);
			combinacion[i] = input.next();
		}
		input.close();
		
		premios.remove(obtenerPremio(combinacion));
	}
	
	private Premio obtenerPremio(String[] combinacion) {
		for (Premio p : premios)
			if (p.soyEsePremio(combinacion))
				return p;

		return null;
	}
	
	
	
	
}
