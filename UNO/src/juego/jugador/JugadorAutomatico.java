package juego.jugador;

import java.util.ArrayList;

import algoritmoVoraz.ensembles.Ensemble;
import algoritmoVoraz.reglas.Regla;
import juego.carta.Carta;

/**
 * Clase que representa un jugador que juega aplicando una estrategia
 * @author Efrén García Valencia UO277189
 *
 */
public class JugadorAutomatico extends JugadorAbstract{

	private Ensemble ensemble; 
	private ArrayList<Regla> reglas;
	
	/**
	 * Constructor para el algoritmo que implementa reglas
	 * @param nombreJugador El nombre del jugador
	 * @param ArrayList<Regla> reglas
	 */
	public JugadorAutomatico(String nombreJugador, ArrayList<Regla> reglas) {
		super(nombreJugador);
		this.reglas = reglas;
	}
	
	
	@Override
	public int elegirNuevoColor(int length) {	
		// Se podría aplicar una estrategia estudiando los casos en los que el jugador tenga más cartas de un palo
		// Para simplificar, de momento se escoge al azar
		return (int) (Math.random()*3);
	}

	@Override
	public int jugarTurno(Carta enMedio, ArrayList<Carta> historial) {
		// Aplica el algoritmo correspondiente con las cartas de la mano y la carta en medio
		return ensemble.ejecutarEnsemble(getCartasMano(), enMedio, historial, reglas);
	}


	/**
	 * Devuelve las reglas que implementa el algoritmo
	 * @return ArrayList<Regla> Las reglas que implementa
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
		String reglas = "";
		for (Regla regla : this.getReglas()) {
			reglas += regla.toString();
		}
		
		return  "{" +
	            "\"nombre\": \"" + this.getNombreJugador() + "\"," +
	            "\"regla\": \"" + reglas + "\"" +
	        "}";
	}
	
	

}
