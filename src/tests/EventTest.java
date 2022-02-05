package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import models.Event;
import models.Team;

class EventTest {
	static Event event=new Event();
	static Team team = new Team();
	@BeforeAll
	static void intializeEvent() {	
		event.setEventId(1);
		event.setEventName("eventTest");
		team.setTeamId(1);
		team.setTeamName("teamTest");
	}
	
	@Test
	void testAddTeam1() {
		event.addTeam(team);
		assertTrue(event.getEventTeams().contains(team));
	}
	@Test
	void testAddTeam2() {
		event.addTeam(team);
		assertTrue(event.getEventTeams().contains(team));
	}
	
	@Test
	void testReplaceTeam() {
		Team newTeam=new Team();
		newTeam.setTeamId(2);
		newTeam.setTeamName("newTeamTest");
		event.replaceTeam(1, newTeam);
		assertTrue(event.getEventTeams().contains(newTeam));
	}
	@Test
	void testReplaceTeam2() {
		Team newTeam=new Team();
		newTeam.setTeamId(3);
		newTeam.setTeamName("newTeamTest");
		event.replaceTeam(4, newTeam);
		assertFalse(event.getEventTeams().contains(newTeam));
	}
	
	@Test
	void testDeleteTeam() {
		event.deleteTeam(2);
		assertFalse(event.getEventTeams().contains(team));
	}
	
	@Test
	void testDeleteTeam2() {
		event.deleteTeam(2);
		assertFalse(event.getEventTeams().contains(team));
	}
	
	
}
