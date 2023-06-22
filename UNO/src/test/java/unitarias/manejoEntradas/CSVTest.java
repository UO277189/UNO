package test.java.unitarias.manejoEntradas;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.logica.EstadisticosJugador;
import main.java.logica.ficheros.CSVParser;





/**
 * Esta clase se encargará de verificar que el sistema entiende lo que es un CSV correcto y uno incorrecto
 * Sirve para verificar que el programa carga bien los CSV para realizar pruebas más complejas
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class CSVTest {

	// ATRIBUTOS
	private CSVParser manejoCSV;
	private ByteArrayOutputStream salidaConsola;

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {
		manejoCSV = new CSVParser();

		// Para las pruebas nos interesa redirigir la salida de la consola
		// para así verificar que salgan los mensajes adecuados
		salidaConsola = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salidaConsola));
	}
	

	/**
	 * Test que verifica que al cargar un CSV que no tenga el formato adecuado salta un mensaje de error
	 */
	@Test
	public void cargarCSVIncorrectoTest() {
		ArrayList<EstadisticosJugador> jugadores = manejoCSV.leerCSV("/test/resources/csv/CSVIncorrecto");
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("Ha ocurrido un error al obtener los datos del fichero CSV."));
		assertEquals(jugadores, null); // Devuelve un objeto NULL
	}
	
	
	/**
	 * Test que al cargar un CSV correcto no aparece ningún error
	 */
	@Test
	public void cargarCSVCorrectaTest() {
		ArrayList<EstadisticosJugador> jugadores = manejoCSV.leerCSV("/test/resources/csv/CSVCorrecto");
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("Se ha cargado el fichero CSV correctamente."));
		
		// Comprobamos que los campos coincidan
		
		// JUGADOR 1
		assertEquals("Jugador1", jugadores.get(0).getNombre());
		assertEquals("ReglaAzar", jugadores.get(0).getReglas().toString());
		assertEquals(1042, jugadores.get(0).getCartasJugadas());
		assertEquals(324, jugadores.get(0).getCartasRobadas());
		assertEquals(68, jugadores.get(0).getCartasMasCuatroJugadas());
		assertEquals(122, jugadores.get(0).getCartasMasDosJugadas());
		assertEquals(106, jugadores.get(0).getCartasCambioSentidoJugadas());
		assertEquals(97, jugadores.get(0).getCartasQuitarTurnoJugadas());
		assertEquals(12, jugadores.get(0).getCartasCambioColorJugadas());
		assertEquals(0, jugadores.get(0).getVecesQueHizoTrampas());
		assertEquals(82, jugadores.get(0).getVecesQueCantaUno());
		assertEquals(32, jugadores.get(0).getVecesQueHaGanado());
	
		// JUGADOR 2
		assertEquals("Jugador2", jugadores.get(1).getNombre());
		assertEquals("ReglaAzar", jugadores.get(1).getReglas().toString());
		assertEquals(1084, jugadores.get(1).getCartasJugadas());
		assertEquals(285, jugadores.get(1).getCartasRobadas());
		assertEquals(32, jugadores.get(1).getCartasMasCuatroJugadas());
		assertEquals(80, jugadores.get(1).getCartasMasDosJugadas());
		assertEquals(95, jugadores.get(1).getCartasCambioSentidoJugadas());
		assertEquals(96, jugadores.get(1).getCartasQuitarTurnoJugadas());
		assertEquals(14, jugadores.get(1).getCartasCambioColorJugadas());
		assertEquals(0, jugadores.get(1).getVecesQueHizoTrampas());
		assertEquals(52, jugadores.get(1).getVecesQueCantaUno());
		assertEquals(15, jugadores.get(1).getVecesQueHaGanado());
		
		// JUGADOR 3
		assertEquals("Jugador3", jugadores.get(2).getNombre());
		assertEquals("ReglaAzar", jugadores.get(2).getReglas().toString());
		assertEquals(1089, jugadores.get(2).getCartasJugadas());
		assertEquals(305, jugadores.get(2).getCartasRobadas());
		assertEquals(22, jugadores.get(2).getCartasMasCuatroJugadas());
		assertEquals(77, jugadores.get(2).getCartasMasDosJugadas());
		assertEquals(67, jugadores.get(2).getCartasCambioSentidoJugadas());
		assertEquals(89, jugadores.get(2).getCartasQuitarTurnoJugadas());
		assertEquals(16, jugadores.get(2).getCartasCambioColorJugadas());
		assertEquals(0, jugadores.get(2).getVecesQueHizoTrampas());
		assertEquals(84, jugadores.get(2).getVecesQueCantaUno());
		assertEquals(26, jugadores.get(2).getVecesQueHaGanado());

		// JUGADOR 4
		assertEquals("Jugador4", jugadores.get(3).getNombre());
		assertEquals("ReglaAzar", jugadores.get(3).getReglas().toString());
		assertEquals(1028, jugadores.get(3).getCartasJugadas());
		assertEquals(361, jugadores.get(3).getCartasRobadas());
		assertEquals(30, jugadores.get(3).getCartasMasCuatroJugadas());
		assertEquals(94, jugadores.get(3).getCartasMasDosJugadas());
		assertEquals(80, jugadores.get(3).getCartasCambioSentidoJugadas());
		assertEquals(92, jugadores.get(3).getCartasQuitarTurnoJugadas());
		assertEquals(18, jugadores.get(3).getCartasCambioColorJugadas());
		assertEquals(0, jugadores.get(3).getVecesQueHizoTrampas());
		assertEquals(87, jugadores.get(3).getVecesQueCantaUno());
		assertEquals(27, jugadores.get(3).getVecesQueHaGanado());


	}

}
