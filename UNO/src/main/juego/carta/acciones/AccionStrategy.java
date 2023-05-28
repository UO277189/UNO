package main.juego.carta.acciones;

import main.juego.Juego;

/**
 * Clase que representa una estrategia a aplicar para las acciones
 * @author Efrén García Valencia UO277189
 *
 */
public interface AccionStrategy {
	
	/**
	 * Método para ejecutar una acción de la baraja
	 * @param game El juego del UNO
	 */
	public void execute(Juego game);
}