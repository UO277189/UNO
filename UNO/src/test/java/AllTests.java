package test.java;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Clase que ejecuta todos los tests de la aplicación
 * @author Efrén García Valencia UO277189
 *
 */
@Suite
@SelectClasses({
	test.java.juego.BarajaTest.class,
	test.java.juego.JuegoTest.class,
	test.java.manejoEntradas.CSVTest.class,
	test.java.manejoEntradas.JSONTest.class,
	test.java.estrategias.EnsemblesTest.class,
	test.java.estrategias.EstrategiaBarajaTest.class,
	test.java.estrategias.ReglasHeuristicasTest.class,
})
public class AllTests {

	// Se ejecutan los tests
}
