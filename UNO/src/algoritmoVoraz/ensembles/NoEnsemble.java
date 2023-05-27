package algoritmoVoraz.ensembles;

import java.util.ArrayList;

import algoritmoVoraz.reglas.Regla;
import juego.carta.Carta;

/**
 * Clase que implementa la situaci�n en la que no se aplique el ensemble de reglas
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class NoEnsemble extends Ensemble{

	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, 
			ArrayList<Carta> cartasJugadas, ArrayList<Regla> reglas) {
		
		// La idea es aplicar 1 sola regla y sacar la carta m�s ponderada de ese jugador
		// Est� pensado para los jugadores que s�lo implementan una regla
		
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
