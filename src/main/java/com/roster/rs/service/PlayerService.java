package com.roster.rs.service;

import java.util.List;

import com.roster.rs.domain.Player;

public interface PlayerService {
	
	public List<Player> findAll(); 
	
	public void addPlayer(Player player); 
	
	public void updatePlayer(Player player, long id); 
	
	public List<Player> findByTeam(String team);
	
	public void deletePlayer(long id); 
	
	public Player findPlayer(long id); 
	
}
