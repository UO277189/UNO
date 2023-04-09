package juego.carta;
import juego.enumerados.Colores;

/**
 * Clase Carta abstracta que sirve como base para desarrollar la carta del UNO
 * @author Efrén García Valencia UO277189
 *
 */
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
     * Establece un nuevo color a la carta. Es importante para cuando se lanza el +4
     * @param color Colores
     */
	public void setColor(Colores color) {
		this.color = color;
	}
	
	
    /**
     * Devuelve el color de la carta
     * @return Colores
     */
    public Colores getColor() {
        return color;
    }



    /**
     * Devuelve el peso de la carta
     * @return float
     */
	public float getPeso() {
		return peso;
	}

	
	/**
	 * Establece el peso de la carta
	 * @param peso el peso de la carta
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}
    
    

    
}
