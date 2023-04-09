package juego.jugador;
import java.util.ArrayList;
import juego.carta.Carta;

/**
 * Clase abstracta que representa los m�todos comunes a ambos tipos de jugadores
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public abstract class JugadorAbstract {
	
	// ATRIBUTOS
	
	private String nombreJugador;
	private ArrayList<Carta> cartasMano;
	
	
	// Par�metros adicionales a indicar por los jugadores al terminar una partida
	private int cartasJugadas;
	private int cartasRobadas;
	private int cartasMasCuatroJugadas;
	private int cartasMasDosJugadas;
	private int cartasCambiarSentidoJugadas;
	private int cartasQuitarTurnoJugadas;
	private int vecesQueHaCantadoUno;
	private int vecesQueHaIntentadoResponderMal;
	private int vecesQueHaIntentadoHacerTrampas;
	private int vecesQueHaGanado;
	
	// CONSTRUCTORES
	
	/**
	 * Constructor con todos los atributos para el jugador
	 * @param nombreJugador String
	 * @param cartasMano ArrayList<Carta>
	 */
	public JugadorAbstract(String nombreJugador, ArrayList<Carta> cartasMano) {
		this.nombreJugador = nombreJugador;
		this.cartasMano = cartasMano;
	}
	
	
	/**
	 * Constructor para el jugador con s�lo su nombre
	 * @param nombreJugador String
	 */
	public JugadorAbstract(String nombreJugador) {
		this.nombreJugador = nombreJugador;
		this.cartasMano = new ArrayList<Carta>();
	}


	// METODOS

	/**
	 * Devuelve la carta del jugador en una posicion determinada
	 * @param posicion int
	 * @return Carta
	 */
	public Carta getCartaPos(int posicion) {
		return this.cartasMano.get(posicion);
	}
	
	
	/**
	 * Le quita la carta al jugador en una posicion concreta 
	 * @param posicion int
	 */
	public void quitarCarta(int posicion) {
		this.cartasMano.remove(posicion);
	}
	
	
	/**
	 * Para saber si el jugador gana la ronda al no tener cartas
	 * @return boolean
	 */
	public boolean quedanCartas() {
		if (this.getCartasMano().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	

	/**
	 * Para incluir una carta en la mano
	 * @param cartaRobada Carta
	 */
	public void robarUnaCarta(Carta cartaRobada) {
		this.cartasMano.add(cartaRobada);
	}
	
	
	/**
	 * Para el +2 y el +4 donde tienes que robar varias cartas 
	 * @param cartasRobadas ArrayList<Carta>
	 */
	public void robarVariasCartas(ArrayList<Carta> cartasRobadas) {
		for (Carta cartasNuevas : cartasRobadas) {
			this.cartasMano.add(cartasNuevas);
		}
	}

	
	/**
	 * Para poner al jugador cartas en la mano al principio de la ronda
	 * @param cartasMano ArrayList<Carta>
	 */
	public void setCartasMano(ArrayList<Carta> cartasMano) {
		this.cartasMano = cartasMano;
	}
	
	
	/**
	 * Devuelve las cartas de la mano del jugador
	 * @return cartasMano ArrayList<Carta>
	 */
	public ArrayList<Carta> getCartasMano() {
		return this.cartasMano;
	}

	/**
	 * Devuelve el nombre del jugador
	 * @return String
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}



	/**
	 * Para ver las cartas en la mano del jugador
	 */
	public void verCartas() {
		
		// No hace falta repetir otra vez el nombre del jugador actual
		//System.out.println("Cartas del jugador " + this.nombreJugador);
		for (int i = 0; i < this.getCartasMano().size() ; i++) {
			Carta cartaMostrada = this.getCartasMano().get(i);
			System.out.println("   " + i + ": " + cartaMostrada);
		}
	}
	
	
	/**
	 * Devuelve en un string las cartas jugadas
	 */
	
	/**
	 * Devuelve en un string las cartas de la mano
	 * @return Las cartas de la mano
	 */
	public String verCartasString() {
		String value = "";
		for (int i = 0; i < this.getCartasMano().size() ; i++) {
			Carta cartaMostrada = this.getCartasMano().get(i);
			value = value.concat("   " + i + ": " + cartaMostrada + "\n");
		}
		return value;
	}
	

	

	/**
	 * Muestra la informacion del jugador
	 */
	@Override
	public String toString() {
		return "El jugador " + nombreJugador + " tiene " + this.getCartasMano().size() + " cartas en la mano.";
	}
	
	

	/**
	 * Determina si el jugador juegue de forma manual o si el algoritmo toma el control
	 * @param enMedio carta ubicada en medio
	 * @param historial las cartas jugadas hasta el momento
	 * @return int el n�mero de carta a devolver
	 */
	public abstract int jugarTurno(Carta enMedio, ArrayList<Carta> historial);
	
	
	/**
	 * M�todo para elegir un nuevo color en el caso en el que salga una carta +4
	 * @param length el tama�o del array
	 * @return int la posici�n del array
	 */
	public abstract int elegirNuevoColor(int length);


	/**
	 * Devuelve el valor del parametro cartasJugadas
	 * @return el valor del parametro cartasJugadas
	 */
	public int getCartasJugadas() {
		return cartasJugadas;
	}

	
	/**
	 * Incrementa el valor del parametro cartasJugadas
	 * @param cartasJugadas el valor del parametro cartasJugadas
	 */
	public void incrementCartasJugadas() {
		this.cartasJugadas++;
	}

	
	/**
	 * Devuelve el valor del parametro cartasRobadas
	 * @return el valor del parametro cartasRobadas
	 */
	public int getCartasRobadas() {
		return cartasRobadas;
	}


	/**
	 * Incrementa el valor del parametro cartasRobadas
	 * @param cartasJugadas el valor del parametro cartasRobadas
	 */
	public void incrementCartasRobadas() {
		this.cartasRobadas++;
	}


	/**
	 * Devuelve el valor del parametro cartasMasCuatroJugadas
	 * @return el valor del parametro cartasMasCuatroJugadas
	 */
	public int getCartasMasCuatroJugadas() {
		return cartasMasCuatroJugadas;
	}

	/**
	 * Incrementa el valor del parametro cartasMasCuatroJugadas
	 * @param cartasJugadas el valor del parametro cartasMasCuatroJugadas
	 */
	public void incrementCartasMasCuatroJugadas() {
		this.cartasMasCuatroJugadas++;
	}
	

	/**
	 * Devuelve el valor del parametro cartasMasDosJugadas
	 * @return el valor del parametro cartasMasDosJugadas
	 */
	public int getCartasMasDosJugadas() {
		return cartasMasDosJugadas;
	}


	/**
	 * Incrementa el valor del parametro cartasMasDosJugadas
	 * @param cartasJugadas el valor del parametro cartasMasDosJugadas
	 */
	public void incrementCartasMasDosJugadas() {
		this.cartasMasDosJugadas++;
	}
	
	
	/**
	 * Devuelve el valor del parametro cartasCambiarSentidoJugadas
	 * @return el valor del parametro cartasCambiarSentidoJugadas
	 */
	public int getCartasCambiarSentidoJugadas() {
		return cartasCambiarSentidoJugadas;
	}

	
	/**
	 * Incrementa el valor del parametro cartasCambiarSentidoJugadas
	 * @param cartasJugadas el valor del parametro cartasCambiarSentidoJugadas
	 */
	public void incrementCartasCambiarSentidoJugadas() {
		this.cartasCambiarSentidoJugadas++;
	}
	


	/**
	 * Devuelve el valor del parametro cartasQuitarTurnoJugadas
	 * @return el valor del parametro cartasQuitarTurnoJugadas
	 */
	public int getCartasQuitarTurnoJugadas() {
		return cartasQuitarTurnoJugadas;
	}

	/**
	 * Incrementa el valor del parametro cartasQuitarTurnoJugadas
	 * @param cartasJugadas el valor del parametro cartasQuitarTurnoJugadas
	 */
	public void incrementCartasQuitarTurnoJugadas() {
		this.cartasQuitarTurnoJugadas++;
	}

	
	/**
	 * Devuelve el valor del parametro vecesQueHaCantadoUno
	 * @return el valor del parametro vecesQueHaCantadoUno
	 */
	public int getVecesQueHaCantadoUno() {
		return vecesQueHaCantadoUno;
	}
	
	/**
	 * Incrementa el valor del parametro vecesQueHaCantadoUno
	 * @param cartasJugadas el valor del parametro vecesQueHaCantadoUno
	 */
	public void incrementVecesQueHaCantadoUno() {
		this.vecesQueHaCantadoUno++;
	}


	/**
	 * Devuelve el valor del parametro vecesQueHaIntentadoResponderMal
	 * @return el valor del parametro vecesQueHaIntentadoResponderMal
	 */
	public int getVecesQueHaIntentadoResponderMal() {
		return vecesQueHaIntentadoResponderMal;
	}
	
	/**
	 * Incrementa el valor del parametro vecesQueHaIntentadoResponderMal
	 * @param cartasJugadas el valor del parametro vecesQueHaIntentadoResponderMal
	 */
	public void incrementVecesQueHaIntentadoResponderMal() {
		this.vecesQueHaIntentadoResponderMal++;
	}
	
	/**
	 * Devuelve el valor del parametro vecesQueHaIntentadoHacerTrampas
	 * @return el valor del parametro vecesQueHaIntentadoHacerTrampas
	 */
	public int getVecesQueHaIntentadoHacerTrampas() {
		return vecesQueHaIntentadoHacerTrampas;
	}
	
	/**
	 * Incrementa el valor del parametro vecesQueHaIntentadoHacerTrampas
	 * @param cartasJugadas el valor del parametro vecesQueHaIntentadoHacerTrampas
	 */
	public void incrementVecesQueHaIntentadoHacerTrampas() {
		this.vecesQueHaIntentadoHacerTrampas++;
	}
	
	
	/**
	 * Devuelve el valor del parametro vecesQueHaGanado
	 * @return el valor del parametro vecesQueHaGanado
	 */
	public int getVecesQueHaGanado() {
		return vecesQueHaGanado;
	}

	/**
	 * Incrementa el valor del parametro vecesQueHaGanado
	 * Este S�LO SIRVE PARA MOSTRAR LOS RESULTADOS DE M�LTIPLES PARTIDAS
	 * @param cartasJugadas el valor del parametro vecesQueHaGanado
	 */
	public void incrementVecesQueHaGanado() {
		this.vecesQueHaGanado++;
	}
	


	/**
	 * M�todo para sumar los datos de un jugador a otro
	 * Hay varios enfoques, �ste pretende reducir la explosi�n de m�todos
	 * @param jugadorAbstract El jugador que pasa los datos
	 */
	public void aumentarDatosJugador(JugadorAbstract jugadorAbstract) {
		this.cartasJugadas += jugadorAbstract.getCartasJugadas();
		this.cartasRobadas += jugadorAbstract.getCartasRobadas();
		this.cartasMasCuatroJugadas += jugadorAbstract.getCartasMasCuatroJugadas();
		this.cartasMasDosJugadas += jugadorAbstract.getCartasMasDosJugadas();
		this.cartasCambiarSentidoJugadas += jugadorAbstract.getCartasCambiarSentidoJugadas();
		this.cartasQuitarTurnoJugadas += jugadorAbstract.getCartasQuitarTurnoJugadas();
		this.vecesQueHaCantadoUno += jugadorAbstract.getVecesQueHaCantadoUno();
		this.vecesQueHaIntentadoResponderMal += jugadorAbstract.getVecesQueHaIntentadoResponderMal();
		this.vecesQueHaIntentadoHacerTrampas += jugadorAbstract.getVecesQueHaIntentadoHacerTrampas();
		this.vecesQueHaGanado += jugadorAbstract.getVecesQueHaGanado();
	}	
	
	
	/**
	 * M�todo que borra los datos del jugador
	 */
	public void vaciarDatos() {
		this.cartasJugadas = 0;
		this.cartasRobadas = 0;
		this.cartasMasCuatroJugadas = 0;
		this.cartasMasDosJugadas = 0;
		this.cartasCambiarSentidoJugadas = 0;
		this.cartasQuitarTurnoJugadas = 0;
		this.vecesQueHaCantadoUno = 0;
		this.vecesQueHaIntentadoResponderMal = 0;
		this.vecesQueHaIntentadoHacerTrampas = 0;
		this.vecesQueHaGanado = 0;
	}

	/**
	 * Saca los estad�sticos concretos del jugador al acabar una partida
	 */
	public void informacionJugadorPartida() {
		System.out.println("Jugador: " + this.nombreJugador);
		System.out.println("	Cartas jugadas: " + this.getCartasJugadas());
		System.out.println("	Cartas robadas: " + this.getCartasRobadas());
		System.out.println("	Cartas +4 jugadas: " + this.getCartasMasCuatroJugadas());
		System.out.println("	Cartas +2 jugadas: " + this.getCartasMasDosJugadas());
		System.out.println("	Cartas Cambio de Sentido jugadas: " + this.getCartasCambiarSentidoJugadas());
		System.out.println("	Cartas Quitar Turno jugadas: " + this.getCartasQuitarTurnoJugadas());	
		System.out.println("	Veces que ha cantado UNO: " + this.getVecesQueHaCantadoUno());	
		System.out.println("	Veces que ha intentado jugar una carta pudiendo responder a un +2/+4: " + this.getVecesQueHaIntentadoResponderMal());
		System.out.println("	Veces que ha intentado hacer trampas: " + this.getVecesQueHaIntentadoHacerTrampas());	
		System.out.println("	Veces que ha ganado: " + this.getVecesQueHaGanado());	
	}


	/**
	 * Saca los estad�sticos concretos del jugador al acabar una partida en un string
	 * @return
	 */
	public String informacionJugadorPartidaString() {
		String value = "";
		value = value.concat("Jugador: " + this.nombreJugador + "\n");
		value = value.concat("	Cartas jugadas: " + this.getCartasJugadas() + "\n");
		value = value.concat("	Cartas robadas: " + this.getCartasRobadas() + "\n");
		value = value.concat("	Cartas +4 jugadas: " + this.getCartasMasCuatroJugadas() + "\n");
		value = value.concat("	Cartas +2 jugadas: " + this.getCartasMasDosJugadas() + "\n");
		value = value.concat("	Cartas Cambio de Sentido jugadas: " + this.getCartasCambiarSentidoJugadas() + "\n");
		value = value.concat("	Cartas Quitar Turno jugadas: " + this.getCartasQuitarTurnoJugadas() + "\n");	
		value = value.concat("	Veces que ha cantado UNO: " + this.getVecesQueHaCantadoUno() + "\n");	
		value = value.concat("	Veces que ha intentado jugar una carta pudiendo responder a un +2/+4: " + this.getVecesQueHaIntentadoResponderMal() + "\n");
		value = value.concat("	Veces que ha intentado hacer trampas: " + this.getVecesQueHaIntentadoHacerTrampas() + "\n");	
		value = value.concat("	Veces que ha ganado: " + this.getVecesQueHaGanado() + "\n");	
		return value;
	}


}
