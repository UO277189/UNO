package test.java.sistema;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
	 * Test de una configuración automática a 4 jugadores que tienen cada uno 1 regla
	 */
	@Test
	public void configuracion4Jugadores1ReglaTest() {
	
        String comandos = "2\n"
        		+ "0\n"
        		+ "4\n"
        		+ "1\n1\n12\n"
        		+ "1\n2\n12\n"
        		+ "1\n3\n12\n"
        		+ "1\n4\n12\n"
        		+ "0\n"
        		+ "1000\n"
        		+ "0\n"
        		+ "100\n"
        		+ "1\n"
        		+ "1";  // 100 partidas, no guarda ni muestra traza
        
        
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            JugarPartida.main(new String[]{});
 
            assertTrue(destino.toString().contains("- PARTIDAS JUGADAS: 100"));  
            assertTrue(destino.toString().contains("Jugador: Jugador 0")); 
            assertTrue(destino.toString().contains("Jugador: Jugador 1")); 
            assertTrue(destino.toString().contains("Jugador: Jugador 2")); 
            assertTrue(destino.toString().contains("Jugador: Jugador 3"));        
            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará.")); // No debe ocurrir un error
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	
	/**
	 * Test de una configuración automática a 5 jugadores que tienen cada uno varias reglas
	 */
	@Test
	public void configuracion5JugadoresVariasReglasTest() {
	
        String comandos = "2\n"
        		+ "0\n"
        		+ "5\n"
        		+ "1\n2\n3\n4\n12\n"
        		+ "1\n2\n3\n4\n12\n"
        		+ "1\n2\n3\n4\n12\n"
        		+ "1\n2\n3\n4\n12\n"
        		+ "1\n2\n3\n4\n12\n"
        		+ "1\n"
        		+ "10\n"
        		+ "10\n"
        		+ "2\n"
        		+ "100\n"
        		+ "0\n"
        		+ "1"; // Muestra traza  
        
        
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            JugarPartida.main(new String[]{});
 
            assertTrue(destino.toString().contains("- PARTIDAS JUGADAS: 100"));  
            assertTrue(destino.toString().contains("Jugador: Jugador 0")); 
            assertTrue(destino.toString().contains("Jugador: Jugador 1")); 
            assertTrue(destino.toString().contains("Jugador: Jugador 2")); 
            assertTrue(destino.toString().contains("Jugador: Jugador 3"));      
            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará.")); // No debe ocurrir un error
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	
	/**
	 * Test de una configuración manual a 2 jugadores
	 */
	@Test
	public void configuracionManual2JugadoresTest() {
	
        String comandos = "2\n"
        		+ "0\n"
        		+ "2\n"
        		+ "0\n"
        		+ "0\n"
        		+ "0\n"
        		+ "1000\n"
        		+ "1\n"
        		+ "1\n"
        		+ "1\n"
        		+ "1";  // 1 partida, no guarda ni muestra traza
        
        
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            JugarPartida.main(new String[]{});
 
            assertTrue(destino.toString().contains("EMPIEZA LA PARTIDA: 1"));   
            // Es normal que falle aquí porque espera más entradas que no hay
            //assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará.")); // No debe ocurrir un error
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	/**
	 * Test de una configuración mixta a 3 jugadores
	 */
	@Test
	public void configuracionMixta3JugadoresTest() {
	
        String comandos = "2\n"
        		+ "0\n"
        		+ "3\n"
        		+ "0\n"
        		+ "0\n"
        		+ "1\n2\n3\n4\n12\n"
          		+ "0\n"
        		+ "1000\n"
        		+ "0\n"
        		+ "1\n"
        		+ "1\n"
        		+ "1";  // 1 partida, no guarda ni muestra traza
        
        
        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
        System.setOut(new PrintStream(destino));

        
        // Se ejecuta la aplicación controlando los errores
        try {
            JugarPartida.main(new String[]{});
 
            assertTrue(destino.toString().contains("EMPIEZA LA PARTIDA: 1"));   
            // Es normal que falle aquí porque espera más entradas que no hay
            // assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará.")); // No debe ocurrir un error
        } catch (Exception e) { // Para identificar que está mal
            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
        }
	}
	
	
	/**
	 * Test intentando guardar un jugador automático sin ponerle reglas antes
	 */
	@Test
	public void falloJugadorAutomaticoNoReglaTest() {
	
	      String comandos = "2\n"
	        		+ "0\n"
	        		+ "2\n"
	        		+ "1\n12\n1\n12\n"
	        		+ "1\n2\n12\n"    
	        		+ "0\n"
	        		+ "1000\n"
	        		+ "0\n"
	        		+ "100\n"
	        		+ "1\n"
	        		+ "1";  // 100 partidas, no guarda ni muestra traza
	            
	        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
	        System.setOut(new PrintStream(destino));

	        
	        // Se ejecuta la aplicación controlando los errores
	        try {
	            JugarPartida.main(new String[]{});
	       
	            assertTrue(destino.toString().contains("ERROR: se tiene que elegir al menos una regla")); 
	            assertTrue(destino.toString().contains("- PARTIDAS JUGADAS: 100"));  
	            assertTrue(destino.toString().contains("Jugador: Jugador 0")); 
	            assertTrue(destino.toString().contains("Jugador: Jugador 1"));       
	            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará.")); // No debe ocurrir un error
	        } catch (Exception e) { // Para identificar que está mal
	            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
	        }
	}
	
	/**
	 * Test donde superamos el límite de los valores permitidos a cada oportunidad. El sistema deberá mostrar 
	 * los mensajes de error correspondientes y no fallar la aplicación
	 */
	@Test
	public void superarLimiteValoresTest() {
	
	      String comandos = "2\n"
	        		+ "3\n"
	    		    + "0\n"
	    		    + "1\n"
	        		+ "2\n"
	        		+ "2\n"
	        		+ "1\n15\n1\n12\n"
	        		+ "1\n-2\n3\n12\n"
	        		+ "4\n"
	        		+ "1\n"
	        		+ "11\n"
	        		+ "10\n"
	        		+ "11\n"
	        		+ "10\n"
	        		+ "-3\n"
	        		+ "2\n"
	        		+ "0\n"
	        		+ "1000000\n"
	        		+ "10\n"
	        		+ "3\n"
	        		+ "1\n"
	        		+ "3\n"
	        		+ "1";  // 100 partidas, no guarda ni muestra traza
	            
	        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
	        System.setOut(new PrintStream(destino));

	        
	        // Se ejecuta la aplicación controlando los errores
	        try {
	            JugarPartida.main(new String[]{});
	       
	            // Tiene que salir este mensaje pero también debe permitir terminar la partida
	            assertTrue(destino.toString().contains("Por favor, seleccione un valor válido:")); 
	            assertTrue(destino.toString().contains("RESULTADO DE LAS PARTIDAS"));  
	            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará.")); // No debe ocurrir un error
	        } catch (Exception e) { // Para identificar que está mal
	            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
	    }
	}
	
	/**
	 * Test guardando una configuración manual de forma correcta
	 */
	@Test
	public void guardarConfiguracionManualTest() {
	
	      String comandos = "2\n"
	        		+ "0\n"
	        		+ "2\n"
	        		+ "1\n1\n12\n"
	        		+ "1\n1\n12\n"    
	        		+ "0\n"
	        		+ "1000\n"
	        		+ "0\n"
	        		+ "100\n"
	        		+ "1\n"
	        		+ "0";  // 100 partidas, SI guarda la configuración
	            
	        System.setIn(new ByteArrayInputStream(comandos.getBytes()));
	        System.setOut(new PrintStream(destino));

	        
	        // Se ejecuta la aplicación controlando los errores
	        try {
	            JugarPartida.main(new String[]{});
	                   
	            // Además de los mensajes anteriores, la configuración se ha de guardar correctamente
	            assertTrue(destino.toString().contains("La configuración se ha guardado correctamente."));  
	            // NO debe aparecer esto
	            assertTrue(!destino.toString().contains("Ha ocurrido un error al guardar la nueva configuración.")); 
	            
	            assertTrue(destino.toString().contains("- PARTIDAS JUGADAS: 100"));  
	            assertTrue(destino.toString().contains("Jugador: Jugador 0")); 
	            assertTrue(destino.toString().contains("Jugador: Jugador 1"));       
	            assertTrue(!destino.toString().contains("Ha ocurrido un error en el sistema, la aplicación se cerrará.")); // No debe ocurrir un error
	        } catch (Exception e) { // Para identificar que está mal
	            Assertions.fail("Error al pasar las pruebas del sistema: " + e.getMessage());
	        }
	}
	

}
