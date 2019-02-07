package be.vdab.toysforboys.web;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.toysforboys.exceptions.NotEnoughQuantityInStockException;
import be.vdab.toysforboys.exceptions.OrderNotFoundException;
import be.vdab.toysforboys.services.OrderService;

@Controller
@RequestMapping("/")
class IndexController {
	private final OrderService orderService;
	private static final String INDEX_VIEW = "index";
	private static final String REDIRECT_INDEX = "redirect:/";
	IndexController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(INDEX_VIEW);
		modelAndView.addObject("unShippedOrders", orderService.findUnShipped());
		return modelAndView;
	}
	
	@PostMapping("setAsShipped")
	ModelAndView setAsShipped(long[] orderId, RedirectAttributes redirectAttributes) {
		Set<Long> mislukteOrderIds = new LinkedHashSet<>();
		if (orderId != null) {
			for(long id : orderId) {
				try {
					orderService.setAsShipped(id);
				} catch (NotEnoughQuantityInStockException | OrderNotFoundException ex) {
					System.out.println(ex.getMessage());
					mislukteOrderIds.add(id);
				}
			}
		}
		redirectAttributes.addAttribute("mislukteOrderIds", mislukteOrderIds);
		ModelAndView modelAndView = new ModelAndView(REDIRECT_INDEX);
		return modelAndView;
	}
}
