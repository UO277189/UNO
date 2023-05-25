package algoritmoVoraz.reglas.reglasQueNoMiranHistorial;

import java.util.ArrayList;

import algoritmoVoraz.reglas.Regla;
import juego.carta.Carta;

/**
 * Clase que implementa una regla poniendo pesos aleatorios
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class ReglaAzar implements Regla {
	


	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		
		for (int i = 0; i < cartas.size(); i++) {
			// Escoge un n�mero aleatorio entre 1 y 10
		    int pesoAleatorio = (int) (Math.random()*10 + 1);
		    cartas.get(i).setPeso(pesoAleatorio);
		}
		
	}

	@Override
	public String toString() {
		return "ReglaAzar";
	}
	
	

}
