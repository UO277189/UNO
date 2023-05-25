package juego.carta.acciones;

import juego.Juego;


/**
 * Clase que representa la estrategia +2
 * @author Efrén García Valencia UO277189
 *
 */
public class MasDos implements AccionStrategy {

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
