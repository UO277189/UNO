package juego.baraja.estrategiasBaraja;

import juego.baraja.Baraja;

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

	/**
	 * Devuelv el JSON que configura la baraja
	 * @return String
	 */
	public String getJSON();
}
