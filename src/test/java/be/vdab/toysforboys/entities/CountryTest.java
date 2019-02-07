package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class CountryTest {
	Country country1;
	Country nogEensCountry1;
	Country country2;
	@Before
	public void before() {
		country1 = new Country("test");
		nogEensCountry1 = new Country("test");
		country2 = new Country("test2");	
	}
	@Test
	public void tweeCountriesMetDezelfdeNameZijnDezelfde() {
		assertEquals(country1, nogEensCountry1);
	}
	@Test
	public void tweeCountriesMetVerschillendeNameZijnVerschillend() {
		assertNotEquals(country1, country2);
	}
}
