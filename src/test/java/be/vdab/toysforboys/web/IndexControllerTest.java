package be.vdab.toysforboys.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class IndexControllerTest {
	private IndexController controller;
	
	@Before
	public void before() {
		controller = new IndexController(null);
	}
	@Test
	public void indexWerktSamenMetDeJspIndex() {
		ModelAndView modelAndView = controller.index();
		assertEquals("index", modelAndView.getViewName());
	}
	@Test
	public void indexGeeftUnShippedOrdersDoor() {
		ModelAndView modelAndView = controller.index();
		assertTrue(modelAndView.getModel().get("unShippedOrders") instanceof Map);
	}

}
