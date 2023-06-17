package test.java.unitarias.estrategias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.algoritmoVoraz.ensembles.tipos.EnsembleRanking;
import main.java.algoritmoVoraz.ensembles.tipos.EnsembleSuma;
import main.java.algoritmoVoraz.ensembles.tipos.EnsembleVotacion;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCompararTiposCartasMenosFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaContarColoresMasFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartaAccion;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartaNumerica;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartasAccionNoComodin;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartasRobar;
import main.java.logica.juego.carta.Carta;
import main.java.logica.juego.carta.CartaAccion;
import main.java.logica.juego.carta.CartaNumerica;
import main.java.logica.juego.carta.acciones.tipos.MasCuatro;
import main.java.logica.juego.carta.acciones.tipos.MasDos;
import main.java.logica.juego.carta.colores.Colores;
import main.java.logica.juego.jugador.JugadorAutomatico;

/**
 * Clase que prueba los diferentes ensembles que integra el sistema
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class EnsemblesTest {

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {
	}

	/**
	 * Test para verificar que el ensemble de votación funciona correctamente
	 */
	@Test
	public void ensembleVotacionTest() {

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPriorizarCartaNumerica());
		reglas0.add(new ReglaPriorizarCartaNumerica());
		reglas0.add(new ReglaPriorizarCartaAccion());

		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();;
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasDos(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasDos(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasDos(), Colores.NOCOLOR));
		cartas.add(new CartaAccion(new MasDos(), Colores.NOCOLOR));
		cartas.add(new CartaNumerica(1, Colores.ROJO));

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(new EnsembleVotacion()); // Se asigna el ensemble

		// La carta mas votada es la numérica situada al final
		assertEquals(5, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));
	}

	/**
	 * Test para verificar que el ensemble de suma funciona correctamente
	 */
	@Test
	public void ensembleSumaTest() {

		// Usamos el historial de cartas

		ArrayList<Carta> cartasHistorial = new ArrayList<Carta>(); // Salieron más cartas numéricas
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.ROJO));
		cartasHistorial.add(new CartaNumerica(1, Colores.AZUL));
		cartasHistorial.add(new CartaNumerica(1, Colores.VERDE));
		cartasHistorial.add(new CartaNumerica(1, Colores.AMARILLO));
		cartasHistorial.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));

		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaContarColoresMasFrecuente());
		reglas0.add(new ReglaCompararTiposCartasMenosFrecuente());

		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaNumerica(1, Colores.ROJO)); // La primera regla escoge esto
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaNumerica(1, Colores.VERDE));
		cartas.add(new CartaNumerica(1, Colores.AMARILLO));
		cartas.add(new CartaNumerica(1, Colores.AZUL));
		cartas.add(new CartaAccion(new MasDos(), Colores.ROJO)); // La segunda escoge esto

		// PESOS
		// Pos 0: 0.6923077 + 0.16666667
		// Pos 6: 0.07692308  + 1 <- GANADOR

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(new EnsembleSuma()); // Se asigna el ensemble

		assertEquals(6, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), cartasHistorial));
	}
	
	/**
	 * Test para verificar que el ensemble de ranking funciona correctamente
	 */
	@Test
	public void ensembleRankingTest() {


		// Se prepara el jugador
		ArrayList<Regla> reglas0 = new ArrayList<Regla>();
		reglas0.add(new ReglaPriorizarCartasRobar());
		reglas0.add(new ReglaPriorizarCartasAccionNoComodin());


		JugadorAutomatico jugador0 = new JugadorAutomatico("Jugador0", reglas0);

		// Se preparan las cartas
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new CartaNumerica(1, Colores.ROJO)); 
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR)); // Pos 2
		cartas.add(new CartaNumerica(1, Colores.ROJO));
		cartas.add(new CartaAccion(new MasDos(), Colores.NOCOLOR)); // Pos 4

		// POSICIONES
		// Pos 4: Segundo y primero
		// Pos 2: Primero y ultimo
		
		// A nivel global gana MasDos pese a estar más apartado de la mano del jugador

		jugador0.setCartasMano(cartas); // Se establecen las cartas en la mano
		jugador0.asignarEnsemble(new EnsembleRanking()); // Se asigna el ensemble

		
		assertEquals(4, jugador0.jugarTurno(new CartaNumerica(1, Colores.ROJO), null));
	}

}
