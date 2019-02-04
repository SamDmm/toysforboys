package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	private Product product1;
	private Product nogEensProduct1;
	private Product product2;
	private Product product3;
	private Product product4;
	private Product product5;
	
	@Before
	public void before() {
		product1 = new Product("test", "test", "test", 10, 5, BigDecimal.TEN, 1);
		nogEensProduct1 = new Product("test", "test", "test", 10, 5, BigDecimal.TEN, 1);
		product2 = new Product("test2", "test", "test", 10, 5, BigDecimal.TEN, 1);
		product3 = new Product("test", "test2", "test", 10, 5, BigDecimal.TEN, 1);
		product4 = new Product("test", "test", "test2", 10, 5, BigDecimal.TEN, 1);
		product5 = new Product("test", "test", "test", 10, 5, BigDecimal.ONE, 1);
	}
	@Test
	public void productenMetDezelfdeNameScaleDescriptionPriceZijnHetzelfde() {
		assertEquals(product1, nogEensProduct1);
	}
	@Test
	public void productenMetVerschillendeNameVerschillend() {
		assertNotEquals(product1, product2);
	}
	@Test
	public void productenMetVerschillendeScaleVerschillend() {
		assertNotEquals(product1, product3);
	}
	@Test
	public void productenMetVerschillendeDescriptionVerschillend() {
		assertNotEquals(product1, product4);
	}
	@Test
	public void productenMetVerschillendeBuyPriceVerschillend() {
		assertNotEquals(product1, product5);
	}

}