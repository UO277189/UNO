package main.java.algoritmoVoraz.reglas.tipos.reglasHistorial;

import java.util.ArrayList;

import main.java.algoritmoVoraz.reglas.Regla;
import main.java.juego.carta.Carta;
import main.java.juego.carta.colores.Colores;
/**
 * Regla que pondera las cartas de colores en funcion de las cartas que ya hayan salido
 * Cuantas mas veces salga MAYOR ES LA PROBABILIDAD
 * @author Efrén García Valencia UO277189
 *
 */
public class ReglaContarColoresMasFrecuente implements Regla{

	@Override
	public void execute(ArrayList<Carta> cartas, ArrayList<Carta> historial) {
		
		// Primero se calcula las veces que sale X carta de color
		
		int colorRojo = 0;
		int colorVerde = 0;
		int colorAmarillo = 0;
		int colorAzul = 0;
		int noColor = 0;
		
		for (Carta carta: historial) {
			if (carta.getColor().equals(Colores.ROJO))
				colorRojo++;
			if (carta.getColor().equals(Colores.VERDE))
				colorVerde++;
			if (carta.getColor().equals(Colores.AZUL))
				colorAzul++;
			if (carta.getColor().equals(Colores.AMARILLO))
				colorAmarillo++;
			if (carta.getColor().equals(Colores.NOCOLOR))
				noColor++;
		}
		
		// Con eso se calculan los pesos siguiendo la regla nVecesColor/sumaTotal
		calcularPesosPorColores(cartas, colorRojo, colorVerde, colorAzul, colorAmarillo, noColor);
	}

	
	/**
	 * Metodo para calcular y asociar los pesos a las cartas del jugador en funcion de las veces que salga X color
	 * @param cartas las cartas a ponderar
	 * @param colorRojo numero de veces que sale el color rojo
	 * @param colorVerde numero de veces que sale el color verde
	 * @param colorAzul numero de veces que sale el color azul
	 * @param colorAmarillo numero de veces que sale el color amarillo
	 * @param noColor numero de veces que sale el color no color
	 */
	private void calcularPesosPorColores(ArrayList<Carta> cartas, int colorRojo, int colorVerde, int colorAzul, 
			int colorAmarillo, int noColor) {
		
		// Cuantas mas veces salga MAYOR es la probabilidad
		
		float sumaTotal = colorRojo + colorVerde + colorAzul + colorAmarillo + noColor;
		
		
		float probRojo;
		float probAzul;
		float probVerde;
		float probAmarillo;
		float probNoColor;
		
		if (sumaTotal == 0) {
			// Deberia ser imposible pero por si acaso
			probRojo = 0;
			probAzul = 0;
			probVerde = 0;
			probAmarillo = 0;
			probNoColor = 0;	
		} else {
			probRojo = colorRojo/sumaTotal; 
			probAzul = colorVerde/sumaTotal; 
			probVerde = colorAzul/sumaTotal; 
			probAmarillo = colorAmarillo/sumaTotal; 
			probNoColor = noColor/sumaTotal;	
		}
		
		for (Carta carta: cartas) {
			if (carta.getColor().equals(Colores.ROJO))
				carta.setPeso(probRojo);
			if (carta.getColor().equals(Colores.VERDE))
				carta.setPeso(probVerde);
			if (carta.getColor().equals(Colores.AZUL))
				carta.setPeso(probAzul);
			if (carta.getColor().equals(Colores.AMARILLO))
				carta.setPeso(probAmarillo);
			if (carta.getColor().equals(Colores.NOCOLOR))
				carta.setPeso(probNoColor);
		}
	}


	@Override
	public String toString() {
		return "ReglaContarColoresMasFrecuente";
	}
	
	

}
