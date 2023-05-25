package algoritmoVoraz.reglas.reglasQueNoMiranHistorial;

import java.util.ArrayList;

import algoritmoVoraz.reglas.Regla;
import juego.carta.Carta;

/**
 * Clase que implementa la regla que prioriza las cartas +2
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class ReglaPriorizarMasDos implements Regla{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).toString().contains("+2")) {
				cartas.get(i).setPeso(1000);
			}
		}
	}

	@Override
	public String toString() {
		return "ReglaPriorizarMasDos";
	}
	
	
	
}
