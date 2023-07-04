package main.java.consola;

/**
 * Esta clase recoge el manual de ayuda implementado en el sistema
 * @author Efrén García Valencia UO277189
 *
 */
public class ManualAyuda {
	
	
	private InterfaceConsola interfaceConsola;
	
	
	/**
	 * Constructor con el manual de ayuda del sistema
	 * @param interfaceConsola La intefaz por consola
	 */
	public ManualAyuda(InterfaceConsola interfaceConsola) {
		this.interfaceConsola = interfaceConsola;
	}
	
	/**
	 * Método para invocar el menú de ayuda
	 */
	public void invocarMenuAyuda() {
		
		System.out.println("**************** MANUAL DE AYUDA ****************");
		System.out.println();
		System.out.println("UNOJava es una aplicación que permite a los usuarios jugar partidas al juego de cartas del UNO");
		System.out.println("y realizar experimentos con diferentes reglas de prioridad.");
		System.out.println("Para ello, diferencia entre dos tipos de usuarios: jugadores manuales, que introducen las instrucciones usando el teclado,");
		System.out.println("y jugadores automáticos, que utilizan diferentes reglas de prioridad para decidir qué carta jugar en su ronda.");
		System.out.println("Los usuarios pueden crear y jugar partidas combinando ambos tipos de jugadores. Al final de la ejecución del programa,");
		System.out.println("se mostrarán diferentes estadísticos obtenidos de las partidas.");
		System.out.println("La aplicación funciona cargando configuraciones. En cada configuración se incluyen los parámetros necesarios para la ejecución del sistema.");
		System.out.println();

		System.out.println("A continuación, se muestran diferentes secciones del manual de ayuda:");
		System.out.println("1 - Explicar las opciones de juego.");
		System.out.println("2 - Explicar qué son las reglas de prioridad.");
		System.out.println("3 - Explicar el contenido de los ficheros de entrada.");
		System.out.println("4 - Explicar los estadísticos del sistema.");
		System.out.println("5 - Salir del manual.");
		
		System.out.println("");
		System.out.print("Por favor seleccione una opción: ");
		
		int valor = interfaceConsola.getLeerConsola().leerValorRango(1, 5);
		if (valor == 1) {
			dejarEspacio();
			this.explicarOpcionesJuego();
		} 
		if (valor == 2) {
			dejarEspacio();
			this.explicarReglasPrioridad();
		} 
		if (valor == 3) {
			dejarEspacio();
			this.explicarContenidoFicheros();
		} 
		if (valor == 4) {
			dejarEspacio();
			this.explicarEstadisticosSistema();
		}
		if (valor == 5) {
			System.out.println();
			System.out.println();
			interfaceConsola.jugarPartida();
		} 
	}
	


	/**
	 * Método que explica las opciones de juego
	 */
	private void explicarOpcionesJuego() {
		
		System.out.println("**************** MANUAL DE AYUDA ****************");
		System.out.println("****   Explicación de las opciones de juego  ****");	
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("Las opciones que se ofrecen al usuario son las siguientes:");
		System.out.println();
		System.out.println("\t" + "Crear una configuración personalizada: el usuario puede crear en detalle su configuración a través de la consola de comandos.");
		System.out.println("\t\t" + "Permite, además, guardar la configuración creada en el fichero de entrada del usuario para que no tenga que escribirla de nuevo.");

		System.out.println("\t" + "Cargar configuraciones de ejemplo: permite que el usuario pueda seleccionar entre algunos ejemplos de uso del sistema");
		System.out.println("\t\t" + "para familiarizarse con su uso.");

		System.out.println("\t" + "Cargar configuraciones del fichero de entrada del usuario: Muestra las configuraciones almacenadas en el fichero de entrada");
		System.out.println("\t\t" + "configuracion.json para que el jugador seleccione la que desea utilizar.");
		System.out.println("\t\t" + "Adicionalmente, el jugador puede modificar el fichero para incluir nuevas configuraciones.");
		System.out.println("\t\t" + "No obstante, si el fichero queda mal escrito no se podrá leer,");
		System.out.println("\t\t" + "por lo que se recomienda almacenar los datos en un fichero aparte antes de modificarlo.");
		System.out.println();
		
		volverAtras();
	}
	
