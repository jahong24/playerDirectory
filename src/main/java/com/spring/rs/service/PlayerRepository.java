package com.spring.rs.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.rs.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	
	List<Player> findAll(); 

	Player findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

	List<Player> findByTeam(String team);

	List<Player> findByJerseyNumber(String jerseyNumber);

	List<Player> findByPosition(String position);
	
}

