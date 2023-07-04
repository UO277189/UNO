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
	 * @param configuracion   La configuración a considerar
	 */
	public abstract void escribirDatos(String nombreFichero, GestionarJuegos gestionarJuegos,
			Configuracion configuracion);

	/**
	 * Para borrar un directorio
	 * 
	 * @param directorio El directorio a borrar
	 */
	public void deleteDirectorio(File directorio) {
		try {
			if (directorio.isDirectory()) {
				for (File file : directorio.listFiles()) {
					if (file.isDirectory()) {
						deleteDirectorio(file); // Si es un directorio se borra también
						if (file.getParent().equals(".\\ficheros\\salidas\\log")) {
							file.delete();
						}
					}
					if (file.isFile()) {
						file.delete();
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error borrando los ficheros");
		}
	}

}
