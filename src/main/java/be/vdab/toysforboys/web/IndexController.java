package be.vdab.toysforboys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboys.services.OrderService;

@Controller
@RequestMapping("/")
class IndexController {
	private final OrderService orderService;
	
	IndexController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private static final String INDEX_VIEW = "index";
	ModelAndView index() {
		return new ModelAndView(INDEX_VIEW, "unShippedOrders", orderService.findUnShipped());
	}
}
