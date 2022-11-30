package gui;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;


import controlador.Casino;

public class VentanaJuego extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2933209452054034622L;
	private Container contenedor;
	private JPanel pnlPrecioJugada;
	private JLabel[] lblFruta;
	private JLabel lblCasino, lblFondo, lblPrecioJugada;
	private JButton btnJugar, btnCredito, btnSalir, btnCobrar;
	private Casino c;
	private int idMaquina, cantidadCasillas;
	private final static String[] frutas = {"banana", "frutilla", "guinda", "manzana", "sandia", "uva"};
	
	
	
	
	public VentanaJuego(Casino c, int idMaquina){
		this.c = c;
		this.idMaquina = idMaquina;
		this.cantidadCasillas = c.cantidadCasillasMaquina(idMaquina);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Maquina : " + idMaquina);
		setSize(810, 405);
		iniciaComponentes();
	}
	
	
	private void iniciaComponentes(){
		contenedor = getContentPane();
		contenedor.setLayout(null);
		
		lblFondo = new JLabel(new ImageIcon(getClass().getResource("Fondo.png")));
		lblFondo.setBounds(0, 0, 810, 405);
		
		lblCasino = new JLabel(new ImageIcon(getClass().getResource("Casino.png")));
		lblCasino.setBounds(50, 20, 530, 100);
		
		pnlPrecioJugada = new JPanel();
		pnlPrecioJugada.setBounds(200, 320, 160, 30);
		pnlPrecioJugada.setBackground(Color.GREEN);
		
		lblPrecioJugada = new JLabel("Precio jugada: $" + c.precioJugadaMaquina(idMaquina));
		pnlPrecioJugada.add(lblPrecioJugada);
		
		lblFruta = new JLabel[cantidadCasillas];
		for(int i=0; i<cantidadCasillas;i++) {
			lblFruta[i] = new JLabel();
			lblFruta[i] = new JLabel(new ImageIcon(getClass().getResource("Frutilla.png")));
			lblFruta[i].setBounds(25 + (i)*110, 160, 90, 90);	
			contenedor.add(lblFruta[i]);		
		}
		
		
		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(680, 160, 100, 100);
		btnJugar.addActionListener(this);
		
		btnCobrar = new JButton("Retirarse");
		btnCobrar.setBounds(50, 320, 100, 30);
		btnCobrar.addActionListener(this);
		
		
		btnCredito = new JButton("Credito: " + c.consultarCreditoMaquina(idMaquina));
		btnCredito.setBounds(645, 35, 145, 25);
		btnCredito.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(680, 320, 100, 30);
		btnSalir.addActionListener(this);
		
		contenedor.add(btnSalir);
		contenedor.add(btnJugar);
		contenedor.add(btnCobrar);
		contenedor.add(btnCredito);
		contenedor.add(pnlPrecioJugada);
		contenedor.add(lblCasino);
		contenedor.add(lblFondo);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == btnCredito) {
			String codigo = JOptionPane.showInputDialog("Ingrese el numero de ticket");
			if (codigo == null || codigo == "")
				JOptionPane.showMessageDialog(contenedor, "No se ingresó ningún ticket");
			else {
				float valor = 0;
				if((valor = c.cargarCredito(idMaquina, codigo)) != 0)
					JOptionPane.showMessageDialog(contenedor, "Usted ingresó $" + valor);
				else
					JOptionPane.showMessageDialog(contenedor, "El código ''" + codigo + "'' no es válido");
			}
			btnCredito.setText("Credito :" + c.consultarCreditoMaquina(idMaquina));
		}
		
		if(e.getSource() == btnCobrar) {
			float credito = c.consultarCreditoMaquina(idMaquina);
			if(credito > 0) {
				String[] botones = {"Aceptar", "Cancelar"};
				int respuesta = JOptionPane.showOptionDialog(contenedor, "¿Desea retirarse con $" + credito + "?", "Retiro de dinero", 0, JOptionPane.QUESTION_MESSAGE, null, botones, "");
				if(respuesta == 0) {
					JOptionPane.showMessageDialog(contenedor, "El código del ticket es: ''" + c.generarTicketCaja(idMaquina) + "'', por un valor de $" + credito);
					btnCredito.setText("Credito :" + c.consultarCreditoMaquina(idMaquina)); //Podria poner 0, pero así veo que no quede saldo realmente
				}
			}else
				JOptionPane.showMessageDialog(contenedor, "No tiene saldo para retirar");
		}
		
		if(e.getSource() == btnSalir) {
			float credito = c.consultarCreditoMaquina(idMaquina);
			if(credito > 0) {
				String[] botones = {"Quedarse", "Salir"};
				
				int respuesta = JOptionPane.showOptionDialog(contenedor, "¿Seguro que desea salir? En la máquina quedarán $" + credito ,"Salir", 0, JOptionPane.QUESTION_MESSAGE, null, botones, "");
				if(respuesta == 1) {
					c.cerrarMaquina(idMaquina);
					dispose();
				}
			}else {
				c.cerrarMaquina(idMaquina);
				dispose();
			}
		}
		
		if(e.getSource() == btnJugar) {
			if (c.precioJugadaMaquina(idMaquina) > c.consultarCreditoMaquina(idMaquina)) {
				JOptionPane.showMessageDialog(contenedor, "Usted necesita al menos $" + c.precioJugadaMaquina(idMaquina) + " para poder jugar");
				return;
			}
			
			btnJugar.setEnabled(false);
			new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {	
					jugada(VentanaJuego.this.cantidadCasillas, c, idMaquina);

					
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

	private void jugada(int cantidadCasillas, Casino c, int idMaquina) throws Exception{
		if(c.recaudacionMaquina(idMaquina) == 0) {
			JOptionPane.showMessageDialog(contenedor, "Esta máquina no está disponible. Retire su dinero");
			return;
		}
		c.jugar(idMaquina);
		float resultado = c.ultimaJugada(idMaquina);
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
		
		btnCredito.setText("Credito :" + c.consultarCreditoMaquina(idMaquina));
		if (resultado == 0)
			JOptionPane.showMessageDialog(contenedor, "Usted perdió");
		else
			JOptionPane.showMessageDialog(contenedor, "Usted ganó $" + resultado);
		
		if (c.recaudacionMaquina(idMaquina) < c.recaudacionMinimaMaquina(idMaquina))
			JOptionPane.showMessageDialog(contenedor, "Esta máquina tiene poco dinero. Es posible que su premio no pueda ser cobrado. Continúe jugando bajo su propio riesgo");
	}
}
