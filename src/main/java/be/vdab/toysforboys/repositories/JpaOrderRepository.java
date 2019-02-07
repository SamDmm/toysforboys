package be.vdab.toysforboys.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import be.vdab.toysforboys.entities.Order;

@Repository
class JpaOrderRepository implements OrderRepository {
	private final EntityManager manager;
	
	JpaOrderRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public List<Order> findUnShipped() {
		return manager.createNamedQuery("Order.findUnShipped", Order.class).setHint("javax.persistence.loadgraph", manager.createEntityGraph("Order.metCustomerEnCountry")).getResultList();
	}
	@Override
	public Optional<Order> read(long id) {
		return Optional.ofNullable(manager.find(Order.class, id));
	}
	@Override
	public Optional<Order> readMetCustomerEnCountry(long id) {
		return Optional.ofNullable(manager.createQuery("select o from Order o where o.id= :id", Order.class).setParameter("id", id).setHint("javax.persistence.loadgraph", manager.createEntityGraph("Order.metCustomerEnCountry")).getSingleResult());
	}
}
