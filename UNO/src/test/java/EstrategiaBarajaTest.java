package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.juego.baraja.Baraja;
import main.java.juego.baraja.estrategiasBaraja.estrategias.CartaACarta;
import main.java.juego.baraja.estrategiasBaraja.estrategias.MontonAMonton;
import main.java.juego.carta.Carta;
import main.java.juego.carta.CartaAccion;
import main.java.juego.carta.CartaNumerica;
import main.java.juego.carta.acciones.tipos.CambiarSentido;
import main.java.juego.carta.acciones.tipos.MasCuatro;
import main.java.juego.carta.acciones.tipos.MasDos;
import main.java.juego.carta.acciones.tipos.QuitarTurno;

/**
 * Clase de test que verifica que las estrategias de la baraja funcionen
 * correctamente
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class EstrategiaBarajaTest {

	// ATRIBUTOS
	private Baraja baraja;

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {
	}

	/**
	 * Test que verifica que al ejecutar la estrategia CartaACarta con parámetros
	 * adecuados el sistema funciona correctamente
	 */
	@Test
	public void cartaACartaOKTest() {

		baraja = new Baraja(new CartaACarta(60)); // Carta a carta con 60 cartas a intercambiar

		// Esto es igual que el método barajaBaseTest pero al ser un método aleatorio
		// realmente existe
		// una posibilidad de que se cambien todas las cartas y acaben todas en el mismo
		// sitio.
		// Entonces lo que se comprueba aquí es que la baraja siga siendo consistente.

		// Cuando se llama al constructor se crea la baraja de cartas base del UNO y se
		// ejecuta la estrategia

		assertEquals(baraja.getBarajaCartas().size(), 99); // Todas las cartas -1
		assertEquals(baraja.getBarajaDescarte().size(), 1); // La carta del medio es la primera a descartar
		assertTrue(baraja.getCartaCentro() != null); // Hay una carta en el medio

		// Atributos adiciones para conocer la dimensión de la baraja
		int cartasNumericas = 0;
		int cartasCambiarSentido = 0;
		int cartasMasDos = 0;
		int cartasQuitarTurno = 0;
		int cartasMasCuatro = 0;

		ArrayList<Carta> cartas = baraja.getBarajaCartas();

		for (Carta carta : cartas) {
			if (carta instanceof CartaNumerica) {
				cartasNumericas++;
			}
			if (carta instanceof CartaAccion) {
				if (((CartaAccion) carta).getAccion() instanceof CambiarSentido) {
					cartasCambiarSentido++;
				}
				if (((CartaAccion) carta).getAccion() instanceof QuitarTurno) {
					cartasQuitarTurno++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasCuatro) {
					cartasMasCuatro++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasDos) {
					cartasMasDos++;
				}

			}
		}

		// Comprobaciones finales
		assertEquals(cartasNumericas, 71); // La primera carta que se saca es una numérica
		assertEquals(cartasCambiarSentido, 8);
		assertEquals(cartasMasDos, 8);
		assertEquals(cartasQuitarTurno, 8);
		assertEquals(cartasMasCuatro, 4);
	}

	/**
	 * Test que verifica que al ejecutar la estrategia MontonAMonton con parámetros
	 * adecuados el sistema funciona correctamente
	 */
	@Test
	public void montonAMontonOKTest() {

		baraja = new Baraja(new MontonAMonton(10, 5)); // 10 cartas en el montón, 5 montones a intercambiar

		// Esto es igual que el método barajaBaseTest pero al ser un método aleatorio
		// realmente existe
		// una posibilidad de que se cambien todas las cartas y acaben todas en el mismo
		// sitio.
		// Entonces lo que se comprueba aquí es que la baraja siga siendo consistente.

		// Cuando se llama al constructor se crea la baraja de cartas base del UNO y se
		// ejecuta la estrategia

		assertEquals(baraja.getBarajaCartas().size(), 99); // Todas las cartas -1
		assertEquals(baraja.getBarajaDescarte().size(), 1); // La carta del medio es la primera a descartar
		assertTrue(baraja.getCartaCentro() != null); // Hay una carta en el medio

		// Atributos adiciones para conocer la dimensión de la baraja
		int cartasNumericas = 0;
		int cartasCambiarSentido = 0;
		int cartasMasDos = 0;
		int cartasQuitarTurno = 0;
		int cartasMasCuatro = 0;

		ArrayList<Carta> cartas = baraja.getBarajaCartas();

		for (Carta carta : cartas) {
			if (carta instanceof CartaNumerica) {
				cartasNumericas++;
			}
			if (carta instanceof CartaAccion) {
				if (((CartaAccion) carta).getAccion() instanceof CambiarSentido) {
					cartasCambiarSentido++;
				}
				if (((CartaAccion) carta).getAccion() instanceof QuitarTurno) {
					cartasQuitarTurno++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasCuatro) {
					cartasMasCuatro++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasDos) {
					cartasMasDos++;
				}

			}
		}

		// Comprobaciones finales
		assertEquals(cartasNumericas, 71); // La primera carta que se saca es una numérica
		assertEquals(cartasCambiarSentido, 8);
		assertEquals(cartasMasDos, 8);
		assertEquals(cartasQuitarTurno, 8);
		assertEquals(cartasMasCuatro, 4);
	}

	/**
	 * Test que comprueba que al ejecutar la estrategia CartaACarta con parámetros
	 * no correctos saltan las excepciones adecuadas
	 */
	@Test
	public void cartaACartaNoOKTest() {

		// Parámetro 0
		try {
			baraja = new Baraja(new CartaACarta(0));
		} catch (IllegalArgumentException e) {
			assertEquals("Las cartas a intercambiar no pueden ser 0", e.getMessage()); 
		}

		// Parámetro negativo
		try {
			baraja = new Baraja(new CartaACarta(-1));
		} catch (IllegalArgumentException e) {
			assertEquals("Las cartas a intercambiar no pueden ser negativas", e.getMessage()); 
		}
	}
	
	/**
	 * Test que comprueba que al ejecutar la estrategia montonAMonton con parámetros
	 * no correctos saltan las excepciones adecuadas
	 */
	@Test
	public void montonAMontonNoOkTest() {

		// Parámetro 1 0
		try {
			baraja = new Baraja(new MontonAMonton(0, 5));
		} catch (IllegalArgumentException e) {
			assertEquals("El número de cartas del montón no puede ser 0", e.getMessage()); 
		}

		// Parámetro 1 negativo
		try {
			baraja = new Baraja(new MontonAMonton(-1, 5));
		} catch (IllegalArgumentException e) {
			assertEquals("El número de cartas del montón no puede ser negativo", e.getMessage()); 
		}
		
		// Parámetro 2 0
		try {
			baraja = new Baraja(new MontonAMonton(10, 0));
		} catch (IllegalArgumentException e) {
			assertEquals("Los montones no pueden ser 0", e.getMessage()); 
		}

		// Parámetro 2 negativo
		try {
			baraja = new Baraja(new MontonAMonton(10, -1));
		} catch (IllegalArgumentException e) {
			assertEquals("Los montones no pueden ser negativos", e.getMessage()); 
		}
	}
	

	/**
	 * Test que comprueba que al ejecutar la estrategia CartaACarta con más cartas 
	 * de las que tiene la baraja el sistema responde correctamente
	 */
	@Test
	public void cartaACartaMasCartasQueBarajaTest() {

		baraja = new Baraja(new CartaACarta(500)); // Carta a carta con 500 cartas a intercambiar
		assertEquals(baraja.getBarajaCartas().size(), 99); // Todas las cartas -1
		assertEquals(baraja.getBarajaDescarte().size(), 1); // La carta del medio es la primera a descartar
		assertTrue(baraja.getCartaCentro() != null); // Hay una carta en el medio

		// Atributos adiciones para conocer la dimensión de la baraja
		int cartasNumericas = 0;
		int cartasCambiarSentido = 0;
		int cartasMasDos = 0;
		int cartasQuitarTurno = 0;
		int cartasMasCuatro = 0;

		ArrayList<Carta> cartas = baraja.getBarajaCartas();

		for (Carta carta : cartas) {
			if (carta instanceof CartaNumerica) {
				cartasNumericas++;
			}
			if (carta instanceof CartaAccion) {
				if (((CartaAccion) carta).getAccion() instanceof CambiarSentido) {
					cartasCambiarSentido++;
				}
				if (((CartaAccion) carta).getAccion() instanceof QuitarTurno) {
					cartasQuitarTurno++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasCuatro) {
					cartasMasCuatro++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasDos) {
					cartasMasDos++;
				}

			}
		}

		// Comprobaciones finales
		assertEquals(cartasNumericas, 71); // La primera carta que se saca es una numérica
		assertEquals(cartasCambiarSentido, 8);
		assertEquals(cartasMasDos, 8);
		assertEquals(cartasQuitarTurno, 8);
		assertEquals(cartasMasCuatro, 4);
	}
	

	/**
	 * Test que verifica que al ejecutar la estrategia MontonAMonton con muchos montones de 1 carta
	 * el sistema responde adecuadamente
	 */
	@Test
	public void montonAMontonUnaCartaPorMontonTest() {

		baraja = new Baraja(new MontonAMonton(1, 500)); // 1 cartas en el montón, 500 montones a intercambiar

		
		assertEquals(baraja.getBarajaCartas().size(), 99); // Todas las cartas -1
		assertEquals(baraja.getBarajaDescarte().size(), 1); // La carta del medio es la primera a descartar
		assertTrue(baraja.getCartaCentro() != null); // Hay una carta en el medio

		// Atributos adiciones para conocer la dimensión de la baraja
		int cartasNumericas = 0;
		int cartasCambiarSentido = 0;
		int cartasMasDos = 0;
		int cartasQuitarTurno = 0;
		int cartasMasCuatro = 0;

		ArrayList<Carta> cartas = baraja.getBarajaCartas();

		for (Carta carta : cartas) {
			if (carta instanceof CartaNumerica) {
				cartasNumericas++;
			}
			if (carta instanceof CartaAccion) {
				if (((CartaAccion) carta).getAccion() instanceof CambiarSentido) {
					cartasCambiarSentido++;
				}
				if (((CartaAccion) carta).getAccion() instanceof QuitarTurno) {
					cartasQuitarTurno++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasCuatro) {
					cartasMasCuatro++;
				}
				if (((CartaAccion) carta).getAccion() instanceof MasDos) {
					cartasMasDos++;
				}

			}
		}

		// Comprobaciones finales
		assertEquals(cartasNumericas, 71); // La primera carta que se saca es una numérica
		assertEquals(cartasCambiarSentido, 8);
		assertEquals(cartasMasDos, 8);
		assertEquals(cartasQuitarTurno, 8);
		assertEquals(cartasMasCuatro, 4);
	}

}
