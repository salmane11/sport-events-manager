package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SportEventsMangerApplication {

	public static void main(String[]args) {
		long eventId=0;
		long teamId=1;
		long playerId=1;
		List<Event> events=new ArrayList<Event>();
		List<Team> teams=new ArrayList<Team>();
		List<Player> players=new ArrayList<Player>();
		
		
		Scanner keyb=new Scanner(System.in);
		Scanner scanner=new Scanner(System.in);
		char enteredValue='a';
		while (enteredValue!='q') {
			System.out.println("\nSport Events Manager App");
			System.out.println("feel free to ask what you want");
			System.out.println("press a to create a new event");
			System.out.println("press b to create a team");
			System.out.println("press c to add a team to an event");
			System.out.println("press d to delete a team");
			System.out.println("press e to visualize the events coming");
			System.out.println("press f to delete an event ");
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
					
					LocalDateTime eventDate=LocalDateTime.now();
					myEvent.setEventDate(eventDate);
					System.out.println(eventDate);					
					
					System.out.println("\n\nyour event was successfully created");
					System.out.println("to add a team press a");
					events.add(myEvent);
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
					for(int i=0;i<n;i++) {
						System.out.println("please enter the player "+i+" name");
						String playerName=scanner.nextLine();
						System.out.println("please enter the player "+i+" position");
						String playerPosition=scanner.nextLine();
						System.out.println("please enter the player "+i+" age");
						int playerAge=keyb.nextInt();
						Player myPlayer=new Player(playerId++,playerName,playerAge,teamId-1,playerPosition);
						players.add(myPlayer);
						myTeam.addPlayer(myPlayer);
					}
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
									event.addTeam(team);
									result.append("team added successfully"+team.getTeamName());
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
					
					for(Team team:teams) {
						if(team.getTeamName().equals(teamsName)) {
							teams.remove(team);
							System.out.println("the team selected was removed successfully");
						}
					}
					System.out.println("the team entered does not exist");
					break;
					
				case 'e':
					if(events.isEmpty()) {
						System.out.println("no events are coming ");
					}else {
						System.out.println("the events comming are ");
						for(int i=0; i<events.size();i++) {
							System.out.println("event "+(i+1));
							System.out.println(events.get(i).getEventName()+"  "+events.get(i).getEventDate());
							if(events.get(i).eventTeams!=null) {
								System.out.println("teams participating: ");
								for(Team team:events.get(i).eventTeams) {
									System.out.print("team "+i+"  :"+team.getTeamName());
								}
							}
							System.out.println("no teams yet");
						}
					}
					break;
					
				case 'f':
					System.out.println("to delete an event please enter his name");
					String enteredName=scanner.nextLine();
					for(Event event:events) {
						if(event.getEventName().equals(enteredName)) {
							events.remove(event);
							System.out.println("the event chosen was removed successfully");
							break;
						}
					}
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
