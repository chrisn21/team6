package com.team6.app.arena;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArenaController {

	@Autowired
	private ArenaService arenaService;
	
	@RequestMapping(value = "/arena", method = RequestMethod.GET)
	public ModelAndView showLobby(HttpServletRequest req) {
		ModelAndView mv;
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			mv = new ModelAndView("login");
		} else {
			String userId = (String) sesh.getAttribute("userid");
			mv = new ModelAndView("arena/lobby");
			mv.addObject("opponents", 
					arenaService.getOpponentsOf(userId, 0, 0));
		}
		return mv;
	}
	
}
