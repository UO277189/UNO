package main.java.algoritmoVoraz.reglas.tipos.reglasHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.logica.juego.carta.Carta;
import main.java.logica.juego.carta.CartaAccion;
import main.java.logica.juego.carta.CartaNumerica;
/**
 * Regla que pondera las cartas por tipos en funcion de las veces que hayan salido
 * Cuantas mas veces salga MAYOR ES LA PROBABILIDAD
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaCompararTiposCartasMasFrecuente implements Regla{

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
		
		// Con eso se calculan los pesos siguiendo la regla nVecesCarta/sumaTotal
		calcularPesosPorTipos(cartas, cartasNumericas, cartasAccion);
	}

	
	/**
	 * Metodo para calcular y asociar los pesos a las cartas del jugador en funcion del tipo que sea
	 * @param cartas las cartas a ponderar
	 * @param cartasNumericas cartas numericas a ponderar
	 * @param cocartasAccion cartas de accion a ponderar
	 */
	private void calcularPesosPorTipos(ArrayList<Carta> cartas, int cartasNumericas, int cartasAccion) {
	
		// Cuantas mas veces salga MAYOR es la probabilidad
		
		float sumaTotal = cartasNumericas + cartasAccion;
		
		float probNum;
		float probAccion;
		
		if (sumaTotal == 0) {
			probNum = 0;
			probAccion = 0;
		} else { 
			probNum = cartasNumericas/sumaTotal;  
			probAccion = cartasAccion/sumaTotal; 
		}

		
		for (Carta carta: cartas) {
			if (carta instanceof CartaNumerica)
				carta.setPeso(probNum);
			if (carta instanceof CartaAccion)
				carta.setPeso(probAccion);
		}
	}


	@Override
	public String toString() {
		return "ReglaCompararTiposCartasMasFrecuente";
	}
	
	


}
