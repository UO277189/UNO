package juego.baraja;
import java.util.ArrayList;

import juego.baraja.estrategiasBaraja.BarajarStrategy;
import juego.carta.Carta;
import juego.carta.CartaAccion;
import juego.carta.CartaNumerica;
import juego.carta.acciones.CambiarSentido;
import juego.carta.acciones.MasCuatro;
import juego.carta.acciones.MasDos;
import juego.carta.acciones.QuitarTurno;
import juego.carta.colores.Colores;


/**
 * Clase que representa la baraja del UNO
 * @author Efr�n Garc�a Valencia UO277189
 * 
 */
public class Baraja {

    // ATRIBUTOS
	
	// Primero necesitamos dos montones: la baraja de cartas y la baraja a descartar
	// Dado que se barajaran las cartas de diferentes formas es mejor trabajar con ArrayList
    private ArrayList<Carta> barajaCartas;
    private ArrayList<Carta> barajaDescarte;
    
    
    // Atributos adiciones para conocer la dimensi�n de la baraja
    private int cartasNumericas = 9;
    private int cartasAccionColor = 2;
    private int cartasAccionSinColor= 4;
    
    
    // La estrategia empleada para barajar
    private BarajarStrategy barajar;
    
    
    // Finalmente, hay que establecer la carta del medio que determinara que pueden robar los jugadores durante la partida
	private Carta cartaCentro;


    
    // CONSTRUCTOR


	/**
	 * Constructor con un par�metro para determinar la estrategia empleada para barajar
	 * @param barajar BarajarStrategy
	 */
    public Baraja(BarajarStrategy barajar) {
        this.barajaCartas = new ArrayList<>();
        this.barajaDescarte = new ArrayList<>();
        
        this.barajar = barajar;
        
        // Formamos la baraja
        this.formarBaraja();
    }
    
    
    // M�TODOS
    
    
    /**
     * Para formar la baraja hay que generar las cartas y barajarlas
     */
    private void formarBaraja() {
    	this.generarBaraja();
    	this.barajarCartas(false);
    	
    	// Se establece una carta en el medio
    	this.cartaCentro = establecerCartaCentro();
    	
    }
    
    
    /**
	 * M�todo para generar la baraja del UNO
	 */
    private void generarBaraja() {
		
		Colores [] colores = {Colores.AMARILLO, Colores.AZUL, Colores.ROJO, Colores.VERDE};
		
		// Hay que reiniciar la baraja para que no se dupliquen las cartas
        this.barajaCartas = new ArrayList<>();
        this.barajaDescarte = new ArrayList<>();
		
		for (Colores color : colores) {
			
			// Dos bucles for para colocar las cartas num�ricas
			for (int i = 1; i <= this.cartasNumericas; i++) {
				this.barajaCartas.add(new CartaNumerica (i, color));
			}
			for (int i = 1; i <= this.cartasNumericas; i++) {
				this.barajaCartas.add(new CartaNumerica (i, color));
			}
			
			// Un bucle for para las cartas de acci�n que tengan color
			for (int i = 0; i < this.cartasAccionColor; i++) {
				this.barajaCartas.add(new CartaAccion(new CambiarSentido(), color));
				this.barajaCartas.add(new CartaAccion(new MasDos(), color));
				this.barajaCartas.add(new CartaAccion(new QuitarTurno(), color));
			}
		}
		
		// Adicionalmente, otro bucle for para colocar las cartas +4
		
		for (int i = 0; i < this.cartasAccionSinColor; i++) {
			// El +4 va con el color NEGRO
			this.barajaCartas.add(new CartaAccion(new MasCuatro(), Colores.NOCOLOR));
		}
		
	}
	
 
    /**
     * M�todo para barajar las cartas de la baraja. El par�metro indica si se mantiene la carta del centro o no
     * @param keepTheMiddle boolean
     */
    private void barajarCartas(boolean keepTheMiddle) {
    	

        // Primero se devuelven todas las cartas de descarte al mont�n a barajar
    	
        while (this.barajaDescarte.isEmpty() == false) {
        	Carta cartaABarajar = this.barajaDescarte.remove(this.barajaDescarte.size()-1);
        	// Hay que restaurar el color de las cartas +4 porque si no genera bucle infinito
        	if (cartaABarajar.toString().contains("+4")) {
        		cartaABarajar.setColor(Colores.NOCOLOR);
        	}
            this.barajaCartas.add(cartaABarajar);
        }
        
        // En este punto hay que decidir si se mantiene la carta del medio o no
        if (keepTheMiddle) {
            for (int i = 0; i < barajaCartas.size(); i++) {
            	if (barajaCartas.get(i).equals(this.cartaCentro)) {
            		// Se incluye la carta de descarte
            		this.barajaDescarte.add(barajaCartas.get(i)); 
            		// La quitamos de la baraja
            		this.barajaCartas.remove(i);
            	}
            }
        } else {
        	this.cartaCentro = null; // No existe la carta del centro entonces
        }
        
        if (this.barajaCartas.size() != 0) {
        	// Si los jugadores no acaparan todas las cartas
        	barajar.execute(this); // Se ejecuta la forma de barajar
        }
    }  
       
    
	/**
	 * M�todo para poner una carta en el centro
	 * @return Carta
	 */
    private Carta establecerCartaCentro() {
		
		Carta carta = this.barajaCartas.remove(this.barajaCartas.size()-1);
		// La carta del medio forma parte del monton de descarte
		this.barajaDescarte.add(carta);
		
		while (carta instanceof CartaAccion) {		
			// Si no es una carta numerica la ponemos debajo y vamos iterando hasta encontrar una nueva		
			this.barajaCartas.add(0, carta);
			// Se quita del mont�n de descarte
			this.barajaDescarte.remove(carta);
			// Sacamos una carta nueva
			carta = this.barajaCartas.remove(this.barajaCartas.size()-1);
			this.barajaDescarte.add(carta);
		}
		return carta;
	}
   

