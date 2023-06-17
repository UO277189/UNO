package main.java.algoritmoVoraz.reglas.tipos.reglasNoHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.logica.juego.carta.Carta;
import main.java.logica.juego.carta.CartaNumerica;

/**
 * Clase que implementa una regla que prioriza las cartas numericas
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaPriorizarCartaNumerica implements Regla {

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i) instanceof CartaNumerica)
				cartas.get(i).setPeso(10);
		}
	}

	@Override
	public String toString() {
		return "ReglaPriorizarCartaNumerica";
	}

}
