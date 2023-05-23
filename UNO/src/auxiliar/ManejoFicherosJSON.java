package auxiliar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import algoritmoVoraz.ensembles.Ensemble;
import algoritmoVoraz.reglas.ReglasCompuestas;
import algoritmoVoraz.reglas.reglasQueNoMiranHistorial.ReglaAzar;
import juego.baraja.BarajarStrategy;
import juego.baraja.CartaACarta;
import juego.configurarJuego.Configuracion;
import juego.jugador.JugadorAbstract;
import juego.jugador.JugadorAlgoritmo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para estudiar el manejo  de ficheros JSON para las entradas
 * @author Efrén García Valencia UO277189
 *
 */
public class ManejoFicherosJSON {
	
	// ATRIBUTOS

	// RUTAS
	static String rutaConfiguracion = ".\\ficheros\\configuracion.json"; // Uso una ruta relativa
	

	
	  public static void main(String[] args) {
	    	
	    	leerJSON();
	    }

	

	/**
	 * Método para leer las configuraciones de un fichero JSON
	 * 
	 */
	public static ArrayList<Configuracion> leerJSON() {
		
		ObjectMapper mapper = new ObjectMapper();
        File readFile = new File(rutaConfiguracion); // Se carga la ruta
        
        ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();

        try {
            JsonNode jsonTree = mapper.readTree(readFile);
            JsonNode configuracionesNode = jsonTree.get("configuraciones");        
            for (JsonNode configuracionNode : configuracionesNode)
            	// Almacenamos las configuraciones en el array que luego devolvemos
            	configuraciones.add(generarConfiguracion(configuracionNode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return configuraciones;
	}



	/**
	 * Método para generar una configuración en base al contenido de la parte de un JSON
	 * @param configuracionNode El nodo con los datos de la configuración
	 * @return La configuración a cargar
	 */
	private static Configuracion generarConfiguracion(JsonNode configuracionNode) {
		String nombreConfiguracion = configuracionNode.get("nombre_configuracion").asText();
		
		String ensemble = configuracionNode.get("ensemble").asText();
		int numeroPartidas = configuracionNode.get("numero_partidas").asInt();
		boolean traza = configuracionNode.get("traza").asBoolean();
		
		return null;
		
	}

}
