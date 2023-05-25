package juego.carta;

import juego.carta.colores.Colores;

/**
 * Clase que representa una carta numérica del UNO
 * @author Efrén Garcíaa Valencia UO277189
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
	

	// MÉTODOS
	
	

	@Override
	protected boolean coincideCarta(Carta c) {
		if (c instanceof CartaNumerica) {
			if (this.getNumero() == ((CartaNumerica) c).getNumero() || this.getColor().equals(c.getColor())){
				return true; // SI coincide el número o el color
			}
		} else {
			if (this.getColor().equals(c.getColor())){
				return true; // SI coincide el color en el caso de una carta de acción
			}
		}
		return false; 
	}



	@Override
	public boolean puedeNoRobar(Carta c) {
		return false; // No puedes contrarrestar con una carta numérica
	}
	

	/**
	 * Devuelve el número de la carta
	 * @return int
	 */
	public int getNumero() {
		return numero;
	}


	/**
	 * Información textual de la carta
	 */
	public String toString() {
		
		return color + " - " + numero;
	}

}
