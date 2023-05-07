package algoritmoVoraz;

import java.util.ArrayList;
import java.util.Collections;

import algoritmoVoraz.reglas.Regla;
import juego.carta.Carta;

/**
 * Clase AlgoritmoVorazUno que ejecuta el algoritmo voraz aplicando las reglas
 * correspondientes
 * 
 * @author Efrén García Valencia UO277189
 */
public class VorazUno {


	/**
	 * Encuentra la carta más adecuada para echar en un momento dado. Devuelve la
	 * posición de esa carta o -1 si no hay nada que sirva para robar la carta
	 * 
	 * @param cartas        las cartas del jugador
	 * @param cartaMedio    la carta del medio
	 * @param cartasJugadas las cartas jugadas hasta el momento
	 * @param regla La regla a aplicar
	 * @return int La posición de la carta
	 */
	public int aplicarAlgoritmoVoraz(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas, Regla regla) {

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
		if (S.size() > 0) {
			return mayorPeso(S, cartas, cartaMedio);
		} else {
			return -1; // Robamos una carta
		}
	}
	
	
	/**
	 * Versión del algoritmo voraz en el que se aplica un ranking 
	 * 
	 * @param cartas        las cartas del jugador
	 * @param cartaMedio    la carta del medio
	 * @param cartasJugadas las cartas jugadas hasta el momento
	 * @param regla La regla a aplicar
	 * @return ArrayList<Integer> Un array con el ranking de cartas hecho
	 */
	public ArrayList<Integer> aplicarAlgoritmoVorazRanking(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas, Regla regla) {

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
		// En este punto S contiene todas las cartas que nos interesan, pero tenemos que ordenarlas 
		// y sacar las posiciones de las cartas originales
		
		ArrayList<Integer> ranking = new ArrayList<Integer>();
		if (S.size() > 0) {
			ranking =  rankingPorPeso(S, cartas, cartaMedio);
		} 
		return ranking;
	}
	
	/**
	 * Método que te devuelve un array con las cartas elegidas ordenadas por peso
	 * @param s          ArrayList<Carta> las cartas a ponderar
	 * @param cartasMano las cartas de la mano para buscar la pos
	 * @param cartaMedio la carta del medio para evitar excepciones
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> rankingPorPeso(ArrayList<Carta> s, ArrayList<Carta> cartasMano, Carta cartaMedio) {
		
		// COMPROBACIÓN QUE FALTA: evitar bucles infinitos
		
		// Ordenamos las cartas
		Collections.sort(s, Carta.PesoComparator);
		
		// Buscamos la posición original de las cartas
		return searchOriginalPos(s, cartasMano);
	}


	/**
	 * Método que te sirve para establecer un array con las posiciones originales de las cartas
	 * @param s Las cartas ponderadas
	 * @param cartasMano Las cartas en la mano
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> searchOriginalPos(ArrayList<Carta> s, ArrayList<Carta> cartasMano) {
		ArrayList<Integer> ranking = new ArrayList<Integer>();
		for (Carta c : s) {
			ranking.add(this.posCartaConcreta(cartasMano, c));
		}
		return ranking;
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

	/**
	 * Devuelve la posición de la carta que tenga mayor peso
	 * 
	 * @param s          ArrayList<Carta> las cartas a ponderar
	 * @param cartasMano las cartas de la mano para buscar la pos
	 * @param cartaMedio la carta del medio para evitar excepciones
	 * @return int
	 */
	private int mayorPeso(ArrayList<Carta> s, ArrayList<Carta> cartasMano, Carta cartaMedio) {
		float maxPeso = -1000; // Valor a superar
		Carta cartaElegida = null;

		// CASO ESPECIAL
		// Si la carta anterior es un +2 y tienes un +2 O si la carta anterior es un +4
		// y tienes un +4 obligatoriamente tienes que responder a la jugada
		// Si no lo haces te genera bucle infinito
		// En esa situacion sin importar la regla aplicada se priorizara siempre estas
		// cartas

		if ((cartaMedio.toString().contains("+2") || cartaMedio.toString().contains("+4"))
				&& tieneQueRobar(s, cartaMedio)) {
			cartaElegida = revisarJugadaMasDosMasCuatro(s, cartaMedio);
		} else {
			for (int i = 0; i < s.size(); i++) {
				// En caso de empate nos quedamos con la que tenga menor índice en el array
				if (s.get(i).getPeso() > maxPeso) {
					maxPeso = s.get(i).getPeso();
					cartaElegida = s.get(i);
				}
			}
		}

		// Ahora que tenemos la carta elegida buscamos su posición original
		return posCartaConcreta(cartasMano, cartaElegida);
	}


	/**
	 * Método para hallar la posición original de una carta en la mano
	 * @param cartasMano Las cartas de la mano
	 * @param cartaElegida La carta elegida
	 * @return int
	 */
	private int posCartaConcreta(ArrayList<Carta> cartasMano, Carta cartaElegida) {
		int pos = -1;
		for (int j = 0; j < cartasMano.size(); j++) {
			try {
				if (cartaElegida.equals(cartasMano.get(j))) {
					pos = j;
				}
			} catch (NullPointerException e) {
				System.out.println("Ha ocurrido un error");
			}
		}
		return pos;
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

	/**
	 * Metodo que elige la carta a robar carta en caso de un +2 o +4. SIEMPRE tiene
	 * prioridad frente a cualquier otra regla
	 * 
	 * @param s          las cartas a ponderar
	 * @param cartaMedio la carta del medio
	 * @return Carta la carta a devolver
	 */
	private Carta revisarJugadaMasDosMasCuatro(ArrayList<Carta> s, Carta cartaMedio) {

		Carta cartaAResponder = null;

		// Si la carta en medio es un +4 obligatoriamente tenemos que buscar un +4
		// Para un +2 te sirve otro +2 o +4

		// Nos quedamos con la primera mejor opcion

		for (Carta carta : s) {
			if (cartaMedio.toString().contains("+4")) {
				if (carta.toString().contains("+4")) {
					cartaAResponder = carta;
					break; // Devuelve esa carta
				}
			}
			if (cartaMedio.toString().contains("+2")) {
				if (carta.toString().contains("+4") || carta.toString().contains("+2")) {
					cartaAResponder = carta;
					break; // Devuelve esa carta
				}
			}
		}

		return cartaAResponder;

	}
}
