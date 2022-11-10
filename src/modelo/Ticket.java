package modelo;

public abstract class Ticket {
	
	//arranca M para maquina C para caja
	
	private String codigo;
	private float valor;
	private boolean usado;
		
	public Ticket(float valor, String codigo) {
		this.codigo = codigo;
		this.valor = valor;
		this.usado = false;
	}
	
	public boolean soyEseTicket(String codigo) {
		return this.codigo.equals(codigo);
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
	
	public String codigoTicket() {
		
		return this.codigo;
	}
	
}
