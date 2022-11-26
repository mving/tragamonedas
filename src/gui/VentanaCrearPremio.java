package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.Casino;



//repetir lo mismo que crear maquina. Llamo directamente a crear con frutas por default
public class VentanaCrearPremio extends JDialog implements ActionListener{

	private static final long serialVersionUID = 4695250599772608146L;
	private Container contenedor;
	private int cantidadCasillas, idMaquina;
	private JButton btnAceptar, btnCancelar;
	private JLabel lblValor;
	private JTextField txtValor;
	private	Casino c;
	@SuppressWarnings("rawtypes")
	private JComboBox[] comboPremios;
	
	public VentanaCrearPremio(VentanaPrincipal miVentanaPrincipal, boolean modal, Casino c, int idMaquina) {
		super(miVentanaPrincipal, modal);

		this.c = c;
		this.idMaquina = idMaquina;
		this.cantidadCasillas = c.cantidadCasillasMaquina(idMaquina);
		iniciarComponentes();
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void iniciarComponentes() {
		setResizable(false);
		setSize(300,cantidadCasillas*30+50);
		setLocationRelativeTo(null);
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		
		comboPremios = new JComboBox[cantidadCasillas];
		for(int i=0; i<cantidadCasillas; i++) {
			comboPremios[i] = new JComboBox(c.frutasDisponibles());
			comboPremios[i].setBounds(10, i*25+10, 100, 25);
			contenedor.add(comboPremios[i]);
		}
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(130, 25, 100, 25);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);

		contenedor.add(btnAceptar);
		contenedor.add(btnCancelar);
		



	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAceptar) {
			String[] combinacion = new String[cantidadCasillas];
			for (int i = 0; i<cantidadCasillas; i++)
				combinacion[i] = comboPremios[i].getItemAt(comboPremios[i].getSelectedIndex()).toString();
			c.agregarPremio(idMaquina, combinacion, 100);
			dispose();
		}
		if (e.getSource()==btnCancelar) {
			dispose();
		}
	}
	
}
