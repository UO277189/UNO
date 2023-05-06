package algoritmoVoraz;

import java.util.ArrayList;
import java.util.HashMap;

import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que lo que hace es recibe una lista de reglas a aplicar y desde ah�
 * aplica las restricciones que sean necesarias para obtener la carta a mostrar
 * en la ronda
 * 
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class EnsembleReglas {

	// ATRIBUTOS
	private ReglasCompuestas reglasCompuestas;

	/**
	 * Constructor con un par�metro para almacenar las reglas compuestas
	 * 
	 * @param reglasCompuestas Las reglas compuestas a almacenar
	 */
	public EnsembleReglas(ReglasCompuestas reglasCompuestas) {
		super();
		this.reglasCompuestas = reglasCompuestas;
	}

	/**
	 * M�todo que te devuelve la posici�n de la carta a jugar que haya sido m�s
	 * votada entre todas las reglas
	 * 
	 * @param cartas        Cartas en la mano del jugador
	 * @param cartaMedio    La carta del medio
	 * @param cartasJugadas Las cartas jugadas hasta el momento
	 * @return int La posici�n de la carta a jugar
	 */
	public int cartaPorVotacion(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas) {

		// Variables locales
		VorazUno voraz = new VorazUno();
		ArrayList<Integer> posiciones = new ArrayList<Integer>();

		for (Regla regla : reglasCompuestas.getReglas()) { // Para cada regla buscamos a�adir una posici�n
			int newPos = voraz.aplicarAlgoritmoVoraz(cartas, cartaMedio, cartasJugadas, regla);
			if (newPos != -1) { // Si puedes no robar
				posiciones.add(newPos);
			}
		}
		return valorMaximoArray(posiciones);
	}

	private int valorMaximoArray(ArrayList<Integer> posiciones) {
		if (posiciones.isEmpty()) {
			// Esto significa que no se puede echar nada y por tanto hay que robar una carta
			return -1;
		} else {
			this.eliminarNegativos(posiciones); // Puede que surja un -1 por una excepci�n anterior que haya que eliminar
			HashMap<Integer, Integer> mapPositions = new HashMap<Integer, Integer>(); // Con un HashMap podemos ir guardando las posiciones
			int contadorMax = 0;
			int valorMax = 0;
			for(int i = 0; i < posiciones.size(); i++) {
				int contador = 1;
				if (mapPositions.containsKey(posiciones.get(i))){
					contador = mapPositions.get(posiciones.get(i)) + 1; // Almacenamos el valor en el HashMap
				}
				mapPositions.put(posiciones.get(i), contador); // Actualiza el contador
			    if(contador > contadorMax) { 
			    	contadorMax = contador;
			        valorMax = posiciones.get(i); // Nuevo valor a sacar
			      }
			    }
			return valorMax; // En caso de empate sacamos el primero
		}
	}

	/**
	 * M�todo para elimitar valores negativos del array
	 * @param posiciones ArrayList<Integer>
	 */
	private void eliminarNegativos(ArrayList<Integer> posiciones) {
		for (int i = 0; i < posiciones.size(); i++) {
			if (posiciones.get(i).equals(-1)) {
				posiciones.remove(i);
			}
		}
	}
}
