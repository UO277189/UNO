package juego.jugador;

import java.util.ArrayList;

import auxiliar.LeerConsola;
import juego.carta.Carta;

/**
 * Clase que representa un jugador manual
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class JugadorManual extends JugadorAbstract {
	
    // Clase para leer por consola
    private LeerConsola leerConsola = new LeerConsola();
    
	public JugadorManual(String nombreJugador) {
		super(nombreJugador);
	}

	@Override
	public int elegirNuevoColor(int length) {
		return leerConsola.intRango("Nuevo Color: ", 0, length);
	}


	@Override
	public int jugarTurno(Carta enMedio, ArrayList<Carta> historial) {
		return leerConsola.intRango( "ELIGE UNA CARTA, -1 PARA ROBAR", -1, this.getCartasMano().size());
	}

}
