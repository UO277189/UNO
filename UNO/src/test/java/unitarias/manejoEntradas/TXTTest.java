package test.java.unitarias.manejoEntradas;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.algoritmoVoraz.ensembles.tipos.EnsembleVotacion;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.algoritmoVoraz.reglas.ReglaFactory;
import main.java.logica.GestionarJuegos;
import main.java.logica.ficheros.TXTParser;
import main.java.logica.juego.baraja.tipos.CartaACarta;
import main.java.logica.juego.jugador.Jugador;
import main.java.logica.juego.jugador.JugadorAutomatico;

/**
 * Esta clase se encargará de verificar que el sistema es capaz de generar
 * archivos TXT de forma correcta
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class TXTTest {
	
	// ATRIBUTOS
	private TXTParser txtParser;
	private ByteArrayOutputStream salidaConsola;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Regla> reglas;
	
	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {
		txtParser = new TXTParser();
		salidaConsola = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salidaConsola));
		
		// Preparar los jugadores
		reglas = new ArrayList<Regla>();
		reglas.add(ReglaFactory.crearRegla("ReglaPrimeraCarta"));
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas);
		JugadorAutomatico jugador1 = new JugadorAutomatico("Jugador1", reglas);
		jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador0);
		jugadores.add(jugador1);

	}
	
	
	/**
	 * Test que verifica que el sistema es capaz de volcar los datos correctamente a un archivo TXT
	 */
	@Test
	public void volcarDatosTXTTest() {
		
		GestionarJuegos juegos = new GestionarJuegos(jugadores, new CartaACarta(60), new EnsembleVotacion(), 100);
		juegos.jugarPartidas();
		txtParser.escribirDatos(null, juegos, null);
		
		// Se verifica que no salta una excepción
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("Se han guardado correctamente los datos en el fichero txt."));
		assertTrue(!salida.contains("Ha ocurrido un error al pasar los datos a un txt."));
	}
	

}
