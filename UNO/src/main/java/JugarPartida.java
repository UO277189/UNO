package main.java;

import main.java.consola.InterfaceConsola;

/**
 * Clase que inicia la aplicación
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class JugarPartida {

	/**
	 * Método para ejecutar el programa
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		// Se inicia la interfaz por consola
		InterfaceConsola interfaceConsola = new InterfaceConsola();
		interfaceConsola.jugarPartida();
	}
}