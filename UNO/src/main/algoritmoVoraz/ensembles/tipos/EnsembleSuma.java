package main.algoritmoVoraz.ensembles.tipos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.algoritmoVoraz.ensembles.Ensemble;
import main.algoritmoVoraz.reglas.Regla;
import main.juego.carta.Carta;

/**
 * Clase que implementa el ensemble de suma para las reglas heurísticas
 * @author Efrén García Valencia UO277189
 *
 */

public class EnsembleSuma extends Ensemble {
	


	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, 
			ArrayList<Carta> cartasJugadas, ArrayList<Regla> reglas) {
		// La idea es desde un Map sumar puntuaciones según la posición en la que esté en el array
		HashMap<Carta, Float> mapSuma = new HashMap<Carta, Float>();
		
		for (Regla regla : reglas) { // Para cada regla buscamos añadir una posición
			ArrayList<Carta> sumaCartas = voraz.algoritmoVoraz(cartas, cartaMedio, cartasJugadas, regla);
			// Para cada uno de los valores
			float pesoExtra = 0;
			for (int i = 0; i < sumaCartas.size(); i++) {
				if (mapSuma.containsKey(sumaCartas.get(i))){
					pesoExtra = mapSuma.get(sumaCartas.get(i)); // Para no perder el peso extra acumulado
				}				
				mapSuma.put(sumaCartas.get(i), sumaCartas.get(i).getPeso() + pesoExtra);
			}
		}
		
		// Ahora buscamos el que MAYOR peso tenga
		float valueMax = 0;
		Carta cartaMax = null;
		for (Map.Entry<Carta, Float> entry : mapSuma.entrySet()) {
			if (entry.getValue() > valueMax) {
				valueMax = entry.getValue();
				cartaMax = entry.getKey();
			}
		}
		
		if (mapSuma.isEmpty()) { // Si hay que robar
			return -1;
		}
		return posCartaOriginal(cartaMax, cartas); // Devolvemos aquel que tenga mayor peso
	}

	@Override
	public String toString() {
		return "EnsembleSuma";
	}

	@Override
	public String getJSON() {
		return "EnsembleSuma";
	}
	

}