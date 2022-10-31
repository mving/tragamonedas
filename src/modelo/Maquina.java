package modelo;

public class Maquina {
	
	private int numeroCasillas;
	private float recaudacion;
	private float recaudacionMinimo;
	private float precioJugada;
	private float credito;
	
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
	
//	public Premio crearPremio()
	
	//public void eliminarPremio()
	
	//comprobarPremio
	
	
	
	
	
}
