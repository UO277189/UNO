package main.manejoDatos.manejoFicheros;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import main.algoritmoVoraz.reglas.Regla;
import main.juego.GestionarJuegos;
import main.juego.jugador.JugadorAutomatico;
import main.juego.jugador.JugadorManual;
import main.manejoDatos.EstadisticosJugador;

/**
 * Clase para manejarse con los ficheros CSV de la aplicación
 * @author Efrén García Valencia UO277189
 *
 */

public class ManejoFicherosCSV {

	

	// Atributos

	// RUTAS
	private String rutaMetricas = ".\\ficheros\\\\salidas\\"; // Uso una ruta relativa
	private char SEPARADOR = ';';


	/**
	 * Método para extraer los datos de las partidas a un CVS
	 * @param nombreFichero El nombre del fichero donde se va a guardar
	 * @param partidasJugadas Las partidas jugadas totales
	 */
	public void escribirCSV(String nombreFichero, GestionarJuegos partidasJugadas) {

		FileWriter fileWriter = null;
		CSVWriter writer = null;

		try {
			// Indicamos la ruta en la que cargar el fichero
			File csvMetricas = new File(rutaMetricas + nombreFichero + ".csv"); // Se indica una ruta relativa

			// Para evitar errores
			if (!csvMetricas.exists()) {
				csvMetricas.createNewFile();
			}
			

			// Se crean los objetos Writer
			fileWriter = new FileWriter(csvMetricas);
			writer = new CSVWriter(fileWriter, SEPARADOR,'"', '"', "\n");

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
	private void writeDataToCSV(GestionarJuegos partidasJugadas, CSVWriter writer) throws IOException {

		// ENCABEZADOS
		List<String> encabezados = new ArrayList<>();
		encabezados.add("JUGADORES");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			encabezados.add(partidasJugadas.getJugadores().get(i).getNombreJugador());
		}
		String [] lineaEncabezados = guardarEnArray(encabezados);
		writer.writeNext(lineaEncabezados);
		
		
		// REGLAS
		List<String> reglas = new ArrayList<>();
		reglas.add("REGLAS");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			if (partidasJugadas.getJugadores().get(i) instanceof JugadorManual) {
				reglas.add("NO APLICA");
			} else {
				String reglasStr = "";
				for (Regla regla : ((JugadorAutomatico)partidasJugadas.getJugadores().get(i)).getReglas()) {
					reglasStr += regla.toString();
				}
				reglas.add(reglasStr);
			}
		}
		String [] lineaReglas = guardarEnArray(reglas);
		writer.writeNext(lineaReglas);
		
		
		// CARTAS JUGADAS
		List<String> cartasJugadas = new ArrayList<>();
		cartasJugadas.add("CARTAS JUGADAS");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			cartasJugadas.add(Integer.toString(partidasJugadas.getJugadores().get(i).getCartasJugadas()));
		}
		String [] lineaCartasJugadas = guardarEnArray(cartasJugadas);
		writer.writeNext(lineaCartasJugadas);
		
		
		// CARTAS JUGADAS
		List<String> cartasRobadas = new ArrayList<>();
		cartasRobadas.add("CARTAS ROBADAS");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			cartasRobadas.add(Integer.toString(partidasJugadas.getJugadores().get(i).getCartasRobadas()));
		}
		String [] lineaCartasRobadas = guardarEnArray(cartasRobadas);
		writer.writeNext(lineaCartasRobadas);
		
		
		
		// CARTAS +4 JUGADAS
		List<String> cartasMasCuatro = new ArrayList<>();
		cartasMasCuatro.add("CARTAS +4");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			cartasMasCuatro.add(Integer.toString(partidasJugadas.getJugadores().get(i).getCartasMasCuatroJugadas()));
		}
		String [] lineaCartasMasCuatro = guardarEnArray(cartasMasCuatro);
		writer.writeNext(lineaCartasMasCuatro);
		
		
		// CARTAS +2 JUGADAS
		List<String> cartasMasDos = new ArrayList<>();
		cartasMasDos.add("CARTAS +2");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			cartasMasDos.add(Integer.toString(partidasJugadas.getJugadores().get(i).getCartasMasDosJugadas()));
		}
		String [] lineaCartasMasDos = guardarEnArray(cartasMasDos);
		writer.writeNext(lineaCartasMasDos);
		
		
		// CARTAS CAMBIO DE SENTIDO JUGADAS
		List<String> cartasCambioSentido = new ArrayList<>();
		cartasCambioSentido.add("CARTAS CAMBIO DE SENTIDO");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			cartasCambioSentido.add(Integer.toString(partidasJugadas.getJugadores().get(i).getCartasCambiarSentidoJugadas()));
		}
		String [] lineaCartasCambioSentido = guardarEnArray(cartasCambioSentido);
		writer.writeNext(lineaCartasCambioSentido);
		
		
		// CARTAS QUITAR TURNO JUGADAS
		List<String> cartasQuitarTurno = new ArrayList<>();
		cartasQuitarTurno.add("CARTAS QUITAR TURNO");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			cartasQuitarTurno.add(Integer.toString(partidasJugadas.getJugadores().get(i).getCartasQuitarTurnoJugadas()));
		}
		String [] lineaCartasQuitarTurno = guardarEnArray(cartasQuitarTurno);
		writer.writeNext(lineaCartasQuitarTurno);
		

		//  VECES QUE HA GANADO UNO
		List<String> cartasCantaUno = new ArrayList<>();
		cartasCantaUno.add("VECES QUE CANTA UNO");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			cartasCantaUno.add(Integer.toString(partidasJugadas.getJugadores().get(i).getVecesQueHaCantadoUno()));
		}
		String [] lineaCartasCantaUno = guardarEnArray(cartasCantaUno);
		writer.writeNext(lineaCartasCantaUno);
		
		//  VECES QUE HA GANADO
		List<String> ganador = new ArrayList<>();
		ganador.add("VECES QUE HA GANADO");
		for (int i = 0; i < partidasJugadas.getJugadores().size(); i++) {
			ganador.add(Integer.toString(partidasJugadas.getJugadores().get(i).getVecesQueHaGanado()));
		}
		String [] lineaGanador = guardarEnArray(ganador);
		writer.writeNext(lineaGanador);
		
		String [] saltoLinea = {};
		writer.writeNext(saltoLinea);

	
		// GANADORES DE LAS PARTIDAS
		List<String> ganadorGlobal = new ArrayList<>();
		
		
		// Indicamos el ganador (o ganadores) de la partida
		if (partidasJugadas.ganadoresDeTodasLasPartidas().size() > 1) {
			ganadorGlobal.add("GANADORES DE LAS PARTIDAS");
			for (int i = 0; i < partidasJugadas.ganadoresDeTodasLasPartidas().size(); i++) {
				ganadorGlobal.add(partidasJugadas.ganadoresDeTodasLasPartidas().get(i).getNombreJugador());
			}
		} else {
			ganadorGlobal.add("GANADOR DE LAS PARTIDAS");
			ganadorGlobal.add(partidasJugadas.ganadoresDeTodasLasPartidas().get(0).getNombreJugador());
		}
		String [] lineaGanadorGlobal = guardarEnArray(ganadorGlobal);
		writer.writeNext(lineaGanadorGlobal);
		
	}
	
	

	/**
	 * Método para obtener los datos de un fichero CSV
	 * @param nombreFichero El nombre del fichero
	 * @return ArrayList<EstadisticosJugador>
	 */
	public ArrayList<EstadisticosJugador> leerCSV(String nombreFichero) {

		FileReader fileReader = null;
		CSVReader reader = null;

		try {
			// Indicamos la ruta en la que cargar el fichero
			File csvMetricas = new File(rutaMetricas + nombreFichero + ".csv"); // Se indica una ruta relativa

			// Para evitar errores
			if (!csvMetricas.exists()) {
				csvMetricas.createNewFile();
			}

			// Se crean los objetos Writer
			fileReader = new FileReader(csvMetricas);
			reader = new CSVReader(fileReader);
			
			// Indicamos los campos a tener
			return obtenerEstadisticosJugador(reader);

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al obtener los datos del csv.");
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
				System.out.println("Ha ocurrido un error al obtener los datos del fichero csv.");
			}
		}
		return null;
	}
	
	
	
	/**
	 * Método que devuelve en un array los estadísticos de los jugadores
	 * @param reader CSVReader
	 * @return ArrayList<EstadisticosJugador>
	 * @throws IOException Excepción del fichero 
	 * @throws CsvException Excepción con la librería
	 */
	private ArrayList<EstadisticosJugador> obtenerEstadisticosJugador(CSVReader reader) throws IOException, CsvException {
		
		ArrayList<EstadisticosJugador> jugadores = new ArrayList<EstadisticosJugador>();
		List<String[]> lineas = reader.readAll();
		
		
		for (int i = 1; i < lineas.size(); i++) {
			String[] reglas = lineas.get(1)[i].split(",");
			
			EstadisticosJugador jugador = new EstadisticosJugador(
					lineas.get(0)[i], reglas, Integer.valueOf(lineas.get(2)[i]), Integer.valueOf(lineas.get(3)[i]),
					Integer.valueOf(lineas.get(4)[i]), Integer.valueOf(lineas.get(5)[i]), Integer.valueOf(lineas.get(6)[i]), 
					Integer.valueOf(lineas.get(7)[i]), Integer.valueOf(lineas.get(8)[i]), Integer.valueOf(lineas.get(9)[i]));
			
			jugadores.add(jugador);
		}
		return jugadores;
	}




	/**
	 * Método que permite pasar los datos a un formato que opencsv 
	 * puede usar para guardar la información
	 * @param elementos List <String>
	 * @return String[]
	 */
	private String[] guardarEnArray(List<String> elementos) {
		String [] intercambio = new String[elementos.size()];
		for (int i = 0; i < elementos.size(); i++) {
			intercambio[i] = elementos.get(i);
		}
		return intercambio;
	}



}