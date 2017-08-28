package com.spring.rs.web;

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

import com.spring.rs.domain.Player;
import com.spring.rs.exception.CustomApplicationException;
import com.spring.rs.exception.TransactionResponse;
import com.spring.rs.service.PlayerService;

@RestController
@RequestMapping(path = "mlb/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	/**
	 * Add a player to directory
	 * 
	 * @param player
	 * @return
	 * @throws CustomApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public TransactionResponse addPlayer(@RequestBody Player player) throws CustomApplicationException {
		playerService.addPlayer(player);
		TransactionResponse transaction = new TransactionResponse();
		transaction.setTransactionCode("200");
		transaction.setTransactionMessage(player.getFirstName() + " " + player.getLastName() + " added to directory");
		return transaction;
	}

	/**
	 * Update a player in directory
	 * 
	 * @param player
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public TransactionResponse updatePlayer(@RequestBody Player player, @PathVariable long id) {
		playerService.updatePlayer(player, id);
		TransactionResponse transaction = new TransactionResponse();
		transaction.setTransactionCode("200");
		transaction
				.setTransactionMessage("Updated information for " + player.getFirstName() + " " + player.getLastName());
		return transaction;
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
	public TransactionResponse deletePlayer(@PathVariable("id") long id) {
		playerService.deletePlayer(id);
		TransactionResponse transaction = new TransactionResponse();
		transaction.setTransactionCode("200");
		transaction.setTransactionMessage("Player deleted from directory");
		return transaction;
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
		TransactionResponse transaction = new TransactionResponse();
		transaction.setTransactionCode(ex.getErrorCode());
		transaction.setTransactionMessage(ex.getErrorMessage());
		return transaction;
	}
}
