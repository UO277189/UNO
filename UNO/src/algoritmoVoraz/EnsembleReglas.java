package algoritmoVoraz;

import java.util.ArrayList;
import java.util.HashMap;

import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.ReglasCompuestas;
import juego.carta.Carta;

/**
 * Clase que lo que hace es recibe una lista de reglas a aplicar y desde ahí
 * aplica las restricciones que sean necesarias para obtener la carta a mostrar
 * en la ronda
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class EnsembleReglas {

	// ATRIBUTOS
	private ReglasCompuestas reglasCompuestas;

	/**
	 * Constructor con un parámetro para almacenar las reglas compuestas
	 * 
	 * @param reglasCompuestas Las reglas compuestas a almacenar
	 */
	public EnsembleReglas(ReglasCompuestas reglasCompuestas) {
		super();
		this.reglasCompuestas = reglasCompuestas;
	}

	/**
	 * Método que te devuelve la posición de la carta a jugar que haya sido más
	 * votada entre todas las reglas
	 * 
	 * @param cartas        Cartas en la mano del jugador
	 * @param cartaMedio    La carta del medio
	 * @param cartasJugadas Las cartas jugadas hasta el momento
	 * @return int La posición de la carta a jugar
	 */
	public int cartaPorVotacion(ArrayList<Carta> cartas, Carta cartaMedio, ArrayList<Carta> cartasJugadas) {

		// Variables locales
		VorazUno voraz = new VorazUno();
		ArrayList<Integer> posiciones = new ArrayList<Integer>();

		for (Regla regla : reglasCompuestas.getReglas()) { // Para cada regla buscamos añadir una posición
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
			this.eliminarNegativos(posiciones); // Puede que surja un -1 por una excepción anterior que haya que eliminar
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
	 * Método para elimitar valores negativos del array
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
