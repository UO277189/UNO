package main.java.logica.ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.java.logica.Configuracion;
import main.java.logica.GestionarJuegos;
import main.java.logica.juego.Juego;

/**
 * Clase para manejarse con los ficheros TXT de la aplicación
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class TXTParser extends Parser{

	

	public void escribirDatos(String nombreFichero, GestionarJuegos partidasJugadas, Configuracion configuracion) {

		FileWriter fileWriter = null;
		BufferedWriter writer = null;

		// Parametros para ir iterando por el numero de ficheros
		int iFichero = 0;
		int limite = 100;
		int partidasSize = partidasJugadas.getJuegos().size();

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
			// Como captura la excepción en caso que haya algo mal lo dejo así pero esto
			// creo que se podría mejorar
			// Además esto es lo último que hace el programa entones no afecta a otras
			// partes

			// writer.close();
			// fileWriter.close();

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

}