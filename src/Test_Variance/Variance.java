package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.bind.ParseConversionEvent;

import org.junit.Test;

import resources.Calcs;

public class Variance {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		ArrayList<Float> listeDonnees = new ArrayList<Float>();
		listeDonnees.add(20f);
		listeDonnees.add(30f);
		listeDonnees.add(40f);
		listeDonnees.add(50f);
		listeDonnees.add(60f);
		
		float variance = Calcs.Variance(listeDonnees);
		int arrondi = (int)variance;
		System.out.println(arrondi);
		assertEquals(250, arrondi);
	}

}
