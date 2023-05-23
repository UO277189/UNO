package main.multiplesPartidas;

import java.util.ArrayList;
import auxiliar.LeerConsola;
import auxiliar.ManejoFicheros;
import juego.baraja.BarajarStrategy;
import juego.configurarJuego.ColeccionJuegos;
import juego.jugador.JugadorAbstract;

/**
 * Clase que ejecuta el juego del UNO para múltiples partidas
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class JugarMultiplesPartidas {

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
			introducirDatosManuales();
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

		// Parámetros necesarios
		LeerDatosFichero leerDatosFichero = new LeerDatosFichero();

		// Paso 1: mostramos las opciones que hay al usuario para que elija una
		leerDatosFichero.mostrarDatosFichero();

		// Paso 2: seleccionamos la opción que queremos		
		String valores [] = leerDatosFichero.elegirOpcion();

		// Paso 3: pasamos esos parámetros a las configuraciones de la partida
		ArrayList<JugadorAbstract> jugadores = leerDatosFichero.elegirJugadoresParametros(valores);
		BarajarStrategy estrategia = leerDatosFichero.elegirEstrategiaParametros(valores);
		int numeroPartidas = leerDatosFichero.elegirNumeroPartidas(valores);
		boolean verTraza = leerDatosFichero.elegirTrazaParametros(valores);

		// Paso 4: ejecutas las partidas
		ejecutarPartidas(jugadores, estrategia, numeroPartidas, verTraza);
	}
	

	/**
	 * Método para meter los datos de forma manual
	 */
	private static void introducirDatosManuales() {
		
		// Parámetros necesarios
		LeerDatosManual leerDatosManual = new LeerDatosManual();
		
		ArrayList<JugadorAbstract> jugadores = leerDatosManual.elegirJugadores();
		BarajarStrategy estrategia = leerDatosManual.elegirEstrategiaBaraja();
		int numeroPartidas = leerDatosManual.elegirNumeroPartidas();
		boolean verTraza = leerDatosManual.elegirVerTraza(jugadores);

		ejecutarPartidas(jugadores, estrategia, numeroPartidas, verTraza);
	}
	
	
	/**
	 * Método que ejecuta las partidas una vez que se han cargado los parámetros
	 * 
	 * @param jugadores      Los jugadores de la partida
	 * @param estrategia     Estrategia a emplear
	 * @param numeroPartidas Número de partidas
	 * @param verTraza       Traza a observar
	 */
	private static void ejecutarPartidas(ArrayList<JugadorAbstract> jugadores, BarajarStrategy estrategia,
			int numeroPartidas, boolean verTraza) {
		
		// Aplicamos todos estos parámetros de entrada en nuestro framework que manejará
		// las partidas
		ColeccionJuegos partidas = new ColeccionJuegos(jugadores, estrategia, numeroPartidas, verTraza);
		// Jugamos las partidas
		partidas.jugarPartidas();
		// Sacamos los estadísticos al final
		partidas.mostrarResultados();
		
		ManejoFicheros manejoFicheros = new ManejoFicheros();
		
		// Se guardan en el CSV
		manejoFicheros.escribirCSV(partidas);
		// Se guarda el log en el txt
		manejoFicheros.escribirTxT(partidas);
		// Se vuelcan los datos en un excel
		manejoFicheros.escribirExcel();
		// Se guardan los graficos en el excel
		manejoFicheros.escribirGraficos();
	}

}
