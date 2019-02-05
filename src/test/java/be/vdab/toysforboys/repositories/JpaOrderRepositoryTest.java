package be.vdab.toysforboys.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	@Autowired
	JpaOrderRepository repository;
	@Autowired
	EntityManager manager;
	public long idVanTestOrder() {
		return super.jdbcTemplate.queryForObject("select id from orders where comments='test'", long.class);
	}
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
	@Test
	public void read() {
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals("test", order.getComments());
	}
	@Test
	public void readOnbestaandOrder() {
		assertFalse(repository.read(-1).isPresent());
		
	}

}
