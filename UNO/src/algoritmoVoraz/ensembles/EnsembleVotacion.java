package algoritmoVoraz.ensembles;

import java.util.ArrayList;

import algoritmoVoraz.VorazUno;
import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que implementa el ensemble de votación para las reglas heurísticas
 * 
 * @author Efén García Valencia UO277189
 *
 */

public class EnsembleVotacion extends Ensemble {

	/**
	 * Constructor para el ensemble por votación
	 * 
	 * @param reglasCompuestas Las reglas heurísticas a aplicar
	 */
	public EnsembleVotacion(ReglasCompuestas reglasCompuestas) {
		super(reglasCompuestas);
	}


	@Override
	public int ejecutarEnsemble(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas) {
		// Variables locales
		VorazUno voraz = new VorazUno();
		ArrayList<Integer> posiciones = new ArrayList<Integer>();

		for (Regla regla : reglasCompuestas.getReglas()) { // Para cada regla buscamos añadir una posición
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
	
	
	/**
	 * Devuelve la posición de la carta que tenga mayor peso
	 * 
	 * @param S          ArrayList<Carta> las cartas a ponderar
	 * @param cartasMano las cartas de la mano para buscar la pos
	 * @param cartaMedio la carta del medio para evitar excepciones
	 * @return int
	 */
	private int mayorPeso(ArrayList<Carta> S, ArrayList<Carta> cartasMano, Carta cartaMedio) {
		float maxPeso = -1000; // Valor a superar
		Carta cartaElegida = null;

		for (int i = 0; i < S.size(); i++) {
			// En caso de empate nos quedamos con la que tenga menor índice en el array
			if (S.get(i).getPeso() > maxPeso) {
				maxPeso = S.get(i).getPeso();
				cartaElegida = S.get(i);
			}
		}

		// Ahora que tenemos la carta elegida buscamos su posición original
		return posCartaConcreta(cartasMano, cartaElegida);
	}

}
