package juego.jugador;

import java.util.ArrayList;

import algoritmoVoraz.VorazUno;
import algoritmoVoraz.reglas.Ranking;
import juego.carta.Carta;

/**
 * Clase que representa un jugador que juega aplicando una estrategia
 * @author Efrén García Valencia UO277189
 *
 */
public class JugadorAlgoritmo extends JugadorAbstract{

	private VorazUno voraz; 
	private Ranking ranking;
	
	/**
	 * Constructor para el algoritmo que implementa reglas
	 * @param nombreJugador El nombre del jugador
	 * @param regla La regla que implementa
	 */
	public JugadorAlgoritmo(String nombreJugador, Ranking ranking) {
		super(nombreJugador);
		this.ranking = ranking;
		voraz = new VorazUno(ranking);
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
		return voraz.vorazUNO(getCartasMano(), enMedio, historial);
	}


	/**
	 * Devuelve el ranking que implementa el algoritmo
	 * @return Ranking El ranking que implementa
	 */
	public Ranking getRanking() {
		return ranking;
	}
	
	

}
