package gui;

import controlador.Casino;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaConfiguracion extends JDialog implements ActionListener{

	private static final long serialVersionUID = -4970688055075112530L;
	private JLabel lblNumeroCasillas, lblRecaudacion, lblPrecioJugada;
	private JTextField txtNumeroCasillas, txtRecaudacion, txtPrecioJugada;
	private JButton btnPremios, btnBorrar, btnAceptar, btnCancelar;
	private int idMaquina;
	private Container contenedor;
	private VentanaPrincipal miVentanaPrincipal;
	private boolean modoCreacion;

	public VentanaConfiguracion(VentanaPrincipal miVentanaPrincipal, boolean modal, int idMaquina, boolean modoCreacion) {
		super(miVentanaPrincipal, modal);
		this.miVentanaPrincipal = miVentanaPrincipal;
		this.idMaquina = idMaquina;
		this.modoCreacion = modoCreacion;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setSize(300,300);
		setTitle(modoCreacion ? "Creando Maquina" : "Editar Maquina " + idMaquina);
		setLocationRelativeTo(null);
		iniciaComponentes();
	}
	
	private void iniciaComponentes() {
		contenedor = this.getContentPane();
		contenedor.setLayout(new GridLayout(6,6));
		
		lblNumeroCasillas = new JLabel("Numero de casillas: ");
		txtNumeroCasillas = new JTextField("3");
		if(!modoCreacion) {
			txtNumeroCasillas.setText(String.valueOf(Casino.getInstancia().cantidadCasillasMaquina(idMaquina)));
			txtNumeroCasillas.setEnabled(false);
		}
		
		txtNumeroCasillas.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
	            if (!((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') ))
	            	ke.consume();
	         }
		});
		
		
		lblRecaudacion = new JLabel("Recaudacion total: ");
		txtRecaudacion = new JTextField("10000");
		if(!modoCreacion)
			txtRecaudacion.setText(String.valueOf(Casino.getInstancia().recaudacionMaquina(idMaquina)));
		txtRecaudacion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
	            if (!((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') ))
	            	ke.consume();
	         }
		});
		
		
		lblPrecioJugada = new JLabel("Precio jugada: ");
		txtPrecioJugada = new JTextField("25");
		if(!modoCreacion)
			txtPrecioJugada.setText(String.valueOf(Casino.getInstancia().precioJugadaMaquina(idMaquina)));
		txtPrecioJugada.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
	            if (!((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') ))
	            	ke.consume();
	         }
		});
		
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
	//TODO que los txt solo acepten numeros	
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
			VentanaPremios miVentanaPremios = new VentanaPremios(miVentanaPrincipal, true, this.idMaquina);
			miVentanaPremios.setVisible(true);
		}
		if (e.getSource()==btnBorrar) {
			String[] botones = {"Aceptar", "Cancelar"};
			int respuesta = JOptionPane.showOptionDialog(contenedor, "¿Confirma eliminar la máquina " + idMaquina + "?", "Eliminar", 0, JOptionPane.QUESTION_MESSAGE, null, botones, "");
			if(respuesta == 0) {
				Casino.getInstancia().borrarUnaMaquina(idMaquina);
				dispose();
			}
		}
		if (e.getSource()==btnAceptar) {
			
			if(Integer.valueOf(txtNumeroCasillas.getText()) < 3 || Integer.valueOf(txtNumeroCasillas.getText()) > 6) {
				JOptionPane.showMessageDialog(contenedor, "La maquina debe tener entre 3 y 6 frutas");
			}else if(Float.valueOf(txtRecaudacion.getText()) <= 0 || Float.valueOf(txtPrecioJugada.getText()) <= 0) {
				JOptionPane.showMessageDialog(contenedor, "Los valores deben ser mayores que 0");
			}else {
				dispose();
				if(!modoCreacion) {
					Casino.getInstancia().cerrarMaquina(idMaquina);
					Casino.getInstancia().modificaRecaudacionMaquina(idMaquina, Float.valueOf(txtRecaudacion.getText()));
					Casino.getInstancia().modificaPrecioJugadaMaquina(idMaquina, Float.valueOf(txtPrecioJugada.getText()));
				}else {
					Casino.getInstancia().crearUnaMaquina(Integer.valueOf(txtNumeroCasillas.getText()), Float.valueOf(txtRecaudacion.getText()), Float.valueOf(txtPrecioJugada.getText()));
					VentanaConfiguracion miVentanaConfiguracion = new VentanaConfiguracion(miVentanaPrincipal, true, Casino.getInstancia().idProximaMaquina()-1, false);
					miVentanaConfiguracion.setVisible(true);
				}	
			}
			
		}
		if (e.getSource()==btnCancelar) {
			if(!modoCreacion)
				Casino.getInstancia().cerrarMaquina(idMaquina);
			dispose();
		}
	}
}
