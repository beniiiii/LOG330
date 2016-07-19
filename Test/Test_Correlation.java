package Test_Correlation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import resources.Calcs;

public class Test_Correlation {

	@Test
	public void TestBorneInf() {
		ArrayList<Float> listeX = new ArrayList<Float>();
		listeX.add(2f);
		listeX.add(3f);
		listeX.add(4f);
		listeX.add(5f);
		listeX.add(6f);
		
		ArrayList<Float> listeY = new ArrayList<Float>();
		listeY.add(7f);
		listeY.add(8f);
		listeY.add(9f);
		listeY.add(10f);
		listeY.add(11f);
		
		float correlation = Calcs.Correlation(listeX, listeY);
		int arrondi = (int)correlation;
		assertEquals(1, arrondi);
	}
	
	@Test
	public void TestBorneSup(){
		ArrayList<Float> listeX = new ArrayList<Float>();
		listeX.add(23473f);
		listeX.add(34235f);
		listeX.add(42234f);
		listeX.add(55673f);
		listeX.add(65678f);
		
		ArrayList<Float> listeY = new ArrayList<Float>();
		listeY.add(76542f);
		listeY.add(86178f);
		listeY.add(93546f);
		listeY.add(10645f);
		listeY.add(11465f);
		
		float correlation = Calcs.Correlation(listeX, listeY);
		int arrondi = (int)correlation;
		assertEquals(0, arrondi);
	}
	
	@Test
	public void TestInvalide(){
		
		ArrayList<Float> listeX = new ArrayList<Float>();
		listeX.add(2f);
		listeX.add(3f);
		listeX.add(4f);
		listeX.add(5f);
		listeX.add(6f);
		
		ArrayList<Float> listeY = new ArrayList<Float>();
		listeY.add(7f);
		listeY.add(8f);
		listeY.add(9f);
		listeY.add(10f);
		listeY.add(-2f);
		
		float correlation = Calcs.Correlation(listeX, listeY);
		int arrondi = (int)correlation;
		assertEquals(-1, arrondi);
		
	}

}
