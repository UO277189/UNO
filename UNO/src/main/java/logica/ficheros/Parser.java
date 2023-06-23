package main.java.logica.ficheros;

import java.io.File;

import main.java.logica.Configuracion;
import main.java.logica.GestionarJuegos;

/**
 * Clase común para todos los parsers del sistema
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public abstract class Parser {

	// ATRIBUTOS

	// Rutas
	protected String rutaMetricas = ".\\ficheros\\salidas\\";
	protected String rutaPartidas = ".\\ficheros\\salidas\\log\\";
	protected String rutaConfiguracion = ".\\";
	// Nombes de ficheros
	protected String nombreFicheroEntrada = "configuracion";
	protected String nombreFicheroEjemplos = "ejemplosBasicos";
	// Separador
	protected char separador = ';';

	/**
	 * Método para escribir datos en un fichero
	 * 
	 * @param nombreFichero   El nombre del fichero
	 * @param gestionarJuegos Framework con todos los datos utilizados
	 * @param configuracion	  La configuración a considerar
	 */
	public abstract void escribirDatos(String nombreFichero, GestionarJuegos gestionarJuegos,
			Configuracion configuracion);

	/**
	 * Para borrar un directorio
	 * 
	 * @param borrar El directorio a borrar
	 */
	public void deleteDirectorio(File directorio) {
		// No deja borrar un directorio directamente, tienes que ir fichero a fichero
		try {

			if (directorio.isDirectory()) {
				for (File file : directorio.listFiles()) {
					if (file.isDirectory()) {
						deleteDirectorio(file);
						file.delete();
						file.deleteOnExit();
					} else {
						if (file.isFile()) {
							file.delete();
							file.deleteOnExit();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error borrando los ficheros");
		} finally {
			directorio.delete();
			directorio.deleteOnExit();
		}
	}

}
