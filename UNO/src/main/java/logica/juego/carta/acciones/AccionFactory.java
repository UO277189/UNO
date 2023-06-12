package main.java.logica.juego.carta.acciones;

import main.java.logica.juego.carta.acciones.tipos.CambiaColor;
import main.java.logica.juego.carta.acciones.tipos.CambiarSentido;
import main.java.logica.juego.carta.acciones.tipos.MasCuatro;
import main.java.logica.juego.carta.acciones.tipos.MasDos;
import main.java.logica.juego.carta.acciones.tipos.QuitarTurno;

/**
 * Clase que representa una factoría para crear las acciones
 * @author Efrén García Valencia UO277189
 *
 */
public class AccionFactory {
	
	/**
	 * Método para crear la acción correspondiente
	 * @param tipo String
	 * @return Accion
	 */
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
		
		if (tipo.equals("CambiaColor")) {
			accion = new CambiaColor();
		}
		
		return accion;
	}

}

