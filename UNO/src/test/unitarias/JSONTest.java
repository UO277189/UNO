package test.unitarias;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.juego.baraja.estrategiasBaraja.estrategias.CartaACarta;
import main.juego.baraja.estrategiasBaraja.estrategias.MontonAMonton;
import main.juego.jugador.Jugador;
import main.juego.jugador.JugadorAutomatico;
import main.juego.jugador.JugadorManual;
import main.manejoDatos.Configuracion;
import main.manejoDatos.manejoFicheros.ManejoFicherosJSON;

/**
 * Esta clase se encargará de estudiar todos los casos posibles que afectan a la carga de datos
 * de ficheros JSON
 * @author Efrén García Valencia UO277189
 *
 */
public class JSONTest {
	
	// ATRIBUTOS
	private ManejoFicherosJSON manejoJSON;
	private ByteArrayOutputStream salidaConsola;
	
	
	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@Before
	 public void inicializar() {
	   manejoJSON = new ManejoFicherosJSON();
	   
	   // Para las pruebas nos interesa redirigir la salida de la consola
	   // para así verificar que salgan los mensajes adecuados
	   salidaConsola = new ByteArrayOutputStream();
	   System.setOut(new PrintStream(salidaConsola));

	}

	/**
	 * Test que verifica que al cargar un JSON vacio el programa lanza el aviso correspondiente
	 */
	@Test
	public void cargarVacioTest() {
		manejoJSON.leerJSON("/pruebas/json/vacio");
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("HA SURGIDO UN PROBLEMA AL LEER LOS NODOS DEL FICHERO. SE CIERRA EL PROGRAMA"));
	}
	
	
	/**
	 * Test que verifica que al cargar un JSON que esté malformado el programa lanza el aviso correspondiente
	 */
	@Test
	public void cargarMalFormadoTest() {
		manejoJSON.leerJSON("/pruebas/json/malFormado");
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("HA SURGIDO UN PROBLEMA AL LEER LOS NODOS DEL FICHERO. SE CIERRA EL PROGRAMA"));
	}

	
	/**
	 * Test que verifica que al cargar un JSON que cumpla la estructura pero incumple los requisitos salte el mensaje correspondiente
	 */
	@Test
	public void cargarParametrosNoValidosTest() {
		manejoJSON.leerJSON("/pruebas/json/parametrosNoValidos");
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("Nombre no válido"));
		assertTrue(salida.contains("Ha surgido un problema al cargar los jugadores"));
		assertTrue(salida.contains("El número mínimo de partidas ha de ser uno"));
		assertTrue(salida.contains("HA SURGIDO UN PROBLEMA AL VALIDAR LOS DATOS INTRODUCIDOS. SE CIERRA EL PROGRAMA"));
	}
	
	/**
	 * Test que verifica que al cargar un JSON que se invente reglas, estrategias o ensembles salte el mensaje correspondiente
	 */
	@Test
	public void cargarParametrosInventadosTest() {
		manejoJSON.leerJSON("/pruebas/json/parametrosInventados");
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("Se ha introducido una regla que no existe"));
		assertTrue(salida.contains("Se ha introducido una estrategia de barajar que no existe"));
		assertTrue(salida.contains("Se ha introducido un ensemble que no existe"));
		assertTrue(salida.contains("HA SURGIDO UN PROBLEMA AL VALIDAR LOS DATOS INTRODUCIDOS. SE CIERRA EL PROGRAMA"));
	}

	/**
	 * Test que verifica que el programa sea capaz de cargar datos correctamente de un JSON
	 */
	@Test
	public void cargarFicheroCorrectamente() {
		
		// Cargamos las configuraciones
		ArrayList<Configuracion> configuraciones = 
				manejoJSON.leerJSON("/pruebas/json/cargaFicheroCorrecta"); 
		
		// Comprobamos que se cargue el fichero correctamente
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("CARGA DEL FICHERO CORRECTA"));
		
		
		// Revisamos que las configuraciones sean correctas
		
		// Primera configuracion con jugadores automaticos
		Configuracion configuracionUNO = configuraciones.get(0);
		assertEquals(configuracionUNO.getNombreConfiguracion(), "pruebaCargaFicheroAutomatico");
		for (Jugador jugador : configuracionUNO.getJugadoresPartida()) {
			assertTrue(jugador instanceof JugadorAutomatico);
			assertEquals(jugador.getNombreJugador(), "Jugador");
			assertEquals(((JugadorAutomatico)jugador).getReglas().get(0).toString(), "ReglaAzar");
		}
		assertTrue(configuracionUNO.getEstrategiaBaraja() instanceof CartaACarta);
		assertEquals(((CartaACarta)configuracionUNO.getEstrategiaBaraja()).getCardsToExchange(), 60);
		assertEquals(configuracionUNO.getEnsemble().toString(), "EnsembleVotacion");
		assertEquals(configuracionUNO.getNumeroPartidas(), 100);
		assertEquals(configuracionUNO.isTraza(), false);
		
		// Segunda configuracion con jugadores manuales
		Configuracion configuracionDOS = configuraciones.get(1);
		assertEquals(configuracionDOS.getNombreConfiguracion(), "pruebaCargaFicheroManual");
		for (Jugador jugador : configuracionDOS.getJugadoresPartida()) {
			assertTrue(jugador instanceof JugadorManual);
			assertEquals(jugador.getNombreJugador(), "Jugador");
		}
		assertTrue(configuracionDOS.getEstrategiaBaraja() instanceof MontonAMonton);
		assertEquals(((MontonAMonton)configuracionDOS.getEstrategiaBaraja()).getCardInLot(), 10);
		assertEquals(((MontonAMonton)configuracionDOS.getEstrategiaBaraja()).getLotToExchange(), 5);
		assertEquals(configuracionDOS.getEnsemble().toString(), "EnsembleVotacion");
		assertEquals(configuracionDOS.getNumeroPartidas(), 100);
		assertEquals(configuracionDOS.isTraza(), true);
	}

	/**
	 * Test que verifica que por defecto el ensemble a introducir sea el de votación
	 */
	@Test
	public void cargarFicheroCorrectamenteEnsemblePorDefecto() {
		
		// Cargamos las configuraciones
		ArrayList<Configuracion> configuraciones = 
				manejoJSON.leerJSON("/pruebas/json/cargaFicheroCorrectaEnsemblePorDefecto"); 
		
		// Comprobamos que se cargue el fichero correctamente
		String salida = salidaConsola.toString();
		assertTrue(salida.contains("CARGA DEL FICHERO CORRECTA"));
		
		
		// Revisamos que las configuraciones sean correctas
		
		// Primera configuracion con jugadores automaticos
		Configuracion configuracionUNO = configuraciones.get(0);
		assertEquals(configuracionUNO.getNombreConfiguracion(), "pruebaCargaFicheroAutomatico");
		for (Jugador jugador : configuracionUNO.getJugadoresPartida()) {
			assertTrue(jugador instanceof JugadorAutomatico);
			assertEquals(jugador.getNombreJugador(), "Jugador");
			assertEquals(((JugadorAutomatico)jugador).getReglas().get(0).toString(), "ReglaAzar");
		}
		assertTrue(configuracionUNO.getEstrategiaBaraja() instanceof CartaACarta);
		assertEquals(((CartaACarta)configuracionUNO.getEstrategiaBaraja()).getCardsToExchange(), 60);
		assertEquals(configuracionUNO.getEnsemble().toString(), "EnsembleVotacion");
		assertEquals(configuracionUNO.getNumeroPartidas(), 100);
		assertEquals(configuracionUNO.isTraza(), false);
	}

}