package main.java.algoritmoVoraz.ensembles;

import java.util.ArrayList;
import java.util.HashMap;

import main.java.algoritmoVoraz.VorazUNO;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.logica.juego.carta.Carta;

/**
 * Clase base que representa los diferentes tipos de ensembles que hay en la aplicación
 * @author Efrén García Valencia UO277189
 *
 */
public abstract class Ensemble {
	
	// ATRIBUTOS
	protected VorazUNO voraz;
	
	
	/**
	 * Constructor con un parámetro para almacenar las reglas compuestas
	 */
	public Ensemble() {
		this.voraz = new VorazUNO();
	}
	
	
	/**
	 * Método que ejecuta el ensemble y devuelve el resultado
	 * 
	 * @param cartas        Cartas en la mano del jugador
	 * @param cartaMedio    La carta del medio
	 * @param cartasJugadas Las cartas jugadas hasta el momento
	 * @param reglas Las reglas que se van a ejecutar
	 * @return int La posición de la carta a jugar
	 */
	public abstract int execute(ArrayList<Carta> cartas, Carta cartaMedio, 
			ArrayList<Carta> cartasJugadas, ArrayList<Regla> reglas);

	
	//********MÉTODOS AUXILIARES********
	
	
	/**
	 * Devuelve la posición de la carta que tenga mayor peso
	 * 
	 * @param S          ArrayList<Carta> las cartas a ponderar
	 * @param cartasMano las cartas de la mano para buscar la pos
	 * @param cartaMedio la carta del medio para evitar excepciones
	 * @return int
	 */
	protected int mayorPeso(ArrayList<Carta> S, ArrayList<Carta> cartasMano, Carta cartaMedio) {
		float maxPeso = -1000; // Valor a superar
		Carta cartaElegida = null;

		for (int i = 0; i < S.size(); i++) {
			// En caso de empate nos quedamos con la que tenga menor índice en el array
			if (S.get(i).getPeso() > maxPeso) {
				maxPeso = S.get(i).getPeso();
				cartaElegida = S.get(i);
			}
		}

		// Ahora que tenemos la carta elegida buscamos su posición original
		return posCartaConcreta(cartasMano, cartaElegida);
	}
	
	/**
	 * Devuelve la posición de la carta original jugada
	 * @param cartaMax La carta a jugar
	 * @param cartas El array de cartas
	 * @return int
	 */
	protected int posCartaOriginal(Carta cartaMax, ArrayList<Carta> cartas) {
		int pos = 0;
		for (Carta carta : cartas) {
			if (carta.equals(cartaMax))
				return pos;
			pos++; // Incrementamos la posición
		}
		return -1; // No coincide ninguna
	}
	
	/**
	 * Método que te devuelve el número que más se repita dentro de un array
	 * @param posiciones ArrayList<Integer>
	 * @return int
	 */
	protected int valorMaximoArray(ArrayList<Integer> posiciones) {
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
	protected void eliminarNegativos(ArrayList<Integer> posiciones) {
		for (int i = 0; i < posiciones.size(); i++) {
			if (posiciones.get(i).equals(-1)) {
				posiciones.remove(i);
			}
		}
	}
	
	
	
	/**
	 * Método que te sirve para establecer un array con las posiciones originales de
	 * las cartas
	 * 
	 * @param s          Las cartas ponderadas
	 * @param cartasMano Las cartas en la mano
	 * @return ArrayList<Integer>
	 */
	protected ArrayList<Integer> searchOriginalPos(ArrayList<Carta> s, ArrayList<Carta> cartasMano) {
		ArrayList<Integer> ranking = new ArrayList<Integer>();
		for (Carta c : s) {
			ranking.add(this.posCartaConcreta(cartasMano, c));
		}
		return ranking;
	}
	
	/**
	 * Método para hallar la posición original de una carta en la mano
	 * 
	 * @param cartasMano   Las cartas de la mano
	 * @param cartaElegida La carta elegida
	 * @return int
	 */
	protected int posCartaConcreta(ArrayList<Carta> cartasMano, Carta cartaElegida) {
		int pos = -1;
		for (int j = 0; j < cartasMano.size(); j++) {
			try {
				if (cartaElegida.equals(cartasMano.get(j))) {
					pos = j;
				}
			} catch (NullPointerException e) {
				System.out.println("Ha ocurrido un error");
			}
		}
		return pos;
	}


	/**
	 * Para devolver el JSON a aplicar 
	 * @return String
	 */
	public abstract String getJSON();
	
}