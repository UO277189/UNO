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

	// ATRIBUTOS
	
	private BufferedWriter writer = null;

	public void escribirDatos(String rutaFichero, GestionarJuegos partidasJugadas, Configuracion configuracion) {
	    // Parametros para ir iterando por el numero de ficheros
	    int limite = 100;
		int iFichero = 0;
	    int partidasSize = partidasJugadas.getJuegos().size();


	    try {
	        // Primero se borran los archivos dentro del directorio
	        File directorio = new File(rutaFichero);
	        if (directorio.exists()) {
	            deleteDirectorio(directorio);
	        } else {
		        directorio.mkdir();	
	        }
	        
	        // Luego hay que ir iterando
	        
	        for (int i = 0; i < partidasSize; i++) {
	            // Cada X partidas cambiamos el archivo txt
	            if (i % limite == 0) {
	                if (i > 0) {
	                	// Se cierran los datos del writer actual
	                    writer.flush(); 
	                    writer.close(); 
	                }
	                
	                iFichero++;

	                // Indicamos la ruta en la que cargar el fichero
	                File logPartidas = new File(rutaFichero + iFichero + "-logPartidas.txt");

	                // Se crea el fichero
	                logPartidas.createNewFile();

	                // Creamos los objetos writer y fileWriter para el nuevo archivo
	                FileWriter fileWriter = new FileWriter(logPartidas);
	                writer = new BufferedWriter(fileWriter);

	                writer.write("********** LOG DE PARTIDAS DEL UNO **********");
	                writer.newLine();
	                writer.newLine();
	            }

	            // Guardamos en un txt los resultados de las partidas
	            writeDataToTxT(partidasJugadas.getJuegos().get(i), writer, i);
	        }
	    } catch (IOException e) {
	        System.out.println("Ha ocurrido un error al pasar los datos a un txt.");
	    } finally {
	    	
	        try {
				if (writer != null) {
					writer.flush(); // Se cierra el último writer
					writer.close();
				}
				
				// Mensaje de salida confirmando que ha salido bien
		        System.out.println("Se han guardado correctamente los datos en el fichero txt.");
	        } catch (IOException e) {
	            System.out.println("Ha ocurrido un error al guardar los datos en un TXT.");
	        }
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