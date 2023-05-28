package main.algoritmoVoraz.ensembles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import main.algoritmoVoraz.reglas.Regla;
import main.juego.carta.Carta;

/**
 * Clase que implementa el ensemble de ranking para las reglas heurísticas
 * 
 * @author Efrén García Valencia UO277189
 *
 */

public class EnsembleRanking extends Ensemble {

	

	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio,
			ArrayList<Carta> cartasJugadas, ArrayList<Regla> reglas) {

		// La idea es desde un Map sumar puntuaciones según la posición en la que esté
		// en el array
		HashMap<Integer, Integer> mapRanking = new HashMap<Integer, Integer>();

		for (Regla regla : reglas) { // Para cada regla buscamos añadir una posición
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

		// Ahora buscamos el que MENOR posición tenga
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
		return posMin; // El que menor valor tenga es aquel que tiene mejor posición en el ranking
	}

	/**
	 * Método que te devuelve un array con las cartas elegidas ordenadas por peso
	 * 
	 * @param s          ArrayList<Carta> las cartas a ponderar
	 * @param cartasMano las cartas de la mano para buscar la pos
	 * @param cartaMedio la carta del medio para evitar excepciones
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> rankingPorPeso(ArrayList<Carta> s, ArrayList<Carta> cartasMano, Carta cartaMedio) {
		// Ordenamos las cartas
		Collections.sort(s, Carta.PesoComparator);

		// Buscamos la posición original de las cartas
		return searchOriginalPos(s, cartasMano);
	}

	@Override
	public String toString() {
		return "EnsembleRanking";
	}

	@Override
	public String getJSON() {
		return "EnsembleRanking";
	}
	
	
	
	
}
