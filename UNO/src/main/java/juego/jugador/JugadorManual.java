package main.java.juego.jugador;

import java.util.ArrayList;

import main.java.juego.carta.Carta;
import main.java.manejoDatos.manejoConsola.LeerConsola;

/**
 * Clase que representa un jugador manual
 * @author Efrén García Valencia UO277189
 *
 */
public class JugadorManual extends Jugador {
	
    // Clase para leer por consola
    private LeerConsola leerConsola = new LeerConsola();
    
    /**
     * Constructor para el jugador manual
     * @param nombreJugador El nombre del jugador
     */
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
		return  "        {\n" +
        		"          \"nombre\": \"" + this.getNombreJugador() + "\",\n" +
        		"          \"regla\": \"" + "no_aplica" + "\"" + "\n" + 
        		"        }"; 
	}
	

}