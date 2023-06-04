package main.java.algoritmoVoraz.reglas;


import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaNoPriorizarContarColores;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaNoPriorizarContarNumerosAcciones;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaPriorizarContarColores;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaPriorizarContarNumerosAcciones;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaAzar;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaNoPriorizarMasCuatro;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaNoPriorizarMasDos;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPrimeraCarta;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarMasCuatro;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarMasDos;

/**
 * Clase que representa una factoría para crear las reglas
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaFactory {
	
	/**
	 * Método que crea las reglas heurísticas del juegi
	 * @param reglaString String
	 * @return Regla
	 */
	public static Regla crearRegla(String reglaString) {
		Regla regla = null;

		if (reglaString.contains("ReglaAzar"))
			regla = new ReglaAzar();

		if (reglaString.contains("ReglaPriorizarMasCuatro"))
			regla = new ReglaPriorizarMasCuatro();

		if (reglaString.contains("ReglaNoPriorizarMasCuatro"))
			regla = new ReglaNoPriorizarMasCuatro();

		if (reglaString.contains("ReglaPriorizarMasDos"))
			regla = new ReglaPriorizarMasDos();

		if (reglaString.contains("ReglaNoPriorizarMasDos"))
			regla = new ReglaNoPriorizarMasDos();

		if (reglaString.contains("ReglaPriorizarContarColores"))
			regla = new ReglaPriorizarContarColores();

		if (reglaString.contains("ReglaNoPriorizarContarColores"))
			regla = new ReglaNoPriorizarContarColores();

		if (reglaString.contains("ReglaPriorizarContarNumerosAcciones"))
			regla = new ReglaPriorizarContarNumerosAcciones();

		if (reglaString.contains("ReglaNoPriorizarContarNumerosAcciones"))
			regla = new ReglaNoPriorizarContarNumerosAcciones();

		if (reglaString.contains("ReglaPrimeraCarta"))
			regla = new ReglaPrimeraCarta();
		
		return regla;
	}

}
