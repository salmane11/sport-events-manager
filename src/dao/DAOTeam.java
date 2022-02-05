package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Player;
import models.Team;

public class DAOTeam {
	List<Team> teams=new ArrayList<Team>();
	
	public DAOTeam() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sport-events","root","");
		Statement st =conn.createStatement();
		ResultSet rs=st.executeQuery("Select * from team");
		
		Statement secondST =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet secondRS=secondST.executeQuery("Select * from eventteam,team,event where eventteam.team_id=team.team_id and eventteam.event_id=event.event_id");
		
		Statement thirdST =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet thirdRS=thirdST.executeQuery("Select * from player");
		
		while (rs.next()) {
			Team team =new Team();
			List<Long>teamEvents=new ArrayList<Long>();
			List<Player>teamPlayers=new ArrayList<Player>();
			team.setTeamId(rs.getLong("team_id"));
			team.setTeamName(rs.getString("team_name"));
			secondRS.absolute(0);
			while(secondRS.next()) {
				if(rs.getLong("team_id")==secondRS.getLong("team_id")) {					
					teamEvents.add(secondRS.getLong("event_id"));
				}
			}
			thirdRS.absolute(0);
			while(thirdRS.next()) {
				if(thirdRS.getLong("team_id")==rs.getLong("team_id")) {
					Player player=new Player();
					player.setPlayerId(thirdRS.getLong("player_id"));
					player.setPlayerName(thirdRS.getString("player_name"));
					player.setPlayerAge(thirdRS.getInt("player_age"));
					player.setPlayerPosition(thirdRS.getNString("player_position"));
					player.setPlayerId(thirdRS.getLong("player_id"));
					teamPlayers.add(player);
				}
			}
			team.setTeamPlayers(teamPlayers);
			team.setTeamEvents(teamEvents);
			teams.add(team);
		}
		
	
		
	}
	
	
	
	public List<Team> getTeams() {
		return teams;
	}



	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	
	public Connection connect() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sport-events","root","");
		return conn;
	}
	
	
	public void createTeam(Team team) {
		try {
			Connection connection=connect();
			PreparedStatement statement=connection.prepareStatement("INSERT INTO team (team_id,team_name) VALUES(?,?)");
			statement.setLong(1, team.getTeamId());
			statement.setString(2, team.getTeamName());
			statement.executeUpdate();
			connection.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteTeam(Team team) {
		try {
			Connection connection=connect();
			PreparedStatement firstStatement=connection.prepareStatement("DELETE FROM team where team_id="+team.getTeamId()+"");
			PreparedStatement secondStatement=connection.prepareStatement("DELETE FROM eventteam where team_id="+team.getTeamId()+"");
			PreparedStatement thirdStatement=connection.prepareStatement("DELETE FROM player where team_id="+team.getTeamId()+"");
			firstStatement.executeUpdate();
			secondStatement.executeUpdate();
			thirdStatement.executeUpdate();
		}catch(Exception e) {
			e.getMessage();
		}
	}
	

	public static void main(String[]args) throws Exception {
		DAOTeam daoteam=new DAOTeam();
		/**System.out.println(daoteam.teams);
		System.out.println(daoteam.teams.get(1).getTeamName());
		System.out.println(daoteam.teams.get(0).getTeamPlayers());
		System.out.println(daoteam.teams.get(0).getTeamEvents().get(0));
		**/
		Team team=new Team();
		team.setTeamId(7);
		team.setTeamName("test");
		//daoteam.createTeam(team);
		daoteam.deleteTeam(team);
	}
}
