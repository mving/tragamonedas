package gui;

import javax.swing.JDialog;

import controlador.Casino;
import modelo.Maquina;

public class VentanaCrearPremio extends JDialog{
	
	private String[] frutas;
	
	public VentanaCrearPremio(VentanaPrincipal miVentanaPrincipal, boolean modal, Casino c) {
		super(miVentanaPrincipal, modal);
		setResizable(false);
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		frutas = Maquina.frutas;	//Mi ventana conoce a la maquina?
		
	}
	
}
