package juego.carta;

import juego.carta.colores.Colores;

/**
 * Clase que representa una carta num�rica del UNO
 * @author Efr�n Garc�aa Valencia UO277189
 *
 */
public class CartaNumerica extends Carta {
	
	
	// ATRIBUTOS
	private int numero;
	
	// CONSTRUCTORES
	
	/**
	 * Constructor para las cartas del juego que vienen numeradas
	 * @param numero int
	 * @param color Colores
	 */
	public CartaNumerica(int numero, Colores color) {
		super(color);
		this.numero = numero;
	}
	

	// M�TODOS
	
	

	@Override
	protected boolean coincideCarta(Carta c) {
		if (c instanceof CartaNumerica) {
			if (this.getNumero() == ((CartaNumerica) c).getNumero() || this.getColor().equals(c.getColor())){
				return true; // SI coincide el n�mero o el color
			}
		} else {
			if (this.getColor().equals(c.getColor())){
				return true; // SI coincide el color en el caso de una carta de acci�n
			}
		}
		return false; 
	}



	@Override
	public boolean puedeNoRobar(Carta c) {
		return false; // No puedes contrarrestar con una carta num�rica
	}
	

	/**
	 * Devuelve el n�mero de la carta
	 * @return int
	 */
	public int getNumero() {
		return numero;
	}


	/**
	 * Informaci�n textual de la carta
	 */
	public String toString() {
		
		return color + " - " + numero;
	}

}
