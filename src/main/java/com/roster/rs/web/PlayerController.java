package com.roster.rs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.roster.rs.domain.Player;
import com.roster.rs.exception.CustomApplicationException;
import com.roster.rs.exception.TransactionResponse;
import com.roster.rs.service.PlayerService;

@RestController
@RequestMapping(path = "mlb/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private TransactionResponse transactionResponse;

	/**
	 * Add a player to directory
	 * 
	 * @param player
	 * @return
	 * @throws CustomApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Player addPlayer(@RequestBody Player player) throws CustomApplicationException {
		playerService.addPlayer(player);
		return player;
	}

	/**
	 * Update a player in directory
	 * 
	 * @param player
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public Player updatePlayer(@RequestBody Player player, @PathVariable long id) {
		playerService.updatePlayer(player, id);
		return player;
	}

	/**
	 * Retrieve all players in the directory
	 * 
	 * @return
	 */
	@RequestMapping(value = "/all")
	public @ResponseBody List<Player> getAllPlayers() {
		return playerService.findAll();
	}

	/**
	 * Retrieve a team roster
	 * 
	 * @param team
	 * @return
	 */
	@RequestMapping(value = "/{team}")
	public @ResponseBody List<Player> getTeamRoster(@PathVariable String team) {
		return playerService.findByTeam(team);
	}

	/**
	 * Retrieve a player with id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping
	public @ResponseBody Player getPlayer(@RequestParam("id") long id) {
		return playerService.findPlayer(id);
	}

	/**
	 * Delete a player from the directory
	 * 
	 * @param player
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deletePlayer(@PathVariable("id") long id) {
		playerService.deletePlayer(id);
	}

	/**
	 * Exception Handler takes in error code and error message and returns JSON
	 * response
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CustomApplicationException.class)
	public TransactionResponse handleCustomException(CustomApplicationException ex) {
		transactionResponse.setTransactionCode(ex.getErrorCode());
		transactionResponse.setTransactionMessage(ex.getErrorMessage());
		return transactionResponse;
	}
}
