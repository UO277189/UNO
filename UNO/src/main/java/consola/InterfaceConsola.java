package main.java.consola;

import java.io.File;
import java.util.ArrayList;

import main.java.algoritmoVoraz.ensembles.Ensemble;
import main.java.logica.Configuracion;
import main.java.logica.GestionarJuegos;
import main.java.logica.ficheros.CSVParser;
import main.java.logica.ficheros.JSONParser;
import main.java.logica.ficheros.TXTParser;
import main.java.logica.juego.baraja.FormaBarajar;
import main.java.logica.juego.jugador.Jugador;
import main.java.logica.juego.jugador.JugadorAutomatico;
import main.java.logica.juego.jugador.JugadorManual;

/**
 * Clase que gestiona la interfaz por consola del juego del UNO
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class InterfaceConsola {

	// ATRUBUTOS
	private static JSONParser manejoJSON;
	private static LeerConsola leerConsola;

	/**
	 * Constructor para la interfaz por consola
	 */
	public InterfaceConsola() {
		// Se inicializa aquí para evitar fallos en los tests
		leerConsola = new LeerConsola();
		manejoJSON = new JSONParser();
	}

	/**
	 * Método para jugar una partida
	 */
	public void jugarPartida() {
		// Mensaje de bienvenida
		int opcion = mensajeBienvenida();

//		try {

			if (opcion == 1) {
				introducirDatosManuales();
			} else if (opcion == 2) {
				elegirConfiguracionesEjemplo();
			} else if (opcion == 3) {
				cargarDatosDeFichero();
			} else if (opcion == 4) {
				mostrarAyuda();
			} else if (opcion == 5) {
				borrarContenidoArchivosSalida();
			}
//		} catch (Exception e) {
//			System.out.println("Ha ocurrido un error en el sistema, la aplicación se cerrará.");
//		}

	}

	/**
	 * Método para dar un mensaje de bienvenida
	 * 
	 * @return value La opción para jugar la partida
	 */
	private int mensajeBienvenida() {

		logoAplicacion();

		System.out.println("");
		System.out
				.println("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:");
		System.out.println("");
		System.out.println("1.	Crear una configuración personalizada");
		System.out.println("2.	Cargar configuraciones de ejemplo");
		System.out.println("3.	Cargar configuraciones del fichero de entrada del usuario");
		System.out.println("4.	Mostrar ayuda");
		System.out.println("5.	Salir");
		System.out.println("");
		System.out.print("Seleccione la opción deseada: ");

		int valor = getLeerConsola().leerValorRango(1, 5);

		return valor;
	}

	/**
	 * Muestra el logo de la aplicacion
	 */
	private void logoAplicacion() {

		System.out.println(" -----------                                 JAVA    -----------  ");
		System.out.println("|           |                                       |           | ");
		System.out.println("|   ---     |   1       1   1       1   1 1 1 1 1   |     ---   | ");
		System.out.println("|  |   |    |  	1       1   1 1     1   1       1   |    |   |  | ");
		System.out.println("|  |  -|-   |   1       1   1   1   1   1       1   |   -|-  |  | ");
		System.out.println("|   -|-  |  |   1       1   1     1 1   1       1   |  |  -|-   | ");
		System.out.println("|    |   |  |   1       1   1       1   1       1   |  |   |    | ");
		System.out.println("|     ---   |   1       1   1       1   1       1   |   ---     | ");
		System.out.println("|           |   1 1 1 1 1   1       1   1 1 1 1 1   |           | ");
		System.out.println(" ----------                                          -----------  ");
	}

	/**
	 * Método para seleccionar configuraciones básicas del juego
	 */
	private void elegirConfiguracionesEjemplo() {

		System.out.println();
		System.out.println("A continuación se muestran algunas configuraciones básicas del juego.");
		System.out.println();
		System.out.println("1.	Partida de dos jugadores manuales");
		System.out.println("2.	Partida de cuatro jugadores manuales");
		System.out.println("3.	Partida automática, cada jugador implementa una única regla");
		System.out.println("4.	Partida automática, cada jugador implementa múltiples reglas");
		System.out.println("5.	Partida mixta con un jugador manual y tres jugadores automáticos");
		System.out.println("6.	Mostrar detalles de las partidas");
		System.out.println("7.	Volver atrás");
		System.out.println();
		System.out.print("Seleccione la opción deseada: ");

		ArrayList<Configuracion> configuraciones = manejoJSON
				.leerJSON("./ficheros/ejemplos/" + manejoJSON.getFicheroEjemplos());
		int valor = getLeerConsola().leerValorRango(1, 7);

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
	private void mostrarDetallesPartidasBasicas(ArrayList<Configuracion> configuraciones) {
		JSONParser manejoJSON = new JSONParser();
		manejoJSON.mostrarDatosFichero(configuraciones);
		volverAtras();
		elegirConfiguracionesEjemplo();

	}

	/**
	 * Muestra los detalles de una configuración por consola
	 * 
	 * @param configuracion La configuración a crear
	 */
	private void mostrarDetallesPartidaBasicaConfiguracion(Configuracion configuracion) {

		System.out.println("\tNombre de la configuración: " + configuracion.getNombreConfiguracion());
		System.out.println("\tJugadores:");
		for (Jugador jugador : configuracion.getJugadoresPartida()) {
			if (jugador instanceof JugadorManual) {
				System.out.println("\t   MANUAL \n\t       Nombre: " + jugador.getNombreJugador());
			} else {
				System.out.print("\t   AUTOMATICO \n\t       Nombre: " + jugador.getNombreJugador()
						+ "\n\t       Reglas que aplica: ");

				for (int j = 0; j < ((JugadorAutomatico)jugador).getReglas().size(); j++) {
					if (j == ((JugadorAutomatico)jugador).getReglas().size() - 1) {
						System.out.print(((JugadorAutomatico)jugador).getReglas().get(j).toString() + " ");
					} else {
						System.out.print(((JugadorAutomatico)jugador).getReglas().get(j).toString() + ",");
					}
				}
				System.out.println();
			}
		}
		System.out.println("\tForma de barajar las cartas: " + configuracion.getEstrategiaBaraja().toString());
		System.out.println("\tEnsemble a aplicar: " + configuracion.getEnsemble().toString());
		System.out.println("\tNúmero de partidas: " + configuracion.getNumeroPartidas());
		System.out.println("\tSe muestra la traza de las partidas: " + configuracion.isTraza());

		// Salto de línea
		System.out.println();
	}

	/**
	 * Método para volver atrás a la hora de escoger una opción
	 */
	public void volverAtras() {
		System.out.println();
		System.out.print("Pulse cualquier tecla para volver atrás: ");
		getLeerConsola().leerCualquierValor();
		System.out.println();
		System.out.println();
	}

	/**
	 * Método para meter los datos de forma manual
	 */
	private void introducirDatosManuales() {

		System.out.println();
		System.out.println("A continuación se solicitarán los datos necesarios para crear una configuración. ");

		// Parámetros necesarios
		ConfiguracionConsola leerDatosManual = new ConfiguracionConsola(getLeerConsola());

		// Objeto configuración para guardar los datos

		String nombreFichero = leerDatosManual.elegirNombreConfiguracion();
		ArrayList<Jugador> jugadores = leerDatosManual.elegirJugadores();
		FormaBarajar estrategia = leerDatosManual.elegirEstrategiaBaraja();
		Ensemble ensemble = leerDatosManual.elegirEnsemble();
		int numeroPartidas = leerDatosManual.elegirNumeroPartidas();
		boolean verTraza = leerDatosManual.elegirVerTraza(jugadores);

		Configuracion configuracion = new Configuracion(nombreFichero, jugadores, estrategia, ensemble, numeroPartidas,
				verTraza);

		System.out.println();
		this.mostrarDetallesPartidaBasicaConfiguracion(configuracion);
		System.out.println();
		System.out.print("¿Desea crear esta configuración? (0 - Si, 1 - No): ");
		int param = getLeerConsola().leerValorRango(0, 1);

		if (param == 1) {
			System.out.println();
			System.out.println();
			jugarPartida();
		} else if (param == 0) {

			System.out.print("¿Desea guardar esta configuración? (0 - Si, 1 - No): ");
			int valor = getLeerConsola().leerValorRango(0, 1);

			if (valor == 0) {
				JSONParser manejoJSON = new JSONParser();
				manejoJSON.escribirDatos("./ficheros/entradas/" + manejoJSON.getFicheroEntrada(), null,
						configuracion);
				System.out.println("La configuración se ha guardado correctamente.");
			}

			ejecutarPartidas(nombreFichero, jugadores, estrategia, ensemble, numeroPartidas, verTraza);
		}
	}

	/**
	 * Método para cargar los datos de un fichero
	 */
	private void cargarDatosDeFichero() {

		System.out.println("A continuación se mostrarán todas las configuraciones cargadas: ");
		System.out.println();

		// Se carga el manejador de objetos JSON
		JSONParser manejoJSON = new JSONParser();

		// Se cargan las configuraciones del JSON
		ArrayList<Configuracion> configuraciones = manejoJSON
				.leerJSON("./ficheros/entradas/" + manejoJSON.getFicheroEntrada());

		if (configuraciones.isEmpty()) {
			System.out.println("NO SE HA PODIDO CARGAR LOS DATOS DEL FICHERO");
		} else {

			// Se muestran los datos de las configuraciones
			manejoJSON.mostrarDatosFichero(configuraciones);

			System.out.println();
			System.out.println("Si desea volver atrás pulse 0.");
			System.out.println();

			// Se selecciona la opción que se considere
			int valor = elegirOpcion(configuraciones);

			if (valor != 0) {

				// Ejecutas las partidas
				valor--;
				ejecutarPartidas(configuraciones.get(valor).getNombreConfiguracion(),
						configuraciones.get(valor).getJugadoresPartida(),
						configuraciones.get(valor).getEstrategiaBaraja(), configuraciones.get(valor).getEnsemble(),
						configuraciones.get(valor).getNumeroPartidas(), configuraciones.get(valor).isTraza());
			} else {
				System.out.println();
				System.out.println();
				System.out.println();
				jugarPartida();
			}
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
	private void ejecutarPartidas(String nombreFichero, ArrayList<Jugador> jugadores, FormaBarajar estrategia,
			Ensemble ensemble, int numeroPartidas, boolean verTraza) {
		
		
		System.out.println();
		System.out.println("Se están ejecutando las partidas... ");
		System.out.println();


		// Aplicamos todos estos parámetros de entrada en nuestro framework que manejará
		// las partidas
		GestionarJuegos juegos = new GestionarJuegos(jugadores, estrategia, ensemble, numeroPartidas);
		// Jugamos las partidas
		juegos.jugarPartidas();
		// Sacamos los estadísticos al final
		juegos.mostrarResultados();

		CSVParser manejoFicherosCSV = new CSVParser();
		TXTParser manejoFicherosTXT = new TXTParser();

		// Se guardan en el CSV
		manejoFicherosCSV.escribirDatos("./ficheros/salidas/" + nombreFichero, juegos, null);
		// Se guarda el log en el txt
		if (verTraza) {
			if (numeroPartidas > 1500) {
				System.out.print(
						"Está a punto de generar un gran número de archivos TXT. ¿Seguro que desea seguir con la operación? (0 - Sí, 1 - No): ");
				int valor = leerConsola.leerValorRango(0, 1);
				if (valor == 0) {
					manejoFicherosTXT.escribirDatos("./ficheros/salidas/log/" + nombreFichero + "/", juegos, null);
				} else {
					System.out.println("No se han guardado los ficheros TXT en el sistema.");
				}
			} else {
				manejoFicherosTXT.escribirDatos("./ficheros/salidas/log/" + nombreFichero + "/", juegos, null);
			}
		}

		System.out.println();
		System.out.println();
		System.out.print("¿Desea volver al inicio de la aplicación? (0 - Si, 1 - No): ");
		int valor = leerConsola.leerValorRango(0, 1);
		if (valor == 0) {		
			System.out.println();
			System.out.println();
			System.out.println();
			this.jugarPartida(); // Se vuelve a empezar
		} else if (valor == 1) {
			borrarContenidoArchivosSalida();
		}
	}

	/**
	 * Borra el contenido de los archivos de salida
	 */
	private void borrarContenidoArchivosSalida() {
		
		System.out.println("Al salir de la aplicación se borrarán los archivos de salida generados.");
		System.out.println("Si desea guardar algún archivo, haga una copia fuera de la carpeta de salida antes de continuar.");
		System.out.print("¿Seguro que desea salir de la aplicación? (0 - Si, 1 - No): ");
		int value = leerConsola.leerValorRango(0, 1);
		if (value == 0) {
			File salidas = new File("./ficheros/salidas"); 
			manejoJSON.deleteDirectorio(salidas);	
			System.out.println("¡Hasta la próxima!");
		}
		
		if (value == 1) {
			System.out.println();
			this.jugarPartida();
		}
	}

	/**
	 * Para elegir la opción a cargar del fichero de configuración
	 * 
	 * @param configuraciones El array con las configuraciones a cargar
	 * @return int La configuración a cargar
	 */
	public int elegirOpcion(ArrayList<Configuracion> configuraciones) {
		System.out.print("Por favor, seleccione una opción: ");
		return getLeerConsola().leerValorRango(0, configuraciones.size());
	}

	/**
	 * Método que muestra una ayuda de forma textual
	 */
	private void mostrarAyuda() {
		System.out.println();
		System.out.println();
		ManualAyuda manualAyuda = new ManualAyuda(this);
		manualAyuda.invocarMenuAyuda();
	}

	/**
	 * Devuelve el objeto consola creado
	 * 
	 * @return LeerConsola
	 */
	public LeerConsola getLeerConsola() {
		return leerConsola;
	}
}