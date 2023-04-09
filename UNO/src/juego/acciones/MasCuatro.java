package juego.acciones;

import juego.Juego;


/**
 * Clase que representa la estrategia de +4
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class MasCuatro implements AccionStrategy{

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
