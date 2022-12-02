package gui;

import controlador.Casino;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMaquinas extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = -547052736035159320L;
	private Container contenedor;
	private int cantidadMaquinas;
	private JButton btnAceptar, btnCancelar;
	String[] listado;
	private boolean modoConfiguracion;	//Si configuro una maquina, o si elijo una maquina para jugar
	private VentanaPrincipal miVentanaPrincipal;
	@SuppressWarnings("rawtypes")
	JComboBox combo;
	
	public VentanaMaquinas(VentanaPrincipal miVentanaPrincipal, boolean modal, boolean modoConfiguracion) {
		super(miVentanaPrincipal, modal);
		setResizable(false);
		this.miVentanaPrincipal = miVentanaPrincipal;
		setTitle(modoConfiguracion ? "Configurar" : "Jugar");
		this.cantidadMaquinas = Casino.getInstancia().cantidadMaquinas();
		this.modoConfiguracion = modoConfiguracion;
				
		iniciarComponentes();

	}
	

	private void iniciarComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		

		setSize(225,170);
		setLocationRelativeTo(null);
		
		int[] listadoMaquinas = Casino.getInstancia().listarMaquinas();
		listado = new String[cantidadMaquinas];
		for(int i=0; i<cantidadMaquinas; i++) {
			listado[i] = "Maquina " + listadoMaquinas[i];
		}
		
		this.combo = new JComboBox<String>(listado);
		combo.setBounds(5,0,200,50);
		combo.addActionListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(5, 65, 90, 50);
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(115, 65, 90, 50);
		btnCancelar.addActionListener(this);
		
		contenedor.add(combo);
		contenedor.add(btnAceptar);
		contenedor.add(btnCancelar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnCancelar) {
			dispose();
		}
		
		if (e.getSource()==btnAceptar) {
			int idMaquina = Integer.valueOf(combo.getItemAt(combo.getSelectedIndex()).toString().substring(8));
			if(modoConfiguracion) {	
				if(Casino.getInstancia().estaAbiertaMaquina(idMaquina)) {
					JOptionPane.showMessageDialog(contenedor, "La máquina ya se encuentra abierta");
				}else {
					dispose();
					Casino.getInstancia().abrirMaquina(idMaquina);
					VentanaConfiguracion miVentanaConfiguracion = new VentanaConfiguracion(miVentanaPrincipal, true, idMaquina, false);
					miVentanaConfiguracion.setVisible(true);
				}
			}else {
				if(Casino.getInstancia().cantidadPremiosMaquina(idMaquina) == 0)
					JOptionPane.showMessageDialog(contenedor, "La máquina seleccionada no tiene premios");
				else {
					if(Casino.getInstancia().estaAbiertaMaquina(idMaquina)) {
						JOptionPane.showMessageDialog(contenedor, "La máquina ya se encuentra abierta");
					}else {
						dispose();
						Casino.getInstancia().abrirMaquina(idMaquina);
						VentanaJuego miVentanaJuego = new VentanaJuego(idMaquina);
						miVentanaJuego.setVisible(true);
					}
				}
			}
		}
	}
	
	
	
}
