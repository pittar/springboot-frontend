package ca.pitt.demo.spring.rest.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.pitt.demo.spring.rest.frontend.services.RandomNumberService;
import ca.pitt.demo.spring.rest.frontend.values.RandomValue;

/**
 * HomeController.
 * 
 * @author Andrew Pitt
 * @since 1.0.0
 */
@Controller
@RequestMapping(path = "/")
public class HomeController {

	/** Random service. */
	@Autowired
	private RandomNumberService randomService;

	/**
	 * Get home.
	 * 
	 * @return <code>ModelAndView</code>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		RandomValue rand = randomService.getRandomValue();
		System.out.println("Recdeived: " + rand.getValue());
		ModelMap model = new ModelMap();
		model.addAttribute("randomValue", rand.getValue());
		return new ModelAndView("home", model);
	}

}
