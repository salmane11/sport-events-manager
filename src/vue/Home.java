package vue;

import java.time.LocalDate;
import java.util.List;

import javax.swing.border.LineBorder;

import dao.DAOEvent;
import dao.DAOTeam;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Event;
import models.Team;

public class Home extends Application{

	
	
		 public void start(Stage homeStage) throws Exception{
			 //home page creation
			 
			 homeStage.setTitle("Sport Events manager");
			 BorderPane root = new BorderPane();
			 Button startButton = new Button("start managing your sport events");
			 root.setCenter(startButton);
			 LinearGradient lg2=new LinearGradient(0, 0, 1, 1, true,CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.web("#fcc200")));
			 Scene scene = new Scene(root,1000, 500);
			 root.setBackground(new Background(new BackgroundFill(lg2, CornerRadii.EMPTY, Insets.EMPTY)));
			 homeStage.setScene(scene);
			 
			 
			 //events page creation
			 Text title=new Text();
			 title.setText("coming events");
			 title.setFont(new Font(30));
			 title.setX(10);
			 
			 //importing events
			 DAOEvent daoEvent=new DAOEvent();
			 List<Event> events=daoEvent.getEvents();
			 //importing teams
			 DAOTeam daoTeam=new DAOTeam();
			 List<Team> teams=daoTeam.getTeams();
			 
			 //events root creation
			 BorderPane root2 = new BorderPane();
			 Group eventsGroup=new Group();
			 eventsGroup.getChildren().add(title);
			 for(int i=0; i<events.size();i++) {
				 Text text=new Text();
				 StringBuilder eventDetails=new StringBuilder("");
				 eventDetails.append((i+1)+". event name : "+events.get(i).getEventName()+"  "+" -event date : "+events.get(i).getEventDate());
				 if(events.get(i).getEventTeams().size()!=0) {
						eventDetails.append("\nteams participating : ");
						for(Team team:events.get(i).getEventTeams()) {
							eventDetails.append(" "+team.getTeamName()+" --");
						}
				 }else {
						eventDetails.append("no teams yet");
				 }
				 text.setText(eventDetails.toString());
				 text.setY(40+70*i);
				 text.setX(30);
				 text.setFont(new Font(20));
				 eventsGroup.getChildren().add(text);
			}
			 
			//functionnalities buttons
			Group buttonsGroup=new Group();
			Button createEventButton=new Button("create new event");
			Button addTeamsButton=new Button("add teams to an event");
			Button deleteEventButton=new Button("delete an existing event");
			addTeamsButton.setTranslateY(50);
			deleteEventButton.setTranslateY(100);
			buttonsGroup.setTranslateY(20);
			buttonsGroup.setTranslateX(-20);
			buttonsGroup.getChildren().addAll(createEventButton,addTeamsButton,deleteEventButton);
			
			root2.setLeft(eventsGroup);
			root2.setRight(buttonsGroup);
			 
			Scene scene2 = new Scene(root2,1000, 500);
			startButton.setOnAction(event -> homeStage.setScene(scene2));
			
			
			//createEvent page
			BorderPane root3 = new BorderPane();
			Group inputGroup=new Group();
			Label eventLabel=new Label("event name :");
			TextField eventName=new TextField();
			Button submitEventName=new Button("submit");
			inputGroup.getChildren().addAll(eventLabel,eventName,submitEventName);
			eventName.setTranslateY(30);
			submitEventName.setTranslateY(70);
			root3.setCenter(inputGroup);
			Scene scene3=new Scene(root3,1000,500);
			createEventButton.setOnAction(event->homeStage.setScene(scene3));
			
			//event input validation
			long eventId=events.size();
			submitEventName.setOnAction(event->{
				Event myEvent=new Event();
				
				myEvent.setEventName(eventName.getText());
				
				myEvent.setEventId(eventId+1);
				
				LocalDate eventDate=LocalDate.now();
				myEvent.setEventDate(eventDate);				
				
				events.add(myEvent);
				try {
					daoEvent.createEvent(myEvent);
				} catch (Exception e) {

					e.printStackTrace();
				}
				homeStage.setScene(scene2);
			});
			
			//add teams page
			BorderPane root4 = new BorderPane();
			Group teamsGroup=new Group();
			Label eventsLabel=new Label("event name :");
			eventsLabel.setTranslateY(-80);
			Label teamsLabel=new Label("teams names :");
			TextField eventsName=new TextField();
			eventsName.setTranslateY(-40);
			TextField teamsName1=new TextField();
			teamsName1.setTranslateY(40);
			TextField teamsName2=new TextField();
			teamsName2.setTranslateY(80);
			Button submit=new Button("submit");
			submit.setTranslateY(120);
			
			teamsGroup.getChildren().addAll(eventsLabel,eventsName,teamsLabel,teamsName1,teamsName2,submit);
			
			root4.setCenter(teamsGroup);
			Scene scene4 =new Scene (root4,1000,500);
			addTeamsButton.setOnAction(event->homeStage.setScene(scene4));
			
			//inputValidation adding teams;
			submit.setOnAction(event->{
				for(Event searchedEvent:events) {
					if(searchedEvent.getEventName().equals(eventsName.getText())) {
						for(Team team : teams ) {
							if(team.getTeamName().equals(teamsName1.getText())||team.getTeamName().equals(teamsName2.getText())) {
								try {
									daoEvent.addTeam(team, searchedEvent.getEventId());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								searchedEvent.addTeam(team);
								team.addEvent(searchedEvent.getEventId());
							}
						}
					}
				}
				homeStage.setScene(scene2);
				
			});
			
			
			
			//deleteEvent page
			BorderPane root5 = new BorderPane();
			Group inputGroup2=new Group();
			Label eventLabel2=new Label("event name :");
			TextField eventName2=new TextField();
			Button submitEventName2=new Button("submit");
			inputGroup2.getChildren().addAll(eventLabel2,eventName2,submitEventName2);
			eventName2.setTranslateY(30);
			submitEventName2.setTranslateY(70);
			root5.setCenter(inputGroup2);
			Scene scene5=new Scene(root5,1000,500);
			deleteEventButton.setOnAction(event->homeStage.setScene(scene5));
			
			//event input validation

			submitEventName2.setOnAction(event->{
		
				for(Event myEvent:events) {
					if(myEvent.getEventName().equals(eventName2.getText())) {
						for(Team team : myEvent.getEventTeams()) {
							team.deleteEvent(myEvent.getEventId());
						}
						try {
							daoEvent.deleteEvent(myEvent);
						} catch (Exception e) {
							e.printStackTrace();
						}
						events.remove(myEvent);
					}
				}
				homeStage.setScene(scene2);
			});
			
			//show home stage
			homeStage.show();
		 }
		 public static void main(String[] args) {
		 launch(args);
		 }

}
