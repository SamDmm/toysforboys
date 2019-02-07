package be.vdab.toysforboys.services;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.toysforboys.exceptions.NotEnoughQuantityInStockException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/insertCountry.sql")
@Sql("/insertCustomer.sql")
@Sql("/insertOrder.sql")
@Sql("/insertProduct.sql")
@Sql("/insertOrderdetail.sql")
public class DefaultOrderServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private DefaultOrderService service;
	@Autowired
	private EntityManager manager;
	
	public long idVanTestOrder() {
		return super.jdbcTemplate.queryForObject("select id from orders where comments='test'", long.class);
	}
	public long idVanTestOrder2() {
		return super.jdbcTemplate.queryForObject("select id from orders where comments='test2'", long.class);
	}
	@Test
	public void setAsShipped() {
		long quantityOrdered = super.jdbcTemplate.queryForObject("select quantityOrdered from orderdetails where priceEach=10", long.class);
		long quantityInStock = super.jdbcTemplate.queryForObject("select quantityInStock from products where name = 'test'", long.class);
		long quantityInOrder = super.jdbcTemplate.queryForObject("select quantityInOrder from products where name = 'test'", long.class);
		long quantityInStockNaShipped = quantityInStock - quantityOrdered;
		long quantityInOrderNaShipped = quantityInOrder - quantityOrdered;
		service.setAsShipped(idVanTestOrder());
		manager.flush();
		assertTrue(quantityInStockNaShipped == super.jdbcTemplate.queryForObject("select quantityInStock from products where name = 'test'", long.class));
		assertTrue(quantityInOrderNaShipped == super.jdbcTemplate.queryForObject("select quantityInOrder from products where name = 'test'", long.class));
		String statusNaShipped = super.jdbcTemplate.queryForObject("select status from orders where comments='test'", String.class);
		assertTrue("SHIPPED".equals(statusNaShipped) || "CANCELLED".equals(statusNaShipped));
	}
	@Test(expected = NotEnoughQuantityInStockException.class)
	public void setAsShippedOnvoldoendeVoorraad() {
		service.setAsShipped(idVanTestOrder2());
	}

}
