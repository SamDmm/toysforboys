package be.vdab.toysforboys.repositories;

import java.util.List;

import be.vdab.toysforboys.entities.Order;

public interface OrderRepository {
	List<Order> findUnShipped();
}
