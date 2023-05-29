package main;

import java.util.ArrayList;

import main.algoritmoVoraz.ensembles.Ensemble;
import main.juego.GestionarJuegos;
import main.juego.baraja.estrategiasBaraja.FormaBarajar;
import main.juego.jugador.JugadorAbstract;
import main.manejoDatos.Configuracion;
import main.manejoDatos.manejoConsola.ConfigurarPartidaConsola;
import main.manejoDatos.manejoConsola.LeerConsola;
import main.manejoDatos.manejoFicheros.ManejoFicherosCSV;
import main.manejoDatos.manejoFicheros.ManejoFicherosJSON;
import main.manejoDatos.manejoFicheros.ManejoFicherosTXT;

/**
 * Clase que ejecuta el juego del UNO para múltiples partidas
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class JugarPartida {

	// ATRUBUTOS
	private static ManejoFicherosJSON manejoJSON = new ManejoFicherosJSON();
	private static LeerConsola leerConsola = new LeerConsola();

	/**
	 * Método para ejecutar el programa
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		jugarPartida();
	}

	/**
	 * Método para jugar una partida
	 */
	private static void jugarPartida() {
		// Mensaje de bienvenida
		int opcion = mensajeBienvenida();

		if (opcion == 1) {
			elegirOpcionRapida();
		} else if (opcion == 2) {
			introducirDatosManuales();
		} else if (opcion == 3) {
			cargarDatosDeFichero();
		} else if (opcion == 4) {
			mostrarAyuda();
		} else if (opcion == 5) {
			System.out.println();
			System.out.println("¡Hasta la próxima!");
		}
	}

	/**
	 * Método para dar un mensaje de bienvenida
	 * 
	 * @return value La opción para jugar la partida
	 */
	private static int mensajeBienvenida() {

		logoAplicacion();

		System.out.println("");
		System.out
				.println("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:");
		System.out.println("");
		System.out.println("1.	Partida rápida");
		System.out.println("2.	Partida personalizada");
		System.out.println("3.	Cargar los datos del fichero de entrada");
		System.out.println("4.	Mostrar ayuda");
		System.out.println("5.	Salir");
		System.out.println("");
		System.out.print("Seleccione la opción deseada: ");

		LeerConsola leerConsola = new LeerConsola();
		int valor = leerConsola.leerValorRango(1, 5);

		return valor;
	}

	/**
	 * Muestra el logo de la aplicacion
	 */
	private static void logoAplicacion() {
		System.out.println(" -------                                     ------- ");
		System.out.println("|   *   |    U	   U   N     N   O  O  O    |   +   |");
		System.out.println("|   *   |    U	   U   N  N  N   O     O    |   +   |");
		System.out.println("|   *   |    U	   U   N     N   O     O    |   +   |");
		System.out.println("|   *   |    U	   U   N         O     O    |   +   |");
		System.out.println("|   *   |       U      N         O  O  O    |   +   |");
		System.out.println(" -------                                     ------- ");
	}

	/**
	 * Método para seleccionar configuraciones básicas del juego
	 */
	private static void elegirOpcionRapida() {

		System.out.println();
		System.out.println("A continuación se muestran algunas configuraciones básicas del juego.");
		System.out.println();
		System.out.println("1.	Partida manual a dos jugadores");
		System.out.println("2.	Partida manual a cuatro jugadores");
		System.out.println("3.	Partida automática, cada jugador implementa una única regla");
		System.out.println("4.	Partida automática, cada jugador implementa múltiples reglas");
		System.out.println("5.	Partida mixta con un jugador manual y tres jugadores automáticos");
		System.out.println("6.	Mostrar detalles de las partidas");
		System.out.println("7.	Volver atrás");
		System.out.println();
		System.out.print("Seleccione la opción deseada: ");

		ArrayList<Configuracion> configuraciones = manejoJSON.leerJSON("/entradas/" + manejoJSON.getFicheroEjemplos());
		int valor = leerConsola.leerValorRango(1, 7);

		if (valor == 6) {
			mostrarDetallesPartidasBasicas(configuraciones);
		} else if (valor == 7) {
			System.out.println();
			System.out.println();
			System.out.println();
			jugarPartida();
		} else {
			valor = valor - 1; // Para colocar bien el cursor
			ejecutarPartidas(configuraciones.get(valor).getNombreConfiguracion(),
					configuraciones.get(valor).getJugadoresPartida(), configuraciones.get(valor).getEstrategiaBaraja(),
					configuraciones.get(valor).getEnsemble(), configuraciones.get(valor).getNumeroPartidas(),
					configuraciones.get(valor).isTraza());
		}
	}

	/**
	 * Muestra los detalles de las partidas básicas de juego
	 * 
	 * @param configuraciones ArrayList<Configuraciones>
	 */
	private static void mostrarDetallesPartidasBasicas(ArrayList<Configuracion> configuraciones) {
		ManejoFicherosJSON manejoJSON = new ManejoFicherosJSON();
		manejoJSON.mostrarDatosFichero(configuraciones);
		volverAtras();
		elegirOpcionRapida();

	}

	/**
	 * Método para volver atrás a la hora de escoger una opción
	 */
	private static void volverAtras() {
		System.out.println();
		System.out.print("Pulse cualquier tecla para volver atrás: ");
		leerConsola.leerCualquierValor();
		System.out.println();
		System.out.println();
	}

	/**
	 * Método para meter los datos de forma manual
	 */
	private static void introducirDatosManuales() {
		
		System.out.println();
		System.out.println("A continuación se solicitarán los datos necesarios para crear una configuración. ");

		// Parámetros necesarios
		ConfigurarPartidaConsola leerDatosManual = new ConfigurarPartidaConsola();

		// Objeto configuración para guardar los datos

		String nombreFichero = leerDatosManual.elegirNombreConfiguracion();
		ArrayList<JugadorAbstract> jugadores = leerDatosManual.elegirJugadores();
		FormaBarajar estrategia = leerDatosManual.elegirEstrategiaBaraja();
		Ensemble ensemble = leerDatosManual.elegirEnsemble();
		int numeroPartidas = leerDatosManual.elegirNumeroPartidas();
		boolean verTraza = leerDatosManual.elegirVerTraza(jugadores);

		Configuracion configuracion = new Configuracion(nombreFichero, jugadores, estrategia, ensemble, numeroPartidas,
				verTraza);

		System.out.println("¿Desea guardar esta configuración? 0 - Si, 1 - No");
		int valor = leerConsola.leerValorRango(0, 1);

		if (valor == 0) {
			ManejoFicherosJSON manejoJSON = new ManejoFicherosJSON();
			manejoJSON.reescribirFicheroJSON(manejoJSON.getFicheroEntrada(), configuracion);
			System.out.println("La configuración se ha guardado correctamente.");
		}

		ejecutarPartidas(nombreFichero, jugadores, estrategia, ensemble, numeroPartidas, verTraza);
	}

	/**
	 * Método para cargar los datos de un fichero
	 */
	private static void cargarDatosDeFichero() {

		System.out.println("A continuación se mostrarán todas las configuraciones cargadas: ");
		System.out.println();

		// Se carga el manejador de objetos JSON
		ManejoFicherosJSON manejoJSON = new ManejoFicherosJSON();

		// Se cargan las configuraciones del JSON
		ArrayList<Configuracion> configuraciones = manejoJSON.leerJSON("/entradas/" + manejoJSON.getFicheroEntrada());

		if (configuraciones.isEmpty()) {
			System.out.println("NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO");
		} else {

			// Se muestran los datos de las configuraciones
			manejoJSON.mostrarDatosFichero(configuraciones);

			// Se selecciona la opción que se considere
			int valor = elegirOpcion(configuraciones);

			// Ejecutas las partidas
			valor--;
			ejecutarPartidas(configuraciones.get(valor).getNombreConfiguracion(),
					configuraciones.get(valor).getJugadoresPartida(), configuraciones.get(valor).getEstrategiaBaraja(),
					configuraciones.get(valor).getEnsemble(), configuraciones.get(valor).getNumeroPartidas(),
					configuraciones.get(valor).isTraza());
		}
	}

	/**
	 * Método que ejecuta las partidas una vez que se han cargado los parámetros
	 * 
	 * @param nombreFichero  El nombre de fichero de salida
	 * @param jugadores      Los jugadores de la partida
	 * @param estrategia     Estrategia a emplear
	 * @param ensemble       El ensemble a aplicar
	 * @param numeroPartidas Número de partidas
	 * @param verTraza       Traza a observar
	 */
	private static void ejecutarPartidas(String nombreFichero, ArrayList<JugadorAbstract> jugadores,
			FormaBarajar estrategia, Ensemble ensemble, int numeroPartidas, boolean verTraza) {

		// Aplicamos todos estos parámetros de entrada en nuestro framework que manejará
		// las partidas
		GestionarJuegos juegos = new GestionarJuegos(jugadores, estrategia, ensemble, numeroPartidas);
		// Jugamos las partidas
		juegos.jugarPartidas();
		// Sacamos los estadísticos al final
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
	 * Para elegir la opción a cargar del fichero de configuración
	 * 
	 * @param configuraciones El array con las configuraciones a cargar
	 * @return int La configuración a cargar
	 */
	public static int elegirOpcion(ArrayList<Configuracion> configuraciones) {
		System.out.println("Por favor, seleccione una opción: ");
		return leerConsola.leerValorRango(1, configuraciones.size());

	}

	/**
	 * Método que muestra una ayuda de forma textual
	 */
	private static void mostrarAyuda() {
		System.out.println();
		System.out.println("A continuación se muestra una explicación de las diferentes opciones del juego: ");
		System.out.println();
		System.out.println("****** " + "Partida rápida" + " ******");
		System.out.println(
				"Muestra algunas configuraciones sencillas que el jugador puede seleccionar. \nSirve como una introducción "
						+ "para los jugadores que no estén familiarizados con la aplicación.");
		System.out.println();
		System.out.println("****** " + "Partida personalizada" + " ******");
		System.out.println(
				"Permite al jugador crear su propia configuración de partida desde 0, \nalmacenarla si desea usarla"
						+ " en un futuro y ejecutar dicha configuración. \nEstá pensado para aquellos jugadores que no estén familiarizados con el formato del "
						+ "archivo de entrada.");
		System.out.println();
		System.out.println("****** " + "Cargar los datos del fichero de entrada" + " ******");
		System.out.println("Muestra las configuraciones almacenadas "
				+ "en el fichero de entrada configuracion.json para que el jugador seleccione la que desea utilizar. \nAdicionalmente, el jugador puede modificar el fichero "
				+ "para incluir nuevas configuraciones. \nNo obstante, si el fichero queda mal escrito no se podrá leer, \npor lo que se recomienda almacenar "
				+ "los datos en un fichero aparte antes de modificarlo.");
		volverAtras();
		System.out.println();
		jugarPartida();
	}

}