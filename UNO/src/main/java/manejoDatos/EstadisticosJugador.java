package main.java.manejoDatos;

/**
 * Clase que recoge los estadísticos de salida de cada uno de los jugadores
 * @author Efrén García Valencia UO277189
 *
 */
public class EstadisticosJugador {
	
	// ATRIBUTOS
	
	private String nombre;
	private String reglas;
	private int cartasJugadas;
	private int cartasRobadas;
	private int cartasMasCuatroJugadas;
	private int cartasMasDosJugadas;
	private int cartasCambioSentidoJugadas;
	private int cartasQuitarTurnoJugadas;
	private int cartasCambioColorJugadas;
	private int vecesQueHizoTrampas;
	private int vecesQueCantaUno;
	private int vecesQueHaGanado;
	


	/**
	 * Constructor con los parámetros de salida del jugador
	 * @param nombre 						El nombre del jugador
	 * @param reglas 						Las reglas que implementa
	 * @param cartasJugadas 				Las cartas jugadas
	 * @param cartasRobadas 				Las cartas robadas
	 * @param cartasMasCuatroJugadas 		Las cartas +4 jugadas
	 * @param cartasMasDosJugadas 			Las cartas +2 jugadas
	 * @param cartasCambioSentidoJugadas 	Las cartas cambio de sentido jugadas
	 * @param cartasCambioColorJugadas		Las cartas cambio de color jugadas
	 * @param cartasQuitarTurnoJugadas 		Las cartas quitar turno jugadas
	 * @param vecesQueHizoTrampas			Las veces que el jugador hizo trampas
	 * @param vecesQueCantaUno 				Las veces que canta uno
	 * @param vecesQueHaGanado 				Las veces que gana
	 */
	public EstadisticosJugador(String nombre, String reglas, int cartasJugadas, int cartasRobadas,
			int cartasMasCuatroJugadas, int cartasMasDosJugadas, int cartasCambioSentidoJugadas,
			int cartasQuitarTurnoJugadas, int cartasCambioColorJugadas, 
			int vecesQueHizoTrampas, int vecesQueCantaUno, int vecesQueHaGanado) {
		super();
		this.nombre = nombre;
		this.reglas = reglas;
		this.cartasJugadas = cartasJugadas;
		this.cartasRobadas = cartasRobadas;
		this.cartasMasCuatroJugadas = cartasMasCuatroJugadas;
		this.cartasMasDosJugadas = cartasMasDosJugadas;
		this.cartasCambioSentidoJugadas = cartasCambioSentidoJugadas;
		this.cartasQuitarTurnoJugadas = cartasQuitarTurnoJugadas;
		this.cartasCambioColorJugadas = cartasCambioColorJugadas;
		this.vecesQueHizoTrampas = vecesQueHizoTrampas;
		this.vecesQueCantaUno = vecesQueCantaUno;
		this.vecesQueHaGanado = vecesQueHaGanado;
	}



	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * @return las reglas
	 */
	public String getReglas() {
		return reglas;
	}



	/**
	 * @return las cartasJugadas
	 */
	public int getCartasJugadas() {
		return cartasJugadas;
	}



	/**
	 * @return las cartasRobadas
	 */
	public int getCartasRobadas() {
		return cartasRobadas;
	}



	/**
	 * @return las cartasMasCuatroJugadas
	 */
	public int getCartasMasCuatroJugadas() {
		return cartasMasCuatroJugadas;
	}



	/**
	 * @return las cartasMasDosJugadas
	 */
	public int getCartasMasDosJugadas() {
		return cartasMasDosJugadas;
	}



	/**
	 * @return las cartasCambioSentidoJugadas
	 */
	public int getCartasCambioSentidoJugadas() {
		return cartasCambioSentidoJugadas;
	}



	/**
	 * @return las cartasQuitarTurnoJugadas
	 */
	public int getCartasQuitarTurnoJugadas() {
		return cartasQuitarTurnoJugadas;
	}

	
	/**
	 * @return las cartasCambioColorJugadas
	 */
	public int getCartasCambioColorJugadas() {
		return cartasCambioColorJugadas;
	}


	/**
	 * @return las vecesQueHizoTrampas
	 */
	public int getVecesQueHizoTrampas() {
		return vecesQueHizoTrampas;
	}



	/**
	 * @return las vecesQueCantaUno
	 */
	public int getVecesQueCantaUno() {
		return vecesQueCantaUno;
	}



	/**
	 * @return las vecesQueHaGanado
	 */
	public int getVecesQueHaGanado() {
		return vecesQueHaGanado;
	}
	
	
	
	

	
	


}