   /**
     * Da un n�mero de cartas a indicar de la baraja que NO van al mont�n de descarte
     * @param cartas int
     * @return ArrayList <Carta>
     */
    public ArrayList<Carta> darCartas(int cartas) {
   	
    	if (barajaCartas.size() <= cartas || this.barajaCartas.isEmpty()) {
    		// Se vuelve a barajar las cartas MANTENIENDO LA CARTA CENTRAL
    		this.barajarCartas(true);
    	}
    	
    	ArrayList<Carta> cartasARepartir = new ArrayList<>();
        // Coges las cartas a repartir
    	for (int i = 0; i < cartas; i++) {
    		// Mientras podamos repartir cartas
    		if (this.barajaCartas.size()-1 != -1) {	
    			Carta c = this.barajaCartas.remove(this.barajaCartas.size()-1);
    			cartasARepartir.add(c); 
    		} else {
    			break; // No hay cartas para repartir
    		}
    	}
            
    	// Las devuelves
    	return cartasARepartir;
    }
    

    /**
     * M�todo para repartir una carta 
     * @param carta Carta
     */
    public Carta robarCarta() {
    	if (barajaCartas.size() == 0) {
        	// Si no hay cartas tenemos que barajar de nuevo
    		this.barajarCartas(true); // Mantenemos la carta del medio
    	}
    	return this.barajaCartas.remove(this.barajaCartas.size()-1);
    }
    

    /**
     * Nos dice si se puede robar una carta o no
     * @return
     */
	public boolean hayCartaParaRobar() {
    	if (barajaCartas.size() == 0) {
        	// Si no hay cartas tenemos que barajar de nuevo
    		this.barajarCartas(true);
    		if (barajaCartas.size() == 0) {
    			// Si directamente no hay cartas porque todos los jugadores las tengan en la mano
    			return false;
    		} else {
    			return true;
    		}
    	} else {
    		return true;
    	}
	}
    
     
    /**
	 * Para cambiar el color actual
	 * @param colorActual Colores
	 */
	public void setColorCartaMedio(Colores colorActual) {
		this.cartaCentro.setColor(colorActual);
	}
	

	
	/**
	 * Coloca una nueva carta en el centro
	 * @param cartaCentro Carta
	 */
	public void setCartaCentro(Carta cartaCentro) {
		this.cartaCentro = cartaCentro;
		// Si metes una nueva carta en el centro tambien forma parte del mont�n de descarte
		this.barajaDescarte.add(cartaCentro);
	}

	
	/**
	 * Obtiene la carta situada en el centro
	 * @return Carta
	 */
	public Carta getCartaCentro() {
		return cartaCentro;
	}
	
	
    /**
     * Devuelve la forma de barahar
     * @return BarajarStrategy
     */
    public BarajarStrategy getBarajarStrategy() {
		return this.barajar;
	}
    
    

	public ArrayList<Carta> getBarajaCartas() {
		return barajaCartas;
	}


	/**
     * Muestro las cartas de la baraja (como si cojes el monton y lo miras entero)
     */
    public void informacionBaraja() {
    	
    	System.out.println("***Monton para robar***");
    	for (int i = 0; i < this.barajaCartas.size(); i++) {
    		System.out.println(i+ " - " + this.barajaCartas.get(i));
    	}

    	System.out.println("***Monton de descarte***");
    	for (int i = 0; i < this.barajaDescarte.size(); i++) {
    		System.out.println(i + " - " + this.barajaDescarte.get(i));
    	}
    }

}
