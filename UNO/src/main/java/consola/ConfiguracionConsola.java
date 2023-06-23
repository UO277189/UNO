package main.java.consola;

import java.util.ArrayList;

import main.java.algoritmoVoraz.ensembles.Ensemble;
import main.java.algoritmoVoraz.ensembles.tipos.EnsembleRanking;
import main.java.algoritmoVoraz.ensembles.tipos.EnsembleSuma;
import main.java.algoritmoVoraz.ensembles.tipos.EnsembleVotacion;
import main.java.algoritmoVoraz.reglas.Regla;
import main.java.algoritmoVoraz.reglas.ReglaFactory;
import main.java.logica.juego.baraja.FormaBarajar;
import main.java.logica.juego.baraja.tipos.CartaACarta;
import main.java.logica.juego.baraja.tipos.MontonAMonton;
import main.java.logica.juego.jugador.Jugador;
import main.java.logica.juego.jugador.JugadorAutomatico;
import main.java.logica.juego.jugador.JugadorManual;

/**
 * Clase que maneja la lógica de los comandos que el usuario introduce por
 * consola
 * 
 * @author Efrén García Valencia UO277189
 *
 */
public class ConfiguracionConsola {

	private LeerConsola leerConsola;
	private boolean rellenarPorDefecto;

	/**
	 * Constructor por defecto
	 * @param leerConsola Para leer por consola
	 */
	public ConfiguracionConsola(LeerConsola leerConsola) {
		this.leerConsola = leerConsola;

		// Antes de empezar preguntamos si el jugador quiere poner datos por defecto
		this.preguntarDatosPorDefecto();

	}

	/**
	 * Este método sirve para preguntar si el jugador quiere rellenar datos por
	 * defecto
	 */
	private void preguntarDatosPorDefecto() {
		System.out.print("¿Desea rellenar el nombre de la configuración y los jugadores por defecto? (0 - Si, 1 - No): ");
		int valor = leerConsola.leerValorRango(0, 1);
		if (valor == 0) {
			this.rellenarPorDefecto = true;
		} else {
			this.rellenarPorDefecto = false;
		}
	}

	/**
	 * Método para elegir el nombre del fichero
	 * 
	 * @return String El nombre de la configuracion
	 */
	public String elegirNombreConfiguracion() {

		System.out.println();
		if (!this.rellenarPorDefecto) {
			System.out.print("Por favor, seleccione el nombre de la configuración: ");
			return leerConsola.leerLinea();
		} else {
			return "ConfiguracionManual";
		}
	}

	/**
	 * Método para elegir los jugadores de la partida y la estrategia que van a
	 * emplear
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Jugador> elegirJugadores() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		System.out.print("Por favor, seleccione el número de jugadores (Mínimo: 2): ");
		int rangoJugadores = leerConsola.leerValorRango(2, Integer.MAX_VALUE);

		// Para cada jugador
		for (int i = 0; i < rangoJugadores; i++) {

			String nombre = "";

			if (!this.rellenarPorDefecto) {
				System.out.print("Por favor, elija el nombre del jugador " + (i + 1) + ": ");
				nombre = leerConsola.leerLinea();
			} else {
				nombre = "Jugador " + i;
			}

			System.out.print(
					"¿Va a ser un jugador manual o automático? Pulse la tecla correspondiente (0 - Manual, 1 - Automático): ");
			int tipoJugador = leerConsola.leerValorRango(0, 1);

			// Jugador manual
			if (tipoJugador == 0) {
				jugadores.add(new JugadorManual(nombre)); // Para las pruebas le paso el parámetro adicional
			}

			// Si es un jugador automático
			if (tipoJugador == 1) {
				jugadores.add(establecerReglas(nombre));

			}
		}
		return jugadores;

	}

	/**
	 * Método para crear la estrategia de barajar a aplicar
	 * 
	 * @return BarajarStrategy La estrategia para barajar
	 */
	public FormaBarajar elegirEstrategiaBaraja() {
		// Estrategia a aplicar
		FormaBarajar estrategia = null;

		System.out.println("A continuación se muestan las estrategias implementadas para barajar");
		System.out.println();
		System.out.println(" - 0: Barajar carta a carta");
		System.out.println(" - 1: Barajar montones de cartas");
		System.out.println();
		System.out.print("Seleccione el valor a implementar: ");
		int value = leerConsola.leerValorRango(0, 1);

		// Caso barajar carta a carta
		if (value == 0) {
			System.out.print(
					"Por favor, seleccione cuántas cartas desea intercambiar de posición (Mínimo: 10, Máximo: 1000): ");
			int cartas = leerConsola.leerValorRango(10, 1000);
			estrategia = new CartaACarta(cartas);

		}

		// Caso barajar montón a montón
		if (value == 1) {
			System.out.print("Por favor, seleccione cuántas cartas tendrá el montón (Mínimo: 2, Máximo: 50): ");
			int cartasMonton = leerConsola.leerValorRango(2, 50);
			System.out.println();
			System.out.print("Por favor, seleccione cuántos montones desea intercambiar (Mínimo: 2, Máximo: 100): ");
			int montonesCambiar = leerConsola.leerValorRango(2, 100);
			System.out.println();
			estrategia = new MontonAMonton(cartasMonton, montonesCambiar);
		}
		return estrategia;
	}

