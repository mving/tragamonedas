package gui;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.Casino;

public class VentanaJuego extends JFrame implements ActionListener{

	private static final long serialVersionUID = -2933209452054034622L;
	private Container contenedor;
	private JLabel[] lblFruta;
	private JPanel[] fruta;
	private JPanel menu, info, salir;
	private JButton btnJugar, btnAgregarCredito, btnSalir;
	private Casino c;
	private int idMaquina, cantidad;
	
	public VentanaJuego(Casino c, int idMaquina){
		this.c = c;
		this.idMaquina = idMaquina;
		this.cantidad = c.cantidadCasillasMaquina(idMaquina);
		setTitle("Maquina : " + idMaquina);
		setSize(810, 405);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		iniciaComponentes();
	}
	
	
	private void iniciaComponentes() {
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		fruta = new JPanel[cantidad];
		lblFruta = new JLabel[cantidad];
		for(int i=0; i<cantidad;i++) {
			fruta[i] = new JPanel();
			lblFruta[i] = new JLabel();
			fruta[i].setBackground(Color.BLUE);
			lblFruta[i] = new JLabel(new ImageIcon(getClass().getResource("Frutilla.png")));
			fruta[i].add(lblFruta[i]);
			fruta[i].setBounds(25+(i)*110, 120, 90, 90);	
			contenedor.add(fruta[i]);		
		}
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(700, 320, 100, 40);
		contenedor.add(btnSalir);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
