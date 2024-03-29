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
	test.java.unitarias.juego.BarajaTest.class,
	test.java.unitarias.juego.JuegoTest.class,
	test.java.unitarias.manejoEntradas.CSVTest.class,
	test.java.unitarias.manejoEntradas.JSONTest.class,
	test.java.unitarias.estrategias.EnsemblesTest.class,
	test.java.unitarias.estrategias.EstrategiaBarajaTest.class,
	test.java.unitarias.estrategias.ReglasTest.class,
	test.java.sistema.ManejoManualTest.class,
	test.java.sistema.ConfiguracionConsolaTest.class,
	test.java.sistema.PartidaManualTest.class,
})
public class AllTests {

	// Se ejecutan los tests
}
