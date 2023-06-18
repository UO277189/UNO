package test.java.unitarias.estrategias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.algoritmoVoraz.ensembles.Ensemble;
import main.java.algoritmoVoraz.ensembles.EnsembleFactory;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCambiarColorMedio;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCompararTiposCartasMasFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCompararTiposCartasMenosFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaContarColoresMasFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaContarColoresMenosFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaNoCambiarColorMedio;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaNoPriorizarCartasRobar;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPrimeraCarta;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartaAccion;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartaNumerica;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartasAccionNoComodin;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartasRobar;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarComodines;
import main.java.logica.juego.carta.Carta;
import main.java.logica.juego.carta.CartaAccion;
import main.java.logica.juego.carta.CartaNumerica;
import main.java.logica.juego.carta.acciones.tipos.CambiaColor;
import main.java.logica.juego.carta.acciones.tipos.CambiarSentido;
import main.java.logica.juego.carta.acciones.tipos.MasCuatro;
import main.java.logica.juego.carta.acciones.tipos.MasDos;
import main.java.logica.juego.carta.acciones.tipos.QuitarTurno;
import main.java.logica.juego.carta.colores.Colores;
import main.java.logica.juego.jugador.JugadorAutomatico;

/**
 * Clase para probar diferentes reglas de peso en el sistema y ver que salen los
 * comportamientos esperados
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglasTest {

	// ATRIBUTOS
	private Ensemble ensemble;

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {
		ensemble = EnsembleFactory.crearEnsemble("EnsembleVotacion"); // En esa clase no se estudian ensembles
	}
	
	
	/**
	 * Test para verificar que la regla PriorizarCartasAccionNoComodin funciona correctamente
	 */
	@Test
	public void reglaPriorizarCartasAccionNoComodinTest() {

		// +2 
		
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPriorizarCartasAccionNoComodin());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.NOCOLOR)); // Pos 2
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));

		jugador0.setCartasMano(cartas);
		jugador0.asignarEnsemble(ensemble);

		assertEquals(2, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

		// Salta
		
		ArrayList<Carta> cartas1 = new ArrayList<Carta>();
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaAccion(new QuitarTurno(), Colores.NOCOLOR));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));

		jugador0.setCartasMano(cartas1);
		jugador0.asignarEnsemble(ensemble);

		assertEquals(1, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));
		
		// Cambio sentido
		
		ArrayList<Carta> cartas2 = new ArrayList<Carta>();
		cartas2.add(new CartaAccion(new CambiarSentido(), Colores.NOCOLOR)); // Pos 2
		cartas2.add(new CartaNumerica(1, Colores.ROJO));
		cartas2.add(new CartaNumerica(1, Colores.ROJO));
		cartas2.add(new CartaNumerica(1, Colores.ROJO));

		jugador0.setCartasMano(cartas2);
		jugador0.asignarEnsemble(ensemble);

		assertEquals(0, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));
		

	}

	/**
	 * Test para verificar que la regla PriorizarCartasRobar funciona correctamente
	 */
	@Test
	public void reglaPriorizarCartasRobarTest() {

		// +2

		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPriorizarCartasRobar());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR)); // Pos 2
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));

		jugador0.setCartasMano(cartas);
		jugador0.asignarEnsemble(ensemble);

		assertEquals(2, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

		// +4

		ArrayList<Regla> reglas1 = new ArrayList<Regla>();
		reglas1.add(new ReglaPriorizarCartasRobar());
		JugadorAutomatico jugador1 = new JugadorAutomatico("Jugador1", reglas1);

		ArrayList<Carta> cartas1 = new ArrayList<Carta>();
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaAccion(new MasDos(), Colores.NOCOLOR)); // Pos 2
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		jugador1.setCartasMano(cartas1);
		jugador1.asignarEnsemble(ensemble);

		assertEquals(2, jugador1.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

	}

	/**
	 * Test para verificar que la regla NoPriorizarCartasRobar funciona
	 * correctamente
	 */
	@Test
	public void reglaNoPriorizarCartasRobarTest() {

		// +4

		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaNoPriorizarCartasRobar());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		;

		jugador0.setCartasMano(cartas);
		jugador0.asignarEnsemble(ensemble);

		assertEquals(0, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

		// +2

		ArrayList<Regla> reglas1 = new ArrayList<Regla>();
		reglas1.add(new ReglaNoPriorizarCartasRobar());
		JugadorAutomatico jugador1 = new JugadorAutomatico("Jugador1", reglas1);

		ArrayList<Carta> cartas1 = new ArrayList<Carta>();
		cartas1.add(new CartaAccion(new MasDos(), Colores.NOCOLOR));
		cartas1.add(new CartaAccion(new MasDos(), Colores.NOCOLOR));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaAccion(new MasDos(), Colores.NOCOLOR));

		jugador1.setCartasMano(cartas1);
		jugador1.asignarEnsemble(ensemble);

		assertEquals(2, jugador1.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

	}

	/**
	 * Test para verificar que la regla PriorizarComodines funciona correctamente
	 */
	@Test
	public void reglaPriorizarComodinesTest() {

		// Solo +4

		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPriorizarComodines());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaNumerica(1, Colores.ROJO));

		jugador0.setCartasMano(cartas);
		jugador0.asignarEnsemble(ensemble);

		assertEquals(5, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

		// Solo cambia color

		ArrayList<Regla> reglas1 = new ArrayList<Regla>();
		reglas1.add(new ReglaPriorizarComodines());
		JugadorAutomatico jugador1 = new JugadorAutomatico("Jugador1", reglas0);

		ArrayList<Carta> cartas1 = new ArrayList<Carta>();
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));
		cartas1.add(new CartaAccion(new CambiaColor(), Colores.NOCOLOR));
		cartas1.add(new CartaNumerica(1, Colores.ROJO));

		jugador1.setCartasMano(cartas1);
		jugador1.asignarEnsemble(ensemble);

		assertEquals(5, jugador1.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

		// Mezcla de ambas (prioriza el +4)

		ArrayList<Regla> reglas2 = new ArrayList<Regla>();
		reglas2.add(new ReglaPriorizarComodines());
		JugadorAutomatico jugador2 = new JugadorAutomatico("Jugador2", reglas0);

		ArrayList<Carta> cartas2 = new ArrayList<Carta>();
		cartas2.add(new CartaNumerica(1, Colores.ROJO));
		cartas2.add(new CartaNumerica(1, Colores.ROJO));
		cartas2.add(new CartaNumerica(1, Colores.ROJO));
		cartas2.add(new CartaNumerica(1, Colores.ROJO));
		cartas2.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas2.add(new CartaAccion(new CambiaColor(), Colores.NOCOLOR));
		cartas2.add(new CartaNumerica(1, Colores.ROJO));

		jugador2.setCartasMano(cartas2);
		jugador2.asignarEnsemble(ensemble);

		assertEquals(4, jugador2.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));

	}

	/**
	 * Test para verificar que la regla PrimeraCarta funciona correctamente
	 */
	@Test
	public void reglaPrimeraCartaTest() {

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPrimeraCarta());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(0, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));
	}

	/**
	 * Test para verificar que la regla PriorizarCartaNumerica funciona
	 * correctamente
	 */
	@Test
	public void reglaPriorizarCartaNumerica() {

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPriorizarCartaNumerica());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(2, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));
	}

	/**
	 * Test para verificar que la regla PriorizarCartaAccion funciona correctamente
	 */
	@Test
	public void reglaPriorizarCartaAccion() {

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPriorizarCartaAccion());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		;
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(5, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));
	}

	/**
	 * Test para verificar que la regla Azar funciona correctamente
	 */
	@Test
	public void reglaAzarTest() {

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPrimeraCarta());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		int val = jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null);
		assertTrue(val >= 0 && val < 6); // Solo se puede ver aquí si está fuera del rango
	}

	/**
	 * Test para verificar que la regla ContarColoresMasFrecuetne funciona
	 * correctamente
	 */
	@Test
	public void reglaContarColoresMasFrecuenteTest() {

		// En las reglas con historial necesitamos indicar un parametro adicional

		ArrayList<Carta> cartasHistorial = new ArrayList<Carta>(); // Salieron más cartas rojas
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.AZUL));
		cartasHistorial.add(new CartaNumerica(1, Colores.AMARILLO));
		cartasHistorial.add(new CartaNumerica(1, Colores.VERDE));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaContarColoresMasFrecuente());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaAccion(new MasDos(), Colores.AZUL));
		cartas.add(new CartaAccion(new MasDos(), Colores.VERDE));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO)); // Pos 2
		cartas.add(new CartaAccion(new MasDos(), Colores.AMARILLO));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(2, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), cartasHistorial));
	}

	/**
	 * Test para verificar que la regla ContarColoresMenosFrecuetne funciona
	 * correctamente
	 */
	@Test
	public void reglaContarColoresMenosFrecuenteTest() {

		// En las reglas con historial necesitamos indicar un parametro adicional

		ArrayList<Carta> cartasHistorial = new ArrayList<Carta>(); // Salieron más cartas rojas
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.AZUL));
		cartasHistorial.add(new CartaNumerica(1, Colores.AZUL));
		cartasHistorial.add(new CartaNumerica(1, Colores.VERDE));
		cartasHistorial.add(new CartaNumerica(1, Colores.AMARILLO));
		cartasHistorial.add(new CartaNumerica(1, Colores.AMARILLO));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaContarColoresMenosFrecuente());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaNumerica(1, Colores.AZUL));
		cartas.add(new CartaNumerica(1, Colores.VERDE)); // Pos 1
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.AMARILLO));
		cartas.add(new CartaNumerica(1, Colores.NOCOLOR));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(1, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), cartasHistorial));
	}

	/**
	 * Test para verificar que la regla CompararTiposCartasMenosFrecuente funciona
	 * correctamente
	 */
	@Test
	public void reglaCompararTiposCartasMenosFrecuenteTest() {

		// En las reglas con historial necesitamos indicar un parametro adicional

		ArrayList<Carta> cartasHistorial = new ArrayList<Carta>(); // Salieron más cartas de accion
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaCompararTiposCartasMenosFrecuente());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();

		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaNumerica(1, Colores.ROJO)); // Pos 2
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(2, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), cartasHistorial));
	}

	/**
	 * Test para verificar que la regla CompararTiposCartasMasFrecuente funciona
	 * correctamente
	 */
	@Test
	public void reglaCompararTiposCartasMasFrecuenteTest() {

		// En las reglas con historial necesitamos indicar un parametro adicional

		ArrayList<Carta> cartasHistorial = new ArrayList<Carta>(); // Salieron más cartas numéricas
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.AZUL));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaCompararTiposCartasMasFrecuente());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();

		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaNumerica(1, Colores.ROJO)); // Pos 1
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(1, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), cartasHistorial));
	}

	/**
	 * Test para verificar que la regla CambiarColorMedio funciona correctamente
	 */
	@Test
	public void reglaCambiarColorMedioTest() {

		// En las reglas con historial necesitamos indicar un parametro adicional

		ArrayList<Carta> cartasHistorial = new ArrayList<Carta>();
		cartasHistorial.add(new CartaAccion(new MasDos(), Colores.AZUL));
		cartasHistorial.add(new CartaAccion(new MasDos(), Colores.ROJO)); // La última carta del historial

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaCambiarColorMedio());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();

		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.AZUL));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(4, jugador0.jugarTurno(new CartaAccion(new MasDos(), Colores.ROJO), cartasHistorial));
	}
	
	/**
	 * Test para verificar que la regla NoCambiarColorMedio funciona correctamente
	 */
	@Test
	public void reglaNoCambiarColorMedioTest() {

		// En las reglas con historial necesitamos indicar un parametro adicional

		ArrayList<Carta> cartasHistorial = new ArrayList<Carta>();
		cartasHistorial.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartasHistorial.add(new CartaAccion(new MasDos(), Colores.AZUL));  // La última carta del historial

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaNoCambiarColorMedio());
		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();

		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.AZUL)); // Mantiene el color
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(ensemble); // Se asigna el ensemble

		assertEquals(3, jugador0.jugarTurno(new CartaAccion(new MasDos(), Colores.AZUL), cartasHistorial));
	}

}
