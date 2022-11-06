package modelo;

/*import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
*/

public class TicketCaja extends Ticket{

	public TicketCaja(String codigo, Float valor, Boolean usado) {
		/*Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
		String strDate = dateFormat.format(date); 
		String codigo = "M" + strDate;
		*/
		super(codigo, valor, usado);
	}
	
}
