package modelo;

public class Premio {
	
	private float valor;
	private String[] combinacion; 
	
	
	public Premio(float valor, String[] combinacion) {
		this.valor = valor;
		this.combinacion = new String[combinacion.length];
		for (int i = 0; i < combinacion.length; i++)
			this.combinacion[i] = combinacion[i];
	}
	
	public boolean soyEsePremio(String[] combinacion) {
		for (int i = 0; i < combinacion.length; i++) {
			if(!this.combinacion[i].equals(combinacion[i]))
				return false;
		}
		return true;
	}
	
	
	public float valorPremio() {
		return this.valor;
	}
	
	//premio tiene que verificar que no exista
	
	
	
	
	
	
	
	
	
}
