package main.java.algoritmoVoraz.ensembles;

import main.java.algoritmoVoraz.ensembles.tipos.EnsembleRanking;
import main.java.algoritmoVoraz.ensembles.tipos.EnsembleSuma;
import main.java.algoritmoVoraz.ensembles.tipos.EnsembleVotacion;

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
			return null;
		}
	}

}
