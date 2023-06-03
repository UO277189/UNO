package main.java.algoritmoVoraz.ensembles.tipos;

import java.util.ArrayList;

import main.java.algoritmoVoraz.ensembles.Ensemble;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.juego.carta.Carta;

/**
 * Clase que implementa el ensemble de votación para las reglas heurísticas
 * 
 * @author Efrén García Valencia UO277189
 *
 */

public class EnsembleVotacion extends Ensemble {
	
	
	@Override
	public int execute(ArrayList<Carta> cartas, Carta cartaMedio,
			ArrayList<Carta> cartasJugadas, ArrayList<Regla> reglas) {
		// Variables locales

		ArrayList<Integer> posiciones = new ArrayList<Integer>();

		for (Regla regla : reglas) { // Para cada regla buscamos añadir una posición
			ArrayList<Carta> cartasJugables =  voraz.algoritmoVoraz(cartas, cartaMedio, cartasJugadas, regla);
			if (!cartasJugables.isEmpty()) {
				int newPos = mayorPeso(cartasJugables, cartas, cartaMedio);
				if (newPos != -1) { // Si puedes no robar
					posiciones.add(newPos);
				}
			}
		}
		
		return valorMaximoArray(posiciones);
	}

	@Override
	public String toString() {
		return "EnsembleVotacion";
	}
	
	@Override
	public String getJSON() {
		return "EnsembleVotacion";
	}
	
}