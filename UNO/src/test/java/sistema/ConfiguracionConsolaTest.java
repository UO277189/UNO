package test.java.sistema;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.JugarPartida;

/**
 * Clase que verifica que el comportamiento de cargar las configuraciones por consola sea el correcto
 * @author Efrén García Valencia UO277189
 *
 */
public class ConfiguracionConsolaTest {

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
            JugarPartida.main(new String[]{});
 
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
	

}
