package juego.carta.acciones;

import juego.Juego;


/**
 * Clase que representa la estrategia de cambio de sentido
 * @author Efrén García Valencia UO277189
 *
 */
public class CambiarSentido implements AccionStrategy {

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
