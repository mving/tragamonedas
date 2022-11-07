package modelo;

import java.util.Random;

public abstract class Ticket {
	
	private int codigo;
	private float valor;
	private boolean usado;
	
	public Ticket(float valor) {
		Random rand = new Random();
		this.codigo = rand.nextInt(9999999);
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
	
}
