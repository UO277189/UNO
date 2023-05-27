package main.juego;

import java.util.ArrayList;

import main.algoritmoVoraz.ensembles.Ensemble;
import main.juego.baraja.estrategiasBaraja.BarajarStrategy;
import main.juego.jugador.JugadorAbstract;
import main.juego.jugador.JugadorAutomatico;
import main.juego.jugador.JugadorManual;

/**
 * Clase que recolecta múltiples partidas y permite realizar experimentos más
 * avanzados con los eurísticos Actúa como "framework" sobre el que poder
 * mostrar resultados estadísticos
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class GestionarJuegos {

	// Lista de juegos a ejecutar
	private ArrayList<Juego> juegos;

	// Lista de jugadores
	private ArrayList<JugadorAbstract> jugadores;

	// Estrategia para barajar
	private BarajarStrategy estrategia;

	// El ensemble que se va a aplicar
	private Ensemble ensemble;
	
	// Número de partidas
	private int numeroPartidas;

	// Para saber si mostrar la traza
	private boolean traza;

	// Estadísticos adicionales para mostrar si se descarta alguna partida
	private int partidasDescartadas;

	/**
	 * Constructor con los datos necesarios para jugar múltiples partidas
	 * 
	 * @param jugadores      La lista de jugadores
	 * @param estrategia     La estrategia a desarrollar
	 * @param ensemble       El ensemble que se va a aplicar
	 * @param numeroPartidas El número de partidas a jugar
	 */
	public GestionarJuegos(ArrayList<JugadorAbstract> jugadores, BarajarStrategy estrategia, Ensemble ensemble, int numeroPartidas) {
		this.jugadores = jugadores;
		this.estrategia = estrategia;
		this.numeroPartidas = numeroPartidas;
		if (hayJugadorManual(jugadores)) {
			// Si hay un jugador manual se muestran los datos por consola
			this.traza = true;
		}
		this.ensemble = ensemble;

		this.partidasDescartadas = 0;

		// Asignamos el ensemble a los jugadores
		asignarEnsembleJugadores(this.jugadores);
		
		// Hay que inicializar los arrays
		this.juegos = new ArrayList<Juego>();
	}
	
	/**
	 * Método que devuelve true si en el array de jugadores hay jugadores manuales
	 * @param jugadores La lista de jugadores 
	 * @return boolean true si hay jugador manual, false si no lo hay
	 */
	private boolean hayJugadorManual(ArrayList<JugadorAbstract> jugadores) {
		for (JugadorAbstract jugador : jugadores) {
			if (jugador instanceof JugadorManual) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Método para asignar el ensemble a los jugadores
	 * @param jugadores La lista de jugadores
	 */
	private void asignarEnsembleJugadores(ArrayList<JugadorAbstract> jugadores) {
		for (JugadorAbstract jugador : jugadores) {
			if (jugador instanceof JugadorAutomatico) {
				((JugadorAutomatico)jugador).asignarEnsemble(this.ensemble);
			}
		}
	}

	/**
	 * Método para iterar y jugar múltiples partidas
	 */
	public void jugarPartidas() {
		for (int i = 0; i < this.numeroPartidas; i++) {
			if (traza) {
				System.out.println("");
				System.out.println("EMPIEZA LA PARTIDA: " + (i + 1));
				System.out.println("");
			}
			jugarUnaPartida();
			System.out.println("Termina la partida " + i);
		}
	}

	/**
	 * Método para jugar una partida
	 */
	private void jugarUnaPartida() {
		ArrayList<JugadorAbstract> jugadoresPartida = this.clonarJugadores();

		Juego uno = new Juego(jugadoresPartida, estrategia, traza);
		while (uno.finPartida() == false) {
			if (!uno.isReachedMaxRondas()) {
				uno.jugarRonda();
			} else {
				// Si llega al límite salimos
				uno.limpiarJugadores(); // Borramos los datos de estos jugadores para evitar contaminar el estudio
				this.partidasDescartadas++; // Se incrementa el número de partidas descartadas
				break;
			}
		}
		// Se guardan los resultados de las partidas
		uno.resultadoPartida();
		
		// Al acabar la partida almacenamos los resultados
		this.juegos.add(uno);
	}

	/**
	 * Método para clonar los jugadores en las partidas y evitar así que se pien las
	 * referencias
	 * 
	 * @return ArrayList<JugadorAbstract> Los jugadores clonados
	 */
	private ArrayList<JugadorAbstract> clonarJugadores() {
		ArrayList<JugadorAbstract> jugadoresClon = new ArrayList<JugadorAbstract>();
		JugadorAbstract jugadorAIncluir = null;
		for (JugadorAbstract jugador : this.jugadores) {
			if (jugador instanceof JugadorAutomatico) {
				jugadorAIncluir = new JugadorAutomatico(jugador.getNombreJugador(),
						((JugadorAutomatico) jugador).getReglas());
			}
			if (jugador instanceof JugadorManual) {
				jugadorAIncluir = new JugadorManual(jugador.getNombreJugador());
			}

			jugadoresClon.add(jugadorAIncluir);
		}
		
		// Asignamos el ensemble a los jugadores
		asignarEnsembleJugadores(jugadoresClon);
		return jugadoresClon;
	}

	/**
	 * Método para mostrar los resultados de las partidas
	 */
	public void mostrarResultados() {

		// Detalles de las partidas totales jugadas

		System.out.println("");
		System.out.println("RESULTADO DE LAS PARTIDAS ");

		System.out.println("");
		System.out.println("- PARTIDAS TOTALES: " + this.numeroPartidas);
		System.out.println("- PARTIDAS JUGADAS: " + (this.numeroPartidas - this.partidasDescartadas));
		System.out.println("- PARTIDAS DESCARTADAS: " + this.partidasDescartadas);
		System.out.println("");

		// Primero se almacena la información
		for (Juego juego : juegos) {
			this.guardarInformacion(juego);
		}

		// Posteriormente se muestra
		System.out.println("");
		for (JugadorAbstract jugador : this.jugadores) {
			jugador.informacionJugadorPartida();
		}

		// También nos interesa saber quién ganó más partidas
		this.ganadorPartidas();
	}

	/**
	 * Método que nos dice quién ha sido el jugador que ha ganado más partidas
	 */
	private void ganadorPartidas() {
		// PROBLEMA: ¿Y si dos o más personas ganaron más partidas?
		ArrayList<JugadorAbstract> jugadoresGanadores = new ArrayList<JugadorAbstract>();

		// Iteramos una vez para hallar el máximo
		int partidasGanadas = this.getPartidasGanadas();

		// Iteramos otra vez para buscar si hay algún empate
		for (JugadorAbstract jugador : this.jugadores) {
			if (jugador.getVecesQueHaGanado() == partidasGanadas) {
				jugadoresGanadores.add(jugador);
			}
		}

		// Mostramos los datos

		System.out.println("");

		if (jugadoresGanadores.size() == 1) {
			System.out.println("GANADOR DE TODAS LAS PARTIDAS: " + jugadoresGanadores.get(0).getNombreJugador());
		} else {
			System.out.println("GANADORES DE LAS PARTIDAS: ");
			for (JugadorAbstract jugador : jugadoresGanadores) {
				System.out.println("	- " + jugador.getNombreJugador());
			}
		}

		System.out.println("");
		System.out.println("NÚMERO DE PARTIDAS GANADAS: " + partidasGanadas);
		System.out.println("");
	}

	/**
	 * Devuelve el número de partidas ganadas más alto
	 * 
	 * @return int El número de partidas ganadas más alto
	 */
	private int getPartidasGanadas() {
		int partidasGanadas = 0;
		for (JugadorAbstract jugador : this.jugadores) {
			if (jugador.getVecesQueHaGanado() >= partidasGanadas) {
				partidasGanadas = jugador.getVecesQueHaGanado();
			}
		}
		return partidasGanadas;
	}

	/**
	 * Método que almacena en el array de jugadores creado previamente todos los
	 * datos de las partidas
	 * 
	 * @param juego El juego a considerar
	 */
	private void guardarInformacion(Juego juego) {
		// La idea es ir acumulando en el array vacío todos los datos de los jugadores
		for (int i = 0; i < juego.getJugadores().size(); i++) {
			// Vamos incrementando parámetro a parámetro
			this.jugadores.get(i).aumentarDatosJugador(juego.getJugadores().get(i));
		}
	}

	/**
	 * Método que devuelve al ganador de todas las partidas
	 * 
	 * @return ArrayList<JugadorAbstract> El array con el ganador/ganadores de todas las partidas
	 */
	public ArrayList<JugadorAbstract> ganadoresDeTodasLasPartidas() {	
		// PROBLEMA: ¿Y si dos o más personas ganaron más partidas?
		ArrayList<JugadorAbstract> jugadoresGanadores = new ArrayList<JugadorAbstract>();

		// Iteramos una vez para hallar el máximo
		int partidasGanadas = this.getPartidasGanadas();

		// Iteramos otra vez para buscar si hay algún empate
		for (JugadorAbstract jugador : this.jugadores) {
			if (jugador.getVecesQueHaGanado() == partidasGanadas) {
				jugadoresGanadores.add(jugador);
			}
		}
		
		return jugadoresGanadores;
	}

	/**
	 * Devuelve los jugadores de la partidas
	 * 
	 * @return ArrayList<JugadorAbstract>
	 */
	public ArrayList<JugadorAbstract> getJugadores() {
		return jugadores;
	}

	/**
	 * Devuelve los juegos a evaluar
	 * @return ArrayList<Juego>
	 */
	public ArrayList<Juego> getJuegos() {
		return juegos;
	}


}
