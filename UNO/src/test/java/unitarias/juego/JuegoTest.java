package test.java.unitarias.juego;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.algoritmoVoraz.ensembles.Ensemble;
import main.java.algoritmoVoraz.ensembles.EnsembleFactory;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.algoritmoVoraz.reglas.ReglaFactory;
import main.java.logica.GestionarJuegos;
import main.java.logica.juego.Juego;
import main.java.logica.juego.baraja.Baraja;
import main.java.logica.juego.baraja.tipos.NoBarajar;
import main.java.logica.juego.carta.Carta;
import main.java.logica.juego.carta.CartaAccion;
import main.java.logica.juego.carta.CartaNumerica;
import main.java.logica.juego.carta.acciones.tipos.CambiaColor;
import main.java.logica.juego.carta.acciones.tipos.CambiarSentido;
import main.java.logica.juego.carta.acciones.tipos.MasCuatro;
import main.java.logica.juego.carta.acciones.tipos.MasDos;
import main.java.logica.juego.carta.acciones.tipos.QuitarTurno;
import main.java.logica.juego.carta.colores.Colores;
import main.java.logica.juego.jugador.Jugador;
import main.java.logica.juego.jugador.JugadorAutomatico;


/**
 * Clase de test encargada de controlar que el juego funciona correctamente
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class JuegoTest {

	// ATRIBUTOS
	private ArrayList<Jugador> jugadores;
	private ArrayList<Regla> reglas;
	private Baraja baraja;
	private GestionarJuegos gestionarJuegos;
	private Ensemble ensemble;

	// En cada uno de los tests se comprueba además que nunca se hace trampa

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {
		// 2 jugadores automaticos

		reglas = new ArrayList<Regla>();
		reglas.add(ReglaFactory.crearRegla("ReglaPrimeraCarta"));
		// La misma regla para los dos
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas);
		JugadorAutomatico jugador1 = new JugadorAutomatico("Jugador1", reglas);

		// JugadorManual jugador0 = new JugadorManual("Jugador0");
		// JugadorManual jugador1= new JugadorManual("Jugador1");

		jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador0);
		jugadores.add(jugador1);

		baraja = new Baraja(new NoBarajar()); // Para controlar situaciones específicas no se puede barajar las cartas

		ensemble = EnsembleFactory.crearEnsemble("EnsembleVotacion"); // De momento nos interesan los jugadores manuales
	}

	/**
	 * En este test partimos de una baraja con solo cartas de quitarTurno
	 */
	@Test
	public void quitarTurnoTest() {

		// Se construye la baraja para el test
		
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 20; i++) {
			cartasMonton.add(new CartaAccion(new QuitarTurno(), Colores.ROJO));
		}

		Carta cartaMedio = new CartaAccion(new QuitarTurno(), Colores.ROJO);
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados
		// El jugador 0 debe haber jugado 7 cartas, todas ellas de tipo "Salta"
		// El jugador 1 no debe haber podido jugar ninguna ronda

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);
	}

	
	
	/**
	 * En este test partimos de una baraja con solo cartas de cambio de sentido
	 */
	@Test
	public void cambiaSentidoTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 30; i++) {
			cartasMonton.add(new CartaAccion(new CambiarSentido(), Colores.ROJO));
		}

		Carta cartaMedio = new CartaAccion(new CambiarSentido(), Colores.ROJO);
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se introduce un jugador adicional
		JugadorAutomatico jugador2 = new JugadorAutomatico("Jugador2", reglas);
		jugadores.add(jugador2);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1 (NO HACE NADA)
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

		// Jugador 2
		assertEquals(juegoResultado.getJugadores().get(2).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasCambiarSentidoJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(2).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getVecesQueHaGanado(), 0);

	}
	
	/**
	 * En este test partimos de una baraja con solo cartas de cambio de color
	 */
	@Test
	public void cambioColorTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 30; i++) {
			cartasMonton.add(new CartaAccion(new CambiaColor(), Colores.NOCOLOR));
		}

		Carta cartaMedio = new CartaAccion(new CambiaColor(), Colores.NOCOLOR);
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se introduce un jugador adicional
		JugadorAutomatico jugador2 = new JugadorAutomatico("Jugador2", reglas);
		jugadores.add(jugador2);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 2
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

	}
	

	/**
	 * En este test verificamos que funcione la carta +2
	 */
	@Test
	public void masDosTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO));

		for (int i = 0; i < 5; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO));

		Carta cartaMedio = new CartaNumerica(1, Colores.ROJO);
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 2);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 6); // Roba 6 cartas acumuladas
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);
	}

	/**
	 * En este test verificamos que funcione la carta +4
	 */
	@Test
	public void masCuatroTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		for (int i = 0; i < 5; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartasMonton.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		Carta cartaMedio = new CartaNumerica(1, Colores.ROJO);
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 2);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 12); // Roba 12 cartas acumuladas
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

	}

	/**
	 * En este test verificamos que al echar un +4 sobre un +2 el efecto se acumula
	 */
	@Test
	public void mezclaEchar4Sobre2Test() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO));

		for (int i = 0; i < 5; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR)); // Se puede echar un +4 SIEMP
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO));

		Carta cartaMedio = new CartaNumerica(1, Colores.ROJO);
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 8); // Roba 8 cartas acumuladas
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

	}

	/**
	 * En este test verificamos que no se puede echar un +2 sobre un +4
	 */
	@Test
	public void mezclaEchar2Sobre4Test() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO));

		for (int i = 0; i < 6; i++) {
			cartasMonton.add(new CartaNumerica(6, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		Carta cartaMedio = new CartaNumerica(1, Colores.ROJO);
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 9);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 2); // Este jugador debería robar 2 cartas
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 8);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 4); // Este jugador debería robar 4 cartas
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

	}

	/**
	 * En este test verificamos que si coincide el número pero no el color de las
	 * cartas numéricas, se puede echar igual
	 */
	@Test
	public void cartasNumericasCoincideNumeroNoColorTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		for (int i = 0; i < 7; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.AZUL)); // NO coincide el color
		}
		for (int i = 0; i < 7; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}

		Carta cartaMedio = new CartaNumerica(1, Colores.ROJO);

		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 0); // No roba ninguna carta
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

	}

	/**
	 * En este test verificamos que si coincide el color pero no el número de las
	 * cartas numéricas, se puede echar igual
	 */
	@Test
	public void cartasNumericasCoincideColorNoNumeroTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		for (int i = 0; i < 7; i++) {
			cartasMonton.add(new CartaNumerica(6, Colores.ROJO)); // NO coincide el número
		}
		for (int i = 0; i < 7; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}

		Carta cartaMedio = new CartaNumerica(1, Colores.ROJO);

		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 0); // No roba ninguna carta
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

	}

	/**
	 * En este test verificamos que si no coinciden ni el color ni el número de las
	 * cartas no se pueden echar
	 */
	@Test
	public void cartasNumericasNoCoincideColorNiNumeroTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		for (int i = 0; i < 7; i++) {
			cartasMonton.add(new CartaNumerica(6, Colores.AZUL)); // NO coincide el número y el color
		}
		for (int i = 0; i < 7; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}

		Carta cartaMedio = new CartaNumerica(1, Colores.ROJO);

		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 3);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 3); // Roba las cartas al no tener nada
																					// que le sirva
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

	}

	/**
	 * Este test es una combinación de los anteriores para verificar que funciona
	 * todo correctamente
	 */
	@Test
	public void probarTodoJuntoTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		// Se introduce un jugador adicional
		JugadorAutomatico jugador2 = new JugadorAutomatico("Jugador2", reglas);
		jugadores.add(jugador2);

		// BASE DE LA BARAJA
		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}

		// CARTAS JUGADOR 2
		for (int i = 0; i < 6; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new CambiarSentido(), Colores.ROJO)); // Cambiar sentido

		// CARTAS JUGADOR 1
		for (int i = 0; i < 6; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR)); // +4
		cartasMonton.add(new CartaAccion(new CambiarSentido(), Colores.ROJO)); // Cambiar sentido

		// CARTAS JUGADOR 0
		for (int i = 0; i < 4; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new CambiaColor(), Colores.NOCOLOR)); // CambiaColor
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO)); // +2
		cartasMonton.add(new CartaAccion(new QuitarTurno(), Colores.ROJO)); // Quitar turno

		Carta cartaMedio = new CartaNumerica(4, Colores.ROJO); // Distinto número, mismo color

		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 1);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial

		// Obtenemos los resultados

		Juego juegoResultado = gestionarJuegos.getJuegos().get(0);

		// Jugador 0
		assertEquals(juegoResultado.getJugadores().get(0).getCartasJugadas(), 7);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasMasDosJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getCartasCambiaColorJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(0).getVecesQueHaGanado(), 1);

		// Jugador 1
		assertEquals(juegoResultado.getJugadores().get(1).getCartasJugadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasRobadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasCuatroJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaCantadoUno(), 1);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(1).getVecesQueHaGanado(), 0);

		// Jugador 2
		assertEquals(juegoResultado.getJugadores().get(2).getCartasJugadas(), 7); // Juega 2 veces en la primera pasada
		assertEquals(juegoResultado.getJugadores().get(2).getCartasRobadas(), 6);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasMasCuatroJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasMasDosJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasCambiarSentidoJugadas(), 1);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getCartasCambiaColorJugadas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getVecesQueHaCantadoUno(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(juegoResultado.getJugadores().get(2).getVecesQueHaGanado(), 0);

	}

	/**
	 * Este test es una combinación de los anteriores para verificar que funciona
	 * todo correctamente durante varias partidas para verificar que los
	 * estadísticos se mantengan correctamente
	 */
	@Test
	public void probarTodoJuntoConVariasPartidasTest() {

		// Se construye la baraja para el test
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();

		// Se introduce un jugador adicional
		JugadorAutomatico jugador2 = new JugadorAutomatico("Jugador2", reglas);
		jugadores.add(jugador2);

		// BASE DE LA BARAJA
		for (int i = 0; i < 50; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}

		// CARTAS JUGADOR 2
		for (int i = 0; i < 6; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new CambiarSentido(), Colores.ROJO)); // Cambiar sentido

		// CARTAS JUGADOR 1
		for (int i = 0; i < 6; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		cartasMonton.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR)); // +4
		cartasMonton.add(new CartaAccion(new CambiarSentido(), Colores.ROJO)); // Cambiar sentido

		// CARTAS JUGADOR 0
		for (int i = 0; i < 4; i++) {
			cartasMonton.add(new CartaNumerica(1, Colores.ROJO));
		}
		
		cartasMonton.add(new CartaAccion(new CambiaColor(), Colores.NOCOLOR)); // CambiaColor
		cartasMonton.add(new CartaAccion(new MasDos(), Colores.ROJO)); // +2
		cartasMonton.add(new CartaAccion(new QuitarTurno(), Colores.ROJO)); // Quitar turno

		Carta cartaMedio = new CartaNumerica(4, Colores.ROJO); // Distinto número, mismo color

		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);

		// Se juega la partida
		gestionarJuegos = new GestionarJuegos(jugadores, baraja, ensemble, 10);
		gestionarJuegos.jugarPartidasBarajaEspecial(0); // Se indica el turno inicial
		
		// Hay que guardar los resultados de las partidas
		for (Juego juego : gestionarJuegos.getJuegos()) {
			gestionarJuegos.guardarInformacion(juego);
		}

		// Obtenemos los resultados para todas las partidas (x10)
		
		// Jugador 0
		assertEquals(gestionarJuegos.getJugadores().get(0).getCartasJugadas(), 70);
		assertEquals(gestionarJuegos.getJugadores().get(0).getCartasRobadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(0).getCartasMasCuatroJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(0).getCartasMasDosJugadas(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(0).getCartasCambiarSentidoJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(0).getCartasQuitarTurnoJugadas(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(0).getCartasCambiaColorJugadas(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(0).getVecesQueHaCantadoUno(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(0).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(0).getVecesQueHaGanado(), 10);

		// Jugador 1
		assertEquals(gestionarJuegos.getJugadores().get(1).getCartasJugadas(), 60);
		assertEquals(gestionarJuegos.getJugadores().get(1).getCartasRobadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(1).getCartasMasCuatroJugadas(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(1).getCartasMasDosJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(1).getCartasCambiarSentidoJugadas(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(1).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(1).getCartasCambiaColorJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(1).getVecesQueHaCantadoUno(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(1).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(1).getVecesQueHaGanado(), 0);

		// Jugador 2
		assertEquals(gestionarJuegos.getJugadores().get(2).getCartasJugadas(), 70);
		assertEquals(gestionarJuegos.getJugadores().get(2).getCartasRobadas(), 60);
		assertEquals(gestionarJuegos.getJugadores().get(2).getCartasMasCuatroJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(2).getCartasMasDosJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(2).getCartasCambiarSentidoJugadas(), 10);
		assertEquals(gestionarJuegos.getJugadores().get(2).getCartasQuitarTurnoJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(2).getCartasCambiaColorJugadas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(2).getVecesQueHaCantadoUno(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(2).getVecesQueHaIntentadoHacerTrampas(), 0);
		assertEquals(gestionarJuegos.getJugadores().get(2).getVecesQueHaGanado(), 0);

	}

}
