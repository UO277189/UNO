package juego.baraja.estrategiasBaraja;

import java.util.ArrayList;

import juego.baraja.Baraja;
import juego.carta.Carta;

/**
 * Clase que implementa la estrategia de barajar carta a carta
 * @author Efrén García Valencia UO277189
 *
 */
public class CartaACarta implements BarajarStrategy{
	
	// ATRIBUTOS
	
	// Este parámetro indica el número de veces que se intercambian las cartas de posición
	private int cardsToExchange;
	
	
	// CONSTRUCTOR
	
	public CartaACarta(int cardsToExchange) {
		this.cardsToExchange = cardsToExchange;
	}


	// MÉTODOS

	@Override
	public void execute(Baraja baraja) {
		
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
	
	

}
