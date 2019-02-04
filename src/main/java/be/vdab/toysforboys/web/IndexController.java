package be.vdab.toysforboys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboys.services.OrderService;

@Controller
@RequestMapping("/")
class IndexController {
	private final OrderService orderService;
	private static final String INDEX_VIEW = "index";
	
	IndexController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(INDEX_VIEW);
		modelAndView.addObject("unShippedOrders", orderService.findUnShipped());
		return modelAndView;
	}
}
