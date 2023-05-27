package manejoDatos.manejoFicheros;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import algoritmoVoraz.ensembles.Ensemble;
import algoritmoVoraz.ensembles.EnsembleRanking;
import algoritmoVoraz.ensembles.EnsembleSuma;
import algoritmoVoraz.ensembles.EnsembleVotacion;
import algoritmoVoraz.ensembles.NoEnsemble;
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
import juego.baraja.estrategiasBaraja.BarajarStrategy;
import juego.baraja.estrategiasBaraja.CartaACarta;
import juego.baraja.estrategiasBaraja.MontonAMonton;
import juego.jugador.JugadorAbstract;
import juego.jugador.JugadorAutomatico;
import juego.jugador.JugadorManual;
import manejoDatos.Configuracion;

/**
 * Clase para estudiar el manejo  de ficheros JSON de la aplicación
 * @author Efrén García Valencia UO277189
 *
 */
public class ManejoFicherosJSON {
	
	// ATRIBUTOS
	private String nombreFicheroEntrada = "configuracion";
	private String nombreFicheroEjemplos = "ejemplosBasicos";

	// RUTAS
	static String rutaConfiguracion = ".\\ficheros\\entradas\\"; // Uso una ruta relativa


	/**
	 * Método para leer las configuraciones del fichero JSON
	 * @param nombreJSON
	 * @return ArrayList<Configuracion>
	 */
	public ArrayList<Configuracion> leerJSON(String nombreJSON) {
		
		ObjectMapper mapper = new ObjectMapper();
        File readFile = new File(rutaConfiguracion + nombreJSON + ".json"); // Se carga la ruta
        
        ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();

        try {
            JsonNode jsonTree = mapper.readTree(readFile);
            JsonNode configuracionesNode = jsonTree.get("configuraciones");        
            for (JsonNode configuracionNode : configuracionesNode)
            	// Almacenamos las configuraciones en el array que luego devolvemos
            	configuraciones.add(generarConfiguracion(configuracionNode));
        } catch (IOException e) {
        	System.out.println("HA SURGIDO UN PROBLEMA AL LEER LOS NODOS DEL FICHERO. EL PROGRAMA PROCEDERÁ A CERRARSE");
        } catch (IllegalArgumentException e) {
        	System.out.println("HA SURGIDO UN PROBLEMA AL VALIDAR LOS DATOS INTRODUCIDOS. EL PROGRAMA PROCEDERÁ A CERRARSE");
        }  catch (NullPointerException e) {
        	System.out.println("HA SURGIDO UN PROBLEMA AL LEER LOS NODOS DEL FICHERO. EL PROGRAMA PROCEDERÁ A CERRARSE");
        }
        
        
        return configuraciones;
	}
	
	
	/**
	 * Método que permite reescribir al final una nueva configuración a indicar en un JSON
	 * @param nombreFicheroJSON El nombre del fichero JSON
	 * @param nuevaConfiguracion La nueva configuración a indicar
	 */
	public void reescribirFicheroJSON(String nombreFicheroJSON, Configuracion nuevaConfiguracion) {
		
		// Primero recopilamos las configuraciones existentes si las hay
        ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
        try {
        	configuraciones = this.leerJSON(nombreFicheroJSON);
        } catch (Exception e) {
        	// Si surge algún problema, quiere decir que ese fichero o está vacío o está mal configurado
        	// No es un problema porque lo vamos a reescribir otra vez
        }
        
        // Añadimos la nueva configuración
        configuraciones.add(nuevaConfiguracion);
        
        try {
        	// A continuación se borran los datos del fichero
        	FileWriter fileEmpty = new FileWriter(rutaConfiguracion + nombreFicheroJSON + ".json");  
        	fileEmpty.close();
        	
        	String jsonFinalString = generarStringJSON(configuraciones);
        	
        	// Volvemos a volcar los datos otra vez
        	 FileWriter fileFull = new FileWriter(rutaConfiguracion + nombreFicheroJSON + ".json");
        	 fileFull.write(jsonFinalString);
             fileFull.close();
             
        } catch(Exception e) {
        	System.out.println("Ha ocurrido un error al guardar la nueva configuración");
        }
	}
	
