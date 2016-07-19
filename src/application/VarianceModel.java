package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import resources.Calcs;

public class VarianceModel {

	private float variance;
	private float ecartType;
	private float correlation;
	private float pente;
	private float constante;
	private float xSelonY;
	private float ySelonX;
	private float intervalle;
	
	private String regression;
	
	private ArrayList<Float> listeX;
	private ArrayList<Float> listeY;
	
	public VarianceModel() {
		
		variance = 0;
		ecartType = 0;
		correlation = 0;
		pente = 0;
		constante = 0;
		xSelonY = 0;
		ySelonX = 0;
		intervalle = 0;
		
		regression = "";
		
		listeX = new ArrayList<Float>();
		listeY = new ArrayList<Float>();
	}
	
	public void extraireFichierListeSimple(File fichier) throws NumberFormatException, IOException{
		
		FileReader lecteurFichier = new FileReader(fichier.getAbsolutePath());
		BufferedReader bufferedReader = new BufferedReader(lecteurFichier);
		String line;
		
		if(fichier.getAbsolutePath().endsWith(".csv")){
			
			listeX.clear();
			while((line = bufferedReader.readLine()) != null){
				float nombre = Float.parseFloat(line);
				listeX.add(nombre);
			}
		}
		
		bufferedReader.close();
		
		calculerVarianceEcartType();
	}
	
	private String changerVirguleParPoint(String donnee){
		
		donnee = donnee.replace(",", ".");
		
		return donnee;
	}
	
	public void extraireFichierListeDouble(File fichier) throws NumberFormatException, IOException{
		
		FileReader lecteurFichier = new FileReader(fichier.getAbsolutePath());
		BufferedReader bufferedReader = new BufferedReader(lecteurFichier);
		String line;
		
		if(fichier.getAbsolutePath().endsWith(".csv")){

			listeX.clear();
			listeY.clear();
			while((line = bufferedReader.readLine()) != null){

				String ligneX = "";
				String ligneY = "";
				String[] ligneSplit = line.split(";");
				ligneX = ligneSplit[0];
				
				if(ligneSplit.length > 1){
					
					if(ligneX.contains(",")){
						
						ligneX = changerVirguleParPoint(ligneX);
					}
					
					if(ligneSplit.length > 1){
						
						ligneY = ligneSplit[1];
						
						if(ligneY.contains(",")){
							
							ligneY = changerVirguleParPoint(ligneY);
						}
					}

					float nombre = Float.parseFloat(ligneX);
					listeX.add(nombre);
					
					if (!ligneY.equals("")){
						
						nombre = Float.parseFloat(ligneY);
						listeY.add(nombre);
					}
				}
			}
		}
		
		bufferedReader.close();
		
		calculerCorrelation();
		calculerFonctionXY();
		calculerIntervalle();
	}
	
	public void calculerXSelonY(float y){
		
		xSelonY = (y-constante)/pente;
	}
	
	public void calculerYSelonX(float x){
		
		ySelonX = (pente*x)+constante;
	}
	
	public void calculerVarianceEcartType(){
		
		variance = Calcs.Variance(listeX);
		ecartType = Calcs.EcartType(listeX);
	}
	
	public void calculerCorrelation(){
		
		correlation = Calcs.Correlation(listeX, listeY);
	}

	public void calculerFonctionXY(){
		
		Float[] reg = Calcs.Regression(listeX, listeY);
		
		pente = reg[0];
		constante = reg[1];
		
		regression = "y = " + pente + " * x + " + constante;
	}
	
	public void calculerIntervalle(){
		
		intervalle = Calcs.Student(listeX, listeY);
	}
	
	public float getVariance(){
		
		return variance;
	}
	
	public float getEcartType(){
		
		return ecartType;
	}
	
	public float getCorrelation(){
		
		return correlation;
	}
	
	public String getRegression(){
		
		return regression;
	}
	
	public float getXSelonY(){
		
		return xSelonY;
	}
	
    public float getYSelonX(){
		
		return ySelonX;
	}
	
	public float getIntervalle(){
		
		return intervalle;
	}
}