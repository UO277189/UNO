package main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.juego.carta.Carta;

/**
 * Clase que implementa la regla que prioriza los comodines
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaPriorizarComodines implements Regla{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).toString().contains("+4")) {
				cartas.get(i).setPeso(10);
			}
			if (cartas.get(i).toString().contains("CambiaColor")) {
				cartas.get(i).setPeso(9);
			}
		}
	}

	@Override
	public String toString() {
		return "ReglaPriorizarComodines";
	}
	
	
}
