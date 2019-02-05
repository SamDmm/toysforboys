package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.exceptions.NotEnoughQuantityInStockException;
import be.vdab.toysforboys.valueobjects.Orderdetail;

public class OrderTest {
	private Order order;
	private Orderdetail orderdetail1;
	private Orderdetail nogEensOrderdetail1;
	private Orderdetail orderdetail2;
	private Product product1;
	private Product product2;
	private Customer customer;
	@Before
	public void before() {
		customer = new Customer("test", "test 1", "test", "test", "1000", 1);
		product1 = new Product("test", "test", "test", 10, 5, BigDecimal.TEN, 1);
		product2 = new Product("test", "test", "test", 10, 5, BigDecimal.ONE, 1);
		orderdetail1 = new Orderdetail(5, BigDecimal.TEN, product1);
		nogEensOrderdetail1 = new Orderdetail(5, BigDecimal.TEN, product1);
		orderdetail2 = new Orderdetail(20, BigDecimal.TEN, product2);
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
	@Test
	public void addOrderDetail() {
		order.addOrderDetail(orderdetail1);
		assertTrue(order.getOrderdetails().contains(orderdetail1));
	}
	@Test
	public void setAsShipped() {
		order.addOrderDetail(orderdetail1);
		long quantityOrdered = orderdetail1.getQuantityOrdered();
		long quantityInStock = orderdetail1.getProduct().getQuantityInStock();
		long quantityInOrder = orderdetail1.getProduct().getQuantityInOrder();
		long quantityInStockNaShipped = quantityInStock - quantityOrdered;
		long quantityInOrderNaShipped = quantityInOrder - quantityOrdered;
		order.setAsShipped();
		assertEquals(Status.SHIPPED, order.getStatus());
		assertEquals(quantityInStockNaShipped, orderdetail1.getProduct().getQuantityInStock());
		assertEquals(quantityInOrderNaShipped, orderdetail1.getProduct().getQuantityInOrder());
	}
	@Test (expected = NotEnoughQuantityInStockException.class)
	public void setAsShippedOnvoldoendeVoorraad() {
		order.addOrderDetail(orderdetail2);
		order.setAsShipped();
	}
}
