package gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.Casino;
import modelo.Maquina;



//repetir lo mismo que crear maquina. Llamo directamente a crear con frutas por default
public class VentanaCrearPremio extends JDialog{

	private static final long serialVersionUID = 4695250599772608146L;
	private Container contenedor;
	private String[] frutas;
	private int cantidadCasillas, idMaquina, idPremio;
	private JButton btnAceptar, btnCancelar;
	private JLabel lblValor;
	private JTextField txtValor;
	private	Casino c;

	public VentanaCrearPremio(VentanaPrincipal miVentanaPrincipal, boolean modal, Casino c, int idMaquina, int idPremio) {
		super(miVentanaPrincipal, modal);
		setResizable(false);
		setSize(300,300);
		this.c = c;
		this.idMaquina = idMaquina;
		this.cantidadCasillas = c.cantidadCasillasMaquina(idMaquina);
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		frutas = Maquina.frutas;	//Mi ventana conoce a la maquina?
		//Segun la cantidad de casilas que tengo, muestro esa cantidad de frutas en pantalla
		contenedor = getContentPane();
		contenedor.setLayout(null);
		

		setSize(225,170);
		setLocationRelativeTo(null);

	
	}
	
}
