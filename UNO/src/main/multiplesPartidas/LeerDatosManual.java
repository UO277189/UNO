package main.multiplesPartidas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import algoritmoVoraz.reglas.Regla;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.colores.ReglaNoPriorizarContarColores;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.colores.ReglaPriorizarContarColores;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.numerosAcciones.ReglaNoPriorizarContarNumerosAcciones;
import algoritmoVoraz.reglas.reglasQueMiranHistorial.numerosAcciones.ReglaPriorizarContarNumerosAcciones;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaAzar;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaNoPriorizarMasCuatro;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaNoPriorizarMasDos;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaPriorizarMasCuatro;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaPriorizarMasDos;
import auxiliar.LeerConsola;
import juego.barajar.BarajarStrategy;
import juego.barajar.CartaACarta;
import juego.barajar.MontonAMonton;
import juego.jugador.JugadorAbstract;
import juego.jugador.JugadorAlgoritmo;
import juego.jugador.JugadorManual;

/**
 * Clase que maneja la l�gica de los comandos que el usuario introduce por
 * consola
 * 
 * @author Efr�n Garc�a Valencia UO277189
 *
 */
public class LeerDatosManual {
	
	
	private LeerConsola leerConsola;
	private Scanner sc;
	
	/**
	 * Constructor por defecto
	 */
	public LeerDatosManual() {
		this.leerConsola = new LeerConsola();
		this.sc = new Scanner(System.in);
	}
	
	/**
	 * M�todo para elegir los jugadores de la partida y la estrategia que van a
	 * emplear
	 * 
	 * @return ArrayList<JugadorAbstract> La lista de jugadores a incluir
	 */
	public ArrayList<JugadorAbstract> elegirJugadores() {
		ArrayList<JugadorAbstract> jugadores = new ArrayList<JugadorAbstract>();
		System.out.println("Por favor, seleccione el n�mero de jugadores (M�nimo: 2, M�ximo: 4)");
		int rangoJugadores = leerConsola.leerValorRango(2, 4);

		// Para cada jugador
		for (int i = 0; i < rangoJugadores; i++) {
			System.out.println("Por favor, elija el nombre del jugador " + (i + 1));
			String nombre = leerNombreJugador();

			System.out.println("�Va a ser un jugador real o autom�tico? (0 - Real, 1 -Autom�tico)");
			int tipoJugador = leerConsola.leerValorRango(0, 1);

			// Jugador manual
			if (tipoJugador == 0) {
				jugadores.add(new JugadorManual(nombre));
			}

			// Si es un jugador autom�tico
			if (tipoJugador == 1) {
				jugadores.add(establecerRegla(nombre));

			}
		}
		return jugadores;

	}
	
	/**
	 * M�todo que devuelve true si hay un jugador manual
	 * 
	 * @param jugadores Lista de jugadores
	 * @return boolean Si hay un jugador manual o no
	 */
	public boolean hayJugadorManual(ArrayList<JugadorAbstract> jugadores) {
		for (JugadorAbstract jugador : jugadores)
			if (jugador instanceof JugadorManual) {
				return true;
			}
		return false;
	}

	
	/**
	 * M�todo para crear la estrategia de barajar a aplicar
	 * 
	 * @return BarajarStrategy La estrategia para barajar
	 */
	public BarajarStrategy elegirEstrategiaBaraja() {
		// Estrategia a aplicar
		BarajarStrategy estrategia = null;

		System.out.println("Por favor, seleccione el m�todo de barajar");
		System.out.println(" - 0: Barajar carta a carta");
		System.out.println(" - 1: Barajar montones de cartas");

		int value = leerConsola.leerValorRango(0, 1);

		// Caso barajar carta a carta
		if (value == 0) {
			System.out.println(
					"Por favor, seleccione cu�ntas cartas desea intercambiar de posici�n (M�nimo: 10, M�ximo: 1000)");
			int cartas = leerConsola.leerValorRango(10, 1000);
			estrategia = new CartaACarta(cartas);

		}

		// Caso barajar mont�n a mont�n
		if (value == 1) {
			System.out.println("Por favor, seleccione cu�ntas cartas tendr� el mont�n (M�nimo: 2, M�ximo: 20)");
			int cartasMonton = leerConsola.leerValorRango(2, 20);
			System.out.println("Por favor, seleccione cu�ntos montones desea intercambiar (M�nimo: 2, M�ximo: 5)");
			int montonesCambiar = leerConsola.leerValorRango(2, 5);
			estrategia = new MontonAMonton(cartasMonton, montonesCambiar);
		}
		return estrategia;
	}
	
