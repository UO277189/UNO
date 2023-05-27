package main.algoritmoVoraz.ensembles;

import java.util.ArrayList;

import main.algoritmoVoraz.VorazUno;
import main.algoritmoVoraz.reglas.Regla;
import main.juego.carta.Carta;

/**
 * Clase que implementa el ensemble de votaci�n para las reglas heur�sticas
 * 
 * @author Efr�n Garc�a Valencia UO277189
 *
 */

public class EnsembleVotacion extends Ensemble {
	
	
	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio,
			ArrayList<Carta> cartasJugadas, ArrayList<Regla> reglas) {
		// Variables locales
		VorazUno voraz = new VorazUno();
		ArrayList<Integer> posiciones = new ArrayList<Integer>();

		for (Regla regla : reglas) { // Para cada regla buscamos a�adir una posici�n
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
