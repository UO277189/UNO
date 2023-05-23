package juego.baraja;

/**
 * Clase que referencia diferentes estrategias de la baraja
 * @author Efrén García Valencia UO277189
 *
 */
public interface BarajarStrategy {

	/**
	 * Método que baraja las cartas
	 * @param baraja Baraja de cartas
	 */
	public void execute(Baraja baraja);
}
