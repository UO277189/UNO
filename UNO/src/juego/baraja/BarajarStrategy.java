package juego.baraja;

/**
 * Clase que referencia diferentes estrategias de la baraja
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public interface BarajarStrategy {

	/**
	 * M�todo que baraja las cartas
	 * @param baraja Baraja de cartas
	 */
	public void execute(Baraja baraja);
}
