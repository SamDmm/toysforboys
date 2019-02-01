package be.vdab.toysforboys.repositories;

import java.util.List;

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
		return manager.createNamedQuery("Order.findUnShipped", Order.class).getResultList();
	}
}
