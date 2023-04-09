package algoritmoVoraz.reglas;

import java.util.ArrayList;

import juego.carta.Carta;

/**
 * Clase que implementa un command que agrupa las diferentes reglas a aplicar
 * @author Efrén García Valencia UO277189
 *
 */
public class Ranking {
	
	// Las reglas que se van a aplicar
	private ArrayList<Regla> reglas = new ArrayList<Regla>();

	
	/**
	 * Constructor que aplica una regla
	 * @param regla La regla a aplicar
	 */
	public Ranking (Regla regla) {
		this.reglas = new ArrayList<Regla>();
		reglas.add(regla);
	}
	
	/**
	 * Constructor que aplica múltiples reglas
	 * @param reglas Las reglas a aplicar
	 */
	public Ranking(ArrayList<Regla> reglas) {
		this.reglas = reglas;
	}
	
	/**
	 * Método que incluye una nueva regla al ranking
	 * @param regla La nueva regla a incluir
	 */
	public void addNewRegla(Regla regla) {
		this.reglas.add(regla);
	}
	
	
	/**
	 * Método que ejecuta el ranking llamando al execute de cada regla
	 * @param cartas Las cartas a echar
	 * @param historial El historial
	 */
	public void rankReglas(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		
		// Primero hay que poner un valor base para las cartas
		ponderarCartasBase(cartas);
		
		// Vamos en orden INVERSO para que se ejecuten al final las reglas más prioritarias
		for (int i = reglas.size()-1; i >= 0; i--) {
			reglas.get(i).execute(cartas, historial);
		}
	}

	/**
	 * Método que pondera las cartas del juego del UNO con un valor base
	 * @param cartas Las cartas del jugador en un momento determinado
	 */
	private void ponderarCartasBase(ArrayList<Carta> cartas) {
		for (Carta carta : cartas) {
			carta.setPeso(0); // La base es 0
		}
	}
	
	

}
