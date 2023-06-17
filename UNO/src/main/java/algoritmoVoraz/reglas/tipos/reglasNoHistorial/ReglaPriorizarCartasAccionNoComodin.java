package main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.logica.juego.carta.Carta;

/**
 * Clase que implementa una regla que prioriza las cartas de acción que no son comodines
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaPriorizarCartasAccionNoComodin implements Regla{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).toString().contains("+2")) {
				cartas.get(i).setPeso(10);
			}
			
			if (cartas.get(i).toString().contains("Turno saltado")) {
				cartas.get(i).setPeso(10);
			}
			
			if (cartas.get(i).toString().contains("Cambio de sentido")) {
				cartas.get(i).setPeso(10);
			}
		}
	}

	@Override
	public String toString() {
		return "ReglaPriorizarCartasAccionNoComodin";
	}
	
}