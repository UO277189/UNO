package main;

import java.util.ArrayList;

import algoritmoVoraz.ensembles.Ensemble;
import juego.GestionarJuegos;
import juego.baraja.estrategiasBaraja.BarajarStrategy;
import juego.jugador.JugadorAbstract;
import manejoDatos.Configuracion;
import manejoDatos.manejoConsola.ConfigurarPartidaConsola;
import manejoDatos.manejoConsola.LeerConsola;
import manejoDatos.manejoFicheros.ManejoFicherosCSV;
import manejoDatos.manejoFicheros.ManejoFicherosJSON;
import manejoDatos.manejoFicheros.ManejoFicherosTXT;

/**
 * Clase que ejecuta el juego del UNO para múltiples partidas
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
		
		// Mensaje de bienvenida
		int opcion = mensajeBienvenida();

		// Dos posibilidades
		
		if (opcion == 1) {
			//elegirOpcionRapida();
		} else if (opcion == 2) {
			introducirDatosManuales();
		} else if (opcion == 3) {
			cargarDatosDeFichero();
		} else if (opcion == 4) {
			//mostrarAyuda();
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
		

		System.out.println(" -------                                     ------- ");
		System.out.println("|   *   |    U	   U   N     N   O  O  O    |   +   |");
		System.out.println("|   *   |    U	   U   N  N  N   O     O    |   +   |");
		System.out.println("|   *   |    U	   U   N     N   O     O    |   +   |");
		System.out.println("|   *   |    U	   U   N         O     O    |   +   |");
		System.out.println("|   *   |       U      N         O  O  O    |   +   |");
		System.out.println(" -------                                     ------- ");

		System.out.println("");
		System.out.println("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:");
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
	 * Método para cargar los datos de un fichero
	 */
	private static void cargarDatosDeFichero() {
		
		System.out.println("A continuación se mostrarán todas las configuraciones cargadas: ");
		System.out.println();

		// Se carga el manejador de objetos JSON
		ManejoFicherosJSON manejoJSON = new ManejoFicherosJSON();

		// Se cargan las configuraciones del JSON
		ArrayList<Configuracion> configuraciones = manejoJSON.leerJSON(manejoJSON.getFicheroEntrada());

		if (configuraciones.isEmpty()) {
			System.out.println("NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO");
		} else {

			// Se muestran los datos de las configuraciones
			manejoJSON.mostrarDatosFichero(configuraciones);

			// Se selecciona la opción que se considere
			int valor = elegirOpcion(configuraciones);

			// Ejecutas las partidas
			ejecutarPartidas(configuraciones.get(valor).getNombreConfiguracion(),
					configuraciones.get(valor).getJugadoresPartida(), configuraciones.get(valor).getEstrategiaBaraja(),
					configuraciones.get(valor).getEnsemble(), configuraciones.get(valor).getNumeroPartidas(),
					configuraciones.get(valor).getTraza());
		}
	}

	/**
	 * Método para meter los datos de forma manual
	 */
	private static void introducirDatosManuales() {

		// Parámetros necesarios
		ConfigurarPartidaConsola leerDatosManual = new ConfigurarPartidaConsola();
		LeerConsola leerConsola = new LeerConsola();
		
		// Objeto configuración para guardar los datos
		
		String nombreFichero = leerDatosManual.elegirNombreConfiguracion();
		ArrayList<JugadorAbstract> jugadores = leerDatosManual.elegirJugadores();
		BarajarStrategy estrategia = leerDatosManual.elegirEstrategiaBaraja();
		Ensemble ensemble = leerDatosManual.elegirEnsemble();
		int numeroPartidas = leerDatosManual.elegirNumeroPartidas();
		boolean verTraza = leerDatosManual.elegirVerTraza(jugadores);
		
		
		Configuracion configuracion = 
				new Configuracion(nombreFichero, jugadores, estrategia, ensemble, numeroPartidas, verTraza);
		
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
			BarajarStrategy estrategia, Ensemble ensemble, int numeroPartidas, boolean verTraza) {

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
		LeerConsola leerConsola = new LeerConsola();
		return leerConsola.leerValorRango(0, configuraciones.size() - 1);

	}

}
