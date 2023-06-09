package main.java.juego.jugador;

import java.util.ArrayList;

import main.java.algoritmoVoraz.ensembles.Ensemble;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.juego.carta.Carta;
import main.java.juego.carta.colores.Colores;

/**
 * Clase que representa un jugador que juega aplicando una estrategia
 * @author Efrén García Valencia UO277189
 *
 */
public class JugadorAutomatico extends Jugador{

	private Ensemble ensemble; 
	private ArrayList<Regla> reglas;
	
	/**
	 * Constructor para el algoritmo que implementa reglas
	 * @param nombreJugador El nombre del jugador
	 * @param reglas Las reglas a implementar
	 */
	public JugadorAutomatico(String nombreJugador, ArrayList<Regla> reglas) {
		super(nombreJugador);
		this.reglas = reglas;
	}
	
	
	@Override
	public int elegirNuevoColor(int length) {	
		// Si el jugador tiene más cartas en la mano de un color, elegimos ese color
		// Si no, elegimos al azar
		int redValue = 0;
		int blueValue = 0;
		int yellowValue = 0;
		int greenValue = 0;
		
		for (Carta carta : this.getCartasMano()) {
			if (carta.getColor().equals(Colores.ROJO)) {
				redValue++;
			} else if (carta.getColor().equals(Colores.AZUL)) {
				blueValue++;
			} else if (carta.getColor().equals(Colores.AMARILLO)) {
				yellowValue++;
			} else if (carta.getColor().equals(Colores.VERDE)) {
				greenValue++;
			} 
		}
		
		// Colores[] coloresCopia = { Colores.VERDE, Colores.ROJO, Colores.AMARILLO, Colores.AZUL };
		
		if (greenValue > blueValue && greenValue > yellowValue 
				&&	greenValue > redValue) {
			return 0;
		}
		
		if (redValue > blueValue && redValue > yellowValue 
				 && redValue > greenValue) {
			return 1;
		}
		
		if (yellowValue > blueValue && yellowValue > redValue 
				&& yellowValue > greenValue) {
			return 2;
		}
		
		if (blueValue > redValue && blueValue > yellowValue 
				 && blueValue > greenValue) {
			return 3;
		}
		
		// Si no hay ninguna carta mejor devolvemos un valor al azar
		return (int) (Math.random()*3);
	}

	@Override
	public int jugarTurno(Carta enMedio, ArrayList<Carta> historial) {
		// Aplica el algoritmo correspondiente con las cartas de la mano y la carta en medio
		return ensemble.execute(getCartasMano(), enMedio, historial, reglas);
	}


	/**
	 * Devuelve las reglas que implementa el algoritmo
	 * @return ArrayList
	 */
	public ArrayList<Regla> getReglas() {
		return reglas;
	}


	/**
	 * Método para asignar el ensemble a los jugadores automáticos
	 * @param ensemble El ensemble
	 */
	public void asignarEnsemble(Ensemble ensemble) {
		this.ensemble = ensemble;
	}


	@Override
	public String getJSON() {
		
		// Ponemos todas las reglas en un string juntas
		int coma = 0;
		String reglas = "";
		for (Regla regla : this.getReglas()) {
			if (coma == 0) {
				reglas += regla.toString();
				coma++;
			} else {
				reglas = reglas + "," + regla.toString();
			}
		}
		
		return  "        {\n" +
	            "          \"nombre\": \"" + this.getNombreJugador() + "\",\n" +
	            "          \"regla\": \"" + reglas + "\""+ "\n" +
	            "        }";
	}
	
	

}