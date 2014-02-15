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

@Controller
public class ArenaController {

	@Autowired
	private ArenaService arenaService;
	
	@Autowired
	private AccountService accService;
	
	@RequestMapping(value = "/arena", method = RequestMethod.GET)
	public ModelAndView showLobby(HttpServletRequest req,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "offset", required = false) String offset) {
		ModelAndView mv;
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String userId = (String) sesh.getAttribute("userid");
		if (userId == null) {
			return new ModelAndView("redirect:/login");
		}
		
		mv = new ModelAndView(Constants.ARENA_LOBBY_PATH_FILE);
		mv.addObject("opponents",
				arenaService.getPotentialOpponentsOf(userId, limit, offset));
		mv.addObject("battles",
				arenaService.getBattlesByUserId(userId));
		return mv;
	}
	
	@RequestMapping(value = "/arena", method = RequestMethod.POST)
	public ModelAndView showLobby(HttpServletRequest req,
			@RequestParam(value = "userId2") String userId2) {
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		String thisUserId = (String) sesh.getAttribute("userid");
		Battle battle = arenaService.createBattle(thisUserId, userId2);
		battle = (battle == null) ? arenaService.createBattle(userId2, thisUserId) : battle;
		
		if (battle == null) {
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		ModelAndView mv = new ModelAndView("redirect:/arena");
		return mv;
	}
	
	@RequestMapping(value = "/arena/{battleId}", method = RequestMethod.GET)
	public ModelAndView showBattle(HttpServletRequest req,
			@PathVariable String battleId) {
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			// Invalid session
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}

		Battle battle = arenaService.getBattleById(battleId);
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
		
		mv.addObject("user1", accService.findById(userId1));
		mv.addObject("user2", accService.findById(userId2));
		mv.addObject("char1", battle.getChar1());
		mv.addObject("char2", battle.getChar2());
		mv.addObject("isUser1", battle.getUserId1().equals(thisUserId));
		mv.addObject("log", battle.getLog());
		return mv;
	}
	
	@RequestMapping(value = "/arena/{battleId}", method = RequestMethod.POST)
	public ModelAndView showBattle(HttpServletRequest req,
			@PathVariable String battleId,
			@RequestParam("cmd") String cmd) {
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			// Invalid session
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}

		Battle battle = arenaService.getBattleById(battleId);
		String thisUserId = (String) sesh.getAttribute("userid");
		
		if (thisUserId == null ||
				!thisUserId.equals(battle.getUserId1()) &&
				!thisUserId.equals(battle.getUserId2())) {
			// Current user isn't in battle
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		arenaService.doBattleCommand(battle, thisUserId, cmd.toLowerCase());
		return new ModelAndView("redirect:/arena/" + battleId);
	}
}
