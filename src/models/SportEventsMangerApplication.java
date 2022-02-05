package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.DAOEvent;
import dao.DAOPlayer;
import dao.DAOTeam;

public class SportEventsMangerApplication {

	public static void main(String[]args) throws Exception {
		
		DAOPlayer daoPlayer=new DAOPlayer();
		DAOTeam daoTeam=new DAOTeam();
		DAOEvent daoEvent=new DAOEvent();
		List<Event> events=daoEvent.getEvents();
		List<Team> teams=daoTeam.getTeams();
		List<Player> players=daoPlayer.getPlayers();
		long eventId=events.size()+1;
		long teamId=teams.size()+1;
		long playerId=players.size()+1;
		
		
		Scanner keyb=new Scanner(System.in);
		Scanner scanner=new Scanner(System.in);
		char enteredValue='a';
		while (enteredValue!='q') {
			System.out.println("\n\nSport Events Manager App");
			System.out.println("feel free to ask what you want");
			System.out.println("press a to create a new event");
			System.out.println("press b to create a team");
			System.out.println("press c to add a team to an event");
			System.out.println("press d to delete a team");
			System.out.println("press e to visualize the events coming");
			System.out.println("press f to delete an event ");
			System.out.println("press g to visualize teams details");
			System.out.println("press q to exit the sport events manager\n");
			enteredValue=keyb.next().charAt(0);
			
			switch(enteredValue) {
				case 'a':
					Event myEvent=new Event();
					
					System.out.println("please enter the event name");
					String eventName=scanner.nextLine();
					System.out.println(eventName);
					myEvent.setEventName(eventName);
					
					long myEventId=++eventId;
					myEvent.setEventId(myEventId);
					System.out.println(myEventId);
					
					LocalDate eventDate=LocalDate.now();
					myEvent.setEventDate(eventDate);
					System.out.println(eventDate);					
					
					System.out.println("\n\nyour event was successfully created");
					System.out.println("to add a team press a");
					events.add(myEvent);
					daoEvent.createEvent(myEvent);
					break;
								
				case 'b':
					Team myTeam=new Team();
					myTeam.setTeamId(teamId++);
					System.out.println("please fill these informations to complete team creation");
					
					System.out.println("please enter the team's name");
					String teamName;
					teamName=scanner.nextLine();
					myTeam.setTeamName(teamName);
					
					System.out.println("please enter players number");
					int n=keyb.nextInt();
					for(int i=1;i<=n;i++) {
						System.out.println("please enter the player "+i+" name");
						String playerName=scanner.nextLine();
						System.out.println("please enter the player "+i+" position");
						String playerPosition=scanner.nextLine();
						System.out.println("please enter the player "+i+" age");
						int playerAge=keyb.nextInt();
						Player myPlayer=new Player(playerId++,playerName,playerAge,teamId-1,playerPosition);
						daoPlayer.createPlayer(myPlayer);
						players.add(myPlayer);
						myTeam.addPlayer(myPlayer);
					}
					daoTeam.createTeam(myTeam);
					teams.add(myTeam);
					System.out.println("your team was created successfully;");
					break;
					
				case 'c':
					System.out.println("please enter the eventName name");
					String evName=scanner.nextLine();
					System.out.println("please enter the team name");
					String teName=scanner.nextLine();
					StringBuilder result=new StringBuilder("");
					for(Event event:events) {
						if(event.getEventName().equals(evName)) {
							for(Team team : teams ) {
								if(team.getTeamName().equals(teName)) {
									daoEvent.addTeam(team, event.getEventId());
									event.addTeam(team);
									team.addEvent(event.getEventId());
									result.append("team added successfully :"+team.getTeamName());
									break;
								}
							}
						}
					}
					if(result.length()<10 && events.size()>0){
						result.append("event Name or team name does not exist please try another name");
					}else if(events.size()==0){
						result.append("there is no events in the moment");
					}
					System.out.println(result.toString());
					break;
					
				case 'd':
					System.out.println("enter the team's name");
					String teamsName = scanner.nextLine();
					StringBuilder response=new StringBuilder("");
					for(Team team:teams) {
						if(team.getTeamName().equals(teamsName)) {
							daoTeam.deleteTeam(team);
							teams.remove(team);
							response.append("the team selected was removed successfully");
							for(Event event :events) {
								event.getEventTeams().remove(team);
							}
							break;
						}
					}
					if(response.length()<5) {
						response.append("the team entered does not exist");
					}
					System.out.println(response.toString());
					break;
					
				case 'e':
					if(events.isEmpty()) {
						System.out.println("no events are coming ");
					}else {
						System.out.println("\nthe events comming are ");
						for(int i=0; i<events.size();i++) {
							System.out.println("\nevent "+(i+1));
							System.out.println(events.get(i).getEventName()+"  "+events.get(i).getEventDate());
							if(events.get(i).eventTeams.size()!=0) {
								System.out.println("teams participating: ");
								for(Team team:events.get(i).eventTeams) {
									System.out.println("team  :"+team.getTeamName()+"  ");
								}
							}else {
								System.out.println("no teams yet");
							}
						}
					}
					break;
					
				case 'f':
					System.out.println("to delete an event please enter his name");
					String enteredName=scanner.nextLine();
					StringBuilder feedback=new StringBuilder("");
					for(Event event:events) {
						if(event.getEventName().equals(enteredName)) {
							for(Team team : event.getEventTeams()) {
								team.deleteEvent(event.getEventId());
							}
							daoEvent.deleteEvent(event);
							events.remove(event);
							feedback.append("the event chosen was removed successfully");
							break;
						}
					}
					if(feedback.length()<5) {
						feedback.append("the event name does not exist");
					}
					System.out.println(feedback.toString());
					break;
					
				case 'g':
					System.out.println("please enter the team you want to discover");
					StringBuilder details=new StringBuilder("");
					String teamLogo=scanner.nextLine();
					for(Team team :teams ) {
						if(team.getTeamName().equals(teamLogo)) {
							details.append("name : "+team.getTeamName());
							details.append("\n_"+team.showTeamPlayers());
							for(Long eventIdentifier : team.teamEvents) {
								for(Event event :events) {
									if(event.getEventId()==eventIdentifier) {
										details.append("\nevent Name : " +event.eventName+" -- event date : "+event.eventDate+" , ");
										break;
									}
								}
							}
						}
					}
					System.out.println(details.toString());
					break;
				case 'q':
					System.out.println("goodbuye");
					break;
					
				default :
					System.out.println("please select a valid character");
					break;
				}
		}
	}
}
