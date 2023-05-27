package main.manejoDatos.manejoConsola;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase para leer por consola
 * @author Efrén García Valencia UO277189
 *
 */
public class LeerConsola {
	
	
	private Scanner sc = new Scanner(System.in);
	
    
	/**
	 * Método para pedir un valor entero por consola entre un rango de valores
	 * @param msg String
	 * @param min int
	 * @param max int
	 * @return int
	 */
    public int intRango(String msg, int min, int max) {
    	
    	// Se parte de un valor muy elevado
        int eleccion = (int) Float.POSITIVE_INFINITY;
        
        
        while (eleccion >= max || eleccion < min) {
            System.out.println(msg);
            
            try {
            	eleccion = sc.nextInt();
                if (eleccion >= max || eleccion < min) {
                    System.out.println("ERROR: sólo se aceptan valores entre " + min + " y " + (max-1));
                }           	
            } catch (InputMismatchException e) {
                sc.next(); // Para que no genere bucle infinito
                eleccion = -100;
                break;
            }
   
       }
        return eleccion;
    }
    
    /**
     * Método para leer un único valor de entrada 
     * @param i El valor a leer
     * @return int
     */
	public int leerCualquierValor() {
		int value = 0;
		try {
			value = sc.nextInt();
		} catch (InputMismatchException e) {
			sc.next();
		}
		// Se avanza de línea
		return value;
	}

    
	/**
	 * Método para pedir un valor int válido en un rango
	 * 
	 * @param min El valor más pequeño
	 * @param max El valor mayor
	 * @return int
	 */
	public int leerValorRango(int min, int max) {
		int value = 0;
		try {
			value = sc.nextInt();
			while (value < min || value > max) {
				System.out.print("Por favor, seleccione un valor válido: ");
				value = sc.nextInt();
			}
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("ERROR: este valor no es válido");
			System.out.println("ERROR: se aplica el valor por defecto para las partidas");
			value = min;
		}
		// Se avanza de línea
		return value;
	}
	
	
	/**
	 * Método para leer el nombre por consola
	 * 
	 * @return String el nombre del jugador
	 */
	public String leerLinea() {
		@SuppressWarnings("resource")
		Scanner scLinea = new Scanner(System.in);
		String nombre = "";
		try {
			nombre = scLinea.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("ERROR: este valor no es válido");
			System.out.println("ERROR: se aplica el valor por defecto para las partidas");
			nombre = "jugador";
		}
		return nombre;
	}
}
