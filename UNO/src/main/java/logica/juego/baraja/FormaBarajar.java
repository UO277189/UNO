package main.java.logica.juego.baraja;

/**
 * Clase que referencia diferentes estrategias de la baraja
 * @author Efrén García Valencia UO277189
 *
 */
public interface FormaBarajar {

	/**
	 * Método que baraja las cartas
	 * @param baraja Baraja de cartas
	 */
	public void execute(Baraja baraja);

	/**
	 * Devuelv el JSON que configura la baraja
	 * @return String
	 */
	public String getJSON();
}
