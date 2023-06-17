package main.java.algoritmoVoraz.reglas.tipos.reglasHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.logica.juego.carta.Carta;

public class ReglaCambiarColorMedio implements Regla {

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		Carta cartaMedio = historial.get(historial.size()-1); // Obtenemos la carta del medio
		
		for (int i = 0; i < cartas.size(); i++) {
			if (!cartas.get(i).getColor().equals(cartaMedio.getColor())) { // Si tienen un color distinto
				cartas.get(i).setPeso(10);
			}
		}
		
	}
	
	@Override
	public String toString() {
		return "ReglaCambiarColorMedio";
	}
	

}
