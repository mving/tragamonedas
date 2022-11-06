package modelo;

public abstract class Ticket {
	
	private String codigo;
	private Float valor;
	private Boolean usado;
	
	public Ticket(String codigo, Float valor, Boolean usado) {
		this.codigo = codigo;
		this.valor = valor;
		this.usado = usado;
	}
	
	
}
