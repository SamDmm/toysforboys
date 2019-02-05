package be.vdab.toysforboys.services;

import java.util.List;

import be.vdab.toysforboys.entities.Order;

public interface OrderService {
	List<Order> findUnShipped();
	void setAsShipped(long id);
}
