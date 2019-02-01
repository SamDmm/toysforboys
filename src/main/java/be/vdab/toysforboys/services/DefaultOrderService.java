package be.vdab.toysforboys.services;

import java.util.List;

import org.springframework.stereotype.Service;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.repositories.OrderRepository;

@Service
class DefaultOrderService implements OrderService {
	private final OrderRepository orderRepository;
	
	DefaultOrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<Order> findUnShipped() {
		return orderRepository.findUnShipped();
	}
	
}
