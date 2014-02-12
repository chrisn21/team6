package com.team6.app.arena;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArenaController {

	@RequestMapping(value = "/arena", method = RequestMethod.GET)
	public ModelAndView showMain() {
		return new ModelAndView("login");
	}
	
}