	/**
	 * Método para determinar el número de partidas a jugar
	 * 
	 * @return int El número de partidas a jugar
	 */
	public int elegirNumeroPartidas() {
		System.out.print("Por favor, elija el número de partidas (Mínimo: 1, Máximo: 10000): ");
		int value = leerConsola.leerValorRango(1, 10000);
		System.out.println();
		return value;
	}

	/**
	 * Método para elegir si ver toda la traza o solamente los resultados al
	 * ejecutar la partida
	 * 
	 * @param jugadores La lista de jugadores para ver si se muestra la traza o no
	 * 
	 * @return boolean Si se quiere ver la traza o no
	 */
	public boolean elegirVerTraza(ArrayList<Jugador> jugadores) {

		System.out.print("Por favor, elija si desea ver la traza de la partida al final (0 - Si, 1 - No): ");
		int value = this.leerConsola.leerValorRango(0, 1);
		System.out.println();
		if (value == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que devuelve un jugador manejado por el algoritmo correspondiente
	 * 
	 * @param nombre Nombre del jugador
	 * @return JugadorAlgoritmo
	 */
	public JugadorAutomatico establecerReglas(String nombre) {

		// Refactorización para que ahora se puedan escoger múltiples reglas
		int eleccion = 0;
		ArrayList<Regla> reglas = new ArrayList<Regla>();

		while (eleccion != 14) {
			mostrarReglasUsuario(); // Mostramos las opciones al usuario
			eleccion = leerConsola.leerValorRango(0, 14); // Elegimos la opción

			if (eleccion != 14) {
				reglas.add(elegirRegla(eleccion));
				System.out.println("Se ha elegido la regla marcada por " + eleccion);
				System.out.println();
			} else {
				if (reglas.isEmpty()) {
					System.out.println("ERROR: se tiene que elegir al menos una regla");
					System.out.println();
					eleccion = 0; // Para que no salga del while
				}
			}
		}
		return new JugadorAutomatico(nombre, reglas); // Devolvemos al jugador
	}

	/**
	 * Para elegir la regla a aplicar
	 * 
	 * @param eleccion La eleccion que se toma
	 * @return Regla
	 */
	private Regla elegirRegla(int eleccion) {
		switch (eleccion) {
		case 0:
			return ReglaFactory.crearRegla("ReglaAzar");
		case 1:
			return ReglaFactory.crearRegla("ReglaPrimeraCarta");
		case 2:
			return ReglaFactory.crearRegla("ReglaPriorizarCartaNumerica");
		case 3:
			return ReglaFactory.crearRegla("ReglaPriorizarCartaAccion");
		case 4:
			return ReglaFactory.crearRegla("ReglaPriorizarCartasRobar");
		case 5:
			return ReglaFactory.crearRegla("ReglaPriorizarComodines");
		case 6:
			return ReglaFactory.crearRegla("ReglaNoPriorizarCartasRobar");
		case 7:
			return ReglaFactory.crearRegla("ReglaPriorizarCartasAccionNoComodin");
		case 8:
			return ReglaFactory.crearRegla("ReglaCambiarColorMedio");
		case 9:
			return ReglaFactory.crearRegla("ReglaNoCambiarColorMedio");
		case 10:
			return ReglaFactory.crearRegla("ReglaCompararTiposCartasMasFrecuente");
		case 11:
			return ReglaFactory.crearRegla("ReglaCompararTiposCartasMenosFrecuente");
		case 12:
			return ReglaFactory.crearRegla("ReglaContarColoresMasFrecuente");
		case 13:
			return ReglaFactory.crearRegla("ReglaContarColoresMenosFrecuente");
		}
		return null;
	}

	/**
	 * Método para mostar las reglas al usuario
	 */
	public void mostrarReglasUsuario() {
		System.out.println("Por favor, seleccione la regla que desea implementar");
		
		
		System.out.println(" - 0: Azar - Al azar");
		System.out.println(" - 1: PrimeraCarta - Sacar la primera carta que encuentr en la mano");
		System.out.println(" - 2: PriorizarCartaNumerica - Priorizar las cartas numéricas");
		System.out.println(" - 3: PriorizarCartaAccion - Priorizar las cartas de acción");
		System.out.println(" - 4: PriorizarCartasRobar - Prioriza las cartas +4 y +2, el resto de cartas al azar");
		System.out.println(" - 5: PriorizarComodines - Prioriza las cartas comodín, resto de cartas al azar");
		System.out.println(" - 6: NoPriorizarCartasRobar - Sacar las cartas +4 y +2 lo más tarde posible, el resto de cartas al azar");
		System.out.println(" - 7: PriorizarCartasAccionNoComodin - Priorizar las cartas de acción que no sean comodines");
		System.out.println(" - 8: CambiarColorMedio - Priorizar las cartas que permitan cambiar el color del medio");
		System.out.println(" - 9: NoCambiarColorMedio - Priorizar las cartas que mantengan el color del medio");
		System.out.println(" - 10: CompararTiposCartasMasFrecuente - Cuantas más veces sale una carta numérica o de acción, más probable es que "
				+ "respondamos con una carta numérica o de acción respectivamente");
		System.out.println(" - 11: CompararTiposCartasMenosFrecuente - Cuantas más veces sale una carta numérica o de acción, menos probable es que"
				+ " respondamos con una carta numérica o de acción respectivamente");
		System.out.println(" - 12: ContarColoresMasFrecuente - Cuantas más veces sale un color, más probable es que "
				+ "respondamos con una carta de ese color");
		System.out.println(" - 13: ContarColoresMenosFrecuente - Cuantas más veces sale un color, menos probable es que "
				+ "respondamos con una carta de ese color");
	
		System.out.println("");
		System.out.println(" Pulse 14 para guardar todas las reglas seleccionadas");
	}

	/**
	 * Método para elegir el ensemble de la aplicación
	 * 
	 * @return Ensemble
	 */
	public Ensemble elegirEnsemble() {

		System.out.println("A continuación se muestran los ensembles que hay en la aplicación");
		System.out.println();
		System.out.println(" - 0: EnsembleRanking: escoger la carta que tenga mejor posición global entre todas las reglas");
		System.out.println(" - 1: EnsembleSuma: escoger la carta con mayor peso sumando el peso de todas las reglas");
		System.out.println(" - 2: EnsembleVotacion: escoger la carta más votada entre todas las reglas");
		System.out.println();
		System.out.print("Seleccione el valor a implementar: ");

		int valor = leerConsola.leerValorRango(0, 2);

		if (valor == 0) {
			return new EnsembleRanking();
		} else if (valor == 1) {
			return new EnsembleSuma();
		} else if (valor == 2) {
			return new EnsembleVotacion();
		}
		return null;
	}

}