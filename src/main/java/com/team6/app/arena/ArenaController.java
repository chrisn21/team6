package com.team6.app.arena;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team6.app.AccountService;
import com.team6.app.Constants;
import com.team6.app.User;

@Controller
public class ArenaController {

	@Autowired
	private ArenaService arenaService;
	
	@Autowired
	private AccountService accService;
	
	@RequestMapping(value = "/arena", method = RequestMethod.GET)
	public ModelAndView showLobby(HttpServletRequest req) {
		ModelAndView mv;
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			mv = new ModelAndView(Constants.LOGIN_PATH_FILE);
		} else {
			String userId = (String) sesh.getAttribute("userid");
			mv = new ModelAndView(Constants.ARENA_LOBBY_PATH_FILE);
			mv.addObject("opponents", 
					arenaService.getOpponentsOf(userId, 0, 0));
		}
		return mv;
	}
	
	@RequestMapping(value = "/arena/create", method = RequestMethod.GET)
	public ModelAndView showLobby(HttpServletRequest req,
			@RequestParam(value = "userId2") String userId2) {
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			return new ModelAndView(Constants.LOGIN_PATH_FILE);
		}
		
		String thisUserId = (String) sesh.getAttribute("userid");
		Battle battle = arenaService.createBattle(thisUserId, userId2);
		
		if (battle == null) {
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		return showLobby(req);
//		ModelAndView mv = new ModelAndView(Constants.ARENA_LOBBY_PATH_FILE);
//		
//		return mv;
	}
	
	@RequestMapping(value = "/arena/{battleId}", method = RequestMethod.GET)
	public ModelAndView showBattle(HttpServletRequest req,
			@PathVariable String battleId) {
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			// Invalid session
			return new ModelAndView(Constants.LOGIN_PATH_FILE);
		}

		Battle battle = arenaService.getBattle(battleId);
		String thisUserId = (String) sesh.getAttribute("userid");
		
		if (thisUserId == null ||
				!thisUserId.equals(battle.getUserId1()) &&
				!thisUserId.equals(battle.getUserId2())) {
			// Current user isn't in battle
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		ModelAndView mv = new ModelAndView(Constants.ARENA_BATTLE_PATH_FILE);

		String userId1 = battle.getUserId1();
		String userId2 = battle.getUserId2();
		Boolean isActiveTurn = false;
		
		// Determine if it's current user's turn
		if (battle.isUser1Turn()) {
			isActiveTurn = thisUserId.equals(userId1);
		} else {
			isActiveTurn = thisUserId.equals(userId2);
		}
		
		mv.addObject("user1", accService.findById(userId1));
		mv.addObject("user2", accService.findById(userId2));
		mv.addObject("char1", battle.getChar1());
		mv.addObject("char2", battle.getChar2());
		mv.addObject("log", battle.getLog());
		mv.addObject("isActiveTurn", isActiveTurn);
		return mv;
	}
	
}
