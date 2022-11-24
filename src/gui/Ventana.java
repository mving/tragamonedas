package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.Casino;


public class Ventana extends JFrame {

	private static final long serialVersionUID = 725488021436028396L;
	
	private JLabel[] lblFruta;
	private JPanel[] fruta;
	private JPanel menu, info, fondo, salir;
	public final static String[] borrar = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	
	public Ventana(int cantidad) throws InterruptedException {
		this.setSize(810, 405);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Tragamonedas");
		this.setResizable(false);
		Casino c = Casino.getInstancia();
		ventanaLogin(c);
		//ventanaJuego(cantidad,c, 1);
			
	}
	
	
	private void ventanaLogin(Casino c) {
		Container cnt = this.getContentPane();
		cnt.setLayout(new GridLayout(1,2));
		JButton btnMaquinas = new JButton("Elegir Maquina");
		JButton btnConfigurar = new JButton("Configurar Maquina");
		cnt.add(btnConfigurar);
		cnt.add(btnMaquinas);
		
		BotonesLogin b = new BotonesLogin(this);
		btnMaquinas.addActionListener(b);
		btnConfigurar.addActionListener(b);
	}
	
	private void ventanaJuego(int cantidad, Casino c, int idMaquina) throws InterruptedException {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		fondo = new JPanel();
		JLabel lblFondo = new JLabel(new ImageIcon(getClass().getResource("Fondo.png")));
		fondo.add(lblFondo);
		fondo.setBounds(0, 0, 810, 405);
		this.getContentPane().add(panel);
		
		info = new JPanel();
		info.setBounds(675, 30, 100, 130);
		info.setBackground(Color.BLUE);
		panel.add(info);
		
		menu = new JPanel();
		menu.setBounds(675, 170, 100, 100);
		menu.setBackground(Color.BLUE);
		panel.add(menu);
		
		JLabel lblCredito = new JLabel("Credito");
		JButton btnJugar = new JButton("Jugar");
		JButton btnSaldo = new JButton("+Saldo");
		menu.add(lblCredito);
		menu.add(btnJugar);	
		menu.add(btnSaldo);
			
		
		salir = new JPanel();
		salir.setBackground(new Color(1, 1, 1, 0));
		salir.setBounds(700, 320, 100, 40);
		JButton btnSalir = new JButton("Salir");
		salir.add(btnSalir);
		panel.add(salir);
		/////////////////////////////
		BotonesMaquina m = new BotonesMaquina(this, c, idMaquina);
		btnSalir.addActionListener(m);
		btnJugar.addActionListener(m);
		
		fruta = new JPanel[cantidad];
		lblFruta = new JLabel[cantidad];
		for(int i=0; i<cantidad;i++) {
			fruta[i] = new JPanel();
			lblFruta[i] = new JLabel();
			fruta[i].setBackground(Color.BLUE);
			lblFruta[i] = new JLabel(new ImageIcon(getClass().getResource("Frutilla.png")));
			fruta[i].add(lblFruta[i]);
			fruta[i].setBounds(25+(i)*110, 120, 90, 90);	
			panel.add(fruta[i]);		
		}
		
		panel.add(fondo);
	}
	
	public void jugada(int cantidad_frutas, Casino c, int idMaquina) throws InterruptedException {
		//String resultado = c.ultimaJugada(idMaquina);
		c.imitaJuego(idMaquina);
		String[] combinacion = c.ultimaCombinacion(idMaquina);
		int contador = 0;
		for(int i=0;i<cantidad_frutas;i++) {
			long finish = System.currentTimeMillis() + 500;
			while(System.currentTimeMillis() < finish) {
				contador = (contador == 5) ? 0 : contador+1; 
				for(int j=i;j<cantidad_frutas;j++) {
					cambiarFruta(j, borrar[contador]);
				
				}
				TimeUnit.MILLISECONDS.sleep(50);
			}
			cambiarFruta(i, combinacion[i]);
		}
	}
	
	public void cambiarFruta(int posicion, String fruta) {
		this.lblFruta[posicion].setIcon(new ImageIcon(getClass().getResource(fruta + ".png")));
	}
	
	class BotonesLogin implements ActionListener{
		private JFrame ventana;
		
		public BotonesLogin(JFrame ventana) {
			this.ventana = ventana;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Elegir Maquina":
				JOptionPane.showMessageDialog(ventana, "Elegir Maquina");
				
				break;
			case "Configurar Maquina":
				JOptionPane.showMessageDialog(ventana, "Configurar Maquina");
				break;
			default:
				break;
			}
			
		}
		
	}
	
	
	class BotonesMaquina implements ActionListener{
		
		private JFrame ventana;
		private Casino c;
		private int idMaquina;
		public BotonesMaquina(JFrame ventana, Casino c, int idMaquina) {
			this.ventana = ventana;
			this.c = c;
			this.idMaquina = idMaquina;
		}

		public void actionPerformed(ActionEvent e) {
			/*switch (e.getActionCommand()) {
			case "+Saldo":
				
				break;
			
			case "Jugar":
				JOptionPane.showMessageDialog(ventana, "Algun dato es inválido");
				System.out.print("hola");
				c.jugar(idMaquina);
				Ventana.this.jugada(idMaquina, c, idMaquina);

				break;
				
			case "Salir":
				
				break;

			default:
				break;
			}
			*/
			if(e.getActionCommand()=="Jugar") {
				JOptionPane.showMessageDialog(ventana, "Algun dato es inválido");
				try {
					Ventana.this.jugada(idMaquina, c, idMaquina);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		}

		
		
	}
	
	
	
	
	
}
