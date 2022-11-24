package gui;

import controlador.Casino;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class VentanaMaquinas extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = -547052736035159320L;
	private Container contenedor;
	private int cantidadMaquinas;
	private JButton btnAceptar, btnCancelar;
	String[] listado;
	private boolean modoConfiguracion;
	private VentanaPrincipal miVentanaPrincipal;
	private Casino c;
	
	public VentanaMaquinas(VentanaPrincipal miVentanaPrincipal, boolean modal, boolean modoConfiguracion, Casino c) {
		super(miVentanaPrincipal, modal);
		setResizable(false);
		this.miVentanaPrincipal = miVentanaPrincipal;
		setTitle(modoConfiguracion ? "Configurar" : "Jugar");
		this.cantidadMaquinas = c.cantidadMaquinas();
		this.modoConfiguracion = modoConfiguracion;
		this.c = c;
		//cantidadMaquinas = 50;
		
		
		
		iniciarComponentes();

	}
	
	private void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		

		setSize(225,170);
		setLocationRelativeTo(null);
		
		listado = new String[cantidadMaquinas];
		for(int i=0; i<cantidadMaquinas; i++) {
			listado[i] = "Maquina " + (i+1);
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox combo = new JComboBox(listado);
		combo.setBounds(5,0,200,50);
		combo.addActionListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(5, 65, 90, 50);
		btnAceptar.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(modoConfiguracion) {	
						dispose();
						VentanaConfiguracion miVentanaConfiguracion = new VentanaConfiguracion(miVentanaPrincipal, true, VentanaMaquinas.this.c, VentanaMaquinas.this.c.cantidadMaquinas());
						miVentanaConfiguracion.setVisible(true);
						
					}else {
						dispose();//como hago para no abrir dos veces la misma maquina. Agrego un estado que sea en uso a la maquina, y desactivo el cerrar.
						VentanaJuego miVentanaJuego = new VentanaJuego(VentanaMaquinas.this.c, Integer.valueOf(combo.getItemAt(combo.getSelectedIndex()).toString().substring(8)));
						miVentanaJuego.setVisible(true);
					}
				}
			}
			);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(115, 65, 90, 50);
		btnCancelar.addActionListener(this);
		
		contenedor.add(combo);
		contenedor.add(btnAceptar);
		contenedor.add(btnCancelar);
	}

	public void actionPerformed(ActionEvent e) {
		//Llamo a la configuracion de la maquina seleccionada
		
	
		if (e.getSource()==btnCancelar) {
			dispose();
		}
	}
	
	
	
}
