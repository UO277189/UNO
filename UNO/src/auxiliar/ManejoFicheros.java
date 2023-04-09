package auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import juego.ColeccionJuegos;
import juego.Juego;

/**
 * Clase para manejarse con los ficheros de entrada y salida de la aplicación
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class ManejoFicheros {

	// Atributos

	// RUTAS
	String rutaMetricas = ".\\ficheros\\csvMetricas.csv"; // Uso una ruta relativa
	String rutaConfig = ".\\ficheros\\csvConfig.csv";
	String rutaPartidas = ".\\ficheros\\log";

	// SEPARADOR
	String separador = ";"; // El separador a aplicar para diferenciar las columnas

	/**
	 * Método para extraer los datos de las partidas a un CVS
	 * 
	 * @param partidasJugadas Las partidas jugadas totales
	 */
	public void escribirCSV(ColeccionJuegos partidasJugadas) {

		FileWriter fileWriter = null;
		BufferedWriter writer = null;

		try {
			// Indicamos la ruta en la que cargar el fichero
			File csvMetricas = new File(rutaMetricas); // Se indica una ruta relativa

			// Para evitar errores
			if (!csvMetricas.exists()) {
				csvMetricas.createNewFile();
			}

			// Se crean los objetos Writer
			fileWriter = new FileWriter(csvMetricas);
			writer = new BufferedWriter(fileWriter);

			// Indicamos los campos a tener
			writeDataToCSV(partidasJugadas, writer);

		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al pasar los datos a un csv");
		} finally {
			try {
				// Se cierran los ficheros en orden

				if (writer != null) {
					writer.flush(); // Para asegurarse de que escribe los datos en el csv
					writer.close();
				}

				if (fileWriter != null) {
					fileWriter.close();
				}

			} catch (IOException e) {
				System.out.println("Ha ocurrido un error al cerrar el fichero csv. Compruebe que no lo tenga abierto");
			}
		}
	}

	/**
	 * Método con los datos a indicar en el CSV
	 * 
	 * @param partidasJugadas Las partidas jugadas
	 * @param writer          El BufferedWriter
	 * @throws IOException La excepción que podemos ir propagando porque se recoge
	 *                     más arriba
	 */
	private void writeDataToCSV(ColeccionJuegos partidasJugadas, BufferedWriter writer) throws IOException {

		// Primero el encabezado central con los jugadores de las partidas
		writer.write("JUGADORES" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getNombreJugador() + this.separador);
		}
		writer.newLine();

		// Resto de valores a incluir

		writer.write("Cartas jugadas" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getCartasJugadas() + this.separador);
		}
		writer.newLine();

		writer.write("Cartas robadas" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getCartasRobadas() + this.separador);
		}
		writer.newLine();

		writer.write("Cartas +4 jugadas" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getCartasMasCuatroJugadas() + this.separador);
		}
		writer.newLine();

		writer.write("Cartas +2 jugadas" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getCartasMasDosJugadas() + this.separador);
		}
		writer.newLine();

		writer.write("Cartas Cambio de Sentido jugadas" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getCartasCambiarSentidoJugadas() + this.separador);
		}
		writer.newLine();

		writer.write("Cartas Quitar Turno jugadas" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getCartasQuitarTurnoJugadas() + this.separador);
		}
		writer.newLine();

		writer.write("Veces que ha cantado UNO" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getVecesQueHaCantadoUno() + this.separador);
		}
		writer.newLine();

		writer.write("Veces que ha ganado" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getVecesQueHaGanado() + this.separador);
		}
		writer.newLine();

		writer.newLine(); // Otro más para diferenciar de lo anterior

		// Indicamos el ganador (o ganadores) de la partida
		if (partidasJugadas.ganadoresDeTodasLasPartidas().size() > 1) {
			writer.write("Ganadores de todas las partidas" + this.separador);
			for (int i = 0; i < partidasJugadas.ganadoresDeTodasLasPartidas().size(); i++) {
				writer.write(partidasJugadas.ganadoresDeTodasLasPartidas().get(i).getNombreJugador() + this.separador);
			}
		} else {
			writer.write("Ganador de todas las partidas" + this.separador);
			writer.write(partidasJugadas.ganadoresDeTodasLasPartidas().get(0).getNombreJugador() + this.separador);
		}
	}

	/**
	 * Método para leer las configuraciones de un fichero csv
	 * 
	 */
	public ArrayList<String> leerCSV() {

		FileReader fileReader = null;
		BufferedReader reader = null;

		try {
			// Indicamos la ruta en la que cargar el fichero
			File csvConfig = new File(rutaConfig); // Se indica una ruta relativa

			// Para evitar errores
			if (!csvConfig.exists()) {
				csvConfig.createNewFile();
			}

			// Se crean los objetos Reader
			fileReader = new FileReader(csvConfig);
			reader = new BufferedReader(fileReader);

			// Indicamos los campos a tener
			return readDataInCSV(reader);

		} catch (IOException e) {
			System.out.println("Ha ocurrido un error leer los datos de un csv");
		} finally {
			try {
				// Se cierran los ficheros en orden

				if (reader != null) {
					reader.close();
				}

				if (fileReader != null) {
					fileReader.close();
				}

			} catch (IOException e) {
				System.out.println("Ha ocurrido un error al cerrar el fichero csv. Compruebe que no lo tenga abierto");
			}
		}
		return null; // Si surge algún problema
	}

	/*
	 * Método que devuelve en un array de strings todas las configuraciones cargadas
	 */

	/**
	 * Método que devuelve en un array de strings todas las configuraciones cargadas
	 * 
	 * @param reader El BufferedReader
	 * @return ArrayList <String>
	 * @throws IOException Esta excepción se recoge más arriba
	 */
	private ArrayList<String> readDataInCSV(BufferedReader reader) throws IOException {

		// Parámetros
		ArrayList<String> configuraciones = new ArrayList<String>();
		String linea;

		while ((linea = reader.readLine()) != null) {
			configuraciones.add(linea); // Basta con que lo pase en una linea, posteriormente ya se trabaja con el
										// string
		}
		return configuraciones;
	}

	/**
	 * Método para guardar en un TXT los resultados de las partidas
	 * 
	 * @param partidasJugadas Las partidas jugadas totales
	 */
	public void escribirTxT(ColeccionJuegos partidasJugadas) {
		
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		
		// Parametros para ir iterando por el numero de ficheros
		int iFichero = 0;
		int limite = 100;
		int partidasSize =  partidasJugadas.getJuegos().size();
		
		try {

			// Primero se borra el directorio si existiera y se crea de nuevo
			File directorio = new File(rutaPartidas);
			if (directorio.exists()) {
				deleteDirectorio(directorio);
			}
			directorio.mkdirs(); // Lo crea otra vez

			// Luego hay que ir iterando 
			
			for (int i = 0; i < partidasSize; i++) {	
				// Cada X partidas cambiamos el archivo txt
				if (i % limite == 0) {
					
					if (i > 0) {
						writer.flush(); // Se guardan los datos de los txt
					}
					iFichero++;
					
					// Indicamos la ruta en la que cargar el fichero
					File logPartidas = new File(rutaPartidas + "\\" + iFichero + "-logPartidas.txt");

					// Se crea el fichero
					logPartidas.createNewFile();

					fileWriter = new FileWriter(logPartidas);
					writer = new BufferedWriter(fileWriter);

					writer.write("********** LOG DE PARTIDAS DEL UNO **********");
					writer.newLine();
					writer.newLine();	
				}
				
				// Guardamos en un txt los resultados de las partidas
				writeDataToTxT(partidasJugadas.getJuegos().get(i), writer, i);
			}
			writer.flush(); // Se guarda la información del último txt
			
			// Por alguna razón si intento hacer un close java pierde el último txt que hay
			// He probado muchos enfoques distintos pero no consigo saber qué le pasa
			// Como captura la excepción en caso que haya algo mal lo dejo así pero esto creo que se podría mejorar
			// Además esto es lo último que hace el programa entones no afecta a otras partes
			
			//writer.close();
			//fileWriter.close();
			
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al pasar los datos a un txt");
		} 
	}

	/**
	 * Método que escribe en un txt los resultados de las partidas
	 * 
	 * @param partidasJugadas Las partidas jugadas
	 * @param writer          BufferedWriter
	 * @param numeroPartida   El número de la partida
	 * @throws IOException Excepción que se recoge más arriba
	 */
	private void writeDataToTxT(Juego juego, BufferedWriter writer, int numeroPartida) throws IOException {
		writer.write("---- Partida numero: " + (numeroPartida + 1) + "\n\n");
		for (String str : juego.getlogPartidas()) {
			writer.write(str);
		}
		writer.newLine();
		writer.newLine();
	}

	/**
	 * Para borrar un directorio
	 * 
	 * @param borrar El directorio a borrar
	 */
	void deleteDirectorio(File directorio) {
		// No deja borrar un directorio directamente, tienes que ir fichero a fichero
		try {
			
			if (directorio.isDirectory()) {
				for (File file : directorio.listFiles()) {
					if (file.isDirectory()) {
						deleteDirectorio(file);
						file.delete();
						file.deleteOnExit();
					} else {
						if (file.isFile()) {
							file.delete();
							file.deleteOnExit();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error borrando los ficheros");
		} finally {
			directorio.delete();
			directorio.deleteOnExit();
		}
	}

	/**
	 * Devuelve el separador usado en los archivos CSV
	 * 
	 * @return String
	 */
	public String getSeparador() {
		return this.separador;
	}

}
