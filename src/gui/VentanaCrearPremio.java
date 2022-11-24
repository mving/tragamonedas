package gui;

import javax.swing.JDialog;

import controlador.Casino;
import modelo.Maquina;

public class VentanaCrearPremio extends JDialog{
	
	private String[] frutas;
	private int cantidadCasillas, idMaquina;
	private	Casino c;

	public VentanaCrearPremio(VentanaPrincipal miVentanaPrincipal, boolean modal, Casino c) {
		super(miVentanaPrincipal, modal);
		setResizable(false);
		setSize(300,300);
		this.c = c;
		this.idMaquina = idMaquina;
		this.cantidadCasillas = c.cantidadCasillas(idMaquina);
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		frutas = Maquina.frutas;	//Mi ventana conoce a la maquina?
			
	}
	
}
