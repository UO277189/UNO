package algoritmoVoraz.ensembles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que implementa el ensemble de ranking para las reglas heur�sticas
 * 
 * @author Ef�n Garc�a Valencia UO277189
 *
 */

public class EnsembleRanking extends Ensemble {

	/**
	 * Constructor para el ensemble de ranking
	 * 
	 * @param reglasCompuestas Las reglas compuestas
	 */
	public EnsembleRanking(ReglasCompuestas reglasCompuestas) {
		super(reglasCompuestas);
	}

	/**
	 * M�todo que te devuelve la posici�n de la carta a jugar que haya sido m�s
	 * votada entre todas las reglas
	 * 
	 * @param cartas        Cartas en la mano del jugador
	 * @param cartaMedio    La carta del medio
	 * @param cartasJugadas Las cartas jugadas hasta el momento
	 * @return int La posici�n de la carta a jugar
	 */
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas) {

		// La idea es desde un Map sumar puntuaciones seg�n la posici�n en la que est�
		// en el array
		HashMap<Integer, Integer> mapRanking = new HashMap<Integer, Integer>();

		for (Regla regla : reglasCompuestas.getReglas()) { // Para cada regla buscamos a�adir una posici�n
			ArrayList<Carta> cartasJugables = voraz.algoritmoVoraz(cartas, cartaMedio, cartasJugadas, regla);
			ArrayList<Integer> rankingRegla = rankingPorPeso(cartasJugables, cartas, cartaMedio);

			// Para cada uno de los valores
			int puntosExtra = 0;
			for (int i = 0; i < rankingRegla.size(); i++) {
				if (mapRanking.containsKey(rankingRegla.get(i))) {
					puntosExtra = mapRanking.get(rankingRegla.get(i));
				}
				mapRanking.put(rankingRegla.get(i), i + puntosExtra);
			}
		}

		// Ahora buscamos el que MENOR posici�n tenga
		int valueMin = Integer.MAX_VALUE;
		int posMin = 0;
		for (Map.Entry<Integer, Integer> entry : mapRanking.entrySet()) {
			if (entry.getValue() < valueMin) {
				valueMin = entry.getValue();
				posMin = entry.getKey();
			}
		}

		if (mapRanking.isEmpty()) { // Si hay que robar
			return -1;
		}
		return posMin; // El que menor valor tenga es aquel que tiene mejor posici�n en el ranking
	}

	/**
	 * M�todo que te devuelve un array con las cartas elegidas ordenadas por peso
	 * 
	 * @param s          ArrayList<Carta> las cartas a ponderar
	 * @param cartasMano las cartas de la mano para buscar la pos
	 * @param cartaMedio la carta del medio para evitar excepciones
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> rankingPorPeso(ArrayList<Carta> s, ArrayList<Carta> cartasMano, Carta cartaMedio) {
		// Ordenamos las cartas
		Collections.sort(s, Carta.PesoComparator);

		// Buscamos la posici�n original de las cartas
		return searchOriginalPos(s, cartasMano);
	}
}
