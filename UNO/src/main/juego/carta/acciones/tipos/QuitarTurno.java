package main.juego.carta.acciones.tipos;

import main.juego.Juego;
import main.juego.carta.acciones.Accion;

/**
 * Clase que representa la estrategia de quitar el turno
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class QuitarTurno implements Accion {

	@Override
	public void execute(Juego game) {
		game.nuevoTurno();
		game.getJugadorActual().incrementCartasQuitarTurnoJugadas();
		game.guardarDatos("SE HA SACADO UNA CARTA DE CAMBIO DE TURNO");
	}

	@Override
	public String toString() {
		return "Turno saltado";
	}

}
