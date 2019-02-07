package be.vdab.toysforboys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboys.services.OrderService;

@Controller
@RequestMapping("orders")
public class OrderController {
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private static final String ORDERDETAIL_VIEW = "orderdetail";
	@GetMapping("{id}")
	ModelAndView orderdetail(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(ORDERDETAIL_VIEW);
		orderService.readMetCustomerEnCountry(id).ifPresent(order -> modelAndView.addObject("order", order));
		return modelAndView;
	}
}
