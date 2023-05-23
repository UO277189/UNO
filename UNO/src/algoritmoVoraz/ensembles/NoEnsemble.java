package algoritmoVoraz.ensembles;

import java.util.ArrayList;

import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que implementa la situaci�n en la que no se aplique el ensemble de reglas
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class NoEnsemble extends Ensemble{

	/**
	 * Constructor para la situaci�n en la que no aplicamos ensembles
	 * @param reglasCompuestas Las reglas compuestas que tiene el jugador
	 */
	public NoEnsemble(ReglasCompuestas reglasCompuestas) {
		super(reglasCompuestas);
	}

	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas) {
		// Este caso suele aplicarse si s�lo tenemos una sola regla
		// Es muy parecido al ensemble de votaci�n, la diferencia es que como es una regla devuelves
		// �nicamente la posici�n de la carta con mayor peso
		
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
