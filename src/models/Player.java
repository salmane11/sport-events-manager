package models;

public class Player {
	long playerId;
	String playerName;
	int playerAge;
	long teamId;
	String playerPosition;
	
	public Player(long playerId, String playerName, int playerAge, long teamId, String playerPosition) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerAge = playerAge;
		this.teamId = teamId;
		this.playerPosition = playerPosition;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getPlayerAge() {
		return playerAge;
	}
	public void setPlayerAge(int playerAge) {
		this.playerAge = playerAge;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	public String getPlayerPosition() {
		return playerPosition;
	}
	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}
	
	
}