	/**
	 * M�todo para determinar el n�mero de partidas a jugar
	 * 
	 * @return int El n�mero de partidas a jugar
	 */
	public int elegirNumeroPartidas() {
		System.out.println("Por favor, elija el n�mero de partidas (M�nimo: 1, M�ximo: 100000)");
		return leerConsola.leerValorRango(1, 100000);
	}


	/**
	 * M�todo para elegir si ver toda la traza o solamente los resultados al
	 * ejecutar la partida
	 * 
	 * @param jugadores La lista de jugadores para ver si se muestra la traza o no
	 * 
	 * @return boolean Si se quiere ver la traza o no
	 */
	public boolean elegirVerTraza(ArrayList<JugadorAbstract> jugadores) {
		if (hayJugadorManual(jugadores)) {
			return true; // Si hay jugadores manuales se muestra
		} else {
			return false;
		}
	}

	/**
	 * M�todo que devuelve un jugador manejado por el algoritmo correspondiente
	 * 
	 * @param nombre Nombre del jugador
	 * @return JugadorAlgoritmo
	 */
	public JugadorAlgoritmo establecerRegla(String nombre) {
		// Definimos el JugadorAlgoritmo
		Regla regla = null;

		mostrarReglasUsuario();

		int eleccion = leerConsola.leerValorRango(0, 8);

		switch (eleccion) {
		case 0:
			regla = new ReglaAzar();
			break;
		case 1:
			regla = new ReglaPriorizarMasCuatro();
			break;
		case 2:
			regla = new ReglaNoPriorizarMasCuatro();
			break;
		case 3:
			regla = new ReglaPriorizarMasDos();
			break;
		case 4:
			regla = new ReglaNoPriorizarMasDos();
			break;
		case 5:
			regla = new ReglaPriorizarContarColores();
			break;
		case 6:
			regla = new ReglaNoPriorizarContarColores();
			break;
		case 7:
			regla = new ReglaPriorizarContarNumerosAcciones();
			break;
		case 8:
			regla = new ReglaNoPriorizarContarNumerosAcciones();
			break;
		}
		return null;
	}

	/**
	 * M�todo para mostar las reglas al usuario
	 */
	public void mostrarReglasUsuario() {
		System.out.println("Por favor, seleccione la regla que desea implementar");
		System.out.println(" - 0: Al azar");
		System.out.println(" - 1: Sacar el +4 lo antes posible");
		System.out.println(" - 2: Sacar el +4 lo m�s tarde posible");
		System.out.println(" - 3: Sacar el +2 lo antes posible");
		System.out.println(" - 4: Sacar el +2 lo m�s tarde posible");
		System.out.println(" - 5: Cuantas m�s veces sale un color, m�s probable es que "
				+ "respondamos con una carta de ese color");
		System.out.println(" - 6: Cuantas m�s veces sale un color, menos probable es que "
				+ "respondamos con una carta de ese color");
		System.out.println(" - 7: Cuantas m�s veces sale una carta num�rica o de acci�n, m�s probable es que "
				+ "respondamos con una carta num�rica o de acci�n respectivamente");
		System.out.println(" - 8: Cuantas m�s veces sale una carta num�rica o de acci�n, menos probable es que"
				+ " respondamos con una carta num�rica o de acci�n respectivamente");
	}

	/**
	 * M�todo para leer el nombre por consola
	 * 
	 * @return String el nombre del jugador
	 */
	public String leerNombreJugador() {
		String nombre;
		try {
			nombre = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: este valor no es v�lido");
			System.out.println("ERROR: se aplica el valor por defecto para las partidas");
			nombre = "jugador";
		}
		return nombre;
	}

}
