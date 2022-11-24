package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import controlador.Casino;

public class VentanaPremios extends JDialog implements ActionListener{

	private static final long serialVersionUID = -3002642616891366182L;
	private Container contenedor;
	private int cantidadMaquinas;
	private JButton btnAceptar, btnCancelar;
	String[] listado;
	private VentanaPrincipal miVentanaPrincipal;
	private Casino c;
	private int idMaquina;

	public VentanaPremios(VentanaPrincipal miVentanaPrincipal, boolean modal, Casino c, int idMaquina) {
		super(miVentanaPrincipal, modal);
		this.miVentanaPrincipal = miVentanaPrincipal;
		this.c = c;
		this.idMaquina = idMaquina;
		setResizable(false);
		setTitle("Editor de premios Maquina " + idMaquina);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	//hago una lista desplegable y muestro todos los premios. Y le puedo dar al boton eliminar. tengo el boton crear premio que me arma el dibujo de las frutas
	
}
