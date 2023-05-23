package algoritmoVoraz.ensembles;

import java.util.ArrayList;

import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que implementa la situación en la que no se aplique el ensemble de reglas
 * @author Efrén García Valencia UO277189
 *
 */
public class NoEnsemble extends Ensemble{

	/**
	 * Constructor para la situación en la que no aplicamos ensembles
	 * @param reglasCompuestas Las reglas compuestas que tiene el jugador
	 */
	public NoEnsemble(ReglasCompuestas reglasCompuestas) {
		super(reglasCompuestas);
	}

	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas) {
		// Este caso suele aplicarse si sólo tenemos una sola regla
		// Es muy parecido al ensemble de votación, la diferencia es que como es una regla devuelves
		// únicamente la posición de la carta con mayor peso
		
		ArrayList<Integer> posiciones = new ArrayList<Integer>();
		for (Regla regla : reglasCompuestas.getReglas()) {
			ArrayList<Carta> cartasJugables =  voraz.algoritmoVoraz(cartas, cartaMedio, cartasJugadas, regla);
			if (!cartasJugables.isEmpty()) {
				int newPos = mayorPeso(cartasJugables, cartas, cartaMedio);
				if (newPos != -1) { 
					posiciones.add(newPos);
				}
			}
		}
		
		return valorMaximoArray(posiciones);
	}

}
