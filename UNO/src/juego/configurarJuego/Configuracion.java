package juego.configurarJuego;

import java.util.ArrayList;

import algoritmoVoraz.ensembles.Ensemble;
import juego.baraja.BarajarStrategy;
import juego.jugador.JugadorAbstract;

/**
 * Clase que almacena los par�metros de configuraci�n que se van a cargar en la partida
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class Configuracion {
	
	// ATRIBUTOS
	
	private String nombreConfiguracion;
	private ArrayList<JugadorAbstract> jugadoresPartida;
	private BarajarStrategy estrategiaBaraja;
	private Ensemble ensemble;
	private int numeroPartidas;
	private boolean mostrarTraza;
	
	
	/**
	 * Constructor con los par�metros necesarios para crear un objeto de configuraci�n
	 * @param nombreConfiguracion El nombre de la configuraci�n 
	 * @param jugadoresPartida Los jugadores de las partidas
	 * @param estrategiaBaraja La estrategia de la baraja
	 * @param ensemble El ensemble a aplicar
	 * @param numeroPartidas El n�mero de partidas
	 * @param mostrarTraza Para mostrar la traza
	 */
	public Configuracion(String nombreConfiguracion, ArrayList<JugadorAbstract> jugadoresPartida,
			BarajarStrategy estrategiaBaraja, Ensemble ensemble, int numeroPartidas, boolean mostrarTraza) {
		super();
		this.nombreConfiguracion = nombreConfiguracion;
		this.jugadoresPartida = jugadoresPartida;
		this.estrategiaBaraja = estrategiaBaraja;
		this.ensemble = ensemble;
		this.numeroPartidas = numeroPartidas;
		this.mostrarTraza = mostrarTraza;
	}


	/**
	 * Devuelve el nombre de la configuraci�n
	 * @return String
	 */
	public String getNombreConfiguracion() {
		return nombreConfiguracion;
	}

	/**
	 * Devuelve los jugadores de la partida
	 * @return ArrayList<JugadorAbstract>
	 */
	public ArrayList<JugadorAbstract> getJugadoresPartida() {
		return jugadoresPartida;
	}

	/**
	 * Devuelve la estrategia para barajar
	 * @return BarajarStrategy
	 */
	public BarajarStrategy getEstrategiaBaraja() {
		return estrategiaBaraja;
	}

	/**
	 * Devuelve el ensemble a aplicar
	 * @return Ensemble
	 */
	public Ensemble getEnsemble() {
		return ensemble;
	}

	/**
	 * Devuelve el n�emero de partidas
	 * @return int
	 */
	public int getNumeroPartidas() {
		return numeroPartidas;
	}

	/**
	 * Nos dice si se muestra la traza o no
	 * @return boolean
	 */
	public boolean isMostrarTraza() {
		return mostrarTraza;
	}
	
	
	
	

}
