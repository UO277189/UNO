package main.manejoDatos.manejoConsola;

import java.util.ArrayList;

import main.algoritmoVoraz.ensembles.Ensemble;
import main.algoritmoVoraz.ensembles.EnsembleRanking;
import main.algoritmoVoraz.ensembles.EnsembleSuma;
import main.algoritmoVoraz.ensembles.EnsembleVotacion;
import main.algoritmoVoraz.ensembles.NoEnsemble;
import main.algoritmoVoraz.reglas.Regla;
import main.algoritmoVoraz.reglas.reglasQueMiranHistorial.colores.ReglaNoPriorizarContarColores;
import main.algoritmoVoraz.reglas.reglasQueMiranHistorial.colores.ReglaPriorizarContarColores;
import main.algoritmoVoraz.reglas.reglasQueMiranHistorial.numerosAcciones.ReglaNoPriorizarContarNumerosAcciones;
import main.algoritmoVoraz.reglas.reglasQueMiranHistorial.numerosAcciones.ReglaPriorizarContarNumerosAcciones;
import main.algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaAzar;
import main.algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaNoPriorizarMasCuatro;
import main.algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaNoPriorizarMasDos;
import main.algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaPriorizarMasCuatro;
import main.algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaPriorizarMasDos;
import main.juego.baraja.estrategiasBaraja.BarajarStrategy;
import main.juego.baraja.estrategiasBaraja.CartaACarta;
import main.juego.baraja.estrategiasBaraja.MontonAMonton;
import main.juego.jugador.JugadorAbstract;
import main.juego.jugador.JugadorAutomatico;
import main.juego.jugador.JugadorManual;

/**
 * Clase que maneja la lógica de los comandos que el usuario introduce por consola
 * @author Efrén García Valencia UO277189
 *
 */
public class ConfigurarPartidaConsola {

	private LeerConsola leerConsola;

	/**
	 * Constructor por defecto
	 */
	public ConfigurarPartidaConsola() {
		this.leerConsola = new LeerConsola();
	}
	

	/**
	 * Método para elegir el nombre del fichero
	 * @return String El nombre de la configuracion
	 */
	public String elegirNombreConfiguracion() {

		System.out.println();
		System.out.print("Por favor, seleccione el nombre de la configuración: ");
		return leerConsola.leerLinea();
	}


	/**
	 * Método para elegir los jugadores de la partida y la estrategia que van a
	 * emplear
	 * 
	 * @return ArrayList<JugadorAbstract> La lista de jugadores a incluir
	 */
	public ArrayList<JugadorAbstract> elegirJugadores() {
		ArrayList<JugadorAbstract> jugadores = new ArrayList<JugadorAbstract>();
		System.out.print("Por favor, seleccione el número de jugadores (Mínimo: 2): ");
		int rangoJugadores = leerConsola.leerValorRango(2, Integer.MAX_VALUE);

		// Para cada jugador
		for (int i = 0; i < rangoJugadores; i++) {
			System.out.print("Por favor, elija el nombre del jugador " + (i + 1) + ": ");
			String nombre = leerConsola.leerLinea();

			System.out.print("¿Va a ser un jugador manual o automático? Pulse la tecla correspondiente (0 - Manual, 1 -Automático): ");
			int tipoJugador = leerConsola.leerValorRango(0, 1);

			// Jugador manual
			if (tipoJugador == 0) {
				jugadores.add(new JugadorManual(nombre));
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
	public BarajarStrategy elegirEstrategiaBaraja() {
		// Estrategia a aplicar
		BarajarStrategy estrategia = null;

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
			System.out.print("Por favor, seleccione cuántas cartas tendrá el montón (Mínimo: 2, Máximo: 20): ");
			int cartasMonton = leerConsola.leerValorRango(2, 20);
			System.out.println();
			System.out.print("Por favor, seleccione cuántos montones desea intercambiar (Mínimo: 2, Máximo: 5): ");
			int montonesCambiar = leerConsola.leerValorRango(2, 5);
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
		System.out.print("Por favor, elija el número de partidas (Mínimo: 1, Máximo: 100000): ");
		int value = leerConsola.leerValorRango(1, 100000);
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
	public boolean elegirVerTraza(ArrayList<JugadorAbstract> jugadores) {

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

		while (eleccion != 9) {
			mostrarReglasUsuario(); // Mostramos las opciones al usuario
			eleccion = leerConsola.leerValorRango(0, 9); // Elegimos la opción

			if (eleccion != 10) {
				reglas.add(elegirRegla(eleccion + 1));
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
			return new ReglaAzar();
		case 1:
			return new ReglaPriorizarMasCuatro();
		case 2:
			return new ReglaNoPriorizarMasCuatro();
		case 3:
			return new ReglaPriorizarMasDos();
		case 4:
			return new ReglaNoPriorizarMasDos();
		case 5:
			return new ReglaPriorizarContarColores();
		case 6:
			return new ReglaNoPriorizarContarColores();
		case 7:
			return new ReglaPriorizarContarNumerosAcciones();
		case 8:
			return new ReglaNoPriorizarContarNumerosAcciones();
		}
		return null;
	}

	/**
	 * Método para mostar las reglas al usuario
	 */
	public void mostrarReglasUsuario() {
		System.out.println("Por favor, seleccione la regla que desea implementar");
		System.out.println(" - 0: Al azar");
		System.out.println(" - 1: Sacar el +4 lo antes posible");
		System.out.println(" - 2: Sacar el +4 lo más tarde posible");
		System.out.println(" - 3: Sacar el +2 lo antes posible");
		System.out.println(" - 4: Sacar el +2 lo más tarde posible");
		System.out.println(" - 5: Cuantas más veces sale un color, más probable es que "
				+ "respondamos con una carta de ese color");
		System.out.println(" - 6: Cuantas más veces sale un color, menos probable es que "
				+ "respondamos con una carta de ese color");
		System.out.println(" - 7: Cuantas más veces sale una carta numérica o de acción, más probable es que "
				+ "respondamos con una carta numérica o de acción respectivamente");
		System.out.println(" - 8: Cuantas más veces sale una carta numérica o de acción, menos probable es que"
				+ " respondamos con una carta numérica o de acción respectivamente");
		System.out.println("");
		System.out.println(" Pulse 9 para guardar todas las reglas seleccionadas");
	}

	

	public Ensemble elegirEnsemble() {

		System.out.println("A continuación se muestran los ensembles que hay en la aplicación");
		System.out.println();
		System.out.println(" - 0: EnsembleRanking");
		System.out.println(" - 1: EnsembleSuma");
		System.out.println(" - 2: EnsembleVotacion");
		System.out.println(" - 3: NoEnsemble");
		System.out.println();
		System.out.print("Seleccione el valor a implementar: ");
		
		int valor = leerConsola.leerValorRango(0, 3);
		
		if (valor == 0) {
			return new EnsembleRanking();
		} else if (valor == 1) {
			return new EnsembleSuma();
		} else if (valor == 2) {
			return new EnsembleVotacion();
		} else if (valor == 3){
			return new NoEnsemble();
		}
		return null;
	}


}