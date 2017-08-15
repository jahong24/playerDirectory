package com.spring.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rs.exception.CustomApplicationException;
import com.spring.rs.exception.TransactionResponse;

@RestController
@RequestMapping(path = "/mlb")
public class AppController {

	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * Add a player to directory
	 * 
	 * @param player
	 * @return
	 * @throws CustomApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/players")
	public TransactionResponse addPlayer(@RequestBody Player player) throws CustomApplicationException {
		if (playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(),
				player.getFirstName()) != null) {
			throw new CustomApplicationException("400", "Player already exists");
		}
		playerRepository.save(player);
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
	@RequestMapping(method = RequestMethod.PUT, value = "/players")
	public TransactionResponse updatePlayer(@RequestBody Player player) {
		Player updatedPlayer = playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(),
				player.getFirstName());
		updatedPlayer.setTeam(player.getTeam());
		updatedPlayer.setJerseyNumber(player.getJerseyNumber());
		updatedPlayer.setPosition(player.getPosition());
		playerRepository.save(updatedPlayer);
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
	@RequestMapping("/players")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	/**
	 * Retrieve a team roster
	 * 
	 * @param team
	 * @return
	 */
	@RequestMapping("/players/{team}")
	public @ResponseBody List<Player> getTeamRoster(@PathVariable String team) {
		return playerRepository.findByTeam(team);
	}

	/**
	 * Delete a player from the directory
	 * 
	 * @param player
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/players")
	public TransactionResponse deletePlayer(@RequestBody Player player) {
		Player playerMarkedForDeletion = playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(),
				player.getFirstName());
		if (playerMarkedForDeletion == null) {
			throw new CustomApplicationException("400", "Player not available for deletion");
		}
		playerRepository.delete(playerMarkedForDeletion);
		TransactionResponse transaction = new TransactionResponse();
		transaction.setTransactionCode("200");
		transaction.setTransactionMessage(playerMarkedForDeletion.getFirstName() + " "
				+ playerMarkedForDeletion.getLastName() + " deleted from directory");
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
