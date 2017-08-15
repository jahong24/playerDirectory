package com.spring.rs;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	List<Player> findByLastName(String lastName);

	List<Player> findByFirstName(String firstName);

	Player findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

	List<Player> findByTeam(String team);

	List<Player> findByJerseyNumber(String jerseyNumber);

	List<Player> findByPosition(String position);
}
