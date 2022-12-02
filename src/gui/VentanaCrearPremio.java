package gui;

//import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Casino;


public class VentanaCrearPremio extends JDialog implements ActionListener{

	private static final long serialVersionUID = 4695250599772608146L;
	private Container contenedor;
	private int cantidadCasillas, idMaquina;
	private JButton btnAceptar, btnCancelar;
	private VentanaPrincipal miVentanaPrincipal;
	private JLabel lblValor;
	private JTextField txtValor;
	@SuppressWarnings("rawtypes")
	private JComboBox[] comboPremios;
	
	public VentanaCrearPremio(VentanaPrincipal miVentanaPrincipal, boolean modal, int idMaquina) {
		super(miVentanaPrincipal, modal);
		this.miVentanaPrincipal = miVentanaPrincipal;
		this.idMaquina = idMaquina;
		this.cantidadCasillas = Casino.getInstancia().cantidadCasillasMaquina(idMaquina);
		setTitle("Agregar premios Maquina " + idMaquina);
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		setResizable(false);
		setSize(335,cantidadCasillas*30+50);
		setLocationRelativeTo(null);
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		
		comboPremios = new JComboBox[cantidadCasillas];
		for(int i=0; i<cantidadCasillas; i++) {
			comboPremios[i] = new JComboBox<String>(Casino.getInstancia().frutasDisponibles());
			comboPremios[i].setBounds(10, i*25+10, 100, 25);
			contenedor.add(comboPremios[i]);
		}
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(130, 10, 40, 25);
		
		txtValor = new JTextField("50");
		txtValor.setBounds(190, 10, 80, 25);
		txtValor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
		        if (!((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') ))
		        	ke.consume();
		     }
		});
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(130, (cantidadCasillas-1)*25+10, 85, 25);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(225, (cantidadCasillas-1)*25+10, 85, 25);

		
		contenedor.add(lblValor);
		contenedor.add(txtValor);
		contenedor.add(btnAceptar);
		contenedor.add(btnCancelar);
		

	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAceptar) {
			String[] combinacion = new String[cantidadCasillas];
			for (int i = 0; i<cantidadCasillas; i++)
				combinacion[i] = comboPremios[i].getItemAt(comboPremios[i].getSelectedIndex()).toString();
			if(Casino.getInstancia().existePremioMaquina(idMaquina, combinacion))
				JOptionPane.showMessageDialog(contenedor, "Ese premio ya existe, elige otro");
			else {
				Casino.getInstancia().agregarPremio(idMaquina, combinacion, Float.valueOf(this.txtValor.getText()));
				dispose();
				VentanaPremios miVentanaPremios = new VentanaPremios(miVentanaPrincipal, true, this.idMaquina);
				miVentanaPremios.setVisible(true);
			}
		}
		if (e.getSource()==btnCancelar) {
			dispose();
			VentanaPremios miVentanaPremios = new VentanaPremios(miVentanaPrincipal, true, this.idMaquina);
			miVentanaPremios.setVisible(true);
		}
	}
	
}
