package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlador.Casino;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 2295449245783091238L;
	private Container contenedor;
	JButton btnConfigurar, btnJugar, btnCrear, btnTicket, btnPagar;
	JLabel lblTitulo;
	private Casino c;
	
	private VentanaPrincipal miVentanaPrincipal;
	
	public VentanaPrincipal() {
		setResizable(false);
		iniciarComponentes();
		c = Casino.getInstancia();
		setTitle("PTM");
		setSize(215,240);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setVentanaPrincipal(VentanaPrincipal miVentana) {
		miVentanaPrincipal = miVentana;
	}
	
	private void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(15, 20, 170, 25);
		btnJugar.addActionListener(this);
		
		btnConfigurar = new JButton("Configurar Maquinas");
		btnConfigurar.setBounds(15, 55, 170, 25);
		btnConfigurar.addActionListener(this);
				
		btnCrear = new JButton("Crear Maquina");
		btnCrear.setBounds(15, 90, 170, 25);
		btnCrear.addActionListener(this);		
		
		btnTicket = new JButton("Crear Ticket");
		btnTicket.setBounds(15, 125, 170, 25);
		btnTicket.addActionListener(this);		
		
		btnPagar = new JButton("Pagar");
		btnPagar.setBounds(15, 160, 170, 25);
		btnPagar.addActionListener(this);
		
		contenedor.add(btnConfigurar);
		contenedor.add(btnJugar);
		contenedor.add(btnCrear);
		contenedor.add(btnTicket);
		contenedor.add(btnPagar);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConfigurar) {
			if(c.cantidadMaquinas() == 0)
				JOptionPane.showMessageDialog(contenedor, "Primero debe crear alguna maquina");
			else {
				VentanaMaquinas miVentanaMaquinas = new VentanaMaquinas(miVentanaPrincipal, true, true, this.c);
				miVentanaMaquinas.setVisible(true);
			}
		}
		
		if (e.getSource() == btnJugar) {
			if(c.cantidadMaquinas() == 0)
				JOptionPane.showMessageDialog(contenedor, "Primero debe crear alguna maquina");
			else {
				VentanaMaquinas miVentanaMaquinas = new VentanaMaquinas(miVentanaPrincipal, false, false, this.c);
				miVentanaMaquinas.setVisible(true);
			}
		}
		
		if (e.getSource() == btnCrear) {
			VentanaConfiguracion miVentanaConfiguracion = new VentanaConfiguracion(miVentanaPrincipal, true, this.c, 0, true);
			miVentanaConfiguracion.setVisible(true);
		}
		
		if (e.getSource() == btnTicket) {
			String respuesta = JOptionPane.showInputDialog("Ingrese el monto del ticket");
			if (respuesta == null || respuesta == "")
				JOptionPane.showMessageDialog(contenedor, "No se creó ningún ticket");
			else {
				String codigo = c.generarTicketMaquina(Float.valueOf(respuesta));
				JOptionPane.showMessageDialog(contenedor, "El código del ticket es: ''" + codigo +"'', por un valor de $" + Float.valueOf(respuesta));
			}
		}
		
		if (e.getSource() == btnPagar) {
			String codigo = JOptionPane.showInputDialog("Ingrese el numero de ticket");
			if (codigo == null || codigo == "")
				JOptionPane.showMessageDialog(contenedor, "No se ingresó ningún ticket");
			else {
				float valor = 0;
				if((valor = c.retirarTicketCaja(codigo)) != 0)
					JOptionPane.showMessageDialog(contenedor, "Debe pagarle al cliente $" + valor + " por el ticket ''" + codigo + "''");
				else
					JOptionPane.showMessageDialog(contenedor, "El código ''" + codigo + "'' no es válido");
			}
		}
	}
	
}
