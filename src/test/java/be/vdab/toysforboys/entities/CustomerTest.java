package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	private Customer customer1;
	private Customer nogEensCustomer1;
	private Customer customer2;
	private Customer customer3;
	private Customer customer4;
	private Customer customer5;
	private Customer customer6;
	private Customer customer7;
	@Before
	public void before() {
		customer1 = new Customer("test", "test 1", "test", "test", "1000", new Country("test"));
		nogEensCustomer1 = new Customer("test", "test 1", "test", "test", "1000", new Country("test"));
		customer2 = new Customer("test2", "test 1", "test", "test", "1000", new Country("test"));
		customer3 = new Customer("test", "test 2", "test", "test", "1000", new Country("test"));
		customer4 = new Customer("test", "test 1", "test2", "test", "1000", new Country("test"));
		customer5 = new Customer("test", "test 1", "test", "test2", "1000", new Country("test"));
		customer6 = new Customer("test", "test 1", "test", "test", "2000", new Country("test"));
		customer7 = new Customer("test", "test 1", "test", "test", "1000", new Country("test2"));
	}
	@Test
	public void tweeCustomersMetDezelfdeNameStreetAndNumberCityStatePostalCodeCountryIdZijnDezelfde() {
		assertEquals(customer1, nogEensCustomer1);
	}
	@Test
	public void customersMetVerschillendeNameZijnVerschillend() {
		assertNotEquals(customer1, customer2);
	}
	@Test
	public void customersMetVerschillendeStreetAndNumberZijnVerschillend() {
		assertNotEquals(customer1, customer3);
	}
	@Test
	public void customersMetVerschillendeCityZijnVerschillend() {
		assertNotEquals(customer1, customer4);
	}
	@Test
	public void customersMetVerschillendeStateZijnVerschillend() {
		assertNotEquals(customer1, customer5);
	}
	@Test
	public void customersMetVerschillendePostalCodeZijnVerschillend() {
		assertNotEquals(customer1, customer6);
	}
	@Test
	public void customersMetVerschillendeCountryZijnVerschillend() {
		assertNotEquals(customer1, customer7);
	}
}
