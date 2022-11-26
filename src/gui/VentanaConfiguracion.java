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
	private VentanaPrincipal miVentanaPrincipal;
	private boolean modoCreacion;

	public VentanaConfiguracion(VentanaPrincipal miVentanaPrincipal, boolean modal, Casino c, int idMaquina, boolean modoCreacion) {
		super(miVentanaPrincipal, modal);
		this.c = c;
		this.miVentanaPrincipal = miVentanaPrincipal;
		this.idMaquina = idMaquina;
		this.modoCreacion = modoCreacion;
		setResizable(false);
		setSize(300,300);
		setTitle("Maquina ");
		setLocationRelativeTo(null); //centra la ventana en la pantalla
		iniciaComponentes();
	}
	
	private void iniciaComponentes() {
		contenedor = this.getContentPane();
		contenedor.setLayout(new GridLayout(6,6));
		
		lblNumeroCasillas = new JLabel("Numero de casillas: ");
		txtNumeroCasillas = new JTextField();
		if(!modoCreacion) {
			txtNumeroCasillas.setText(String.valueOf(c.cantidadCasillasMaquina(idMaquina)));
			txtNumeroCasillas.setEnabled(false);
		}
		
		lblRecaudacion = new JLabel("Recaudacion total: ");
		txtRecaudacion = new JTextField();
		if(!modoCreacion)
			txtRecaudacion.setText(String.valueOf(c.recaudacionMaquina(idMaquina)));
		
		lblPrecioJugada = new JLabel("Precio jugada: ");
		txtPrecioJugada = new JTextField();
		if(!modoCreacion)
			txtPrecioJugada.setText(String.valueOf(c.precioJugadaMaquina(idMaquina)));

		
		btnPremios = new JButton("Editar Premios");
		btnPremios.addActionListener(this);
		if(modoCreacion)
			btnPremios.setEnabled(false);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnBorrar = new JButton("Eliminar");
		btnBorrar.addActionListener(this);
		if(modoCreacion)
			btnBorrar.setEnabled(false);
		
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
			VentanaPremios miVentanaPremios = new VentanaPremios(miVentanaPrincipal, true, this.c, this.idMaquina);
			miVentanaPremios.setVisible(true);
		}
		if (e.getSource()==btnBorrar) {
			//JOptionPane.show
			c.borrarUnaMaquina(idMaquina);
		}
		if (e.getSource()==btnAceptar) {
			if(Integer.valueOf(txtNumeroCasillas.getText()) < 3 || Integer.valueOf(txtNumeroCasillas.getText()) > 6) {
				JOptionPane.showMessageDialog(contenedor, "La maquina debe tener entre 3 y 6 frutas");
			}else if(Float.valueOf(txtRecaudacion.getText()) <= 0 || Float.valueOf(txtPrecioJugada.getText()) <= 0) {
				JOptionPane.showMessageDialog(contenedor, "Los valores deben ser mayores que 0");
			}else {
				dispose();
				if(!modoCreacion) {
					c.modificaRecaudacionMaquina(idMaquina, Float.valueOf(txtRecaudacion.getText()));
					c.modificaPrecioJugadaMaquina(idMaquina, Float.valueOf(txtPrecioJugada.getText()));
				}else {
					c.crearUnaMaquina(Integer.valueOf(txtNumeroCasillas.getText()), Float.valueOf(txtRecaudacion.getText()), Float.valueOf(txtPrecioJugada.getText()));
					VentanaConfiguracion miVentanaConfiguracion = new VentanaConfiguracion(miVentanaPrincipal, true, this.c, c.idProximaMaquina()-1, false);
					miVentanaConfiguracion.setVisible(true);
				}	
			}
			
		}
		if (e.getSource()==btnCancelar) {
			dispose();
		}
		
		//TODO agregar eliminar maquina
	}
	
}
