package main.multiplesPartidas;

import java.util.ArrayList;

import algoritmoVoraz.reglas.ReglasCompuestas;
import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.colores.ReglaNoPriorizarContarColores;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.colores.ReglaPriorizarContarColores;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.numerosAcciones.ReglaNoPriorizarContarNumerosAcciones;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.numerosAcciones.ReglaPriorizarContarNumerosAcciones;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaAzar;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaNoPriorizarMasCuatro;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaNoPriorizarMasDos;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaPriorizarMasCuatro;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaPriorizarMasDos;
import auxiliar.LeerConsola;
import auxiliar.ManejoFicheros;
import juego.baraja.BarajarStrategy;
import juego.baraja.CartaACarta;
import juego.baraja.MontonAMonton;
import juego.jugador.JugadorAbstract;
import juego.jugador.JugadorAlgoritmo;
import juego.jugador.JugadorManual;

/**
 * Clase que maneja la l�gica necesaria para interpretar los datos del fichero csv
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class LeerDatosFichero {
	
	// Clase que maneja la l�gica de ficheros
	private ManejoFicheros manejoFicheros;
	
	// Array con todos los strings a interpretar
	private ArrayList<String> configuracion;
	
	// Para leer por consola
	private LeerConsola leerConsola;
	
	/**
	 * Constructor por defecto
	 */
	public LeerDatosFichero() {
		this.manejoFicheros = new ManejoFicheros();
		configuracion = manejoFicheros.leerCSV();
		leerConsola = new LeerConsola();
	}

	/**
	 * M�todo para mostrar los datos del fichero de configuraci�n por pantalla
	 */
	public void mostrarDatosFichero() {
		System.out.println("A continuaci�n se muestran todas las configuraciones cargadas\n");

		for (int i = 0; i < configuracion.size(); i++) {
			// Primero separamos los valores
			String valores[] = configuracion.get(i).split(manejoFicheros.getSeparador());

			// Hay que tener en cuenta que tienes configuraciones para jugadores manuales y
			// autom�ticos
			if (valores[0].equals("AUTOMATICO")) {
				mostrarDatosFicheroAutomatico(i, valores);
			} else {
				mostrarDatosFicheroManual(i, valores);
			}
		}

		System.out.println("Por favor, seleccione una configuraci�n a cargar");
	}

	/**
	 * Muestra los datos del fichero con jugadores manuales
	 * 
	 * @param iteracion La iteracion en la que estamos
	 * @param valores   Los valores del fichero
	 */
	public void mostrarDatosFicheroManual(int iteracion, String[] valores) {
		String jugadoresPantalla = "";
		for (int j = 0; j < 4; j++) {
			jugadoresPantalla = jugadoresPantalla.concat("\tNombre del jugador " + j / 2 + ": " + valores[j] + "\n");
		}

		if (valores.length == 8) { // CartaACarta
			System.out.println(iteracion + "- " + jugadoresPantalla + "\tEstrategia que usa: " + valores[5] + ","
					+ valores[6] + "\n\tN�mero de partidas: " + valores[7]);
		} else { // Mont�nAMont�n
			System.out.println(iteracion + "- " + jugadoresPantalla + "\tEstrategia que usa: " + valores[5] + ","
					+ valores[6] + "," + valores[7] + "\n\tN�mero de partidas: " + valores[8]);
		}
		System.out.println(""); // Espacio
	}

	/**
	 * Muestra los datos del fichero de forma autom�tica
	 * 
	 * @param iteracion La iteracion en la que estamos
	 * @param valores   Los valores del fichero
	 */
	public void mostrarDatosFicheroAutomatico(int iteracion, String[] valores) {
		// Los 8 primeros valores indican los jugadores y sus configuraciones
		String jugadoresPantalla = "";
		for (int j = 1; j < 9; j++) {
			if (j % 2 != 0) { // Nombre del jugador
				jugadoresPantalla = jugadoresPantalla
						.concat("\tNombre del jugador " + j / 2 + ": " + valores[j] + " - ");
			} else {
				jugadoresPantalla = jugadoresPantalla.concat("Regla que implementa: " + valores[j] + "\n");
			}
		}

		if (valores.length == 12) { // CartaACarta
			System.out.println(iteracion + "- " + jugadoresPantalla + "\tEstrategia que usa: " + valores[9] + ","
					+ valores[10] + "\n\tN�mero de partidas: " + valores[11]);
		} else { // Mont�nAMont�n
			System.out.println(iteracion + "- " + jugadoresPantalla + "\tEstrategia que usa: " + valores[9] + ","
					+ valores[10] + "," + valores[11] + "\n\tN�mero de partidas: " + valores[12]);
		}
		System.out.println(""); // Espacio
	}
	
	
	/**
	 * Para elegir la opci�n a cargar del fichero de configuraci�n
	 * @return String[] La configuraci�n a cargar
	 */
	public String[] elegirOpcion() {
		int valor = leerConsola.leerValorRango(0, configuracion.size() - 1);
		return configuracion.get(valor).split(manejoFicheros.getSeparador());

	}

	/**
	 * M�todo que devuelve la estrategia que se va a aplicar
	 * 
	 * @param string
	 * @return BarajarStrategy
	 */
	public BarajarStrategy elegirEstrategiaParametros(String[] valores) {

		if (valores[0].equals("AUTOMATICO")) {
			if (valores.length == 12) { // Carta a carta
				return new CartaACarta(Integer.valueOf(valores[10]));
			} else { // Mont�n a mont�n
				return new MontonAMonton(Integer.valueOf(valores[10]), Integer.valueOf(valores[11]));
			}
		} else {
			if (valores.length == 8) { // Carta a carta
				return new CartaACarta(Integer.valueOf(valores[6]));
			} else { // Mont�n a mont�n
				return new MontonAMonton(Integer.valueOf(valores[6]), Integer.valueOf(valores[7]));
			}
		}
	}

	/**
	 * Devuelve el n�mero de partidas a cargar
	 * @param valores String []
	 * @return int El n�mero de partidas
	 */
	public int elegirNumeroPartidas(String[] valores) {
		 return Integer.valueOf(valores[valores.length - 1]); // �ltima posici�n
	}

	/**
	 * M�todo que dice si se elige la traza o no para ver
	 * 
	 * @param valores
	 * @return boolean
	 */
	public boolean elegirTrazaParametros(String[] valores) {
		if (valores[0].contains("AUTOMATICO")) {
			return false;
		} else {
			return true;
		}
	}
	

	/**
	 * M�todo para pasar los jugadores de la partida desde el fichero de
	 * configuraci�n
	 * 
	 * @param string
	 * @return ArrayList<JugadorAbstract>
	 */
	public ArrayList<JugadorAbstract> elegirJugadoresParametros(String[] valores) {

		ArrayList<JugadorAbstract> jugadores = new ArrayList<JugadorAbstract>();

		// Opci�n 1: jugador autom�tico
		if (valores[0].equals("AUTOMATICO")) {
			// A�adimos los jugadores
			jugadores.add(new JugadorAlgoritmo(valores[1], evaluarStringRegla(valores[2])));
			jugadores.add(new JugadorAlgoritmo(valores[3], evaluarStringRegla(valores[4])));
			jugadores.add(new JugadorAlgoritmo(valores[5], evaluarStringRegla(valores[6])));
			jugadores.add(new JugadorAlgoritmo(valores[7], evaluarStringRegla(valores[8])));
		} else { // Opci�n 2: jugador manual
			jugadores.add(new JugadorManual(valores[1]));
			jugadores.add(new JugadorManual(valores[2]));
			jugadores.add(new JugadorManual(valores[3]));
			jugadores.add(new JugadorManual(valores[4]));
		}
		return jugadores;

	}

	/**
	 * M�todo para leer un string y pasarlo a un objeto ReglasCompuestas
	 * 
	 * @param string El valor String
	 * @return ReglasCompuestas Las reglas a aplicar
	 */
	private ReglasCompuestas evaluarStringRegla(String value) {
		
		ReglasCompuestas reglasCompuestas = null;
		
		// Primero hay que estudiar si hay varias reglas
		if (value.contains(",")) {
			// En este caso hacemos un split y evaluamos cada regla por separado
			String [] reglas = value.split(",");
			ArrayList<Regla> reglasArray = new ArrayList<Regla>();
			for (String regla : reglas) {
				reglasArray.add(sacarRegla(regla));
			}
			reglasCompuestas = new ReglasCompuestas(reglasArray);
		} else {
			// Si es una sola regla
			reglasCompuestas = new ReglasCompuestas(sacarRegla(value));
		}
		
		return reglasCompuestas;
		
	}

	/**
	 * M�todo que saca la regla del string que le pases
	 * @param reglaString El string a evaluar
	 * @return Regla
	 */
	private Regla sacarRegla(String reglaString) {
		Regla regla = null;

		if (reglaString.contains("ReglaAzar"))
			regla = new ReglaAzar();

		if (reglaString.contains("ReglaPriorizarMasCuatro"))
			regla = new ReglaPriorizarMasCuatro();

		if (reglaString.contains("ReglaNoPriorizarMasCuatro"))
			regla = new ReglaNoPriorizarMasCuatro();

		if (reglaString.contains("ReglaPriorizarMasDos"))
			regla = new ReglaPriorizarMasDos();

		if (reglaString.contains("ReglaNoPriorizarMasDos"))
			regla = new ReglaNoPriorizarMasDos();

		if (reglaString.contains("ReglaPriorizarContarColores"))
			regla = new ReglaPriorizarContarColores();

		if (reglaString.contains("ReglaNoPriorizarContarColores"))
			regla = new ReglaNoPriorizarContarColores();

		if (reglaString.contains("ReglaPriorizarContarNumerosAcciones"))
			regla = new ReglaPriorizarContarNumerosAcciones();

		if (reglaString.contains("ReglaNoPriorizarContarNumerosAcciones"))
			regla = new ReglaNoPriorizarContarNumerosAcciones();

		return regla;
	}

}
