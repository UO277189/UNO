package main.algoritmoVoraz.reglas.reglasQueNoMiranHistorial;

import java.util.ArrayList;

import main.algoritmoVoraz.reglas.Regla;
import main.juego.carta.Carta;

/**
 * Clase que implementa la regla que no prioriza las cartas +4
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaNoPriorizarMasCuatro implements Regla{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).toString().contains("+4")) {
				cartas.get(i).setPeso(-1);
			}
		}
	}

	@Override
	public String toString() {
		return "ReglaNoPriorizarMasCuatro";
	}
	
	
	
	
}
