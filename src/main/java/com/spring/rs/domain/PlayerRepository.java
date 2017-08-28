package com.spring.rs.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	
	List<Player> findAll(); 

	Player findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);

	List<Player> findByTeam(String team);

	List<Player> findByJerseyNumber(String jerseyNumber);

	List<Player> findByPosition(String position);
	
}

