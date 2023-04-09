package algoritmoVoraz.reglas;

import java.util.ArrayList;
import juego.carta.Carta;

/**
 * Clase que representa una regla a aplicar para dar pesos a las cartas
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public interface ReglaStrategy {

	/**
	 * M�todo para ejecutar una regla para las cartas
	 * @param cartas las cartas del jugador
	 * @param historial las cartas jugadas hasta el momento
	 */
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial);
}
