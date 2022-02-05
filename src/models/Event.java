package models;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {
	long eventId;
	String eventName;
	LocalDate eventDate;
	List<Team>eventTeams;
	
	
	
	public Event() {
		eventTeams=new ArrayList<Team>();
	}


	public Event(long eventId, String eventName, LocalDate eventDate, List<Team> eventTeams) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTeams = eventTeams;
	}
	
	
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public LocalDate getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}


	public List<Team> getEventTeams() {
		return eventTeams;
	}
	public void setEventTeams(List<Team> eventTeams) {
		this.eventTeams = eventTeams;
	}
	
	
	public void addTeam(Team team) {
		
		if(!eventTeams.contains(team) && eventTeams.size()<2) {
			eventTeams.add(team);
		}else {
			System.out.println("the event teams are already set. you can replace a team by another one");
		}
	}
	
	public long replaceTeam(long id , Team newTeam) {
		for(Team team :eventTeams) {
			if(team.getTeamId()==id) {
				eventTeams.remove(team);
				eventTeams.add(newTeam);
				System.out.println("the team selected was replaced successfully");
				return id;
			}
		}
		System.out.println("there is no team identified by "+id);
		return -1;
	}
	
	public long deleteTeam(long id ) {
		for(Team team :eventTeams) {
			if(team.getTeamId()==id) {
				eventTeams.remove(team);
				System.out.println("the "+team.getTeamName()+" team was deleted successfully");
				return id;
			}
		}
		System.out.println("there is no team identified by "+id);
		return -1;
	}
	
}
