package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Event;
import models.Team;

public class DAOEvent {
	List<Event> events =new ArrayList<Event>();
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public DAOEvent() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sport-events","root","");
		Statement st =conn.createStatement();
		ResultSet rs=st.executeQuery("Select * from event");
		Statement secondST =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet secondRS=secondST.executeQuery("Select * from eventteam,team,event where eventteam.team_id=team.team_id and eventteam.event_id=event.event_id");
		
		
		while (rs.next()) {
			Event event =new Event();
			List<Team>eventTeams=new ArrayList<Team>();
			event.setEventId(rs.getLong("event_id"));
			event.setEventName(rs.getString("event_name"));
			event.setEventDate(rs.getDate("event_date").toLocalDate());
			secondRS.absolute(0);
			while(secondRS.next()) {
				if(secondRS.getLong("event_id")==rs.getLong("event_id")) {
					Team team=new Team();
					team.setTeamId(secondRS.getLong("team_id"));
					team.setTeamName(secondRS.getString("team_name"));
					eventTeams.add(team);
				}
			}
			event.setEventTeams(eventTeams);
			events.add(event);
		}
		conn.close();
	}
	
	public Connection connect() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sport-events","root","");
		return conn;
	}
	
	public void createEvent(Event event) throws Exception {
		try {
			Connection connection=connect();
			PreparedStatement statement=connection.prepareStatement("INSERT INTO event (event_id,event_name) VALUES(?,?)");
			statement.setLong(1, event.getEventId());
			statement.setString(2, event.getEventName());
			statement.executeUpdate();
			connection.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void addTeam(Team team ,Long event_id) throws Exception {
		try {
			Connection connection=connect();
			PreparedStatement statement=connection.prepareStatement("INSERT INTO eventteam (event_id,team_id) VALUES(?,?)");
			statement.setLong(1, event_id);
			statement.setLong(2, team.getTeamId());
			statement.executeUpdate();
			connection.close();
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	public void deleteEvent(Event event) throws Exception{
		try {
			Connection connection=connect();
			PreparedStatement firstStatement=connection.prepareStatement("DELETE FROM event where event_id="+event.getEventId()+"");
			PreparedStatement secondStatement=connection.prepareStatement("DELETE FROM eventteam where event_id="+event.getEventId()+"");
			firstStatement.executeUpdate();
			secondStatement.executeUpdate();
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	public static void main(String[]args) throws Exception {
		DAOEvent daoEvent=new DAOEvent();
		/**System.out.println(daoEvent.events.get(0).getEventName());
		System.out.println(daoEvent.events.get(0).getEventTeams().get(0).getTeamName());
		System.out.println(daoEvent.events.get(0).getEventTeams().get(1).getTeamName());**/
		Event event=new Event();
		event.setEventId(5);
		event.setEventName("rma vs atm");
		daoEvent.deleteEvent(event);
		/**Team team=new Team();
		team.setTeamId(6);
		team.setTeamName("atm");
		daoEvent.addTeam(team, 5l);**/
	}
}
