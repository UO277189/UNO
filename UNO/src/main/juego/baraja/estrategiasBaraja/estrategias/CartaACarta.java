package main.juego.baraja.estrategiasBaraja.estrategias;

import java.util.ArrayList;

import main.juego.baraja.Baraja;
import main.juego.baraja.estrategiasBaraja.FormaBarajar;
import main.juego.carta.Carta;

/**
 * Clase que implementa la estrategia de barajar carta a carta
 * @author Efrén García Valencia UO277189
 *
 */

public class CartaACarta implements FormaBarajar{
	
	// ATRIBUTOS
	
	// Este parámetro indica el número de veces que se intercambian las cartas de posición
	private int cardsToExchange;
	
	
	// CONSTRUCTOR
	
	/**
	 * Constructor para el tipo de barajar CartaACarta
	 * @param cardsToExchange int
	 */
	public CartaACarta(int cardsToExchange) {
		this.cardsToExchange = cardsToExchange;
	}


	// MÉTODOS

	@Override
	public void execute(Baraja baraja) {
		
		// Antes que nada validamos que los parámetros sean correctos
		validarParametros();
		
		ArrayList<Carta> barajaCartas = baraja.getBarajaCartas();
		int size = barajaCartas.size() - 1;
		
		int i = 0;
		
		while (i < cardsToExchange) {
			
			int pos1 = (int) (Math.random() * size);
			int pos2 = (int) (Math.random() * size);
			
			Carta cardOne = barajaCartas.get(pos1);
			Carta cardTwo = barajaCartas.get(pos2);
			
			barajaCartas.set(pos1, cardTwo);
			barajaCartas.set(pos2, cardOne);
			
			i++;
		}
	
	}
	

	/**
	 * Método que valida que los parámetros sean correctos y saca una excepción en caso de 
	 * que no lo sean
	 */
	private void validarParametros() {
		if (this.cardsToExchange == 0) {
			throw new IllegalArgumentException("Las cartas a intercambiar no pueden ser 0");
		}
		if (this.cardsToExchange < 0) {
			throw new IllegalArgumentException("Las cartas a intercambiar no pueden ser negativas");
		}
	}


	@Override
	public String toString() {
		return "CartaACarta con " + cardsToExchange + " cartas para intercambiar";
	}


	@Override
	public String getJSON() {
		return  "        \"tipo\": \"" + "CartaACarta" + "\",\n" +
		        "        \"parametroInicial\": " + cardsToExchange + ",\n" +
		        "        \"parametroAdicional\": \"" + "no_aplica" + "\"";
	}


	/**
	 * @return Las cartas a intercambiar
	 */
	public int getCardsToExchange() {
		return cardsToExchange;
	}
	
	
	
	
	

}
