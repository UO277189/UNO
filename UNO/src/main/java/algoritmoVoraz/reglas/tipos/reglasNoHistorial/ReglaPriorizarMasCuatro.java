package main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.juego.carta.Carta;

/**
 * Clase que implementa la regla que prioriza las cartas +4
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaPriorizarMasCuatro implements Regla{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).toString().contains("+4")) {
				cartas.get(i).setPeso(1000);
			}
		}
	}

	@Override
	public String toString() {
		return "ReglaPriorizarMasCuatro";
	}
	
	
	
}