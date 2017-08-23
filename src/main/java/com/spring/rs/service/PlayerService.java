package com.spring.rs.service;

import java.util.List;

import com.spring.rs.domain.Player;

public interface PlayerService {
	
	public List<Player> findAll(); 
	
	public void addPlayer(Player player); 
	
	public void updatePlayer(Player player); 
	
	public List<Player> findByTeam(String team);
	
	public void deletePlayer(Player player); 
	
}
