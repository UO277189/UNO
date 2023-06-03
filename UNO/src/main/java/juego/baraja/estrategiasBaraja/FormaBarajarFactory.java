package main.java.juego.baraja.estrategiasBaraja;

import main.java.juego.baraja.estrategiasBaraja.estrategias.CartaACarta;
import main.java.juego.baraja.estrategiasBaraja.estrategias.MontonAMonton;

/**
 * Clase que representa una factoría para las diferentes formas de barajar
 * @author Efrén García Valencia UO277189
 *
 */
public class FormaBarajarFactory {
	
	/**
	 * Método que devuelve la forma de barajar
	 * @param tipo El tipo de forma de barajar
	 * @param parametroInicial El parámetro inicial 
	 * @param parametroAdicional El parámetro adicional
	 * @return FormaBarajar
	 */
	public static FormaBarajar crearFormaBarajar(String tipo, int parametroInicial, int parametroAdicional) {

		if (tipo.equals("CartaACarta")) {
			return new CartaACarta(parametroInicial);
		} else if (tipo.equals("MontonAMonton")) {
			return new MontonAMonton(parametroInicial, parametroAdicional);
		} else {
			return null;
		}
		
	}

}
