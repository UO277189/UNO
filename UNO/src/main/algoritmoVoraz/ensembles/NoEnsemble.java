package main.algoritmoVoraz.ensembles;

import java.util.ArrayList;

import main.algoritmoVoraz.reglas.Regla;
import main.juego.carta.Carta;

/**
 * Clase que implementa la situación en la que no se aplique el ensemble de reglas
 * @author Efrén García Valencia UO277189
 *
 */
public class NoEnsemble extends Ensemble{

	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, 
			ArrayList<Carta> cartasJugadas, ArrayList<Regla> reglas) {
		
		// La idea es aplicar 1 sola regla y sacar la carta más ponderada de ese jugador
		// Está pensado para los jugadores que sólo implementan una regla
		
		Regla regla = reglas.get(0);
		ArrayList<Carta> cartasJugables =  voraz.algoritmoVoraz(cartas, cartaMedio, cartasJugadas, regla);
		
		if (!cartasJugables.isEmpty()) {
			return mayorPeso(cartasJugables, cartas, cartaMedio);
		} else {
			return -1;
		}
	}

	
	@Override
	public String toString() {
		return "NoEnsemble";
	}

	@Override
	public String getJSON() {
		return "NoEnsemble";
	}
	
}