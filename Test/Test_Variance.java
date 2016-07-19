package Test_Variance;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.bind.ParseConversionEvent;

import org.junit.Before;
import org.junit.Test;

import resources.Calcs;

public class Test_Variance {

	@Test
	public void TestBorneInf() {
		ArrayList<Float> listeDonnees = new ArrayList<Float>();
		listeDonnees.add(2f);
		listeDonnees.add(3f);
		listeDonnees.add(4f);
		listeDonnees.add(5f);
		listeDonnees.add(6f);
		
		float variance = Calcs.Variance(listeDonnees);
		int arrondi = (int)variance;
		assertEquals(2, arrondi);
	}
	
	@Test
	public void TestBorneSup(){
		ArrayList<Float> listeDonnees = new ArrayList<Float>();
		listeDonnees.add(2000f);
		listeDonnees.add(3000f);
		listeDonnees.add(4000f);
		listeDonnees.add(5000f);
		listeDonnees.add(6000f);
		
		float variance = Calcs.Variance(listeDonnees);
		int arrondi = (int)variance;
		
		assertEquals(2500000, arrondi);
	}
	
	@Test
	public void TestInvalide(){
		ArrayList<Float> listeDonnees = new ArrayList<Float>();
		listeDonnees.add(-30f);
		listeDonnees.add(60f);
		listeDonnees.add(33f);
		listeDonnees.add(42f);
		listeDonnees.add(0f);
		
		float variance = Calcs.Variance(listeDonnees);
		int arrondi = (int)variance;
		
		assertEquals(-1, arrondi);
	}

}
