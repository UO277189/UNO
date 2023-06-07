package main.java.juego.carta;


import main.java.juego.carta.acciones.Accion;
import main.java.juego.carta.acciones.tipos.MasCuatro;
import main.java.juego.carta.acciones.tipos.MasDos;
import main.java.juego.carta.colores.Colores;

/**
 * Clase que representa una carta de acción
 * @author Efrén García Valencia UO277189
 *
 */


public class CartaAccion extends Carta{
	
	// ATRIBUTOS
	
	// Acción asociada a una carta del juego
	private Accion accion; 

		
	// CONSTRUCTORES
		
	/**
	 * Constructor para las cartas de acción
	 * @param accion Accion
	 * @param color Colores
	 */
	public CartaAccion(Accion accion, Colores color) {
		
		super(color);
		this.accion = accion;	
		
		if (accion.toString().equals("+4")){ // Para asegurar
			this.setColor(Colores.NOCOLOR);
		}
		if (accion.toString().equals("CambiaColor")){ 
			this.setColor(Colores.NOCOLOR);
		}
	}
		


	// MÉTODOS
	
	
	@Override
	protected boolean coincideCarta(Carta c) {
		if (c instanceof CartaAccion) {
			Accion accionAEchar = ((CartaAccion) c).getAccion();
			
			// Si fuera un +4 ya se habría echado antes
			
			// Aqui sirve si coincide el color o la acción
			if (this.color.equals(c.getColor()) || this.accion.toString().equals(accionAEchar.toString())) {
				return true;	
			}
			
		} else {
			if (this.color.equals(c.getColor())) {
				return true; // Puedes lanzar una carta numérica si coincide el color
			}	
		}
		return false; 
	}
	
	
	
	@Override
	public boolean puedeNoRobar(Carta c) {
		
		// El parámetro es la carta a echar
		if (this instanceof CartaAccion && c instanceof CartaAccion) {
			Accion accionEnMedio = ((CartaAccion)this).getAccion();
			Accion accionAEchar = ((CartaAccion)c).getAccion();		
				
			// Si echas un +4 o si  tienes un +2 y la anterior carta es un +2 te salvas
			if (accionAEchar instanceof MasCuatro|| accionEnMedio instanceof MasDos && accionAEchar instanceof MasDos){
				return true;
			} 
		}
		return false;
	}	



	/**
	 * Informacion textual de la carta
	*/
	public String toString() {		
		return color + " - " + this.accion.toString();
	}



	/**
	 * @return la accion
	 */
	public Accion getAccion() {
		return accion;
	}



	/**
	 * @param accion la accion a establecer
	 */
	public void setAccion(Accion accion) {
		this.accion = accion;
	}
	
	
	

}