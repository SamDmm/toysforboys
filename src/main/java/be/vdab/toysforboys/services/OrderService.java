package be.vdab.toysforboys.services;

import java.util.List;
import java.util.Optional;

import be.vdab.toysforboys.entities.Order;

public interface OrderService {
	Optional<Order> read(long id);
	List<Order> findUnShipped();
	void setAsShipped(long id);
	Optional<Order> readMetCustomerEnCountry(long id);
}
