package resources;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Calcs {

	
	public static float Somme(ArrayList<Float> lstFloat){
		
		float total = 0;
		
		for (Float donnee : lstFloat) {
			total = total + donnee;
		}
		
		return total;
	}
	
	public static float Moyenne(ArrayList<Float> lstFloat){
		
		float total = 0;
		float moyenne = 0;
		
		total = Somme(lstFloat);
		
		moyenne = total / lstFloat.size();
		
		return moyenne;
	}
	
	public static float Racine(float nombre){
		
		float valeurTemp = 0;
		float racineCarre = nombre / 2;
	 
		do {
			valeurTemp = racineCarre;
			racineCarre = (valeurTemp + (nombre / valeurTemp)) / 2;
		} while ((valeurTemp - racineCarre) != 0);
	 
		return racineCarre;
	}
	
	public static float SommeDistance(ArrayList<Float> lstFloat){
		
		float sommeDistances = 0;
		float moyenne = Moyenne(lstFloat);
		
		for (Float donnee : lstFloat) {
			sommeDistances = sommeDistances + ((donnee - moyenne)*(donnee - moyenne));
		}
		
		return sommeDistances;
	}
	
	/** Les donn�es de la liste qui ont un indice impair sont consid�r�es comme la liste X
	    et les donn�es avec un indice pair consitutent la liste Y.
	    La premi�re donn�e de la liste indique le nombre de donn�es.
	    i.e. lstFloat.get(1) donne la premi�re donn�e en X et
	    lstFloat.get(2) donne la premi�re donn�e en Y.**/
	public static float Correlation(ArrayList<Float> listeX, ArrayList<Float> listeY){
		
		float corr = 0;
		float n = 0;
		float sommeXMultipliY = 0;
		float sommeX = 0;
		float sommeY = 0;
		float sommeXCarre = 0;
		float sommeYCarre = 0;
		
		sommeX = Somme(listeX);
		sommeY = Somme(listeY);
		
		if(n != listeX.size()){
			n = listeX.size();
		}
		
		//Fait la somme des valeurs de X au carr�
		for (Float donnee : listeX) {
			sommeXCarre = sommeXCarre + (donnee * donnee);
		}
		
		//Fait la somme des valeurs de Y au carr�
		for (Float donnee : listeY) {
			sommeYCarre = sommeYCarre + (donnee * donnee);
		}
		
		//Fait la somme de (Xi * Yi)
		for(int i = 0; i < listeX.size(); i++){
			sommeXMultipliY = sommeXMultipliY + (listeX.get(i) * listeY.get(i));
		}
		
		corr = ((n*sommeXMultipliY)-(sommeX*sommeY))/Racine(((n*sommeXCarre)-(sommeX*sommeX))*((n*sommeYCarre)-(sommeY*sommeY)));
		
		return corr;
	}
	
	public static Float[] Regression(ArrayList<Float> listeX, ArrayList<Float> listeY){
		Float[] regression = new Float[2];
		float sommeXMultipliY = 0;
		int n = listeX.size();
		
		float moyenneX = Moyenne(listeX);
		float moyenneY = Moyenne(listeY);
		
		for(int i = 0; i < listeX.size(); i++){
			sommeXMultipliY = sommeXMultipliY + (listeX.get(i) * listeY.get(i));
		}
		
		float pente = sommeXMultipliY - n*moyenneX*moyenneY;
		float constante = moyenneY - pente*moyenneX;
		
		regression[0] = pente;
		regression[1] = constante;
		
		return regression;
	}
	
	public static float Variance(ArrayList<Float> lstFloat){
		
		return SommeDistance(lstFloat)/(lstFloat.size() - 1);
	}
	
	public static float EcartType(ArrayList<Float> lstFloat){
		
		return Racine(Variance(lstFloat));
	}
	
	public static float Student(ArrayList<Float> listeX, ArrayList<Float> listeY){
		
		float intervalle = 0;
		float n = listeX.size();
		float xMoy = Moyenne(listeX);
		float yMoy = Moyenne(listeY);
		float b1 = 0;
		float b0 = 0;
		float ecartType = 0;
		float racineIntervalle = 0;
		
		ArrayList<Float> listeXCarre = new ArrayList<Float>();
		ArrayList<Float> listeXfoisY = new ArrayList<Float>(); 
		ArrayList<Float> listeXiXmoy = new ArrayList<Float>();
		ArrayList<Float> listeCalculEcartType = new ArrayList<Float>();
		
		for (Float donnee : listeX) {
			listeXCarre.add(donnee*donnee);
			listeXiXmoy.add((donnee-xMoy)*(donnee-xMoy));
		}
		
		for (int i = 0; i<listeX.size(); i++){
			listeXfoisY.add(listeX.get(i) * listeY.get(i));
		}
		
		//Cette partie calcule b1 et b0
		
		b1 = (Somme(listeXfoisY)-(n*xMoy*yMoy))/(Somme(listeXCarre)-(n*xMoy*xMoy));
		b0 = yMoy-(b1*xMoy);
		
		//Cette partie calcule l'�cart-type

		for (int i = 0; i<listeX.size(); i++){
			float donnee = listeY.get(i) - b0 - (b1*listeX.get(i));
			listeCalculEcartType.add(donnee);
		}
		
		ecartType = EcartType(listeCalculEcartType);
		//ecartType = Racine((1/(n-2))*Somme(listeCalculEcartType));
		
		//Cette partie calcule l'intervalle
		
		racineIntervalle = Racine(1+(1/n)+(((Const.LOC_EST-xMoy)*(Const.LOC_EST-xMoy))/Somme(listeXiXmoy)));
		
		intervalle = Const.T*ecartType*racineIntervalle;
		
		return intervalle;
	}
}