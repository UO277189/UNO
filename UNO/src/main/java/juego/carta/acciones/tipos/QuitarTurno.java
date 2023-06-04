package main.java.juego.carta.acciones.tipos;

import main.java.juego.Juego;
import main.java.juego.carta.acciones.Accion;

/**
 * Clase que representa la estrategia de quitar el turno
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class QuitarTurno implements Accion {

	@Override
	public void execute(Juego game) {
		// Esto tiene que ser antes porque si no se incrementa mal
		game.getJugadorActual().incrementCartasQuitarTurnoJugadas();
		game.nuevoTurno();
		game.guardarDatos("SE HA SACADO UNA CARTA DE CAMBIO DE TURNO");
	}

	@Override
	public String toString() {
		return "Turno saltado";
	}

}
