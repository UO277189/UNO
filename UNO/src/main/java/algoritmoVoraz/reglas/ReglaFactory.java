package main.java.algoritmoVoraz.reglas;


import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCambiarColorMedio;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCompararTiposCartasMasFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaCompararTiposCartasMenosFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaContarColoresMasFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaContarColoresMenosFrecuente;
import main.java.algoritmoVoraz.reglas.tipos.reglasHistorial.ReglaNoCambiarColorMedio;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaAzar;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaNoPriorizarCartasRobar;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPrimeraCarta;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartaAccion;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartaNumerica;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartasAccionNoComodin;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarCartasRobar;
import main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial.ReglaPriorizarComodines;

/**
 * Clase que representa una factoría para crear las reglas
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaFactory {
	
	/**
	 * Método que crea las reglas heurísticas del juego
	 * @param reglaString String
	 * @return Regla
	 */
	public static Regla crearRegla(String reglaString) {
		Regla regla = null;

		if (reglaString.contains("ReglaAzar"))
			regla = new ReglaAzar();
		
		if (reglaString.contains("ReglaPrimeraCarta"))
			regla = new ReglaPrimeraCarta();
		
		if (reglaString.contains("ReglaPriorizarCartaNumerica"))
			regla = new ReglaPriorizarCartaNumerica();
		
		if (reglaString.contains("ReglaPriorizarCartaAccion"))
			regla = new ReglaPriorizarCartaAccion();
		
		if (reglaString.contains("ReglaPriorizarCartasRobar"))
			regla = new ReglaPriorizarCartasRobar();
		
		if (reglaString.contains("ReglaPriorizarComodines"))
			regla = new ReglaPriorizarComodines();
		
		if (reglaString.contains("ReglaNoPriorizarCartasRobar"))
			regla = new ReglaNoPriorizarCartasRobar();
		
		if (reglaString.contains("ReglaPriorizarCartasAccionNoComodin"))
			regla = new ReglaPriorizarCartasAccionNoComodin();
		
		if (reglaString.contains("ReglaCambiarColorMedio"))
			regla = new ReglaCambiarColorMedio();
		
		if (reglaString.contains("ReglaNoCambiarColorMedio"))
			regla = new ReglaNoCambiarColorMedio();

		if (reglaString.contains("ReglaCompararTiposCartasMasFrecuente"))
			regla = new ReglaCompararTiposCartasMasFrecuente();

		if (reglaString.contains("ReglaCompararTiposCartasMenosFrecuente"))
			regla = new ReglaCompararTiposCartasMenosFrecuente();
		
		if (reglaString.contains("ReglaContarColoresMasFrecuente"))
			regla = new ReglaContarColoresMasFrecuente();

		if (reglaString.contains("ReglaContarColoresMenosFrecuente"))
			regla = new ReglaContarColoresMenosFrecuente();

		return regla;
	}

}
