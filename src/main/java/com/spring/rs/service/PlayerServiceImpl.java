package com.spring.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rs.domain.Player;
import com.spring.rs.exception.CustomApplicationException;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	public void addPlayer(Player player) {
		if (playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(),
				player.getFirstName()) != null) {
			throw new CustomApplicationException("400", "Player already exists");
		}
		playerRepository.save(player);
	}

	public void updatePlayer(Player player) {
		Player updatedPlayer = playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(),
				player.getFirstName());
		updatedPlayer.setTeam(player.getTeam());
		updatedPlayer.setJerseyNumber(player.getJerseyNumber());
		updatedPlayer.setPosition(player.getPosition());
		playerRepository.save(updatedPlayer);
	}

	public List<Player> findByTeam(String team) {
		return playerRepository.findByTeam(team);
	}

	public void deletePlayer(Player player) {
		Player playerMarkedForDeletion = playerRepository.findByLastNameAndFirstNameAllIgnoreCase(player.getLastName(),
				player.getFirstName());
		if (playerMarkedForDeletion == null) {
			throw new CustomApplicationException("400", "Player not available for deletion");
		}
		playerRepository.delete(playerMarkedForDeletion);
	}

}
