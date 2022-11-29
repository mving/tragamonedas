package gui;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controlador.Casino;

public class VentanaJuego extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2933209452054034622L;
	private Container contenedor;
	private JLabel[] lblFruta;
	//private JLabel credito;
	private JPanel[] fruta;
	private JButton btnJugar, btnAgregarCredito, btnSalir;
	private Casino c;
	private int idMaquina, cantidadCasillas;
	private final static String[] frutas = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	
	
	
	
	public VentanaJuego(Casino c, int idMaquina){
		this.c = c;
		this.idMaquina = idMaquina;
		this.cantidadCasillas = c.cantidadCasillasMaquina(idMaquina);
		setTitle("Maquina : " + idMaquina);
		setSize(810, 405);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		iniciaComponentes();
	}
	
	
	private void iniciaComponentes(){
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		
		fruta = new JPanel[cantidadCasillas];
		lblFruta = new JLabel[cantidadCasillas];
		for(int i=0; i<cantidadCasillas;i++) {
			fruta[i] = new JPanel();
			lblFruta[i] = new JLabel();
			fruta[i].setBackground(Color.BLUE);
			lblFruta[i] = new JLabel(new ImageIcon(getClass().getResource("Frutilla.png")));
			fruta[i].add(lblFruta[i]);
			fruta[i].setBounds(25+(i)*110, 120, 90, 90);	
			contenedor.add(fruta[i]);		
		}
		
		
		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(650, 250, 100, 100);
		btnJugar.addActionListener(this);
		
		btnAgregarCredito = new JButton("Cargar Credito");
		btnAgregarCredito.setBounds(650, 25, 100, 30);
		btnAgregarCredito.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(700, 320, 100, 40);
		btnSalir.addActionListener(this);
		
		contenedor.add(btnSalir);
		contenedor.add(btnJugar);
		contenedor.add(btnAgregarCredito);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == btnAgregarCredito) {
			String respuesta = JOptionPane.showInputDialog("Ingrese el numero de ticket");
			if (respuesta == null || respuesta == "")
				JOptionPane.showMessageDialog(contenedor, "No se ingresó ningún ticket");
			else {
				if(c.cargarCredito(idMaquina, respuesta))
					JOptionPane.showMessageDialog(contenedor, "Usted ingresó saldo correctamente");
				else
					JOptionPane.showMessageDialog(contenedor, "El código ingresado no es válido");
			}
		}
		
		if(e.getSource()== btnJugar) {
			boolean jugada;
				jugada = jugada(this.cantidadCasillas, c, idMaquina);

			String respuesta = jugada ? "Ganó" : "Perdió";
			JOptionPane.showMessageDialog(contenedor, "Usted" + respuesta);
		}
	}
	
	private void cambiarFruta(int posicion, String fruta) {
		this.lblFruta[posicion].setIcon(new ImageIcon(getClass().getResource(fruta + ".png")));
	}

	private boolean jugada(int cantidadCasillas, Casino c, int idMaquina){

		
		c.jugar(idMaquina);
		boolean resultado = c.ultimaJugada(idMaquina);
		String[] combinacion = c.ultimaCombinacion(idMaquina);
		int contador = 0;
		//long pausa, finish;
		for(int i=0;i<cantidadCasillas;i++) {
			long finish = System.currentTimeMillis() + 500;
			while(System.currentTimeMillis() < finish) {
				contador = (contador == 5) ? 0 : contador+1; 
				for(int j=i;j<cantidadCasillas;j++) {
					cambiarFruta(j, frutas[contador]);
				}
				//pausa = System.currentTimeMillis() + 50;
				//while(System.currentTimeMillis() < pausa);
			}
			cambiarFruta(i, combinacion[i]);
		}
		return resultado;
	}
}
