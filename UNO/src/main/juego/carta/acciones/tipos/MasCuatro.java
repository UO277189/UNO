package main.juego.carta.acciones.tipos;

import main.juego.Juego;
import main.juego.carta.acciones.Accion;


/**
 * Clase que representa la estrategia de +4
 * @author Efrén García Valencia UO277189
 *
 */
public class MasCuatro implements Accion{

	@Override
	public void execute(Juego game) {
		game.nuevoColorCentro();
        game.incrementCardsToPick(4); // Se roban dos cartas adicionales
		game.getJugadorActual().incrementCartasMasCuatroJugadas();	
		game.guardarDatos("SE HA SACADO UNA CARTA +4");

	}

	
	@Override
	public String toString() {
		return "+4";
	}
	
}
