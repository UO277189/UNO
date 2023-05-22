package juego.jugador;

import java.util.ArrayList;

import algoritmoVoraz.ensembles.Ensemble;
import algoritmoVoraz.ensembles.EnsembleRanking;
import algoritmoVoraz.ensembles.EnsembleSuma;
import algoritmoVoraz.ensembles.EnsembleVotacion;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que representa un jugador que juega aplicando una estrategia
 * @author Efrén García Valencia UO277189
 *
 */
public class JugadorAlgoritmo extends JugadorAbstract{

	private Ensemble ensemble; 
	private ReglasCompuestas reglasCompuestas;
	
	/**
	 * Constructor para el algoritmo que implementa reglas
	 * @param nombreJugador El nombre del jugador
	 * @param regla La regla que implementa
	 */
	public JugadorAlgoritmo(String nombreJugador, ReglasCompuestas reglasCompuestas) {
		super(nombreJugador);
		this.reglasCompuestas = reglasCompuestas;
		ensemble = new EnsembleRanking(reglasCompuestas);
	}
	
	
	@Override
	public int elegirNuevoColor(int length) {	
		// Se podría aplicar una estrategia estudiando los casos en los que el jugador tenga más cartas de un palo
		// Para simplificar, de momento se escoge al azar
		return (int) (Math.random()*3);
	}

	@Override
	public int jugarTurno(Carta enMedio, ArrayList<Carta> historial) {
		// Aplica el algoritmo correspondiente con las cartas de la mano y la carta en medio
		return ensemble.ejecutarEnsemble(getCartasMano(), enMedio, historial);
	}


	/**
	 * Devuelve las reglas que implementa el algoritmo
	 * @return ReglasCompuestas Las reglas que implementa
	 */
	public ReglasCompuestas getReglasCompuestas() {
		return reglasCompuestas;
	}
	
	

}
