package main.juego.carta.acciones;

import main.juego.Juego;

/**
 * Clase que representa una estrategia a aplicar para las acciones
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public interface AccionStrategy {
	
	/**
	 * M�todo para ejecutar una acci�n de la baraja
	 * @param game El juego del UNO
	 */
	public void execute(Juego game);
}