	/**
	 * Método que explica las reglas de prioridad
	 */
	private void explicarReglasPrioridad() {
		System.out.println("**************** MANUAL DE AYUDA ****************");
		System.out.println("****  Explicación de las reglas de prioridad ****");	
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("Las reglas de prioridad determinan el comportamiento de un jugador al robar una carta.");
		System.out.println("Por ejemplo, una regla que priorice las cartas +4 hará que el jugador eche esa carta en el momento que la tenga en su mano.");
		System.out.println("Por otro lado, están los ensembles. Un ensemble lo que hace es agrupar diferentes reglas de prioridad bajo algún criterio.");	
		System.out.println("Por ejemplo, el ensemble de votación lo que hace es ejecutar el algoritmo voraz para cada una de las reglas de prioridad");
		System.out.println("y seleccionar aquella carta que sea la más votada de todas.");
		
		System.out.println();
		System.out.println("A continuación, se indicarán los ensembles y reglas implementadas en el sistema.");
		System.out.println();
		
		System.out.println("Reglas:");
		System.out.println("\t" + "Azar: Al azar");
		System.out.println("\t" + "PrimeraCarta: Sacar la primera carta que encuentra en la mano.");
		System.out.println("\t" + "PriorizarCartaNumerica: Priorizar las cartas numéricas.");
		System.out.println("\t" + "PriorizarCartaAccion: Priorizar las cartas de acción.");
		System.out.println("\t" + "PriorizarCartasRobar: Prioriza las cartas +4 y +2, el resto de cartas al azar.");
		System.out.println("\t" + "PriorizarComodines: Prioriza las cartas comodín, resto de cartas al azar.");
		System.out.println("\t" + "NoPriorizarCartasRobar: Sacar las cartas +4 y +2 lo más tarde posible, el resto de cartas al azar.");
		System.out.println("\t" + "PriorizarCartasAccionNoComodin: Priorizar las cartas de acción que no sean comodines.");
		System.out.println("\t" + "CambiarColorMedio: Priorizar las cartas que permitan cambiar el color del medio.");
		System.out.println("\t" + "NoCambiarColorMedio: Priorizar las cartas que mantengan el color del medio.");
		System.out.println("\t" + "CompararTiposCartasMasFrecuente: Cuantas más veces sale una carta numérica o de acción, \n\t\t  más probable es que respondamos con una carta numérica o de acción respectivamente.");
		System.out.println("\t" + "CompararTiposCartasMenosFrecuente: Cuantas más veces sale una carta numérica o de acción, \n\t\t  menos probable es que respondamos con una carta numérica o de acción respectivamente.");
		System.out.println("\t" + "ContarColoresMasFrecuente: Cuantas más veces sale un color, más probable es que respondamos con una carta de ese color.");
		System.out.println("\t" + "ContarColoresMenosFrecuente: Cuantas más veces sale un color, menos probable es que respondamos con una carta de ese color.");
		System.out.println();
		
		System.out.println("Ensembles:");
		System.out.println("\t" + "EnsembleVotacion: el sistema seleccionará la carta más votada entre todas las reglas de prioridad que implemente el jugador automático.");
		System.out.println("\t\t" + "La carta más votada será aquella que se repita más veces con mayor valor para cada regla de prioridad que implemente el jugador.");
		System.out.println("\t" + "EnsembleRanking: el sistema seleccionará la carta que tenga una mejor posición a nivel global de todas las reglas de prioridad que implemente el jugador automático.");
		System.out.println("\t\t" + "La carta con mejor posición a nivel global se obtendrá de la siguiente manera:");
		System.out.println("\t\t -" + "El sistema ordenará las cartas que devuelvan cada una de las reglas de prioridad por su valor, de mayor a menor.");
		System.out.println("\t\t -" + "La carta que tenga una mejor posición a nivel global considerando todos los conjuntos de reglas que implementa el jugador automático \n\t\t  será la carta más valorada por el ensemble.");
		System.out.println("\t" + "EnsembleSuma: el sistema seleccionará la carta con mayor valor global entre todas las reglas de prioridad que implemente el jugador automático.");
		System.out.println("\t\t" + "La carta con mayor valor global se obtendrá sumando el valor de las cartas que devuelvan todas las reglas de prioridad que implemente el jugador.");
		System.out.println();

		volverAtras();
	}
	
