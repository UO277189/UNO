package test.unitarias;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import main.manejoDatos.manejoFicheros.ManejoFicherosCSV;
import main.manejoDatos.manejoFicheros.ManejoFicherosJSON;

/**
 * Esta clase se encargará de verificar que el sistema entiende lo que es un CSV correcto y uno incorrecto
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class CSVTest {

	// ATRIBUTOS
	private ManejoFicherosJSON manejoJSON;
	private ManejoFicherosCSV manejoCSV;
	private ByteArrayOutputStream salidaConsola;

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@Before
	public void inicializar() {
		manejoJSON = new ManejoFicherosJSON();
		manejoCSV = new ManejoFicherosCSV();

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
		manejoCSV.leerCSV("/pruebas/csv/csvIncorrecto");
	}
	
	
	/**
	 * Test que al cargar un CSV correcto no aparece ningún error
	 */
	@Test
	public void cargarCSVCorrectaTest() {
		manejoCSV.leerCSV("/pruebas/csv/csvCorrecto");
	}
	
	/**
	 * Test que salta un error si no se muestra ningún ganador de la partida
	 */
	@Test
	public void cargarCSVJugadorNoGanadorTest() {
		manejoCSV.leerCSV("/pruebas/csv/csvJugadorNoGanador");
	}
	
	/**
	 * Test que salta un error si el jugador que más partidas ha ganado no sale como ganador del juego al final
	 */
	@Test
	public void cargarCSVJugadorGanadorIncorrectoTest() {
		manejoCSV.leerCSV("/pruebas/csv/csvJugadorGanadorIncorrecto");
	}

	

}
