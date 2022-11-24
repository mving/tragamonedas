package gui;

import controlador.Casino;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentanaConfiguracion extends JDialog implements ActionListener{

	private static final long serialVersionUID = -4970688055075112530L;
	private JLabel lblNumeroCasillas, lblRecaudacion, lblPrecioJugada;
	private JTextField txtNumeroCasillas, txtRecaudacion, txtPrecioJugada;
	private JButton btnPremios, btnBorrar, btnAceptar, btnCancelar;
	private Casino c;
	private int idMaquina;
	private Container contenedor;
	
	public VentanaConfiguracion(VentanaPrincipal miVentanaPrincipal, boolean modal, Casino c, int idMaquina) {
		super(miVentanaPrincipal, modal);
		this.c = c;
		this.idMaquina = idMaquina;
		setResizable(false);
		setSize(300,300);
		setTitle("Maquina " + idMaquina);
		setLocationRelativeTo(null); //centra la ventana en la pantalla
		iniciaComponentes();
	}
	
	private void iniciaComponentes() {
		contenedor = this.getContentPane();
		contenedor.setLayout(new GridLayout(6,6));
		lblNumeroCasillas = new JLabel("Numero de casillas: ");
		txtNumeroCasillas = new JTextField();
		txtNumeroCasillas.setText(String.valueOf(c.cantidadCasillasMaquina(idMaquina)));
		lblRecaudacion = new JLabel("Recaudacion total: ");
		txtRecaudacion = new JTextField();
		txtRecaudacion.setText(String.valueOf(c.recaudacionMaquina(idMaquina)));
		lblPrecioJugada = new JLabel("Precio jugada: ");
		txtPrecioJugada = new JTextField();
		txtPrecioJugada.setText(String.valueOf(c.precioJugadaMaquina(idMaquina)));
		
		btnPremios = new JButton("Editar Premios");
		btnPremios.addActionListener(this);
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnBorrar = new JButton("Eliminar");
		btnBorrar.addActionListener(this);
		
		contenedor.add(lblNumeroCasillas);
		contenedor.add(txtNumeroCasillas);
		contenedor.add(lblRecaudacion);
		contenedor.add(txtRecaudacion);
		contenedor.add(lblPrecioJugada);
		contenedor.add(txtPrecioJugada);
		contenedor.add(btnPremios);
		contenedor.add(btnBorrar);
		contenedor.add(new JLabel());
		contenedor.add(new JLabel());
		contenedor.add(btnAceptar);
		contenedor.add(btnCancelar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnPremios) {
			
		}
		if (e.getSource()==btnBorrar) {
					
				}
		if (e.getSource()==btnAceptar) {
			if(Integer.valueOf(txtNumeroCasillas.getText()) < 3 || Integer.valueOf(txtNumeroCasillas.getText()) > 6) {
				JOptionPane.showMessageDialog(contenedor, "La maquina debe tener entre 3 y 6 frutas");
			}else if(Float.valueOf(txtRecaudacion.getText()) <= 0 || Float.valueOf(txtPrecioJugada.getText()) <= 0) {
				JOptionPane.showMessageDialog(contenedor, "Los valores deben ser mayores que 0");
			}else {
				c.modificaCasillasMaquina(idMaquina, Integer.valueOf(txtNumeroCasillas.getText()));
				c.modificaRecaudacionMaquina(idMaquina, Float.valueOf(txtRecaudacion.getText()));
				c.modificaPrecioJugadaMaquina(idMaquina, Float.valueOf(txtPrecioJugada.getText()));
				dispose();
			}
			
		}
		if (e.getSource()==btnCancelar) {
			//si estoy creando, no muestro eliminar y cancelar elimina la maquina. Restar id maquina si tengo ganas
			dispose();
		}
	}
	
}
