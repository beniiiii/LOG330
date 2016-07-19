package application;
import java.io.File;
import java.io.IOException;

import resources.Const;

public class VarianceController {

	public VarianceModel vModel;
	
	public VarianceController(VarianceModel model) {
		vModel = model;
	}
	
	public void calculAvecX(float x){
		vModel.calculerYSelonX(x);
	}
	
	public void calculAvecY(float y){
		vModel.calculerXSelonY(y);
	}
	
	public void extraireDonnees(File fichier, int typeExtraction) throws NumberFormatException, IOException{
		if(typeExtraction == Const.TYPE_X){
			vModel.extraireFichierListeSimple(fichier);
		}
		else if(typeExtraction == Const.TYPE_XY){
			vModel.extraireFichierListeDouble(fichier);
		}
	}
}