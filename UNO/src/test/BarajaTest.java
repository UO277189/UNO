package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.juego.baraja.Baraja;
import main.juego.baraja.estrategiasBaraja.estrategias.CartaACarta;
import main.juego.carta.Carta;
import main.juego.carta.CartaAccion;
import main.juego.carta.CartaNumerica;
import main.juego.carta.acciones.tipos.CambiarSentido;
import main.juego.carta.acciones.tipos.MasCuatro;
import main.juego.carta.acciones.tipos.MasDos;
import main.juego.carta.acciones.tipos.QuitarTurno;
import main.juego.carta.colores.Colores;

/**
 * Clase que verifica que la baraja tenga los componentes que debe tener
 * y que se puede modificar para realizar pruebas más complejas
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class BarajaTest {
	

	// ATRIBUTOS
	private Baraja baraja;
	
	
	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@Before
	public void inicializar() {
		baraja = new Baraja(new CartaACarta(60)); // La estrategia a usar no afecta a esta clase de test
	}
	
	/**
	 * Test que verifica que la baraja por defecto se crea con todas las cartas del UNO que corresponda
	 */
	@Test
	public void barajaBaseTest() {
		
		// Cuando se llama al constructor se crea la baraja de cartas base del UNO
		// y se prepara para la partida
		
		
		assertEquals(baraja.getBarajaCartas().size(), 99);  // Todas las cartas -1
		assertEquals(baraja.getBarajaDescarte().size(), 1); // La carta del medio es la primera a descartar
		assertTrue(baraja.getCartaCentro() != null); // Hay una carta en el medio 
		
		
	    // Atributos adiciones para conocer la dimensión de la baraja
	    int cartasNumericas = 0;
	    int cartasCambiarSentido = 0;
	    int cartasMasDos = 0;
	    int cartasQuitarTurno = 0;
	    int cartasMasCuatro= 0;
	    
		ArrayList<Carta> cartas = baraja.getBarajaCartas();
		
		for (Carta carta : cartas ) {
			if (carta instanceof CartaNumerica) {
				cartasNumericas++;
			}
			if (carta instanceof CartaAccion) {
				if (((CartaAccion) carta).getAccion() instanceof CambiarSentido){
					cartasCambiarSentido++;		
				}
				if (((CartaAccion) carta).getAccion() instanceof QuitarTurno){
					cartasQuitarTurno++;		
				}
				if (((CartaAccion) carta).getAccion() instanceof MasCuatro){
					cartasMasCuatro++;		
				}
				if (((CartaAccion) carta).getAccion() instanceof MasDos){
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
	 * Test que verifica que al robar una carta en situaciones normales no hay inconsistencias
	 */
	@Test
	public void robarCartaTest() {

		// Parámetros previos
		int lengthBarajaPrevio = baraja.getBarajaCartas().size();
		Carta centroPrevia = baraja.getCartaCentro(); 
		
		// Robamos una carta
		Carta cartaRobada = baraja.robarCarta(); // Damos una carta
		int lengthBarajaPosterior = baraja.getBarajaCartas().size();
		
		// Establecemos la carta en el centro
		baraja.setCartaCentro(cartaRobada); // Marcamos una nueva carta en el centro
		
		// Comprobamos
		assertTrue(!centroPrevia.equals(cartaRobada)); // NO son iguales
		assertTrue(lengthBarajaPrevio == (lengthBarajaPosterior + 1)); // Hay una carta menos en el montón de robar
		assertEquals(baraja.getBarajaDescarte().size(), 2); // Hay una carta más en el montón de descarte
	}
	
	
	/**
	 * Test que verifica que al repartir cartas a los jugadores en situaciones normales no hay inconsistencias
	 */
	@Test
	public void repartirCartasTest() {
	
		// Parámetros previos
		int lengthBarajaPrevio = baraja.getBarajaCartas().size();
		Carta centroPrevia = baraja.getCartaCentro(); 

		// Repartimos las cartas
		ArrayList<Carta> darCartas = baraja.darCartas(10); // Repartimos 10 cartas
		int lengthBarajaPosterior = baraja.getBarajaCartas().size();
		
		// Comprobamos
		assertTrue(lengthBarajaPrevio == (lengthBarajaPosterior + 10)); // 10 cartas adicionales
		assertTrue(darCartas.size() == 10); // Se roban las cartas
		assertEquals(baraja.getBarajaDescarte().size(), 1); // No se incrementan las cartas de descarte
		assertTrue(centroPrevia.equals(baraja.getCartaCentro())); // La carta del centro se mantiene	
	}
	
	
	/**
	 * Test que verifica que se puede generar una baraja personalizada
	 */
	@Test
	public void barajaPersonalizadaTest() {

		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();
		
		// Meto 10 cartas en las cartas del montón
		for (int i = 0; i < 10; i++) {
			cartasMonton.add(new CartaNumerica (1, Colores.ROJO));
		}
		
		// Meto 5 cartas en el montón de robar
		for (int i = 0; i < 5; i++) {
			cartasRobar.add(new CartaNumerica (1, Colores.AZUL));
		}
		
		Carta cartaMedio = new CartaNumerica (1, Colores.AMARILLO);
		
		// Recreamos la baraja
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, cartaMedio);
		
		// Hacemos comprobaciones
		assertEquals(baraja.getBarajaCartas().size(), 10);  
		assertEquals(baraja.getBarajaDescarte().size(), 6);
		assertTrue(baraja.getCartaCentro().getColor() == Colores.AMARILLO); 
		assertTrue(baraja.getCartaCentro() instanceof CartaNumerica); 
		
		// Repetimos y ponemos la carta del medio a null ahora
		cartasRobar = new ArrayList<Carta>();
		for (int i = 0; i < 5; i++) {
			cartasRobar.add(new CartaNumerica (1, Colores.AZUL));
		}
		
		
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, null);
		
		// Hacemos comprobaciones
		assertEquals(baraja.getBarajaCartas().size(), 10);  
		assertEquals(baraja.getBarajaDescarte().size(), 5);
		assertTrue(baraja.getCartaCentro() == null); 
		
	}
	
	
	/**
	 * Test que verifica que con una baraja personalizada vacía hay acciones que no se pueden realizar
	 */
	@Test
	public void barajaPersonalizadaVaciaTest() {

		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();
	
		// Recreamos la baraja
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, null);
		
		// Hacemos comprobaciones
		assertEquals(baraja.hayCartaParaRobar(), false); // No se pueden robar cartas	
		try {
			baraja.robarCarta();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("No hay cartas", e.getMessage()); // Salta la excepción
		}
		assertEquals(baraja.darCartas(10).size(), 0); // No hay cartas para robar		
	}
	
	/**
	 * Test que verifica que si tenemos todas las cartas en el montón de descarte
	 * la baraja funciona correctamente
	 */
	@Test
	public void barajaPersonalizadaTodoDescarteTest() {
		
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();
		
		// Meto 5 cartas en el montón de robar
		for (int i = 0; i < 10; i++) {
			cartasRobar.add(new CartaNumerica (1, Colores.AZUL));
		}
	
		// Hacemos comprobaciones (hay que restaurar el estado con cada comprobación)
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, new CartaNumerica (1, Colores.AMARILLO));
		assertEquals(baraja.hayCartaParaRobar(), true); // Se pueden robar cartas	
		

		cartasMonton = new ArrayList<Carta>();
		cartasRobar = new ArrayList<Carta>();
		for (int i = 0; i < 10; i++) {
			cartasRobar.add(new CartaNumerica (1, Colores.AZUL));
		}
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, new CartaNumerica (1, Colores.AMARILLO));

		assertEquals(baraja.darCartas(6).size(), 6); // Reparte 6 cartas (quedan 4)
		assertEquals(baraja.darCartas(20).size(), 4); // Reparte 20 cartas (devuelve las que quedan)
		assertEquals(baraja.getBarajaCartas().size(), 0); 
		assertEquals(baraja.getBarajaDescarte().size(), 1); // Queda únicamente la carta del centro
		assertTrue(baraja.getCartaCentro().getColor() == Colores.AMARILLO); 
		assertTrue(baraja.getCartaCentro() instanceof CartaNumerica);  // Mantenemos la carta del centro (se saca y se recoge)		
	}
	
	/**
	 * Test que verifica que si no hay cartas suficientes para dar el sistema responde apropiadamente
	 */
	@Test
	public void barajaPersonalizadaNoHayCartasSuficienteseTest() {
	
		ArrayList<Carta> cartasMonton = new ArrayList<Carta>();
		ArrayList<Carta> cartasRobar = new ArrayList<Carta>();
		
		// Meto 5 cartas en el montón de robar
		for (int i = 0; i < 10; i++) {
			cartasRobar.add(new CartaNumerica (1, Colores.AZUL));
		}
		
		baraja.formarBarajaPersonalizada(cartasMonton, cartasRobar, new CartaNumerica (1, Colores.AMARILLO));
		baraja.darCartas(10); // Quitamos todas las cartas
		assertEquals(baraja.darCartas(20).size(), 0); // Reparte 20 cartas (no devuelve nada)	
		
		// En este punto solo debería estar la carta del medio y la baraja no sería operativa
		try {
			baraja.robarCarta();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("No hay cartas", e.getMessage()); // Si robo una carta falla
		}
		assertEquals(baraja.hayCartaParaRobar(), false); // No se pueden robar cartas	
		
	}
	
}
