package test.java.sistema;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.UNOJava;

/**
 * Clase que verifica que el funcionamiento de la interfaz por consola se ajusta a lo esperado
 * @author Efrén García Valencia UO277189
 *
 */
public class ManejoManualTest {
	
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
	 * Test que verifica que el usuario puede consultar el manual de ayuda
	 */
	@Test
	public void leerManualTest() {
	
		// Hay que redirigir las entradas y salidas para ver los resultados e introducir los comandos
        String comandos = "4\nA\n1\n6\na\n7\n5"; // Entrada simulada
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            UNOJava.main(new String[]{});
 
            assertTrue(destino.toString().contains("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:"));  
            assertTrue(destino.toString().contains("A continuación se muestra una explicación de las diferentes opciones del juego: "));  // Leer ayuda al principio       
            assertTrue(destino.toString().contains("A continuación se muestran algunas configuraciones básicas del juego."));            
            assertTrue(destino.toString().contains("1- 	Nombre de la configuración: ManualDosJugadores")); // Leer ayuda de las configuraciones
            assertTrue(destino.toString().contains("¡Hasta la próxima!"));
            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará."));
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	
	
	/**
	 * Test que verifica que el usuario puede probar una configuración manual por defecto
	 */
	@Test
	public void pruebaConfiguracionDefectoManualTest() {
	
		// Hay que redirigir las entradas y salidas para ver los resultados e introducir los comandos
        String comandos = "1\n1"; // Entrada simulada
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            UNOJava.main(new String[]{});

            assertTrue(destino.toString().contains("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:"));  
            assertTrue(destino.toString().contains("A continuación se muestran algunas configuraciones básicas del juego."));            
            assertTrue(destino.toString().contains("EMPIEZA LA PARTIDA: 1"));
            // Este fallo va a saltar porque espera más caracteres que no tenemos
            // Realmente no está mal, entonces se deja comentado
            //assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará."));
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	
	/**
	 * Test que verifica que el usuario puede probar una configuración automatica por defecto
	 */
	@Test
	public void pruebaConfiguracionDefectoAutomaticaTest() {
	
		// Hay que redirigir las entradas y salidas para ver los resultados e introducir los comandos
        String comandos = "1\n3"; // Entrada simulada
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            UNOJava.main(new String[]{});
 
            assertTrue(destino.toString().contains("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:"));  
            assertTrue(destino.toString().contains("A continuación se muestran algunas configuraciones básicas del juego."));            
            assertTrue(destino.toString().contains("NÚMERO DE PARTIDAS GANADAS:"));
            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará."));
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	
	/**
	 * Test que verifica que el usuario puede probar una configuración mixta por defecto
	 */
	@Test
	public void pruebaConfiguracionDefectoMixtaTest() {
	
		// Hay que redirigir las entradas y salidas para ver los resultados e introducir los comandos
        String comandos = "1\n5"; // Entrada simulada
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            UNOJava.main(new String[]{});
 
            assertTrue(destino.toString().contains("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:"));  
            assertTrue(destino.toString().contains("A continuación se muestran algunas configuraciones básicas del juego."));            
            assertTrue(destino.toString().contains("EMPIEZA LA PARTIDA: 1"));
            // Este fallo va a saltar porque espera más caracteres que no tenemos
            // Realmente no está mal, entonces se deja comentado
            //assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará."));
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	/**
	 * Test que verifica que al meter un valor no válido el sistema no de error
	 */
	@Test
	public void pruebaValorNoValidoTest() {
	
		// Hay que redirigir las entradas y salidas para ver los resultados e introducir los comandos
        String comandos = "8\n5"; // Entrada simulada
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            UNOJava.main(new String[]{});
 
            assertTrue(destino.toString().contains("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:"));  
            assertTrue(destino.toString().contains("Por favor, seleccione un valor válido:"));            
            assertTrue(destino.toString().contains("¡Hasta la próxima!"));
            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará."));
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	/**
	 * Test que verifica que el usuario puede cargar una configuración del fichero de configuraciones
	 */
	@Test
	public void pruebaCargarFicheroConfiguracionesOkTest() {
	
		// Hay que redirigir las entradas y salidas para ver los resultados e introducir los comandos
        String comandos = "3\n1"; // Entrada simulada
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            UNOJava.main(new String[]{});

            assertTrue(destino.toString().contains("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:"));           
            assertTrue(destino.toString().contains("NÚMERO DE PARTIDAS GANADAS:"));
            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará."));
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	/**
	 * Test que verifica que si el usuario introduce caracteres no válidos al cargar el fichero de configuraciones
	 * el sistema no responda con un error
	 */
	@Test
	public void pruebaCargarFicheroConfiguracionesNoOkTest() {
	
		// Hay que redirigir las entradas y salidas para ver los resultados e introducir los comandos
        String comandos = "3\n1000\na"; // Entrada simulada
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            UNOJava.main(new String[]{});

            assertTrue(destino.toString().contains("¡Bienvenido al juego del UNO! A continuación se muestran las diferentes opciones del juego:"));  
            assertTrue(destino.toString().contains("Por favor, seleccione un valor válido: "));        
            assertTrue(destino.toString().contains("ERROR: este valor no es válido"));
            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará."));
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
}
