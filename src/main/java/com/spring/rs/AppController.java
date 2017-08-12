package com.spring.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/mlb")
public class AppController {

	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * Add a player to directory
	 * @param player
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/players")
	public String addPlayer(@RequestBody Player player) {
		if (playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(), player.getFirstName()) != null) {
			return "Player Already Exists";
		}

		playerRepository.save(player);
		return "Player Saved";
	}
	
	/**
	 * Update a player in directory
	 * @param player
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value="/players")
	public String updatePlayer(@RequestBody Player player) {
		Player updatedPlayer = playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(), player.getFirstName());
		updatedPlayer.setTeam(player.getTeam());
		updatedPlayer.setJerseyNumber(player.getJerseyNumber());
		updatedPlayer.setPosition(player.getPosition());
		
		playerRepository.save(updatedPlayer);
		return "Player Information Updated";
	}

	/**
	 * Retrieve a team roster
	 * @param team
	 * @return
	 */
	@RequestMapping("/players/{team}")
	public @ResponseBody List<Player> getTeamRoster(@PathVariable String team) {
		return playerRepository.findByTeam(team);
	}

	/**
	 * Retrieve all players in the directory
	 * @return
	 */
	@RequestMapping("/players")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

}
