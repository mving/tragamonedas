package gui;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;


import controlador.Casino;

public class VentanaJuego extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2933209452054034622L;
	private Container contenedor;
	private JLabel[] lblFruta;
	private JLabel lblCredito, lblCasino, lblFondo;
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
		setLocationRelativeTo(null);
		setResizable(false);
		iniciaComponentes();
	}
	
	
	private void iniciaComponentes(){
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		lblFondo = new JLabel(new ImageIcon(getClass().getResource("Fondo.png")));
		lblFondo.setBounds(0, 0, 810, 405);
		
		lblCasino = new JLabel(new ImageIcon(getClass().getResource("Casino.png")));
		lblCasino.setBounds(10, 50, 530, 100);
		
		
		
		lblFruta = new JLabel[cantidadCasillas];
		for(int i=0; i<cantidadCasillas;i++) {
			lblFruta[i] = new JLabel();
			lblFruta[i] = new JLabel(new ImageIcon(getClass().getResource("Frutilla.png")));
			lblFruta[i].setBounds(25+(i)*110, 120, 90, 90);	
			contenedor.add(lblFruta[i]);		
		}
		
		
		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(680, 120, 100, 100);
		btnJugar.addActionListener(this);
		
		lblCredito = new JLabel("Credito: " + c.consultarCreditoMaquina(idMaquina));
		lblCredito.setBounds(650, 35, 100, 20);
		
		btnAgregarCredito = new JButton("");
		btnAgregarCredito.setBounds(740, 35, 25, 25);
		btnAgregarCredito.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(680, 300, 100, 40);
		btnSalir.addActionListener(this);
		
		contenedor.add(btnSalir);
		contenedor.add(btnJugar);
		contenedor.add(lblCredito);
		contenedor.add(btnAgregarCredito);
		contenedor.add(lblCasino);
		contenedor.add(lblFondo);
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
			btnJugar.setEnabled(false);
			new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {	
					boolean jugada;
						jugada = jugada(VentanaJuego.this.cantidadCasillas, c, idMaquina);

					String respuesta = jugada ? "Ganó" : "Perdió";
					JOptionPane.showMessageDialog(contenedor, "Usted" + respuesta);
					btnJugar.setEnabled(true);
					return null;
				}
				
	            @Override
	            protected void done() {
	                try {
	                    get();
	                } catch (Exception ignore) {
	                } finally {
	                    btnJugar.setEnabled(true);
	                }
	            } 		
			
		}.execute();
		
		
		
		}
	}
	
	private void cambiarFruta(int posicion, String fruta) {
		this.lblFruta[posicion].setIcon(new ImageIcon(getClass().getResource(fruta + ".png")));
	}

	private boolean jugada(int cantidadCasillas, Casino c, int idMaquina) throws Exception{
//TODO aviso de credito, comprobacion
		
		c.jugar(idMaquina);
		boolean resultado = c.ultimaJugada(idMaquina);
		String[] combinacion = c.ultimaCombinacion(idMaquina);
		int contador = 0;
		long pausa, finish;
		for(int i=0;i<cantidadCasillas;i++) {
			finish = System.currentTimeMillis() + 500;
			while(System.currentTimeMillis() < finish) {
				contador = (contador == 5) ? 0 : contador+1; 
				for(int j=i;j<cantidadCasillas;j++) {
					cambiarFruta(j, frutas[contador]);
				}
				pausa = System.currentTimeMillis() + 50;
				while(System.currentTimeMillis() < pausa);
			}
			cambiarFruta(i, combinacion[i]);
		}
		
		lblCredito.setText("Credito :" + c.consultarCreditoMaquina(idMaquina));
		
		return resultado;
	}
}
