package main.manejoDatos;

import java.util.ArrayList;

import main.algoritmoVoraz.ensembles.Ensemble;
import main.juego.baraja.estrategiasBaraja.BarajarStrategy;
import main.juego.jugador.JugadorAbstract;

/**
 * Clase que almacena los parámetros de configuración que se van a cargar en la partida
 * @author Efrén García Valencia UO277189
 *
 */
public class Configuracion {
	
	// ATRIBUTOS
	
	private String nombreConfiguracion;
	private ArrayList<JugadorAbstract> jugadoresPartida;
	private BarajarStrategy estrategiaBaraja;
	private Ensemble ensemble;
	private int numeroPartidas;
	private boolean traza;
	
	
	/**
	 * Constructor con los parámetros necesarios para crear un objeto de configuración
	 * @param nombreConfiguracion El nombre de la configuración 
	 * @param jugadoresPartida Los jugadores de las partidas
	 * @param estrategiaBaraja La estrategia de la baraja
	 * @param ensemble El ensemble a aplicar
	 * @param numeroPartidas El número de partidas
	 * @param traza Para mostrar la traza
	 */
	public Configuracion(String nombreConfiguracion, ArrayList<JugadorAbstract> jugadoresPartida,
			BarajarStrategy estrategiaBaraja, Ensemble ensemble, int numeroPartidas, boolean traza) {
		super();
		this.nombreConfiguracion = nombreConfiguracion;
		this.jugadoresPartida = jugadoresPartida;
		this.estrategiaBaraja = estrategiaBaraja;
		this.ensemble = ensemble;
		this.numeroPartidas = numeroPartidas;
		this.traza = traza;
	}


	/**
	 * Devuelve el nombre de la configuración
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
	 * Devuelve el núemero de partidas
	 * @return int
	 */
	public int getNumeroPartidas() {
		return numeroPartidas;
	}

	/**
	 * Nos dice si se muestra la traza o no
	 * @return boolean
	 */
	public boolean getTraza() {
		return traza;
	}
	
	
	
	

}