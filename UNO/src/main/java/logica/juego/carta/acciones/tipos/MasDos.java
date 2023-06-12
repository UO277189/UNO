package main.java.logica.juego.carta.acciones.tipos;

import main.java.logica.juego.Juego;
import main.java.logica.juego.carta.acciones.Accion;

/**
 * Clase que representa la estrategia +2
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class MasDos implements Accion {

	@Override
	public void execute(Juego game) {
		game.incrementCardsToPick(2); // Se roban dos cartas adicionales
		game.getJugadorActual().incrementCartasMasDosJugadas();
		game.guardarDatos("SE HA SACADO UNA CARTA +2");
	}

	@Override
	public String toString() {
		return "+2";
	}

}