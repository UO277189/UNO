package main;


import java.util.ArrayList;

import algoritmoVoraz.ensembles.Ensemble;
import auxiliar.LeerConsola;
import auxiliar.ManejoFicheros;
import auxiliar.LectorFicherosJSON;
import juego.baraja.BarajarStrategy;
import juego.jugador.JugadorAbstract;

/**
 * Clase que ejecuta el juego del UNO para múltiples partidas
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class JugarPartidas {
	
	
	// Para leer por consola
	
	private LeerConsola leerConsola = new LeerConsola();

	/**
	 * Método para ejecutar el programa
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {

		// Mensaje de bienvenida
		int opcion = mensajeBienvenida();

		// Dos posibilidades
		if (opcion == 0) { // Opción automática
			cargarDatosDeFichero();
		} else { // Opción manual
			System.out.println("EN PROCESO DE ARREGLE");
			//introducirDatosManuales();
			
		}
	}
	
	
	/**
	 * Método para dar un mensaje de bienvenida
	 * 
	 * @return value La opción para jugar la partida
	 */
	private static int mensajeBienvenida() {
		System.out.println("¡Bienvenido al juego del UNO!");
		System.out.println("¿Desea cargar datos del fichero de configuración para experimentar? (0 - Sí, 1 - No)");
		
		LeerConsola leerConsola = new LeerConsola();	
		int valor = leerConsola.leerValorRango(0, 1);
		
		return valor;
	}
	
	/**
	 * Método para cargar los datos de un fichero
	 */
	private static void cargarDatosDeFichero() {

		// Se carga el manejador de objetos JSON
		LectorFicherosJSON manejoJSON = new LectorFicherosJSON();
		
		// Se cargan las configuraciones del JSON
		ArrayList<Configuracion> configuraciones = manejoJSON.leerJSON();
		
		// Se muestran los datos de las configuraciones
		mostrarDatosFichero(configuraciones);
		
		// Se selecciona la opción que se considere
		int valor = elegirOpcion(configuraciones);
		
		// Ejecutas las partidas
		ejecutarPartidas(configuraciones.get(valor).getNombreConfiguracion(),
				configuraciones.get(valor).getJugadoresPartida(),
				configuraciones.get(valor).getEstrategiaBaraja(),
				configuraciones.get(valor).getEnsemble(),
				configuraciones.get(valor).getNumeroPartidas(),
				configuraciones.get(valor).getTraza());
		
	}
	

	private static void mostrarDatosFichero(ArrayList<Configuracion> configuraciones) {
		// COMPLETAR
		
	}


	/**
	 * Método para meter los datos de forma manual
	 */
	private static void introducirDatosManuales() {
		
//		// Parámetros necesarios
//		LeerDatosManual leerDatosManual = new LeerDatosManual();
//		
//		ArrayList<JugadorAbstract> jugadores = leerDatosManual.elegirJugadores();
//		BarajarStrategy estrategia = leerDatosManual.elegirEstrategiaBaraja();
//		int numeroPartidas = leerDatosManual.elegirNumeroPartidas();
//		boolean verTraza = leerDatosManual.elegirVerTraza(jugadores);
//
//		ejecutarPartidas(jugadores, estrategia, numeroPartidas, verTraza);
	}
	
	
	/**
	 * Método que ejecuta las partidas una vez que se han cargado los parámetros
	 * @param nombreFichero 		El nombre de fichero de salida
	 * @param jugadores      Los jugadores de la partida
	 * @param estrategia     Estrategia a emplear
	 * @param ensemble 		 El ensemble a aplicar
	 * @param numeroPartidas Número de partidas
	 * @param verTraza       Traza a observar
	 */
	private static void ejecutarPartidas(String nombreFichero, ArrayList<JugadorAbstract> jugadores, BarajarStrategy estrategia,
			Ensemble ensemble, int numeroPartidas, boolean verTraza) {
		
		// Aplicamos todos estos parámetros de entrada en nuestro framework que manejará
		// las partidas
		ColeccionJuegos partidas = new ColeccionJuegos(jugadores, estrategia, ensemble, numeroPartidas, verTraza);
		// Jugamos las partidas
		partidas.jugarPartidas();
		// Sacamos los estadísticos al final
		partidas.mostrarResultados();
		
		ManejoFicheros manejoFicheros = new ManejoFicheros();
		
		// Se guardan en el CSV
		manejoFicheros.escribirCSV(nombreFichero, partidas);
		// Se guarda el log en el txt
		manejoFicheros.escribirTxT(partidas);

	}
	
	
	
	
	/**
	 * Para elegir la opción a cargar del fichero de configuración
	 * @param configuraciones El array con las configuraciones a cargar
	 * @return int La configuración a cargar
	 */
	public static int elegirOpcion(ArrayList<Configuracion> configuraciones) {
		
		// COMPLETAR
		
		// Eliges una opción
		
//		int valor = leerConsola.leerValorRango(0, configuracion.size() - 1);
//		return configuracion.get(valor).split(manejoFicheros.getSeparador());

		return -1;
	}

}
