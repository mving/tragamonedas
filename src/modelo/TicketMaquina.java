package modelo;

public class TicketMaquina extends Ticket {
	
	private static int codigoProximoTicketMaquina = 1;

	public TicketMaquina(float valor) {
		super(valor,"M" + codigoProximoTicketMaquina);
		codigoProximoTicketMaquina++;
	}
}