	/**
	 * Método que explica el contenido del fichero
	 */
	private void explicarContenidoFicheros() {
		System.out.println("**************** MANUAL DE AYUDA ****************");
		System.out.println("*****   Explicación del fichero de entrada  *****");	
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("Los parámetros que el sistema necesita para ejecutar una configuración son los siguientes:");
		System.out.println();
		System.out.println("\t" + "nombre_configuracion: el nombre del archivo CSV de salida.");
		System.out.println("\t" + "jugadores: la lista de jugadores del sistema. Cada jugador tiene dos parámetros:");
		System.out.println("\t" + "    nombre: indica el nombre del jugador.");
		System.out.println("\t" + "    regla: indica las reglas que implementa. Cada jugador puede implementar tantas reglas como desea separadas por comas.");
		System.out.println("\t" + "           Para indicar que el jugador es manual, en este campo se pone \"no_aplica\".");
		System.out.println("\t" + "formaBarajar: indica la forma de barajar las cartas al principio de la partida. Hay dos estrategias que se recomiendan en el sistema:");
		System.out.println("\t" + "    CartaACarta: se desplazan cartas sueltas de forma aleatoria. parametroInicial es el número de cartas a cambiar, en parametroAdicional se indica \"no_aplica\".");
		System.out.println("\t" + "    MontonAMonton: desplaza cartas de montón a montón. parametroInicial indica las cartas que hay en el montón y parametroAdicional el número de montones a intercambiar.");
		System.out.println("\t" + "ensemble: indica el ensemble que implementan los jugadores automáticos. Este valor es obligatorio incluso si sólo implementan una regla, aunque no se llegue a aplicar.");
		System.out.println("\t" + "          Si sólo hay jugadores manuales, se puede dejar como \"no_aplica\".");
		System.out.println("\t" + "numero_partidas: el número de partidas que tiene el juego.");
		System.out.println("\t" + "traza: indica si se generan ficheros de logs al finalizar la partida o no. Cada fichero contendrá los datos de 100 partidas de juego.");
		System.out.println();
		System.out.println("Es importante destacar que todos los jugadores automáticos deben implementar al menos una regla de prioridad.");
		System.out.println();
		
		volverAtras();
	}


	
	/**
	 * Método que explica los estadísticos del sistema
	 */
	private void explicarEstadisticosSistema() {
		System.out.println("**************** MANUAL DE AYUDA ****************");
		System.out.println("****  Explicación de las reglas de prioridad ****");	
		System.out.println("*************************************************");
		System.out.println();
		
		System.out.println("En los estadísticos del sistema se muestran las cartas que juega cada jugador, las veces que gana y el ganador de la configuración.");
		System.out.println("También se incluyen dos valores adicionales:");
		System.out.println("\t" + "Veces que ha intentado hacer trampa: indica las veces que el jugador ha intentado echar una carta que no debe o robar cuando no le toca.");
		System.out.println("\t" + "Partidas descartadas: este parámetro aparece por consola y muestra el número de partidas que se descartan al ser durar demasiado.");
		System.out.println("\t\t" + "La razón por la que esto ocurre se puede estudiar en la memoria del proyecto en mayor profundidad.");
		System.out.println();

		volverAtras();
	}
	
	
	/**
	 * Método para volver atrás en las opciones del menú de ayuda
	 */
	private void volverAtras() {
		
		System.out.println("Por favor, seleccione una opción:");
		System.out.println("1 - Volver al principio del manual.");
		System.out.println("2 - Salir del manual de ayuda.");
		System.out.println();
		System.out.print("Opción seleccionada: ");
		
		int valor = interfaceConsola.getLeerConsola().leerValorRango(1, 2);
		if (valor == 1) {
			dejarEspacio();
			this.invocarMenuAyuda();
		} else if (valor == 2) {
			System.out.println();
			System.out.println();
			interfaceConsola.jugarPartida();
		}
	}
	
	
	/**
	 * Para dejar espacio en la consola
	 */
	private void dejarEspacio() {
		System.out.println();
		System.out.println();
	}
	

}
