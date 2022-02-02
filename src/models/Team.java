package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
	long teamId;
	String teamName;
	List <Player> teamPlayers;
	List <Long> teamEvents;
	
	
	public Team() {
		System.out.println("a team was created");
		teamPlayers=new ArrayList<Player>();
		teamEvents=new ArrayList<Long>();
	}


	public Team(long teamId, String teamName, List<Player> teamPlayers) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamPlayers = teamPlayers;
	}
	
	
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public List<Player> getTeamPlayers() {
		return teamPlayers;
	}
	public void setTeamPlayers(List<Player> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}
	
	public List<Long> getTeamEvents() {
		return teamEvents;
	}
	public void setTeamEvents(List<Long> teamEvents) {
		this.teamEvents = teamEvents;
	}
	
	
	public Player addPlayer(Player player) {
		teamPlayers.add(player);
		return player;
	}
	
	public long deletePlayer(long id) {
		for(int i=0;i<teamPlayers.size();i++) {
			if(teamPlayers.get(i).playerId==id) {
				teamPlayers.remove(i);
				System.out.println("the chosen player is deleted");
				return id;
			}
		}
		System.out.println("there is no player with the id "+id);
		return 0;
	}
	
	public Player updatePlayer(Player player) {
		for(int i=0;i<teamPlayers.size();i++) {
			if(teamPlayers.get(i).playerId==player.playerId) {
				teamPlayers.set(i, player);
				System.out.println("the chosen player has been updated");
				return player;
			}
		}
		System.out.println("there is no player with this id "+ player.playerId);
		return null;
	}
	
	public String showTeamPlayers() {
		StringBuilder players=new StringBuilder("[ ");
		for(Player player : teamPlayers) {
			players.append("id : "+player.playerId+" -- name : "+player.playerName+" -- position : "+player.playerPosition+" -- age : "+player.playerAge+" , ");
		}
		players.append(" ].");
		return players.toString();
	}
	
	public long addEvent(long id) {
		if(teamEvents.contains(id)) {
			System.out.println("the event is already added");
			return -1;
		}else {
			teamEvents.add(id);
			return id;
		}
	}
	
	public long deleteEvent(long id) {
		if(teamEvents.contains(id)) {
			teamEvents.remove(id);
			return id;
		}else {
			System.out.println("the event does not exist");
			return -1;
		}
	}
}
