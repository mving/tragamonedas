package controlador;



import java.util.ArrayList;
import modelo.Maquina;
import modelo.Ticket;
import modelo.TicketCaja;
import modelo.TicketMaquina;

public class Casino {
	
	private ArrayList<Maquina> maquinas;
	private ArrayList<Ticket> tickets;
	
	private static Casino instancia;
	
	public Casino(){ 
		maquinas = new ArrayList<Maquina>();
		tickets = new ArrayList<Ticket>();
	}
	
	public static Casino getInstancia(){
		if(instancia == null)
			instancia = new Casino();
		return instancia;
	}
	
	public void crearUnaMaquina(int numeroCasillas, float recaudacion, float precioJugada) {
		Maquina maquina = new Maquina(numeroCasillas, recaudacion, 0/*recaudacion minima*/, precioJugada, 0/*credito*/);
		maquinas.add(maquina);
	}
	
	public void agregarPremio(int idMaquina, String[] combinacion, float valor) {
		
		Maquina m = obtenerMaquina(idMaquina);
		
		//String[] combinacion = new String[m.numeroCasillas()];
		
		//Scanner input = new Scanner(System.in);
		//System.out.println("Ingrese el valor que tendra el premio");
		//float valor = input.nextFloat();
		
		System.out.println("Usted debe ingresar " + m.numeroCasillas() + " frutas en el orden deseado");
		
		for(int i = 1; i <= m.numeroCasillas() ; i++) {
			System.out.println("Ingrese la fruta nº" + i);
			combinacion[i] = input.next();
		}
		
		input.close();
		
		m.crearPremio(valor, combinacion);
		
	}
	
	public void borrarPremio(int idMaquina) {
		
		Maquina m = obtenerMaquina(idMaquina);
		
		System.out.println("Modo baja de premio");

		Scanner input = new Scanner(System.in);
		
		String[] combinacion = new String[m.numeroCasillas()];
		
		System.out.println("Usted debe ingresar " + m.numeroCasillas() + " frutas en el orden deseado");
		
		for(int i = 1; i <= m.numeroCasillas(); i++) {
			System.out.println("Ingrese la fruta nº" + i);
			combinacion[i] = input.next();
		}
		input.close();
		
		m.eliminarPremio(combinacion);
		
	}
	
	public void jugar(int idMaquina) {
		obtenerMaquina(idMaquina).jugar();;
	}
	
	public void cargarCredito(int idMaquina, int codigo) {

		Ticket ticket = null;
		for (Ticket t : tickets) {
			if (t.soyEseTicket(codigo)) {
				if (t instanceof TicketMaquina) {
					ticket = t;
					break;
				}
			}
		}
		//Preguntar que pasa si me devuelve un ticket del tipo Caja
		Maquina m = obtenerMaquina(codigo);
		
		m.agregarCredito(ticket.valorTicket());
		
		System.out.println("Credito: " + m.creditoMaquina());
		
	}
	
	private Maquina obtenerMaquina(int idMaquina) {
		for (Maquina m : maquinas) {
			if (m.soyEsaMaquina(idMaquina))
				return m;
		}
		return null;
	}
	
	//Genera el ticket del usuario para retirar su dinero por caja
	public int generarTicketCaja(int idMaquina) {
		Maquina m = obtenerMaquina(idMaquina);
		
		TicketCaja t = new TicketCaja(m.creditoMaquina());
		tickets.add(t);
		return t.codigoTicket();
	}
	
	//Genera el ticket para ser ingresado en una máquina (lo crea el casino)
	public int generarTicketMaquina(float valor) {
		
		TicketMaquina t = new TicketMaquina(valor);
		tickets.add(t);
		return t.codigoTicket();
	}
	
}
