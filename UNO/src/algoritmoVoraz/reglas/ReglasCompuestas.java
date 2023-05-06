package algoritmoVoraz.reglas;

import java.util.ArrayList;

/**
 * Clase que implementa un command que agrupa las diferentes reglas a aplicar
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglasCompuestas {
	
	// Las reglas que se van a aplicar
	private ArrayList<Regla> reglas = new ArrayList<Regla>();

	
	/**
	 * Constructor que aplica una regla
	 * @param regla La regla a aplicar
	 */
	public ReglasCompuestas (Regla regla) {
		this.reglas = new ArrayList<Regla>();
		reglas.add(regla);
	}
	
	/**
	 * Constructor que aplica múltiples reglas
	 * @param reglas Las reglas a aplicar
	 */
	public ReglasCompuestas(ArrayList<Regla> reglas) {
		this.reglas = reglas;
	}
	
	/**
	 * Método que incluye una nueva regla al conjunto
	 * @param regla La nueva regla a incluir
	 */
	public void addNewRegla(Regla regla) {
		this.reglas.add(regla);
	}

	/**
	 * Devuelve las reglas del conjunto
	 * @return ArrayList <Regla>
	 */
	public ArrayList<Regla> getReglas() {
		return reglas;
	}
}
