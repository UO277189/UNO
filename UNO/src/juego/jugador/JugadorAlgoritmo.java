package juego.jugador;

import java.util.ArrayList;

import algoritmoVoraz.VorazUno;
import algoritmoVoraz.reglas.ReglaStrategy;
import juego.carta.Carta;

/**
 * Clase que representa un jugador que juega aplicando una estrategia
 * @author Efrén García Valencia UO277189
 *
 */
public class JugadorAlgoritmo extends JugadorAbstract{

	private VorazUno voraz; 
	private ReglaStrategy regla;
	
	public JugadorAlgoritmo(String nombreJugador, ReglaStrategy regla) {
		super(nombreJugador);
		this.regla = regla;
		voraz = new VorazUno(regla);
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
	 * Devuelve la regla que se aplica al algoritmo vorza
	 * @return ReglaStrategy La regla que se aplica al algoritmo voraz
	 */
	public ReglaStrategy getRegla() {
		return regla;
	}
	
	

}
