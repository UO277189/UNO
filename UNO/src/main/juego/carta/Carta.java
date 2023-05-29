package main.juego.carta;
import java.util.Comparator;

import lombok.Getter;
import lombok.Setter;
import main.juego.carta.colores.Colores;

/**
 * Clase Carta abstracta que sirve como base para desarrollar la carta del UNO
 * @author Efrén García Valencia UO277189
 *
 */

@Getter
@Setter
public abstract class Carta {

    //ATRIBUTOS
	
    protected Colores color;
    protected float peso; // Todas las cartas tienen un peso a estimar por el algoritmo


    // CONSTRUCTORES
    
    /**
     * Constructor para las cartas del UNO
     * @param color Colores
     */
    public Carta(Colores color) {
        this.color = color;
    }

     
    // MÉTODOS


	/**
	 * Método para saber si se puede echar una carta
	 * @param c Carta
	 * @return boolean
	 */
	public boolean sePuedeEchar(Carta c) {
		
		
		if (c.getColor() == Colores.NOCOLOR) {
			return true; // El +4 se puede echar siempre
		} else if (coincideCarta(c)) { // Hay que ver si la carta coincide
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Método para evaluar una carta de acción
	 * @param c Carta
	 * @return boolean
	 */
	protected abstract boolean coincideCarta(Carta c);
	
	
	/**
	 * Método para estudiar si el jugador puede contrarrestar un +2/+4
	 * @param c Carta
	 * @return boolean
	 */
	public abstract boolean puedeNoRobar(Carta c);


	/**
	 * Método para ordenar las cartas a comprarar
	 */
	 public static Comparator<Carta> PesoComparator = new Comparator<Carta>() {
	        @Override
	        public int compare(Carta c1, Carta c2) {
	            if (c1.getPeso() < c2.getPeso()) {
	                return 1;
	            } else if (c1.getPeso() > c2.getPeso()) {
	                return -1;
	            } else {
	                return 0;
	            }
	        }
	    };

    
}