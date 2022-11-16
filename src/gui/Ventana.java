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
	
	public Ventana(int cantidad) throws InterruptedException {
		this.setSize(110*cantidad+150, 375);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Tragamonedas");
		this.setResizable(false);
		iniciarComponentes(cantidad);
	}
	
	private void iniciarComponentes(int cantidad) throws InterruptedException {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		getContentPane().add(panel);
		
		fruta = new JPanel[cantidad];
		for(int i=0; i<cantidad;i++)
			fruta[i] = new JPanel();

		lblFruta = new JLabel[cantidad];
		for(int i=0; i<cantidad;i++)
			lblFruta[i] = new JLabel();
		
		for(int i=0; i<cantidad;i++) {
			fruta[i].setBackground(Color.BLUE);
			lblFruta[i] = new JLabel(new ImageIcon(getClass().getResource("Frutilla.png")));
			fruta[i].add(lblFruta[i]);
			panel.add(fruta[i]);
		}
		for(int i=0; i<cantidad; i++)
			fruta[i].setBounds(25+(i)*110, 120, 90, 90);
		/*fruta[0].setBounds(50, 120, 90, 90);
		fruta[1].setBounds(190, 120, 90, 90);
		fruta[2].setBounds(330, 120, 90, 90);
		*/
		

	}
	
	public void random(int cantidad_frutas) throws InterruptedException {
		int contador = 0;
		for(int i=0;i<cantidad_frutas;i++) {
			long finish = System.currentTimeMillis() + 500;
			while(System.currentTimeMillis() < finish) {
				if(contador == 5)
					contador = 0;
				else
					contador++;
				for(int j=i;j<cantidad_frutas;j++)
					cambiarFruta(j+1, borrar[contador]);
				TimeUnit.MILLISECONDS.sleep(50);
			}
		}
	}
	
	public void cambiarFruta(int posicion, String fruta) {
		this.lblFruta[posicion-1].setIcon(new ImageIcon(getClass().getResource(fruta + ".png")));
	}
}
