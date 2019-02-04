package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Orderdetail;

public class OrderTest {
	private Order order;
	private Orderdetail orderdetail1;
	private Orderdetail nogEensOrderdetail1;
	private Orderdetail orderdetail2;
	private Product product;
	private Product product2;
	private Customer customer;
	@Before
	public void before() {
		customer = new Customer("test", "test 1", "test", "test", "1000", 1);
		product = new Product("test", "test", "test", 10, 5, BigDecimal.TEN, 1);
		product2 = new Product("test", "test", "test", 10, 5, BigDecimal.ONE, 1);
		orderdetail1 = new Orderdetail(5, BigDecimal.TEN, product);
		nogEensOrderdetail1 = new Orderdetail(5, BigDecimal.TEN, product);
		orderdetail2 = new Orderdetail(5, BigDecimal.TEN, product2);
		order = new Order(LocalDate.now(), LocalDate.now(), LocalDate.now(), "test", 1, customer, Status.PROCESSING);
		order.addOrderDetail(orderdetail1);
	}
	@Test
	public void orderDetailToevoegen() {
		order.addOrderDetail(orderdetail2);
		assertEquals(2, order.getOrderdetails().size());
		
	}
	@Test
	public void tweeDezelfdeOrderdetailsToevoegenKanNiet() {
		order.addOrderDetail(nogEensOrderdetail1);
		assertNotEquals(2, order.getOrderdetails().size());
	}
}
