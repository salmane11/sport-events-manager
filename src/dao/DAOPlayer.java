package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Player;

public class DAOPlayer {
	
	List<Player> players=new ArrayList<Player>();
	
	public List<Player> getteams() {
		return players;
	}
	public void setteams(List<Player> players) {
		this.players = players;
	}
	
	public DAOPlayer() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sport-events","root","");
		Statement st =conn.createStatement();
		ResultSet rs=st.executeQuery("Select * from player");
		Player player=new Player();
		while (rs.next()) {
			player.setPlayerId(rs.getLong("player_id"));
			player.setPlayerName(rs.getString("player_name"));
			player.setPlayerAge(rs.getInt("player_age"));
			player.setPlayerPosition(rs.getNString("player_position"));
			player.setPlayerId(rs.getLong("player_id"));
			player.setTeamId(rs.getLong("team_id"));
			players.add(player);
		}
	}
	
	
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public Connection connect() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sport-events","root","");
		return conn;
	}
	
	public void createPlayer(Player player) {
		try {
			Connection connection=connect();
			PreparedStatement statement=connection.prepareStatement("INSERT INTO player (player_id,player_name,player_age,player_position,team_id) VALUES(?,?,?,?,?)");
			statement.setLong(1, player.getPlayerId());
			statement.setString(2, player.getPlayerName());
			statement.setInt(3, player.getPlayerAge());
			statement.setString(4, player.getPlayerPosition());
			statement.setLong(5, player.getTeamId());
			statement.executeUpdate();
			connection.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void main(String[]args) throws Exception {
		DAOPlayer daoplayer=new DAOPlayer();
		System.out.println(daoplayer.players.get(0).getTeamId());
		Player myPlayer=new Player(9,"TerSteigen",30,2,"GK");
		daoplayer.createPlayer(myPlayer);
	}
}
