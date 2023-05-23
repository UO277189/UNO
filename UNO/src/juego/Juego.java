package juego;

import java.util.ArrayList;
import juego.acciones.AccionStrategy;
import juego.baraja.Baraja;
import juego.baraja.BarajarStrategy;
import juego.carta.Carta;
import juego.carta.CartaAccion;
import juego.enumerados.Colores;
import juego.jugador.JugadorAbstract;

/**
 * Clase que maneja la l�gica de una partida del juego UNO
 * 
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class Juego {

	// ATRIBUTOS

	// La baraja de juego
	private Baraja barajaUNO;

	// Para saber el orden en el que vas (true == sentido horario)
	private boolean orden;

	// Los jugadores de la partida
	private ArrayList<JugadorAbstract> jugadores;

	// Para saber cual es el turno actual
	private int turnoActual;

	// Par�metro adicional para saber las cartas que tiene que robar el siguiente
	// jugador
	private int cardsToPick;

	// Este p�rametro sirve si hace falta mostrar toda la informacion del tablero
	boolean allInfo = true;

	// Este p�rametro recoge las cartas jugadas hasta el momento para mas adelante
	// ponderar las cartas
	private ArrayList<Carta> historial;

	// Este par�metro nos dice si mostramos la traza del juego o no
	private boolean traza;

	// Este par�metro determina cu�ntas rondas se han jugado actualmente
	private int rondas;

	// Este par�metro limita el n�mero de turnos en una ronda para evitar
	// situaciones de bucle infinito
	private int maxRondas = 10000;

	// Este par�metro indica si se ha llegado al m�ximo de rondas para incluir o no
	// los datos
	private boolean hasReachedMaxRondas = false;

	// Este par�metro sirve para guardar toda la informaci�n que se muestra por
	// consola de una ronda
	private String logRonda = "";

	// Este par�metro sirve para guardar toda la informaci�n que se muestra por
	// consola de toda la partida
	private ArrayList<String> logPartidas = new ArrayList<String>();

	/**
	 * Constructor que se usa para iniciar una partida
	 * 
	 * @param jugadores ArrayList<Jugador>
	 * @param barajar   BarajarStrategy
	 */
	public Juego(ArrayList<JugadorAbstract> jugadores, BarajarStrategy barajar) {

		// Establecemos los jugadores
		this.jugadores = jugadores;

		// Creamos la baraja
		this.barajaUNO = new Baraja(barajar);

		// El orden por defecto es de izquiera a derecha (lo marcamos con un true)
		this.orden = true;

		// Al principio del juego almacenamos tambien la primera carta colocada
		historial = new ArrayList<Carta>();
		this.historial.add(this.barajaUNO.getCartaCentro());

		// Por defecto mostramos la traza
		this.traza = true;

		// Preparamos el juego
		this.comenzarRonda();

	}

	/**
	 * Construxtor que se usa para iniciar una partida indicando adem�s si se ve la
	 * traza o no
	 * 
	 * @param jugadores ArrayList<Jugador>
	 * @param barajar   BarajarStrategy
	 * @param traza     boolean
	 */
	public Juego(ArrayList<JugadorAbstract> jugadores, BarajarStrategy barajar, boolean traza) {
		// Establecemos los jugadores
		this.jugadores = jugadores;

		// Creamos la baraja
		this.barajaUNO = new Baraja(barajar);

		// El orden por defecto es de izquiera a derecha (lo marcamos con un true)
		this.orden = true;

		// Al principio del juego almacenamos tambien la primera carta colocada
		historial = new ArrayList<Carta>();
		this.historial.add(this.barajaUNO.getCartaCentro());

		// Indicamos si mostramos la traza o no
		this.traza = traza;

		// Preparamos el juego
		this.comenzarRonda();

	}

	/**
	 * M�todo para empezar cada ronda del juego
	 */
	private void comenzarRonda() {

		// Barajamos cartas a cada jugador
		this.darCartasJugadores(7);

		// Al principio no hay que robar cartas adicionales
		this.cardsToPick = 0;

		// Para indicar el turno actual se elegir� un jugador al azar
		this.turnoActual = (int) (Math.random() * this.getJugadores().size());

		// Se juega la primera ronda
		this.rondas++;

		// Mensaje textual para saber qui�n empieza la partida
		this.guardarDatos("El jugador " + this.getJugadores().get(turnoActual).getNombreJugador() 
				+ " empieza la partida");
		}

	/**
	 * Para repartir las cartas a cada jugador al principio de cada ronda
	 * 
	 * @param cartasRepartidas int
	 */
	private void darCartasJugadores(int cartasRepartidas) {
		for (JugadorAbstract jugador : this.getJugadores()) {
			jugador.setCartasMano(this.barajaUNO.darCartas(cartasRepartidas));
		}
	}

	/**
	 * M�todo para establecer un nuevo turno
	 */
	public void nuevoTurno() {
		boolean haLLegadoAlFinal = turnoActual == this.getJugadores().size() - 1;

		if (this.orden == true && haLLegadoAlFinal == false) {
			// Caso 1: baraja en orden, NO llegamos al final y empieza el primero otra vez
			turnoActual++;
		} else if (this.orden == false && (turnoActual == 0) == false) {
			// Caso 2: baraja en sentido opuesto, no ha llegado al principip
			turnoActual--;
		} else if (this.orden == true && haLLegadoAlFinal == true) {
			// Caso 3: baraja al final, estamos en orden
			turnoActual = 0;
		} else if (this.orden == false && turnoActual == 0) {
			// Caso 4: baraja al principio, estamos en desorden
			turnoActual = this.getJugadores().size() - 1;
		}
	}

	/**
	 * M�todo para cambiar el orden del juego
	 */
	public void cambiarOrden() {
		if (this.orden == true) {
			this.orden = false;
		} else {
			this.orden = true;
		}
	}

	/**
	 * M�tod para echar una carta en el medio
	 * 
	 * @param cartaPos int
	 */
	private void echarCarta(int cartaPos) {

		Carta aEchar = this.getJugadorActual().getCartaPos(cartaPos);

		// Quitamos la carta el jugador y la echamos al centro
		this.getJugadorActual().quitarCarta(cartaPos);
		this.barajaUNO.setCartaCentro(aEchar);

		// Informaci�n textual de la carta jugada
		this.guardarDatos(
				"El jugador " + this.getJugadorActual().getNombreJugador() + " juega la carta " + aEchar.toString());

		// Si tiene una sola carta el jugador tiene que decir UNO
		if (this.getJugadorActual().getCartasMano().size() == 1) {
			this.guardarDatos("El jugador " + this.getJugadorActual().getNombreJugador() + " ha cantado �UNO!");
			this.getJugadorActual().incrementVecesQueHaCantadoUno();
		}

		// Aplicamos el efecto de la carta
		this.aplicarEfecto();

		// A�adimos tambien la carta al array
		historial.add(aEchar);
	}

	/**
	 * M�todo para robar una carta para el jugador actual
	 */
	private void robarCartaBaraja() {

		if (this.barajaUNO.hayCartaParaRobar()) {
			// Extraemos la nueva carta
			Carta cartaRobada = this.barajaUNO.robarCarta();
			// La ponemos en el jugador actual
			this.getJugadorActual().robarUnaCarta(cartaRobada);
		} else {
			this.guardarDatos("No se pueden robar cartas");
		}

	}

	/**
	 * M�todo que establece un nuevo color en el centro
	 */
	public void nuevoColorCentro() {

		Colores[] coloresCopia = { Colores.VERDE, Colores.ROJO, Colores.AMARILLO, Colores.AZUL };

		this.guardarDatos("El jugador actual procede a escoger un nuevo color:");
		for (int i = 0; i < coloresCopia.length; i++) {
			this.guardarDatos("   " + i + " -  " + coloresCopia[i].toString());
		}

		int colorElegido = this.getJugadorActual().elegirNuevoColor(coloresCopia.length);

		this.barajaUNO.setColorCartaMedio(coloresCopia[colorElegido]);
	}

	/**
	 * M�todo que aplica el efecto de una carta
	 */
	private void aplicarEfecto() {

		Carta enMedio = this.barajaUNO.getCartaCentro();

		if (enMedio instanceof CartaAccion) {
			AccionStrategy accion = ((CartaAccion) enMedio).getAccion();
			accion.execute(this); // Se ejecuta la acci�n asociada
		}
	}

	/**
	 * M�todo para incrementar las cartas a robar
	 * 
	 * @param increment int
	 */
	public void incrementCardsToPick(int increment) {
		this.cardsToPick += increment;
	}

	/**
	 * M�todo para robar las cartas por efecto de otros jugadores
	 */
	private void robarCartasPorEfecto() {

		// El jugador afectado, que es el siguietne
		JugadorAbstract actual = this.getJugadorActual();
		// Las cartas que tiene que robar
		ArrayList<Carta> sumaCartas;

		sumaCartas = this.barajaUNO.darCartas(this.cardsToPick);
		actual.robarVariasCartas(sumaCartas);

		this.guardarDatos("El jugador " + this.getJugadorActual().getNombreJugador() + " tiene que robar "
				+ this.cardsToPick + " cartas");

		this.cardsToPick = 0; // Se reinicia el contador de catas a robar

	}

	/**
	 * M�todo para saber si el jugador actual tiene alguna carta que pueda echar
	 * 
	 * @param nuevaCarta int
	 * @return boolean
	 */
	private boolean tieneCartaParaEchar(int nuevaCarta) {

		// Las cartas a comparar
		Carta enMedio = this.barajaUNO.getCartaCentro();
		Carta aEchar = this.getJugadorActual().getCartaPos(nuevaCarta);

		if (enMedio.sePuedeEchar(aEchar)) {
			return true; // Si se puede echar devuelve true
		} else {
			return false;
		}
	}

	/**
	 * M�todo para saber si el jugador actual tiene cartas que pueda echar
	 * 
	 * @param nuevaCarta int
	 * @return boolean
	 */
	private boolean tieneCartasParaEchar() {
		for (int i = 0; i < this.numCartasJugadorActivo(); i++) {
			if (this.tieneCartaParaEchar(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * M�todo para saber si el jugador no cumple con las normas y no responde frente
	 * a un +2/+4
	 * 
	 * @param nuevaCarta int
	 * @return boolean
	 */
	private boolean comprobarEfectoMasDosYCuatro(int nuevaCarta) {

		Carta aEchar = this.getJugadorActual().getCartaPos(nuevaCarta);
		Carta enMedio = this.barajaUNO.getCartaCentro();

		boolean isCheating = false;

		if (enMedio.puedeNoRobar(aEchar)) {
			isCheating = false;
		} else {
			isCheating = true;
		}
		return isCheating;
	}

	/**
	 * M�todo para robar cartas al principio de la ronda si no tiene nada en la mano
	 * que pueda echar
	 */
	private void revisarSiCogeCartas() {
		boolean doesItHaveIt = tieneMasDosOCuatro();
		if (doesItHaveIt == false) {
			this.guardarDatos("El jugador " + this.getJugadorActual().getNombreJugador()
					+ " no tiene ninguna carta para acumular el efecto");

			this.robarCartasPorEfecto();

		}
	}

	/**
	 * M�todo para saber si tiene una carta +2/+4 que se pueda aprovechar
	 * 
	 * @return boolean
	 */
	private boolean tieneMasDosOCuatro() {
		Carta enMedio = this.barajaUNO.getCartaCentro();
		for (int i = 0; i < this.numCartasJugadorActivo(); i++) {
			Carta aEchar = this.getJugadorActual().getCartaPos(i);
			if (enMedio.puedeNoRobar(aEchar)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * M�todo para saber el jugador que ha ganado la ronda de la partida
	 * 
	 * @return JugadorUNO
	 */
	private JugadorAbstract quienGanaPartida() {
		JugadorAbstract jugadorGanador = null;
		for (JugadorAbstract jugador : this.getJugadores()) {
			if (jugador.quedanCartas() == false) {
				jugadorGanador = jugador;
				// El jugador ha ganado la partida y se tiene que guardar
				jugadorGanador.incrementVecesQueHaGanado();
			}
		}
		return jugadorGanador;
	}
	
	
	/**
	 * M�todo que devuelve el jugador ganador de la partida
	 * @return JugadorUNO
	 */
	private JugadorAbstract jugadorGanador() {
		JugadorAbstract jugadorGanador = null;
		for (JugadorAbstract jugador : this.getJugadores()) {
			if (jugador.quedanCartas() == false) {
				jugadorGanador = jugador;
			}
		}
		return jugadorGanador;
	}

	/**
	 * M�todo que mira si alguien ya no tiene cartas para jugar
	 * 
	 * @return boolean
	 */
	public boolean finPartida() {
		if (this.quienGanaPartida() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * M�todo que devuelve el numero de cartas del jugador activo
	 * 
	 * @return int
	 */
	private int numCartasJugadorActivo() {
		return this.getJugadorActual().getCartasMano().size();
	}

	/**
	 * M�todo que devuelve los jugadores de la partida
	 * 
	 * @param jugadores ArrayList<Jugadores>
	 */
	public ArrayList<JugadorAbstract> getJugadores() {
		ArrayList<JugadorAbstract> copy = new ArrayList<JugadorAbstract>();
		for (JugadorAbstract jugador : this.jugadores) {
			copy.add(jugador);
		}
		return copy;
	}

	/**
	 * M�todo que devuelve el jugador actual
	 * 
	 * @return Jugador
	 */
	public JugadorAbstract getJugadorActual() {
		return this.getJugadores().get(turnoActual);
	}

	/**
	 * Devuelve si se muestra la traza de la aplicaci�n o no
	 * 
	 * @return boolean
	 */
	public boolean isTraza() {
		return this.traza;
	}

	/**
	 * Devuelve el array que tiene toda la informaci�n de la partida
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getlogPartidas() {
		return logPartidas;
	}

	/**
	 * Devuelve si se ha llegado al m�ximo de rondas posibles
	 * 
	 * @return boolean
	 */
	public boolean isReachedMaxRondas() {
		return this.hasReachedMaxRondas;
	}

	/**
	 * M�todo para jugar una ronda del UNO
	 */
	public void jugarRonda() {

		// Primero se almacena el string con las rondas hasta el momento
		this.almacenarRondasString();

		// Se suman las rondas
		this.rondas++;

		// Si llegamos al l�mite aceptado de rondas
		if (rondas >= maxRondas) {
			// Caso especial, este se muestra por consola siempre
			System.out.println(" - SE HA LLEGADO AL M�XIMO DE RONDAS EN LA PARTIDA, SE DESCARTA");
			this.hasReachedMaxRondas = true;
			this.guardarDatos(" - SE HA LLEGADO AL M�XIMO DE RONDAS EN LA PARTIDA, SE DESCARTA");

			// Como la partida se descarta, se almacena el mensaje
			this.almacenarRondasString();

		}

		// Primero se revisa la mano del jugador para ver si tiene que robar cartas
		// adicionales
		if (this.cardsToPick > 0) {
			this.revisarSiCogeCartas();
		}

		// Muestra la informacion del tablero
		if (allInfo) {
			this.informacionTablero();
		} else {
			// No siempre hace falta ver toda la informacion
			this.getJugadorActual().verCartas();
		}

		// Pide una nueva carta para robar
		int nuevaCarta = this.getJugadorActual().jugarTurno(this.barajaUNO.getCartaCentro(), this.historial);
		if (nuevaCarta == -100) {
			// Para evitar excepciones si pulsas una letra al jugar
			this.guardarDatos("ERROR: introduzca un car�cter v�lido");
		} else {
			// Si pides robar una carta (NO DEBER�A DEJAR SI TIENES ALGO QUE SE PUEDA ECHAR)
			if (nuevaCarta == -1) {
				// Roba una carta
				this.robaCartaRonda();
			} else {
				// Juega la carta en la mano
				this.jugarCartaRonda(nuevaCarta);
			}
		}

	}

	/**
	 * M�todo para almacenar en el arrayList el string con la ronda
	 */
	private void almacenarRondasString() {
		String copy = "";
		copy = copy.concat(this.logRonda);
		this.logRonda = "";
		logPartidas.add(copy); // Almacenamos la partida en el array
	}

	/**
	 * M�todo para ver si el jugador puede robar una carta en el ronda actual
	 */
	private void robaCartaRonda() {
		if (this.tieneCartasParaEchar()) {
			this.guardarDatos("�TRAMPOSO! NO PUEDES ROBAR CARTAS SI TIENES ALGUNA PARA ECHAR");
			this.getJugadorActual().incrementVecesQueHaIntentadoHacerTrampas();
			allInfo = false;
		} else {
			this.guardarDatos("EL JUGADOR " + this.getJugadorActual().getNombreJugador() + " ROBA UNA CARTA");
			this.robarCartaBaraja();
			this.getJugadorActual().incrementCartasRobadas();
			this.nuevoTurno();
			allInfo = true;
		}
	}

	/**
	 * M�todo para ver si el jugador puede jugar una carta en la ronda actual
	 * 
	 * @param nuevaCarta La nueva carta a jugar
	 */
	private void jugarCartaRonda(int nuevaCarta) {
		if (this.tieneCartaParaEchar(nuevaCarta)) {
			// Si tiene una carta nueva para echar hay que mirar si se da la situaci�n de
			// que tenga que responder a un +2/+4
			if (this.cardsToPick > 0 && comprobarEfectoMasDosYCuatro(nuevaCarta)) {
				// Si acumulas cartas y puedes responder frente a ello debes hacerlo
				this.guardarDatos("�TRAMPOSO! SI TIENES UNA CARTA PARA EVITAR ROBAR LA TIENES QUE LANZAR");
				this.getJugadorActual().incrementVecesQueHaIntentadoResponderMal();
				allInfo = false;
			} else {
				// Si no acumula cartas juega la ronda de forma normal
				this.echarCarta(nuevaCarta);
				this.getJugadorActual().incrementCartasJugadas();
				this.nuevoTurno();
				allInfo = true;
			}
		} else {
			// Si intenta jugar una carta que no puede echar
			this.guardarDatos("�TRAMPOSO! NO SE PUEDE ECHAR ESA CARTA");
			this.getJugadorActual().incrementVecesQueHaIntentadoHacerTrampas();
			allInfo = false;
		}
	}

	/**
	 * M�todo que muestra la informacion textual de la partida
	 */
	public void informacionTablero() {
		
		this.guardarDatos("\n");
		this.guardarDatos("******************************");
		this.guardarDatos("* TURNO ACTUAL: " + this.getJugadorActual().getNombreJugador());
		// Orden del juego
		if (!this.orden) {
			this.guardarDatos("* SENTIDO ACTUAL: Antihorario");
		} else {
			this.guardarDatos("* SENTIDO ACTUAL: Horario");
		}
		this.guardarDatos("* CARTA EN MEDIO: " + this.barajaUNO.getCartaCentro().toString());
		this.guardarDatos("******************************");
		this.guardarDatos("CARTAS DEL JUGADOR ACTUAL: ");
		this.guardarDatos(this.getJugadorActual().verCartasString());

	}

	/**
	 * M�todo que muestra el resultado de la partida
	 */
	public void resultadoPartida() {

		if (!this.hasReachedMaxRondas) { // Si no se ha llegado al m�ximo de rondas es que hay ganador
			this.guardarDatos("\n");
			this.guardarDatos("************ FIN DE LA PARTIDA ************");
			this.guardarDatos("GANADOR: " + this.jugadorGanador());
			for (int i = 0; i < this.getJugadores().size(); i++)
				this.guardarDatos(this.getJugadores().get(i).informacionJugadorPartidaString());

			// Resultado final de la partida
			this.almacenarRondasString();
		}
	}

	/**
	 * M�todo que muestra la baraja
	 */
	public void mostrarBaraja() {
		this.barajaUNO.informacionBaraja();
	}

	/**
	 * Limpia los datos de los jugadores
	 */
	public void limpiarJugadores() {
		for (JugadorAbstract jugador : this.jugadores) {
			jugador.vaciarDatos();
		}
	}


	/**
	 * M�todo para saber si hay que mostrar los datos por consola y guarda el string en el log
	 * @param value El string a imprimir/guardar en el log
	 */
	public void guardarDatos(String value) {
		if (isTraza()) {
			System.out.println(value);
		}
		logRonda = logRonda.concat(value + "\n");
	}

}
