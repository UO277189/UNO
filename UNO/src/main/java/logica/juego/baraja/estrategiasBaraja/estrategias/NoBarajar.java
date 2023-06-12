package main.java.logica.juego.baraja.estrategiasBaraja.estrategias;

import main.java.logica.juego.baraja.Baraja;
import main.java.logica.juego.baraja.estrategiasBaraja.FormaBarajar;


/**
 * Estrategia de barajar que consiste en no barajar las cartas
 * @author Efrén García Valencia UO277189
 *
 */
public class NoBarajar implements FormaBarajar{
	

	// MÉTODOS

	@Override
	public void execute(Baraja baraja) {
		// No hace nada
	}


	@Override
	public String toString() {
		return "No se barajan las cartas";
	}


	@Override
	public String getJSON() {
		return  "        \"tipo\": \"" + "NoBarajar" + "\",\n" +
		        "        \"parametroInicial\": " + "no_aplica" + ",\n" +
		        "        \"parametroAdicional\": \"" + "no_aplica" + "\"";
	}




}
