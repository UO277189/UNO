package juego.jugador;

import java.util.ArrayList;

import juego.carta.Carta;
import manejoDatos.manejoConsola.LeerConsola;

/**
 * Clase que representa un jugador manual
 * @author Efrén García Valencia UO277189
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
	
	@Override
	public String getJSON() {
		return  "{" +
	            "\"nombre\": \"" + this.getNombreJugador() + "\"," +
	            "\"regla\": \"" + "no_aplica" + "\"" +
	        "}";
	}
	

}
