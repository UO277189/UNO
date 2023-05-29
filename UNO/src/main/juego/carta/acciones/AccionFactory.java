package main.juego.carta.acciones;

import main.juego.carta.acciones.tipos.CambiarSentido;
import main.juego.carta.acciones.tipos.MasCuatro;
import main.juego.carta.acciones.tipos.MasDos;
import main.juego.carta.acciones.tipos.QuitarTurno;

/**
 * Clase que representa una factoría para crear las acciones
 * @author Efrén García Valencia UO277189
 *
 */
public class AccionFactory {
	
	public static Accion crearAccion (String tipo) {
		
		Accion accion = null;
		
		if (tipo.equals("CambiarSentido")) {
			accion = new CambiarSentido();
		}
		
		if (tipo.equals("MasCuatro")) {
			accion = new MasCuatro();
		}
		
		if (tipo.equals("MasDos")) {
			accion = new MasDos();
		}
		
		if (tipo.equals("QuitarTurno")) {
			accion = new QuitarTurno();
		}
		
		return accion;
	}

}
