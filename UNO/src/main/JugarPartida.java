package main;

import java.util.ArrayList;

import algoritmoVoraz.ensembles.Ensemble;
import algoritmoVoraz.reglas.Regla;
import juego.GestionarJuegos;
import juego.baraja.estrategiasBaraja.BarajarStrategy;
import juego.jugador.JugadorAbstract;
import juego.jugador.JugadorAutomatico;
import juego.jugador.JugadorManual;
import manejoDatos.manejoConsola.LeerConsola;
import manejoDatos.manejoFicheros.Configuracion;
import manejoDatos.manejoFicheros.ManejoFicherosCSV;
import manejoDatos.manejoFicheros.ManejoFicherosJSON;
import manejoDatos.manejoFicheros.ManejoFicherosTXT;

/**
 * Clase que ejecuta el juego del UNO para m�ltiples partidas
 * 
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class JugarPartida {

	/**
	 * M�todo para ejecutar el programa
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {

		// Mensaje de bienvenida
		int opcion = mensajeBienvenida();

		// Dos posibilidades
		if (opcion == 0) { // Opci�n autom�tica
			cargarDatosDeFichero();
		} else { // Opci�n manual
			System.out.println("EN PROCESO DE ARREGLE");
			// introducirDatosManuales();

		}
	}

	/**
	 * M�todo para dar un mensaje de bienvenida
	 * 
	 * @return value La opci�n para jugar la partida
	 */
	private static int mensajeBienvenida() {
		System.out.println("�Bienvenido al juego del UNO!");
		System.out.println("�Desea cargar datos del fichero de configuraci�n para experimentar? (0 - S�, 1 - No)");

		LeerConsola leerConsola = new LeerConsola();
		int valor = leerConsola.leerValorRango(0, 1);

		return valor;
	}

	/**
	 * M�todo para cargar los datos de un fichero
	 */
	private static void cargarDatosDeFichero() {

		// Se carga el manejador de objetos JSON
		ManejoFicherosJSON manejoJSON = new ManejoFicherosJSON();

		// Se cargan las configuraciones del JSON
		ArrayList<Configuracion> configuraciones = manejoJSON.leerJSON();

		if (configuraciones.isEmpty()) {
			System.out.println("NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO");
		} else {

			// Se muestran los datos de las configuraciones
			manejoJSON.mostrarDatosFichero(configuraciones);

			// Se selecciona la opci�n que se considere
			int valor = elegirOpcion(configuraciones);

			// Ejecutas las partidas
			ejecutarPartidas(configuraciones.get(valor).getNombreConfiguracion(),
					configuraciones.get(valor).getJugadoresPartida(), configuraciones.get(valor).getEstrategiaBaraja(),
					configuraciones.get(valor).getEnsemble(), configuraciones.get(valor).getNumeroPartidas(),
					configuraciones.get(valor).getTraza());
		}
	}

	/**
	 * M�todo para meter los datos de forma manual
	 */
	private static void introducirDatosManuales() {

//		// Par�metros necesarios
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
	 * M�todo que ejecuta las partidas una vez que se han cargado los par�metros
	 * 
	 * @param nombreFichero  El nombre de fichero de salida
	 * @param jugadores      Los jugadores de la partida
	 * @param estrategia     Estrategia a emplear
	 * @param ensemble       El ensemble a aplicar
	 * @param numeroPartidas N�mero de partidas
	 * @param verTraza       Traza a observar
	 */
	private static void ejecutarPartidas(String nombreFichero, ArrayList<JugadorAbstract> jugadores,
			BarajarStrategy estrategia, Ensemble ensemble, int numeroPartidas, boolean verTraza) {

		// Aplicamos todos estos par�metros de entrada en nuestro framework que manejar�
		// las partidas
		GestionarJuegos juegos = new GestionarJuegos(jugadores, estrategia, ensemble, numeroPartidas, verTraza);
		// Jugamos las partidas
		juegos.jugarPartidas();
		// Sacamos los estad�sticos al final
		juegos.mostrarResultados();

		ManejoFicherosCSV manejoFicherosCSV = new ManejoFicherosCSV();
		ManejoFicherosTXT manejoFicherosTXT = new ManejoFicherosTXT();

		// Se guardan en el CSV
		manejoFicherosCSV.escribirCSV(nombreFichero, juegos);
		// Se guarda el log en el txt
		if (verTraza) {
			manejoFicherosTXT.escribirTxT(juegos);
		}

	}


	/**
	 * Para elegir la opci�n a cargar del fichero de configuraci�n
	 * 
	 * @param configuraciones El array con las configuraciones a cargar
	 * @return int La configuraci�n a cargar
	 */
	public static int elegirOpcion(ArrayList<Configuracion> configuraciones) {
		System.out.println("Por favor, seleccione una opci�n: ");	
		LeerConsola leerConsola = new LeerConsola();
		return leerConsola.leerValorRango(0, configuraciones.size() - 1);

	}

}
