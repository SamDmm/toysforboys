package be.vdab.toysforboys.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.exceptions.OrderNotFoundException;
import be.vdab.toysforboys.repositories.OrderRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultOrderService implements OrderService {
	private final OrderRepository orderRepository;
	
	DefaultOrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Optional<Order> read(long id) {
		return orderRepository.read(id);
	}
	@Override
	public List<Order> findUnShipped() {
		return orderRepository.findUnShipped();
	}
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void setAsShipped(long id) {
		Optional<Order> orderOptional = orderRepository.read(id);
		if (orderOptional.isPresent()) {
			orderOptional.get().setAsShipped();
		}
		else {
			throw new OrderNotFoundException(id);
		}
	}
	@Override
	public Optional<Order> readMetCustomerEnCountry(long id) {
		return orderRepository.readMetCustomerEnCountry(id);
	}
}
