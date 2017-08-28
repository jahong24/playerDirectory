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
	private PlayerRepository playerDao;

	public List<Player> findAll() {
		return playerDao.findAll();
	}

	public void addPlayer(Player player) {
		if (playerDao.findByFirstNameAndLastNameAllIgnoreCase(player.getFirstName(), player.getLastName()) != null) {
			throw new CustomApplicationException("400", "Player already exists");
		}
		playerDao.save(player);
	}

	public void updatePlayer(Player player, long id) {
		Player updatedPlayer = playerDao.findOne(id);
		updatedPlayer.setFirstName(player.getFirstName());
		updatedPlayer.setLastName(player.getLastName());
		updatedPlayer.setTeam(player.getTeam());
		updatedPlayer.setJerseyNumber(player.getJerseyNumber());
		updatedPlayer.setPosition(player.getPosition());
		playerDao.save(updatedPlayer);
	}

	public List<Player> findByTeam(String team) {
		return playerDao.findByTeam(team);
	}

	public void deletePlayer(Player player) {
		Player playerMarkedForDeletion = playerDao.findByFirstNameAndLastNameAllIgnoreCase(player.getFirstName(),
				player.getLastName());
		if (playerMarkedForDeletion == null) {
			throw new CustomApplicationException("400", "Player not available for deletion");
		}
		playerDao.delete(playerMarkedForDeletion);
	}

}
