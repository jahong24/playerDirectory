package com.spring.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rs.domain.Player;
import com.spring.rs.domain.PlayerRepository;
import com.spring.rs.exception.CustomApplicationException;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	public void addPlayer(Player player) {
		if (playerRepository.findByFirstNameAndLastNameAllIgnoreCase(player.getFirstName(), player.getLastName()) != null) {
			throw new CustomApplicationException("400", "Player already exists");
		}
		playerRepository.save(player);
	}

	public void updatePlayer(Player player, long id) {
		Player updatedPlayer = playerRepository.findOne(id);
		updatedPlayer.setFirstName(player.getFirstName());
		updatedPlayer.setLastName(player.getLastName());
		updatedPlayer.setTeam(player.getTeam());
		updatedPlayer.setJerseyNumber(player.getJerseyNumber());
		updatedPlayer.setPosition(player.getPosition());
		playerRepository.save(updatedPlayer);
	}

	public List<Player> findByTeam(String team) {
		return playerRepository.findByTeam(team);
	}

	public void deletePlayer(long id) {
		playerRepository.delete(id);
	}

	public Player findPlayer(long id) {
		return playerRepository.findOne(id);
	}

}
