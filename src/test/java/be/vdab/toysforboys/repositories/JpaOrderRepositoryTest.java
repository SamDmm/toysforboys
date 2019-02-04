package be.vdab.toysforboys.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.enums.Status;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JpaOrderRepository.class)
@Sql("/insertCountry.sql")
@Sql("/insertCustomer.sql")
@Sql("/insertOrder.sql")
public class JpaOrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
//	private Order order;
//	private Orderdetail orderdetail;
//	private Product product;
//	private Customer customer;
//	@Before
//	public void before() {
//		customer = new Customer("test", "test 1", "test", "test", "1000", 1);
//		product = new Product("test", "test", "test", 10, 5, BigDecimal.TEN, 1);
//		orderdetail = new Orderdetail(5, BigDecimal.TEN, product);
//		order = new Order(LocalDate.now(), LocalDate.now(), LocalDate.now(), "test", 1, customer, Status.PROCESSING);
//	}
	@Autowired
	JpaOrderRepository repository;
	@Autowired
	EntityManager manager;
	@Test
	public void findUnShipped() {
		List<Order> unShippedOrders = repository.findUnShipped();
		manager.clear();
		assertEquals(super.countRowsInTableWhere("orders", "status not in ('SHIPPED', 'CANCELLED')" ), unShippedOrders.size());
		long vorigId = 0;
		for (Order order : unShippedOrders) {
			assertTrue(! (order.getStatus().equals(Status.SHIPPED) || order.getStatus().equals(Status.CANCELLED)));
			assertTrue(order.getId() >= vorigId);
			System.out.println(order.getCustomer().getName());
		}
		
	}
	
	

}
