package com.team6.app.game;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
	
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getGameNames() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "tongue twister");
		return map;
	}
	
	@RequestMapping(value = "/games/{gameName}", method = RequestMethod.GET)
	public ModelAndView showGame(@PathVariable String gameName) {
		ModelAndView mv = new ModelAndView(gameName);
		mv.addObject("gameName", gameName);
		return mv;
	}
	
}
