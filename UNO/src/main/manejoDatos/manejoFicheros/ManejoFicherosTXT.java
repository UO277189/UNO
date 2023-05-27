package main.manejoDatos.manejoFicheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.juego.GestionarJuegos;
import main.juego.Juego;

/**
 * Clase para manejarse con los ficheros TXT de la aplicaci�n
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class ManejoFicherosTXT {

	// Atributos
		String rutaPartidas = ".\\ficheros\\salidas\\log";


		/**
		 * M�todo para guardar en un TXT los resultados de las partidas
		 * 
		 * @param partidasJugadas Las partidas jugadas totales
		 */
		public void escribirTxT(GestionarJuegos partidasJugadas) {
			
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
				writer.flush(); // Se guarda la informaci�n del �ltimo txt
				
				// Por alguna raz�n si intento hacer un close java pierde el �ltimo txt que hay
				// He probado muchos enfoques distintos pero no consigo saber qu� le pasa
				// Como captura la excepci�n en caso que haya algo mal lo dejo as� pero esto creo que se podr�a mejorar
				// Adem�s esto es lo �ltimo que hace el programa entones no afecta a otras partes
				
				//writer.close();
				//fileWriter.close();
				
			} catch (IOException e) {
				System.out.println("Ha ocurrido un error al pasar los datos a un txt");
			} 
		}

		/**
		 * M�todo que escribe en un txt los resultados de las partidas
		 * 
		 * @param partidasJugadas Las partidas jugadas
		 * @param writer          BufferedWriter
		 * @param numeroPartida   El n�mero de la partida
		 * @throws IOException Excepci�n que se recoge m�s arriba
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
}
