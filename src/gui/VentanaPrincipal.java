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
	JButton btnConfigurar, btnJugar, btnCrear, btnTicket;
	JLabel lblTitulo;
	private Casino c;
	
	private VentanaPrincipal miVentanaPrincipal;
	
	public VentanaPrincipal() {
		setResizable(false);
		iniciarComponentes();
		c = Casino.getInstancia();
		setTitle("PTM");
		setSize(215,205);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setVentanaPrincipal(VentanaPrincipal miVentana) {
		miVentanaPrincipal = miVentana;
	}
	
	private void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		btnJugar = new JButton();
		btnJugar.setText("Jugar");
		btnJugar.setBounds(15, 20, 170, 25);
		btnJugar.addActionListener(this);
		
		btnConfigurar = new JButton();
		btnConfigurar.setText("Configurar Maquinas");
		btnConfigurar.setBounds(15, 55, 170, 25);
		btnConfigurar.addActionListener(this);
				
		btnCrear = new JButton();
		btnCrear.setText("Crear Maquina");
		btnCrear.setBounds(15, 90, 170, 25);
		btnCrear.addActionListener(this);		
		
		btnTicket = new JButton();
		btnTicket.setText("Crear Ticket");
		btnTicket.setBounds(15, 125, 170, 25);
		btnTicket.addActionListener(this);		
		
		
		contenedor.add(btnConfigurar);
		contenedor.add(btnJugar);
		contenedor.add(btnCrear);
		contenedor.add(btnTicket);
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
				VentanaMaquinas miVentanaMaquinas = new VentanaMaquinas(miVentanaPrincipal, true, false, this.c);
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
	}
	
}
