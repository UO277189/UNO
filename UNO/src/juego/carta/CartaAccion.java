package juego.carta;

import juego.carta.acciones.AccionStrategy;
import juego.carta.acciones.MasCuatro;
import juego.carta.acciones.MasDos;
import juego.carta.colores.Colores;

/**
 * Clase que representa una carta de acción
 * @author Efrén García Valencia UO277189
 *
 */
public class CartaAccion extends Carta{
	
	// ATRIBUTOS
	
	// Acción asociada a una carta del juego
	private AccionStrategy accion; 

		
	// CONSTRUCTORES
		
	/**
	 * Constructor para las cartas de acción
	 * @param accion Accion
	 * @param color Colores
	 */
	public CartaAccion(AccionStrategy accion, Colores color) {
		super(color);
		this.accion = accion;	
	}
		


	// MÉTODOS
	
	
	@Override
	protected boolean coincideCarta(Carta c) {
		if (c instanceof CartaAccion) {
			AccionStrategy accionAEchar = ((CartaAccion) c).getAccion();
			
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
			AccionStrategy accionEnMedio = ((CartaAccion)this).getAccion();
			AccionStrategy accionAEchar = ((CartaAccion)c).getAccion();		
				
			// Si echas un +4 o si  tienes un +2 y la anterior carta es un +2 te salvas
			if (accionAEchar instanceof MasCuatro|| accionEnMedio instanceof MasDos && accionAEchar instanceof MasDos){
				return true;
			} 
		}
		return false;
	}	



	/**
	 * Para devolver la acción de la carta
	 * @return Acciones
	 */
	public AccionStrategy getAccion() {
		return accion;
	}

	

	/**
	 * Informacion textual de la carta
	*/
	public String toString() {		
		return color + " - " + this.accion.toString();
	}

}
