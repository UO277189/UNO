package algoritmoVoraz.reglas.reglasQueMiranHistorial.numerosAcciones;

import java.util.ArrayList;

import algoritmoVoraz.reglas.ReglaStrategy;
import juego.carta.Carta;
import juego.carta.CartaAccion;
import juego.carta.CartaNumerica;
/**
 * Regla que pondera las cartas por tipos en funcion de las veces que hayan salido
 * Cuantas mas veces salga MENOR ES LA PROBABILIDAD
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class ReglaNoPriorizarContarNumerosAcciones implements ReglaStrategy{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		
		// Primero se calcula las veces que sale X carta de cada tipo
		
		int cartasNumericas = 0;
		int cartasAccion = 0;
		
		for (Carta carta: historial) {
			if (carta instanceof CartaNumerica)
				cartasNumericas++;
			if (carta instanceof CartaAccion)
				cartasAccion++;
		}
		
		// Con eso se calculan los pesos siguiendo la regla 1/nVecesCarta
		calcularPesosPorTipos(cartas, cartasNumericas, cartasAccion);
	}

	
	/**
	 * Metodo para calcular y asociar los pesos a las cartas del jugador en funcion del tipo que sea
	 * @param cartas las cartas a ponderar
	 * @param cartasNumericas cartas numericas a ponderar
	 * @param cocartasAccion cartas de accion a ponderar
	 */
	private void calcularPesosPorTipos(ArrayList<Carta> cartas, int cartasNumericas, int cartasAccion) {
	
		// Cuantas mas veces salga MENOR es la probabilidad	
		
		float probNum;
		float probAccion;
		
		if (cartasNumericas == 0)
			probNum = 1;
		else 
			probNum = 1/cartasNumericas; 
		
		if (cartasAccion == 0)
			probAccion = 1;
		else 
			probAccion = 1/cartasAccion;
			
		
		for (Carta carta: cartas) {
			if (carta instanceof CartaNumerica)
				carta.setPeso(probNum);
			if (carta instanceof CartaAccion)
				carta.setPeso(probAccion);
		}
	}


}
