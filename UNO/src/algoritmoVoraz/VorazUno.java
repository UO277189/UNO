package algoritmoVoraz;

import java.util.ArrayList;

import algoritmoVoraz.reglas.Regla;
import juego.carta.Carta;

/**
 * Clase AlgoritmoVorazUno que ejecuta el algoritmo voraz aplicando las reglas
 * en base al ensemble que se tenga en un momento dado
 * 
 * @author Efrén García Valencia UO277189
 */
public class VorazUno {

	/**
	 * Método que implementa el algoritmo voraz del cual parten todos los ensembles
	 * 
	 * @param cartas        las cartas del jugador
	 * @param cartaMedio    la carta del medio
	 * @param cartasJugadas las cartas jugadas hasta el momento
	 * @param regla         La regla a aplicar
	 * @return ArrayList<Carta>  Las cartas que se pueden jugar
	 */
	public ArrayList<Carta> algoritmoVoraz(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas,
			Regla regla) {

		// Hay que crear un array auxiliar para evitar perder las cartas del jugador
		ArrayList<Carta> cartasVoraz = new ArrayList<Carta>();
		for (int i = 0; i < cartas.size(); i++) {
			cartasVoraz.add(cartas.get(i));
		}

		// Primero se ponderan las cartas incluyendo el peso adecuado
		// Dentro del conjunto se ejecutan todas las reglas
		ponderarCartasBase(cartasVoraz);
		regla.execute(cartasVoraz, cartasJugadas);

		// Para evitar errores al decrementar las cartas
		int size = cartasVoraz.size();

		// Algoritmo
		ArrayList<Carta> S = new ArrayList<Carta>();
		while (S.size() == 0 && cartasVoraz.size() > 0) {
			for (int i = 0; i < size; i++) {
				Carta carta = cartasVoraz.remove(0);
				// Si se puede echar esa carta lo añade
				if (cartaMedio.sePuedeEchar(carta)) {
					S.add(carta);
				}
			}
		}

		// Paso adicional: quitamos las cartas que no se aplican en caso de que tengamos
		// que responder a un +2/+4
		// De esta forma evitamos bucles infinitos
		S = extraerCartasNoJugables(S, cartaMedio);
		
		return S;
	}
	
	//********MÉTODOS AUXILIARES********

	/**
	 * Método que pondera las cartas del juego del UNO con un valor base
	 * 
	 * @param cartas Las cartas del jugador en un momento determinado
	 */
	private void ponderarCartasBase(ArrayList<Carta> cartas) {
		for (Carta carta : cartas) {
			carta.setPeso(0); // La base es 0
		}
	}

	/**
	 * Método que extrae las cartas no jugables en caso de que tengamos que evitar
	 * un bucle infinito en la aplicación
	 * 
	 * @param s          Las cartas ponderadas
	 * @param cartaMedio La carta del medio
	 * @return ArrayList<Carta>
	 */
	private ArrayList<Carta> extraerCartasNoJugables(ArrayList<Carta> s, Carta cartaMedio) {
		ArrayList<Carta> sortCartas = new ArrayList<Carta>();

		if ((cartaMedio.toString().contains("+2") || cartaMedio.toString().contains("+4"))
				&& tieneQueRobar(s, cartaMedio)) {
			sortCartas = quitarCartasQueNoAplican(s, cartaMedio);
		} else {
			sortCartas = s;
		}
		return sortCartas;
	}

	/**
	 * Método para quitar las cartas que no se aplican en caso de que haya que
	 * responder a un +2/+4
	 * 
	 * @param s          El array de cartas
	 * @param cartaMedio La carta del medio
	 * @return El array de cartas con aquellas que nos sirven
	 */
	private ArrayList<Carta> quitarCartasQueNoAplican(ArrayList<Carta> s, Carta cartaMedio) {

		ArrayList<Carta> sortCartasNoAplican = new ArrayList<Carta>();
		for (int i = 0; i < s.size(); i++) {
			sortCartasNoAplican.add(s.get(i));
		}

		for (Carta carta : s) {
			if (cartaMedio.toString().contains("+4")) {
				if (!carta.toString().contains("+4")) {
					sortCartasNoAplican.remove(carta);
				}
			}
			if (cartaMedio.toString().contains("+2")) {
				if (!carta.toString().contains("+4") && !carta.toString().contains("+2")) {
					sortCartasNoAplican.remove(carta);
				}
			}
		}
		return sortCartasNoAplican;
	}

	/**
	 * Metodo para saber si hay que priorizar un +4/+2 o no
	 * 
	 * @param s          cartas a ponderar
	 * @param cartaMedio la carta del medio
	 * @return boolean
	 */
	private boolean tieneQueRobar(ArrayList<Carta> s, Carta cartaMedio) {
		boolean doesHaveIt = false;
		for (Carta aEchar : s) {
			if (cartaMedio.puedeNoRobar(aEchar)) {
				// Si tienes una carta que puedes echar para no robar
				doesHaveIt = true;
			}
		}
		return doesHaveIt;
	}

}
