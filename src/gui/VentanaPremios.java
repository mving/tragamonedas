package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import controlador.Casino;

public class VentanaPremios extends JDialog implements ActionListener{

	private static final long serialVersionUID = -3002642616891366182L;
	private Container contenedor;
	private int cantidadMaquinas;
	private JButton btnSalir, btnEliminar, btnPremioCrear;
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
		setSize(225,170);
		setLocationRelativeTo(null);
		
	}
	
	public void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		

		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox combo = new JComboBox(listado);
		combo.setBounds(5,0,200,50);
		combo.addActionListener(this);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(5, 65, 90, 50);
		btnEliminar.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					VentanaJuego miVentanaJuego = new VentanaJuego(VentanaPremios.this.c, Integer.valueOf(combo.getItemAt(combo.getSelectedIndex()).toString().substring(8)));
						miVentanaJuego.setVisible(true);
					
				}
			}
			);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(115, 65, 90, 50);
		btnSalir.addActionListener(this);
		
		contenedor.add(btnAceptar)
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnCancelar) {
			dispose();
		}
		if (e.getSource()==btnPremioCrear) {
			VentanaCrearPremio miVentanaCrearPremio = new VentanaCrearPremio(miVentanaPrincipal, true, VentanaPremios.this.c, VentanaPremios.this.idMaquina);
		}
	}
	//hago una lista desplegable y muestro todos los premios. Y le puedo dar al boton eliminar. tengo el boton crear premio que me arma el dibujo de las frutas
	
}
