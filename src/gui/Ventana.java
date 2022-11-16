package gui;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import view.BananaView;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel[] lblFruta;
	private JPanel[] fruta;
	public final static String[] borrar = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	
	public Ventana() throws InterruptedException {
		this.setSize(640, 375);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Tragamonedas");
		this.setResizable(false);
		iniciarComponentes();
	}
	
	private void iniciarComponentes() throws InterruptedException {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		getContentPane().add(panel);
		
		fruta = new JPanel[3];
		for(int i=0; i<3;i++)
			fruta[i] = new JPanel();

		lblFruta = new JLabel[3];
		for(int i=0; i<3;i++)
			lblFruta[i] = new JLabel();
		
		for(int i=0; i<3;i++) {
			fruta[i].setBackground(Color.BLUE);
			lblFruta[i] = new JLabel(new ImageIcon(getClass().getResource("Frutilla.png")));
			fruta[i].add(lblFruta[i]);
			panel.add(fruta[i]);
		}
		fruta[0].setBounds(50, 120, 90, 90);
		fruta[1].setBounds(190, 120, 90, 90);
		fruta[2].setBounds(330, 120, 90, 90);
		
		

	}
	
	public void random() throws InterruptedException {
		int contador = 0;
		long finish = System.currentTimeMillis() + 500;
		while(System.currentTimeMillis() < finish) {
			if(contador == 5)
				contador = 0;
			else
				contador++;
			cambiarFruta(1, borrar[contador]);
			cambiarFruta(2, borrar[contador]);
			cambiarFruta(3, borrar[contador]);
			TimeUnit.MILLISECONDS.sleep(50);
		}
		
		finish = System.currentTimeMillis() + 500;
		while(System.currentTimeMillis() < finish) {
			if(contador == 5)
				contador = 0;
			else
				contador++;
			cambiarFruta(2, borrar[contador]);
			cambiarFruta(3, borrar[contador]);
			TimeUnit.MILLISECONDS.sleep(50);
		}
		
		finish = System.currentTimeMillis() + 500;
		while(System.currentTimeMillis() < finish) {
			if(contador == 5)
				contador = 0;
			else
				contador++;
			cambiarFruta(3, borrar[contador]);
			TimeUnit.MILLISECONDS.sleep(50);
		}
	}
	
	public void cambiarFruta(int posicion, String fruta) {
		this.lblFruta[posicion-1].setIcon(new ImageIcon(getClass().getResource(fruta + ".png")));
	}
}
