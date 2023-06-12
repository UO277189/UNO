package main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.logica.juego.carta.Carta;

/**
 * Clase que implementa la regla que prioriza las cartas +2
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaPriorizarMasDos implements Regla{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).toString().contains("+2")) {
				cartas.get(i).setPeso(10);
			}
		}
	}

	@Override
	public String toString() {
		return "ReglaPriorizarMasDos";
	}
	
	
	
}
