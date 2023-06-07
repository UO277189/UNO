package test.java.sistema;

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

	/**
	 * Para inicializar los parámetros que necesitaremos
	 */
	@BeforeEach
	public void inicializar() {}
	

	/**
	 * Test que verifica que al cargar un CSV que no tenga el formato adecuado salta un mensaje de error
	 */
	@Test
	public void prueba() {
		 // Redirigir la entrada y salida estándar
        String entrada = "4\n4\n5"; // Entrada simulada
        InputStream entradaOriginal = System.in;
        System.setIn(new ByteArrayInputStream(entrada.getBytes()));
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        PrintStream salidaOriginal = System.out;
        System.setOut(new PrintStream(salidaCapturada));

        // Ejecutar la aplicación
        try {
            JugarPartida.main(new String[]{});
        } catch (NoSuchElementException e) {
            Assertions.fail("La aplicación intentó leer más elementos de los proporcionados en la entrada.");
        }

        // Restaurar la entrada y salida estándar
        System.setIn(entradaOriginal);
        System.setOut(salidaOriginal);

        // Verificar la salida
        System.out.println(salidaCapturada.toString());

	}
	

}