	/**
	 * Método que te devuelve un string con toda la información que se vuelca desde el JSON
	 * @param configuraciones ArrayList<Configuracion>
	 * @return String
	 */
	private String generarStringJSON(ArrayList<Configuracion> configuraciones) {
		// En este punto se genera la cadena string con los datos a meter
		String configuracionesStr = "";
		for (int i = 0; i < configuraciones.size(); i++) {
			if (i == configuraciones.size() -1) {
				configuracionesStr += escribirConfiguracionEnJSON(configuraciones.get(i));
			} else {
				configuracionesStr += escribirConfiguracionEnJSON(configuraciones.get(i)) + ",\n";
			}
		}
		
		String jsonFinalString = "{\n" +
			    "  \"configuraciones\": [\n" + configuracionesStr +
			    "\n  ]\n" +
			    "}";
		
		return jsonFinalString;
	}
	
	/**
	 * Método para escribir código java en formato JSON
	 * @param configuracion La configuración a aplicar
	 * @return El string con la configuración
	 */
	private String escribirConfiguracionEnJSON(Configuracion configuracion) {
		
		// Sacamos el JSON de cada jugador
		String stringJugadores = "";
		
		for (int i = 0; i < configuracion.getJugadoresPartida().size(); i++) {
			if (i == configuracion.getJugadoresPartida().size() - 1) {
				stringJugadores += configuracion.getJugadoresPartida().get(i).getJSON() + "\n";
			} else {
				stringJugadores += configuracion.getJugadoresPartida().get(i).getJSON() + ",\n";
			}
		}
		
		// Definimos el formato JSON a indicar
		String configuracionString = "    {" +
				"\n      \"nombre_configuracion\": \"" + configuracion.getNombreConfiguracion() + "\"," +
			    "\n      \"jugadores\": [\n" + stringJugadores + "\n      ]," +
			    "\n      \"estrategia\": {\n" + configuracion.getEstrategiaBaraja().getJSON() +  "\n      }," +
			    "\n      \"ensemble\": \"" + configuracion.getEnsemble().getJSON() + "\"," +
			    "\n      \"numero_partidas\": " + configuracion.getNumeroPartidas() + "," + 
			    "\n      \"traza\": " + configuracion.getTraza()+
			"\n    }";
				
		return configuracionString;
	}



	/**
	 * Método para generar una configuración en base al contenido de la parte de un JSON
	 * @param configuracionNode El nodo con los datos de la configuración
	 * @return La configuración a cargar
	 */
	private Configuracion generarConfiguracion(JsonNode configuracionNode) {
		String nombreConfiguracion = configuracionNode.get("nombre_configuracion").asText();
		ArrayList<JugadorAbstract> jugadores = generarJugadores(configuracionNode.get("jugadores"));
		BarajarStrategy estrategia = generarEstrategia(configuracionNode.get("estrategia"));
		Ensemble ensemble = generarEnsemble(configuracionNode.get("ensemble").asText());
		int numeroPartidas = configuracionNode.get("numero_partidas").asInt();
		boolean traza = configuracionNode.get("traza").asBoolean();
		
		// Revisamos la configuración y lanzamos una excepción de ser necesario
		revisarConfiguracion(nombreConfiguracion, jugadores, estrategia, ensemble, numeroPartidas, traza);
		
		if (nombreConfiguracion.isBlank() || nombreConfiguracion.isEmpty()) {
			System.out.println("ERROR AL CARGAR EL NOMBRE DE LA CONFIGURACIÓN");
		}
		
		return new Configuracion(nombreConfiguracion, jugadores, estrategia, ensemble, numeroPartidas, traza);
		
	}



