package main.manejoDatos.manejoFicheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.algoritmoVoraz.reglas.Regla;
import main.juego.GestionarJuegos;
import main.juego.jugador.JugadorAutomatico;
import main.juego.jugador.JugadorManual;


/**
 * Clase para manejarse con los ficheros CSV de la aplicación
 * @author Efrén García Valencia UO277189
 *
 */

public class ManejoFicherosCSV {

	

	// Atributos

	// RUTAS
	String rutaMetricas = ".\\ficheros\\\\salidas\\"; // Uso una ruta relativa

	// SEPARADOR
	String separador = ";"; // El separador a aplicar para diferenciar las columnas

	/**
	 * Método para extraer los datos de las partidas a un CVS
	 * @param nombreFichero El nombre del fichero donde se va a guardar
	 * @param partidasJugadas Las partidas jugadas totales
	 */
	public void escribirCSV(String nombreFichero, GestionarJuegos partidasJugadas) {

		FileWriter fileWriter = null;
		BufferedWriter writer = null;

		try {
			// Indicamos la ruta en la que cargar el fichero
			File csvMetricas = new File(rutaMetricas + nombreFichero + ".csv"); // Se indica una ruta relativa

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
			System.out.println("Ha ocurrido un error al pasar los datos a csv. Compruebe que no tenga el archivo abierto");
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
	private void writeDataToCSV(GestionarJuegos partidasJugadas, BufferedWriter writer) throws IOException {

		// Primero el encabezado central con los jugadores de las partidas
		writer.write("JUGADORES" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			writer.write(partidasJugadas.getJugadores().get(i).getNombreJugador() + this.separador);
		}
		writer.newLine();
		
		// Luego se indican las reglas de cada jugador
		writer.write("REGLAS" + this.separador);
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			if (partidasJugadas.getJugadores().get(i) instanceof JugadorManual) {
				writer.write("No aplica reglas" + this.separador);
			} else {
				String reglasStr = "";
				for (Regla regla : ((JugadorAutomatico)partidasJugadas.getJugadores().get(i)).getReglas()) {
					reglasStr += regla.toString();
				}
				writer.write(reglasStr + this.separador);
			}
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
	 * Devuelve el separador usado en los archivos CSV
	 * 
	 * @return String
	 */
	public String getSeparador() {
		return this.separador;
	}
	

}
