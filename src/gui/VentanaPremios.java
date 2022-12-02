package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controlador.Casino;

public class VentanaPremios extends JDialog implements ActionListener{

	private static final long serialVersionUID = -3002642616891366182L;
	private Container contenedor;
	private JButton btnSalir, btnEliminar, btnPremioCrear;
	String[][] listado;
	String[] listadoPremios;
	private VentanaPrincipal miVentanaPrincipal;
	private int idMaquina;
	@SuppressWarnings("rawtypes")
	JComboBox combo;

	public VentanaPremios(VentanaPrincipal miVentanaPrincipal, boolean modal, int idMaquina) {
		super(miVentanaPrincipal, modal);
		this.miVentanaPrincipal = miVentanaPrincipal;
		this.idMaquina = idMaquina;
		setResizable(false);
		setTitle("Editor de premios Maquina " + idMaquina);
		setSize(65*Casino.getInstancia().cantidadCasillasMaquina(idMaquina)+95, 170);
		setLocationRelativeTo(null);
		iniciarComponentes();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		listado = Casino.getInstancia().listadoPremiosMaquina(idMaquina);		
		listadoPremios = new String[Casino.getInstancia().cantidadPremiosMaquina(idMaquina)];
		for(int i=0;i<Casino.getInstancia().cantidadPremiosMaquina(idMaquina);i++) {
			listadoPremios[i] = "" + i + ": ";
			for(int j=0; j<Casino.getInstancia().cantidadCasillasMaquina(idMaquina);j++) {
				listadoPremios[i] = listadoPremios[i] + listado[i][j] + ", ";
			}
			listadoPremios[i] = listadoPremios[i] + "$" + Casino.getInstancia().valorPremioMaquina(idMaquina, listado[i]);
		}

		this.combo = new JComboBox(listadoPremios);
		combo.setBounds(10,10,65*Casino.getInstancia().cantidadCasillasMaquina(idMaquina)+60, 25);
		combo.addActionListener(this);
		if(Casino.getInstancia().cantidadPremiosMaquina(idMaquina) == 0)
			combo.setEnabled(false);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(10, 95, 90, 25);
		btnEliminar.addActionListener(this);
		if(Casino.getInstancia().cantidadPremiosMaquina(idMaquina) == 0)
			btnEliminar.setEnabled(false);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(65*Casino.getInstancia().cantidadCasillasMaquina(idMaquina)-20, 65, 90, 55);
		btnSalir.addActionListener(this);
		
		btnPremioCrear = new JButton("Agregar");
		btnPremioCrear.setBounds(10, 65, 90, 25);
		btnPremioCrear.addActionListener(this);
		
		contenedor.add(combo);
		contenedor.add(btnEliminar);
		contenedor.add(btnSalir);
		contenedor.add(btnPremioCrear);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnSalir) {
			dispose();
		}
		if (e.getSource()==btnPremioCrear) {
			dispose();
			VentanaCrearPremio miVentanaCrearPremio = new VentanaCrearPremio(miVentanaPrincipal, true, VentanaPremios.this.idMaquina);
			miVentanaCrearPremio.setVisible(true);
		}
		if (e.getSource()==btnEliminar) {
			String[] botones = {"Aceptar", "Cancelar"};
			int respuesta = JOptionPane.showOptionDialog(contenedor, "¿Confirma eliminar la máquina " + idMaquina + "?", "Eliminar", 0, JOptionPane.QUESTION_MESSAGE, null, botones, "");
			if(respuesta == 0) {
				Casino.getInstancia().borrarPremio(idMaquina, listado[Integer.valueOf(combo.getItemAt(combo.getSelectedIndex()).toString().substring(0,1))]);
				dispose();
				VentanaPremios miVentanaPremios = new VentanaPremios(miVentanaPrincipal, true, this.idMaquina);
				miVentanaPremios.setVisible(true);
			}
		}
	}
	//hago una lista desplegable y muestro todos los premios. Y le puedo dar al boton eliminar. tengo el boton crear premio que me arma el dibujo de las frutas
	
}
