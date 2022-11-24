package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controlador.Casino;
import gui.VentanaMaquinas;
import controlador.Casino;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 2295449245783091238L;
	private Container contenedor;
	JButton btnConfigurar, btnJugar;
	JLabel lblTitulo;
	private Casino c;
	
	private VentanaPrincipal miVentanaPrincipal;
	
	public VentanaPrincipal() {
		iniciarComponentes();
		c = Casino.getInstancia();
		setTitle("Tragamonedas");
		setSize(400,105);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setVentanaPrincipal(VentanaPrincipal miVentana) {
		miVentanaPrincipal = miVentana;
	}
	
	private void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		btnConfigurar = new JButton();
		btnConfigurar.setText("Configurar Maquinas");
		btnConfigurar.setBounds(15, 20, 170, 25);
		btnConfigurar.addActionListener(this);
		
		btnJugar = new JButton();
		btnJugar.setText("Seleccionar Maquina");
		btnJugar.setBounds(200, 20, 170, 25);
		btnJugar.addActionListener(this);
		
		contenedor.add(btnConfigurar);
		contenedor.add(btnJugar);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConfigurar) {
			VentanaMaquinas miVentanaMaquinas = new VentanaMaquinas(true, this.c);
			miVentanaMaquinas.setVisible(true);
		}
		if (e.getSource() == btnJugar) {
			VentanaMaquinas miVentanaMaquinas = new VentanaMaquinas(false, this.c);
			miVentanaMaquinas.setVisible(true);
		}
	}
	
}
