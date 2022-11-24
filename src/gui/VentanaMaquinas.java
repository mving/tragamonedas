package gui;

import controlador.Casino;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class VentanaMaquinas extends JFrame implements ActionListener {

	private static final long serialVersionUID = -547052736035159320L;
	private Container contenedor;
	private int cantidadMaquinas;
	JButton[] btnMaquinas;
	
	public VentanaMaquinas(boolean modoConfiguracion, Casino c) {
		setTitle(modoConfiguracion ? "Configurar" : "Jugar");
		//cantidadMaquinas = c.cantidadMaquinas();
		cantidadMaquinas = 50;
		
		contenedor = getContentPane();
		int x = cantidadMaquinas/5 ; 
		contenedor.setLayout(new GridLayout(8,x));
		

		setSize(135*(x+1),50*x);
		setLocationRelativeTo(null);
		
		
		btnMaquinas = new JButton[cantidadMaquinas];
		for(int i=0; i<cantidadMaquinas;i++) {
			btnMaquinas[i] = new JButton();
			btnMaquinas[i].setText("Maquina " + (i+1));
			contenedor.add(btnMaquinas[i]);
			btnMaquinas[i].addActionListener(this);
		}
		
		
		if(modoConfiguracion) {
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		//Llamo a la configuracion de la maquina seleccionada
		JOptionPane.showMessageDialog(contenedor, "Seleccionó la maquina: " + e.getActionCommand().substring(8));
		
	}
	
	
	
}
