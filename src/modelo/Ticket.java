package modelo;

public abstract class Ticket {
	
	private int codigo;
	private float valor;
	private boolean usado;
	
	private int codigoProximoTicket = 1;
	
	public Ticket(float valor) {
		this.codigo = codigoProximoTicket++;
		this.valor = valor;
		this.usado = false;
	}
	
	public boolean soyEseTicket(int codigo) {
		return this.codigo == codigo;
	}
	
	public boolean usado() {
		return this.usado;
	}
	
	public float valorTicket() {
		return this.valor;
	}
	
	public void usarTicket() {
		this.usado = true;
	}
	
	public int codigoTicket() {
		
		return this.codigo;
	}
	
}
