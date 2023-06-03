package main.java.manejoDatos;

import java.util.ArrayList;

import main.java.algoritmoVoraz.ensembles.Ensemble;
import main.java.juego.baraja.estrategiasBaraja.FormaBarajar;
import main.java.juego.jugador.Jugador;

/**
 * Clase que almacena los parámetros de configuración que se van a cargar en la partida
 * @author Efrén García Valencia UO277189
 *
 */
public class Configuracion {
	
	// ATRIBUTOS
	
	private String nombreConfiguracion;
	private ArrayList<Jugador> jugadoresPartida;
	private FormaBarajar estrategiaBaraja;
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
	public Configuracion(String nombreConfiguracion, ArrayList<Jugador> jugadoresPartida,
			FormaBarajar estrategiaBaraja, Ensemble ensemble, int numeroPartidas, boolean traza) {
		super();
		this.nombreConfiguracion = nombreConfiguracion;
		this.jugadoresPartida = jugadoresPartida;
		this.estrategiaBaraja = estrategiaBaraja;
		this.ensemble = ensemble;
		this.numeroPartidas = numeroPartidas;
		this.traza = traza;
	}


	/**
	 * @return the nombreConfiguracion
	 */
	public String getNombreConfiguracion() {
		return nombreConfiguracion;
	}


	/**
	 * @return the jugadoresPartida
	 */
	public ArrayList<Jugador> getJugadoresPartida() {
		return jugadoresPartida;
	}


	/**
	 * @return the estrategiaBaraja
	 */
	public FormaBarajar getEstrategiaBaraja() {
		return estrategiaBaraja;
	}


	/**
	 * @return the ensemble
	 */
	public Ensemble getEnsemble() {
		return ensemble;
	}


	/**
	 * @return the numeroPartidas
	 */
	public int getNumeroPartidas() {
		return numeroPartidas;
	}


	/**
	 * @return the traza
	 */
	public boolean isTraza() {
		return traza;
	}

	

}