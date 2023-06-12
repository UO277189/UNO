package main.java.logica.juego.carta.acciones.tipos;

import main.java.logica.juego.Juego;
import main.java.logica.juego.carta.acciones.Accion;

/**
 * Clase que representa la estrategia de cambio de sentido
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class CambiarSentido implements Accion {

	@Override
	public void execute(Juego game) {
		game.cambiarOrden();
		game.getJugadorActual().incrementCartasCambiarSentidoJugadas();
		game.guardarDatos("SE HA SACADO UNA CARTA DE CAMBIO DE SENTIDO");
	}

	@Override
	public String toString() {
		return "Cambio de sentido";
	}
}
