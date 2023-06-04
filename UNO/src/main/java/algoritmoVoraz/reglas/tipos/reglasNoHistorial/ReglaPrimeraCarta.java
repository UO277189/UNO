package main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.juego.carta.Carta;

/**
 * Clase que implementa una regla poniendo el mismo peso a todas las cartas, haciendo
 * que el jugador eche la primera que encuentre
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaPrimeraCarta implements Regla {
	


	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
		    cartas.get(i).setPeso(0); // Todas las cartas se quedan con el mismo peso
		}
		
	}

	@Override
	public String toString() {
		return "ReglaPrimeraCarta";
	}
	
	
}
