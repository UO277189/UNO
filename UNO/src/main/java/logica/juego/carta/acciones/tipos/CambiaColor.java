package main.java.logica.juego.carta.acciones.tipos;

import main.java.logica.juego.Juego;
import main.java.logica.juego.carta.acciones.Accion;

/**
 * Clase que representa la carta de cambio de color
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class CambiaColor implements Accion {

	@Override
	public void execute(Juego game) {
		game.nuevoColorCentro();
		game.getJugadorActual().incrementCartaCambiaColorJugadas();
		game.guardarDatos("SE HA SACADO UNA CARTA CAMBIO DE COLOR");
	}

	@Override
	public String toString() {
		return "CambiaColor";
	}

}
