package algoritmoVoraz.reglas.reglasQueNoMiranHistorial;

import java.util.ArrayList;

import algoritmoVoraz.reglas.ReglaStrategy;
import juego.carta.Carta;

/**
 * Clase que implementa la regla que prioriza las cartas +2
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaPriorizarMasDos implements ReglaStrategy{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).toString().contains("+2")) {
				cartas.get(i).setPeso(1000);
			} else {
			    cartas.get(i).setPeso(1);
			}
		}
	}
	
}
