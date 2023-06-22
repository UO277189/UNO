package test.java.sistema;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.algoritmoVoraz.ensembles.tipos.EnsembleVotacion;
import main.java.consola.LeerConsola;
import main.java.logica.GestionarJuegos;
import main.java.logica.juego.baraja.tipos.CartaACarta;
import main.java.logica.juego.jugador.Jugador;
import main.java.logica.juego.jugador.JugadorManual;



/**
 * Clase para controlar situaciones anómalas durante una partida manual
 * @author Efrén García Valencia UO277189
 *
 */
public class PartidaManualTest {

	// PARÁMETROS
	InputStream entradaInicio;
	ByteArrayOutputStream destino;
	PrintStream destinoInicio;

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {
		destino = new ByteArrayOutputStream();
	}

	/**
	 * Test que revisa que si en una partida manual metes un valor fuera del rango
	 * el sistema no deje de funcionar
	 */
	@Test
	public void fueraLimiteTest() {

		String comandos = "20";

		System.setIn(new ByteArrayInputStream(comandos.getBytes()));
		System.setOut(new PrintStream(destino));

		try {

			LeerConsola leerConsola = new LeerConsola();

			JugadorManual manual1 = new JugadorManual("Manual1", leerConsola);
			JugadorManual manual2 = new JugadorManual("Manual2", leerConsola);

			ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
			jugadores.add(manual1);
			jugadores.add(manual2);

			GestionarJuegos juegos = new GestionarJuegos(jugadores, new CartaACarta(60), new EnsembleVotacion(), 1);
			juegos.jugarPartidas();

		} catch (NoSuchElementException e) {
			// Recoge una excepción porque la partida terminó y espera más entradas
			// Nos interesa ver si saca el mensaje adecuado al comando de entrada
			assertTrue(destino.toString().contains("ERROR: sólo se aceptan valores entre -1 y 6"));
		}
	}
	
	/**
	 * Test que revisa que si en una partida manual metes un valor no válido
	 * el sistema no deje de funcionar
	 */
	@Test
	public void valorNoValidoTest() {

		String comandos = "a";

		System.setIn(new ByteArrayInputStream(comandos.getBytes()));
		System.setOut(new PrintStream(destino));

		try {

			LeerConsola leerConsola = new LeerConsola();

			JugadorManual manual1 = new JugadorManual("Manual1", leerConsola);
			JugadorManual manual2 = new JugadorManual("Manual2", leerConsola);

			ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
			jugadores.add(manual1);
			jugadores.add(manual2);

			GestionarJuegos juegos = new GestionarJuegos(jugadores, new CartaACarta(60), new EnsembleVotacion(), 1);
			juegos.jugarPartidas();

		} catch (NoSuchElementException e) {
			// Recoge una excepción porque la partida terminó y espera más entradas
			// Nos interesa ver si saca el mensaje adecuado al comando de entrada
			assertTrue(destino.toString().contains("ERROR: introduzca un carácter válido"));
		}
	}

}
