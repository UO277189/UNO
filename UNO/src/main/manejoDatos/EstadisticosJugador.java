package main.manejoDatos;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que recoge los estadísticos de salida de cada uno de los jugadores
 * @author Efrén García Valencia UO277189
 *
 */

@Getter
@Setter
public class EstadisticosJugador {
	
	// ATRIBUTOS
	
	private String nombre;
	private String[] reglas;
	private int cartasJugadas;
	private int cartasRobadas;
	private int cartasMasCuatroJugadas;
	private int cartasMasDosJugadas;
	private int cartasCambioSentidoJugadas;
	private int cartasQuitarTurnoJugadas;
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
	 * @param cartasQuitarTurnoJugadas 		Las cartas quitar turno jugadas
	 * @param vecesQueCantaUno 				Las veces que canta uno
	 * @param vecesQueHaGanado 				Las veces que gana
	 */
	public EstadisticosJugador(String nombre, String[] reglas, int cartasJugadas, int cartasRobadas,
			int cartasMasCuatroJugadas, int cartasMasDosJugadas, int cartasCambioSentidoJugadas,
			int cartasQuitarTurnoJugadas, int vecesQueCantaUno, int vecesQueHaGanado) {
		this.nombre = nombre;
		this.reglas = reglas;
		this.cartasJugadas = cartasJugadas;
		this.cartasRobadas = cartasRobadas;
		this.cartasMasCuatroJugadas = cartasMasCuatroJugadas;
		this.cartasMasDosJugadas = cartasMasDosJugadas;
		this.cartasCambioSentidoJugadas = cartasCambioSentidoJugadas;
		this.cartasQuitarTurnoJugadas = cartasQuitarTurnoJugadas;
		this.vecesQueCantaUno = vecesQueCantaUno;
		this.vecesQueHaGanado = vecesQueHaGanado;
	}


}