	/**
	 * Método que lanza una excepción si hay algún fallo al cargar una configuración
	 * @param nombreConfiguracion 	El nombre de la configuración 
	 * @param jugadoresPartida 		Los jugadores de las partidas
	 * @param estrategiaBaraja 		La estrategia de la baraja
	 * @param ensemble 				El ensemble a aplicar
	 * @param numeroPartidas 		El número de partidas
	 * @param traza 				Para mostrar la traza
	 */
	private void revisarConfiguracion(String nombreConfiguracion, ArrayList<JugadorAbstract> jugadores,
			BarajarStrategy estrategia, Ensemble ensemble, int numeroPartidas, boolean traza) {

		boolean configuracionOK = true;
		
		// Nombre de la configuración
		
		if (nombreConfiguracion.isBlank() || nombreConfiguracion.isEmpty()) {
			System.out.println("Nombre no válido");
			configuracionOK = false;
		}
		
		// Jugadores
		
		if (jugadores == null) {
			System.out.println("Ha surgido un problema al cargar los jugadores");
			configuracionOK = false;
		}
		
		// Numero de partidas
		
		if (numeroPartidas < 1) {
			System.out.println("El número mínimo de partidas ha de ser uno");
			configuracionOK = false;
		}
	
		if (!configuracionOK) {
			throw new IllegalArgumentException();
		}
	}



	/**
	 * Método que devuelve el ensemble a aplicar pasando un string
	 * @param ensemble El texto a traducir
	 * @return Ensemble
	 */
	private Ensemble generarEnsemble(String ensemble) {
		if (ensemble.equals("EnsembleSuma")) {
			return new EnsembleSuma();
		} else if (ensemble.equals("EnsembleRanking")) {
			return new EnsembleRanking();
		} else if (ensemble.equals("EnsembleVotacion")) {
			return new EnsembleVotacion();
		} else {
			return new NoEnsemble(); // No se corresponde con ningún ensemble
		}
	}


	/**
	 * Método que devuelve la forma de barajar a través de un elemento JsonNode
	 * @param barajarNode JsonNode
	 * @return BarajarStrategy 
	 */
	private BarajarStrategy generarEstrategia(JsonNode barajarNode) {
		String tipo = barajarNode.get("tipo").asText();
		if (tipo.equals("CartaACarta")) {
			return new CartaACarta(barajarNode.get("parametroInicial").asInt());
		} else if (tipo.equals("MontonAMonton")) {
			return new MontonAMonton(barajarNode.get("parametroInicial").asInt(), barajarNode.get("parametroAdicional").asInt());
		} else {
			return null; // Ha ocurrido un error
		}
	}



	/**
	 * Método que devuelve el array de jugadores a generar
	 * @param jsonNode El node con el que se trabaja
	 * @return ArrayList<JugadorAbstract>
	 */
	private ArrayList<JugadorAbstract> generarJugadores(JsonNode jugadoresNode) {
		ArrayList<JugadorAbstract> jugadores = new ArrayList<JugadorAbstract>();
		for (JsonNode jugador : jugadoresNode) {
			String reglas = jugador.get("regla").asText();
			if (reglas.equals("no_aplica")) { // Jugador manual
				jugadores.add(new JugadorManual(jugador.get("nombre").asText()));
			} else { // JugadorAutomatico
				ArrayList<Regla> reglasJugador = evaluarStringRegla(reglas);
				if (reglasJugador == null) {
					return null; // Devuelve nulo porque algo salió mal
				} else {
					jugadores.add(new JugadorAutomatico(jugador.get("nombre").asText(), reglasJugador));
				}
				
			}
		}
		if (jugadores.isEmpty() || jugadores.size() < 2) {
			return null; // No puede haber ningún jugador o menos de dos jugadores
		} else {
			return jugadores;
		}
	}
	
	
	/**
	 * Método para leer un string y devolver un array con las reglas a aplicar
	 * 
	 * @param string El valor String
	 * @return ArrayList<Regla> Las reglas a aplicar
	 */
	private ArrayList<Regla> evaluarStringRegla(String value) {
		
		ArrayList<Regla> reglasJugador = new ArrayList<Regla>();
		
		if (value.contains(",")) {
			String [] reglas = value.split(",");
			for (String regla : reglas) {
				reglasJugador.add(sacarRegla(regla));
			}
		} else {
			// Si es una sola regla
			reglasJugador.add(sacarRegla(value));
		}
		
		if (reglasJugador.contains(null)) {
			return null; // Si hay un nulo significa que algo cargó mal
		}
		
		return reglasJugador;
	}
	
