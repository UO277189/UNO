package algoritmoVoraz.ensembles;

import java.util.ArrayList;

import algoritmoVoraz.VorazUno;
import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que implementa el ensemble de votaci�n para las reglas heur�sticas
 * 
 * @author Efr�n Garc�a Valencia UO277189
 *
 */

public class EnsembleVotacion extends Ensemble {

	/**
	 * Constructor para el ensemble por votaci�n
	 * 
	 * @param reglasCompuestas Las reglas heur�sticas a aplicar
	 */
	public EnsembleVotacion(ReglasCompuestas reglasCompuestas) {
		super(reglasCompuestas);
	}


	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas) {
		// Variables locales
		VorazUno voraz = new VorazUno();
		ArrayList<Integer> posiciones = new ArrayList<Integer>();

		for (Regla regla : reglasCompuestas.getReglas()) { // Para cada regla buscamos a�adir una posici�n
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
	
}
