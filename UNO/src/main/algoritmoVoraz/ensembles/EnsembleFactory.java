package main.algoritmoVoraz.ensembles;

import main.algoritmoVoraz.ensembles.tipos.EnsembleRanking;
import main.algoritmoVoraz.ensembles.tipos.EnsembleSuma;
import main.algoritmoVoraz.ensembles.tipos.EnsembleVotacion;

/**
 * Clase que representa una factoría para generar los diferentes tipos de ensembles
 * @author Efrén García Valencia UO277189
 *
 */
public class EnsembleFactory {
	
	/**
	 * Método para crear el tipo de ensemble
	 * @param ensemble String
	 * @return Ensemble
	 */
	public static Ensemble crearEnsemble(String ensemble) {
		
		if (ensemble.equals("EnsembleSuma")) {
			return new EnsembleSuma();
		} else if (ensemble.equals("EnsembleRanking")) {
			return new EnsembleRanking();
		} else if (ensemble.equals("EnsembleVotacion")) {
			return new EnsembleVotacion();
		} else {
			return new EnsembleVotacion(); // Si no hay ningún ensemble devuelve el de votación
		}
	}

}