	/**
	 * Método que saca la regla del string que le pases
	 * @param reglaString El string a evaluar
	 * @return Regla
	 */
	private Regla sacarRegla(String reglaString) {
		Regla regla = null;

		if (reglaString.contains("ReglaAzar"))
			regla = new ReglaAzar();

		if (reglaString.contains("ReglaPriorizarMasCuatro"))
			regla = new ReglaPriorizarMasCuatro();

		if (reglaString.contains("ReglaNoPriorizarMasCuatro"))
			regla = new ReglaNoPriorizarMasCuatro();

		if (reglaString.contains("ReglaPriorizarMasDos"))
			regla = new ReglaPriorizarMasDos();

		if (reglaString.contains("ReglaNoPriorizarMasDos"))
			regla = new ReglaNoPriorizarMasDos();

		if (reglaString.contains("ReglaPriorizarContarColores"))
			regla = new ReglaPriorizarContarColores();

		if (reglaString.contains("ReglaNoPriorizarContarColores"))
			regla = new ReglaNoPriorizarContarColores();

		if (reglaString.contains("ReglaPriorizarContarNumerosAcciones"))
			regla = new ReglaPriorizarContarNumerosAcciones();

		if (reglaString.contains("ReglaNoPriorizarContarNumerosAcciones"))
			regla = new ReglaNoPriorizarContarNumerosAcciones();

		return regla;
	}
	
	
	/**
	 * Método que muestra en consola las diferentes configuraciones que hay
	 * @param configuraciones El array con las configuraciones
	 */
	public void mostrarDatosFichero(ArrayList<Configuracion> configuraciones) {
		System.out.println();
		for (int i = 0; i < configuraciones.size(); i++) {
			System.out.print(i+1 + "- ");
			System.out.println("\tNombre de la configuración: " + configuraciones.get(i).getNombreConfiguracion());
			System.out.println("\tJugadores:");
			for (JugadorAbstract jugador: configuraciones.get(i).getJugadoresPartida()) {
				if (jugador instanceof JugadorManual) {
					System.out.println("\t   MANUAL \n\t       Nombre: " + jugador.getNombreJugador());
				} else {
					System.out.print("\t   AUTOMATICO \n\t       Nombre: " + jugador.getNombreJugador() + "\n\t       Reglas que aplica: ");
					
					for (Regla regla : ((JugadorAutomatico)jugador).getReglas()){
						System.out.print(regla.toString() + " ");
					}
					System.out.println();
				}
			}
			System.out.println("\tEstrategia de la baraja: " + configuraciones.get(i).getEstrategiaBaraja().toString());
			System.out.println("\tEnsemble a aplicar: " + configuraciones.get(i).getEnsemble().toString());
			System.out.println("\tNúmero de partidas: " + configuraciones.get(i).getNumeroPartidas());
			System.out.println("\tSe muestra la traza de las partidas: " + configuraciones.get(i).getTraza());
			
			// Salto de línea
			System.out.println();
		}
		
	}
	
	/**
	 * Método que te devuelve el nombre del fichero que se aplica en la entrada
	 * @return String
	 */
	public String getFicheroEntrada() {
		return this.nombreFicheroEntrada;
	}


	/**
	 * Método que devuelve el nombre del fichero con los ejemplos básicos
	 * @return String
	 */
	public String getFicheroEjemplos() {
		return this.nombreFicheroEjemplos;
	}


}
