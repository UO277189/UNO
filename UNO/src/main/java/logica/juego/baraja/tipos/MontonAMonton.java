package main.java.logica.juego.baraja.tipos;

import java.util.ArrayList;

import main.java.logica.juego.baraja.Baraja;
import main.java.logica.juego.baraja.FormaBarajar;
import main.java.logica.juego.carta.Carta;

/**
 * Clase que implementa la estrategia de barajar montón a montón
 * 
 * @author Efrén García Valencia UO277189
 *
 */

public class MontonAMonton implements FormaBarajar {

	// ATRIBUTOS

	// Este parámetro indica las cartas que formarán los montones a intercambiar
	private int cardInLot;

	// Este parámetro indica la cantidad de montones que se van a formar
	private int lotToExchange;

	// CONSTRUCTOR

	/**
	 * Constructor para la estrategia de barajar MontonAMonton
	 * @param cardInLot Las cartas en un montón
	 * @param lotToExchange La candtidad de montones que se van a formar
	 */
	public MontonAMonton(int cardInLot, int lotToExchange) {
		this.cardInLot = cardInLot;
		this.lotToExchange = lotToExchange;
	}

	// MÉTODOS

	@Override
	public void execute(Baraja baraja) {

		// Antes que nada validamos que los parámetros sean correctos
		validarParametros();
		
		ArrayList<Carta> barajaCartas = baraja.getBarajaCartas();
		int size = barajaCartas.size() - 1;

		int i = 0;

		// PROBLEMA: si no hay suficientes cartas para cambiar entre montones entonces no se baraja montón a montón
		// Si no, genera un bucle infinito
		if (size % cardInLot == 0 && size > cardInLot) { // Si hay bastantes cartas para cambiar entre sí
			
			while (i < this.lotToExchange) {

				// Hay que coger dos bloques de n cartas aleatorias dentro de la baraja e
				// intercambiarlos
				// Hace faltan unas comprobaciones adicionales

				int pos1 = (int) (Math.random() * size);
				int pos2 = (int) (Math.random() * size);

				// Hay que hacer muchas comprobaciones para no salirse del rango
				while (Math.abs(pos2 - pos1) < cardInLot || pos1 > (size - cardInLot) || pos2 > (size - cardInLot)
						|| Math.abs(pos2 - pos1) > cardInLot && pos1 > (size - cardInLot)
						|| Math.abs(pos2 - pos1) > cardInLot && pos2 > (size - cardInLot)) {
					pos1 = (int) (Math.random() * size);
					pos2 = (int) (Math.random() * size);

				}

				for (int j = 0; j < this.cardInLot; j++) {
					Carta cardA = barajaCartas.get(pos1 + j);
					Carta cardB = barajaCartas.get(pos2 + j);

					barajaCartas.set(pos1 + j, cardB);
					barajaCartas.set(pos2 + j, cardA);
				}

				i++;
			}
		} else {
			// En su lugar optamos por la estrategia similar a la de carta a carta y barajamos al azar
			while (i < size) {
				
				int pos1 = (int) (Math.random() * size);
				int pos2 = (int) (Math.random() * size);
				
				Carta cardOne = barajaCartas.get(pos1);
				Carta cardTwo = barajaCartas.get(pos2);
				
				barajaCartas.set(pos1, cardTwo);
				barajaCartas.set(pos2, cardOne);
				
				i++;
			}
		}
	}
	
	

	/**
	 * Método que valida que los parámetros sean correctos y saca una excepción en caso de 
	 * que no lo sean
	 */
	private void validarParametros() {
		if (this.cardInLot == 0) {
			throw new IllegalArgumentException("El número de cartas del montón no puede ser 0");
		}
		if (this.cardInLot < 0) {
			throw new IllegalArgumentException("El número de cartas del montón no puede ser negativo");
		}
		if (this.lotToExchange == 0) {
			throw new IllegalArgumentException("Los montones no pueden ser 0");
		}
		if (this.lotToExchange < 0) {
			throw new IllegalArgumentException("Los montones no pueden ser negativos");
		}
	}
	

	@Override
	public String toString() {
		return "MontonAMonton con " + cardInLot + " cartas en los montones y " + lotToExchange + " montones para intercambiar";
	}
	
	@Override
	public String getJSON() {
		return  "        \"tipo\": \"" + "MontonAMonton" + "\",\n" +
		        "        \"parametroInicial\": " + cardInLot + ",\n" +
		        "        \"parametroAdicional\": " + lotToExchange;
	}

	/**
	 * @return Las cartas por montón
	 */
	public int getCardInLot() {
		return cardInLot;
	}

	/**
	 * @return Los montones a intercambiar
	 */
	public int getLotToExchange() {
		return lotToExchange;
	}
	
	
	

}
