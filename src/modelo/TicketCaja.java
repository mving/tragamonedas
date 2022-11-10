package modelo;


public class TicketCaja extends Ticket{

	private static int codigoProximoTicketCaja = 1;
	
	public TicketCaja(float valor) {
		super(valor, "C" + codigoProximoTicketCaja);
		codigoProximoTicketCaja++;
	}
	
}
