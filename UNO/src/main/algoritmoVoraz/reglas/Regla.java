package main.algoritmoVoraz.reglas;

import java.util.ArrayList;

import main.juego.carta.Carta;

/**
 * Clase que representa una regla a aplicar para dar pesos a las cartas
 * @author Efrén García Valencia UO277189
 *
 */
public interface Regla {

	/**
	 * Método para ejecutar una regla para las cartas
	 * @param cartas las cartas del jugador
	 * @param historial las cartas jugadas hasta el momento
	 */
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial);
}
