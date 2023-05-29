package main.juego.jugador;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import main.juego.carta.Carta;

/**
 * Clase abstracta que representa los métodos comunes a ambos tipos de jugadores
 * @author Efrén García Valencia UO277189
 *
 */

@Getter
@Setter
public abstract class JugadorAbstract {
	
	// ATRIBUTOS
	
	private String nombreJugador;
	private ArrayList<Carta> cartasMano;
	
	
	// Parámetros adicionales a indicar por los jugadores al terminar una partida
	private int cartasJugadas;
	private int cartasRobadas;
	private int cartasMasCuatroJugadas;
	private int cartasMasDosJugadas;
	private int cartasCambiarSentidoJugadas;
	private int cartasQuitarTurnoJugadas;
	private int vecesQueHaCantadoUno;
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
	 * Constructor para el jugador con sólo su nombre
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
	 * @return int el número de carta a devolver
	 */
	public abstract int jugarTurno(Carta enMedio, ArrayList<Carta> historial);
	
	
	/**
	 * Método para elegir un nuevo color en el caso en el que salga una carta +4
	 * @param length el tamaño del array
	 * @return int la posición del array
	 */
	public abstract int elegirNuevoColor(int length);


	
	/**
	 * Incrementa el valor del parametro cartasJugadas
	 * @param cartasJugadas el valor del parametro cartasJugadas
	 */
	public void incrementCartasJugadas() {
		this.cartasJugadas++;
	}

	/**
	 * Incrementa el valor del parametro cartasRobadas
	 * @param cartasJugadas el valor del parametro cartasRobadas
	 */
	public void incrementCartasRobadas() {
		this.cartasRobadas++;
	}


	/**
	 * Incrementa el valor del parametro cartasMasCuatroJugadas
	 * @param cartasJugadas el valor del parametro cartasMasCuatroJugadas
	 */
	public void incrementCartasMasCuatroJugadas() {
		this.cartasMasCuatroJugadas++;
	}
	


	/**
	 * Incrementa el valor del parametro cartasMasDosJugadas
	 * @param cartasJugadas el valor del parametro cartasMasDosJugadas
	 */
	public void incrementCartasMasDosJugadas() {
		this.cartasMasDosJugadas++;
	}
	
	
	/**
	 * Incrementa el valor del parametro cartasCambiarSentidoJugadas
	 * @param cartasJugadas el valor del parametro cartasCambiarSentidoJugadas
	 */
	public void incrementCartasCambiarSentidoJugadas() {
		this.cartasCambiarSentidoJugadas++;
	}
	
	
	/**
	 * Incrementa el valor del parametro cartasQuitarTurnoJugadas
	 * @param cartasJugadas el valor del parametro cartasQuitarTurnoJugadas
	 */
	public void incrementCartasQuitarTurnoJugadas() {
		this.cartasQuitarTurnoJugadas++;
	}

	

	/**
	 * Incrementa el valor del parametro vecesQueHaCantadoUno
	 * @param cartasJugadas el valor del parametro vecesQueHaCantadoUno
	 */
	public void incrementVecesQueHaCantadoUno() {
		this.vecesQueHaCantadoUno++;
	}


	/**
	 * Incrementa el valor del parametro vecesQueHaIntentadoHacerTrampas
	 * @param cartasJugadas el valor del parametro vecesQueHaIntentadoHacerTrampas
	 */
	public void incrementVecesQueHaIntentadoHacerTrampas() {
		this.vecesQueHaIntentadoHacerTrampas++;
	}
	

	/**
	 * Incrementa el valor del parametro vecesQueHaGanado
	 * Este SÓLO SIRVE PARA MOSTRAR LOS RESULTADOS DE MÚLTIPLES PARTIDAS
	 * @param cartasJugadas el valor del parametro vecesQueHaGanado
	 */
	public void incrementVecesQueHaGanado() {
		this.vecesQueHaGanado++;
	}
	


	/**
	 * Método para sumar los datos de un jugador a otro
	 * Hay varios enfoques, éste pretende reducir la explosión de métodos
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
		this.vecesQueHaIntentadoHacerTrampas += jugadorAbstract.getVecesQueHaIntentadoHacerTrampas();
		this.vecesQueHaGanado += jugadorAbstract.getVecesQueHaGanado();
	}	
	
	
	/**
	 * Método que borra los datos del jugador
	 */
	public void vaciarDatos() {
		this.cartasJugadas = 0;
		this.cartasRobadas = 0;
		this.cartasMasCuatroJugadas = 0;
		this.cartasMasDosJugadas = 0;
		this.cartasCambiarSentidoJugadas = 0;
		this.cartasQuitarTurnoJugadas = 0;
		this.vecesQueHaCantadoUno = 0;
		this.vecesQueHaIntentadoHacerTrampas = 0;
		this.vecesQueHaGanado = 0;
	}

	/**
	 * Saca los estadísticos concretos del jugador al acabar una partida
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
		System.out.println("	Veces que ha intentado hacer trampas: " + this.getVecesQueHaIntentadoHacerTrampas());	
		System.out.println("	Veces que ha ganado: " + this.getVecesQueHaGanado());	
	}


	/**
	 * Saca los estadísticos concretos del jugador al acabar una partida en un string
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
		value = value.concat("	Veces que ha intentado hacer trampas: " + this.getVecesQueHaIntentadoHacerTrampas() + "\n");	
		value = value.concat("	Veces que ha ganado: " + this.getVecesQueHaGanado() + "\n");	
		return value;
	}


	/**
	 * Devuelve el formato JSON que se aplica a los jugadores
	 * @return String El JSON a indicar
	 */
	public abstract String getJSON();

	
	

}