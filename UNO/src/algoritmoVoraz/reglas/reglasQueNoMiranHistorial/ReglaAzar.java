package algoritmoVoraz.reglas.reglasQueNoMiranHistorial;

import java.util.ArrayList;

import algoritmoVoraz.reglas.ReglaStrategy;
import juego.carta.Carta;

/**
 * Clase que implementa una regla poniendo pesos aleatorios
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaAzar implements ReglaStrategy {
	


	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		
		for (int i = 0; i < cartas.size(); i++) {
			// Escoge un número aleatorio entre 1 y 10
		    int pesoAleatorio = (int) (Math.random()*10 + 1);
		    cartas.get(i).setPeso(pesoAleatorio);
		}
		
	}

}
