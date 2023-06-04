package main.java.algoritmoVoraz.reglas;


import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCompararTiposCartasMasFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCompararTiposCartasMenosFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaContarColoresMasFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaContarColoresMenosFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaAzar;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaNoPriorizarMasCuatro;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaNoPriorizarMasDos;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPrimeraCarta;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartasRobar;
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

		if (reglaString.contains("ReglaContarColoresMasFrecuente"))
			regla = new ReglaContarColoresMasFrecuente();

		if (reglaString.contains("ReglaContarColoresMenosFrecuente"))
			regla = new ReglaContarColoresMenosFrecuente();

		if (reglaString.contains("ReglaCompararTiposCartasMasFrecuente"))
			regla = new ReglaCompararTiposCartasMasFrecuente();

		if (reglaString.contains("ReglaCompararTiposCartasMenosFrecuente"))
			regla = new ReglaCompararTiposCartasMenosFrecuente();

		if (reglaString.contains("ReglaPrimeraCarta"))
			regla = new ReglaPrimeraCarta();
		
		if (reglaString.contains("ReglaPriorizarCartasRobar"))
			regla = new ReglaPriorizarCartasRobar();
		
		return regla;
	}

}
