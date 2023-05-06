package main;

import java.util.ArrayList;

import algoritmoVoraz.reglas.ReglasCompuestas;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaAzar;
import juego.Juego;
import juego.barajar.BarajarStrategy;
import juego.barajar.CartaACarta;
import juego.jugador.JugadorAbstract;
import juego.jugador.JugadorAlgoritmo;
/**
 * Clase que ejecuta el juego del UNO para una sóla partida
 * @author Efrén García Valencia UO277189
 *
 */
public class JugarUnaPartida {
	
	/**
	 * Método para ejecutar el programa
	 * @param args String[]
	 */
    public static void main(String[] args) {
    	
    	// PASO 1: pones a los jugadores
    	ArrayList<JugadorAbstract> jugadores = new ArrayList<JugadorAbstract>();
    	jugadores.add(new JugadorAlgoritmo("PriorizarContarColores", new ReglasCompuestas(new ReglaAzar())));
    	jugadores.add(new JugadorAlgoritmo("Azar",  new ReglasCompuestas(new ReglaAzar())));
    	jugadores.add(new JugadorAlgoritmo("NoPriorizarContarNumerosAcciones",  new ReglasCompuestas(new ReglaAzar())));
    	jugadores.add(new JugadorAlgoritmo("PriorizarContarMasCuatro",  new ReglasCompuestas(new ReglaAzar())));
    	//jugadores.add(new JugadorManual("Manual));

    	// PASO 2: indicas la estrategia de bararjar
    	
    	// El parámetro son el número de cartas a intercambiar
		BarajarStrategy estrategiaA = new CartaACarta(60);
    	
    	// El primer parámetro son las cartas del montón, el segundo es el número de montones a intercambiar
      	// BarajarStrategy estrategiaB = new MontonAMonton(5,10);
    	
    	// PASO 3: juegas al UNO indicando los parámetros
    	jugarUNO(jugadores, estrategiaA);
    }

    
    /**
     * Método privado estatico para jugar al UNO
     * @param jugadores ArrayList<JugadorUNO>
     * @param bararjarStrategy La estrategia para barajar
     */
	private static void jugarUNO(ArrayList<JugadorAbstract> jugadores, BarajarStrategy bararjarStrategy) {

    	Juego uno = new Juego(jugadores, bararjarStrategy);
    	
    	// Mientras no se alcance el fin de la partida
    	while (uno.finPartida() == false) {
    		
    		// Muestra la baraja
    		//uno.mostrarBaraja();
    		
    		// Se juega la ronda
    		uno.jugarRonda();
    	}
    			
    	// Fin de la partida
    	uno.resultadoPartida();
		
	}
}
