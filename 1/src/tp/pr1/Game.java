package tp.pr1;
import java.util.Scanner;

import tp.pr1.Directions;
import tp.pr1.Room;
import tp.pr1.Parser;

public class Game {

	private Parser parser;
	private Room current;
	
	//Creates the parser and the rooms
	public Game(){

	this.parser = new Parser();  //Creates a new parser with an UNKNOWN verb and direction
	
	}
	
	//Tries to change the room where the player is located, moving him into another room.
	protected void changeRoom(Directions direction){
		
		switch(direction){
		
		case NORTH:
			if(current.getRooms()[0] != null){
				this.setCurrent(this.current.getRooms()[0]);
				System.out.println(current.getDescription());				
				}
			else
				System.out.println("The way is closed in direction " + direction.name());
			break;
		
		case SOUTH:
			if(current.getRooms()[1] != null){
				this.setCurrent(this.current.getRooms()[1]);
				System.out.println(current.getDescription());				
				}
			else
				System.out.println("The way is closed in direction " + direction.name());
			break;
		
		case EAST:
		if(current.getRooms()[2] != null){
			this.setCurrent(this.current.getRooms()[2]);
			System.out.println(current.getDescription());				
			}
		else System.out.println("The way is closed in direction " + direction.name());
			break;
		
		case WEST:
			if(current.getRooms()[3] != null){
				this.setCurrent(this.current.getRooms()[3]);
				System.out.println(current.getDescription());				
				}
			else System.out.println("The way is closed in direction " + direction.name());
			break;
			
			default: break;  //If the direction is UNKNOWN
			
			
		}				
		
	}
	
	//Returns a reference to the room where the player is currently located.
	public Room getCurrentRoom(){
		
		return this.current;
	}

	//Initializes the application, creating the rooms and the parser. Returns false if the start room is null
	public boolean initGame(Room startRoom){
		
		boolean ok = false;
		
		if(startRoom != null){
			ok = true;
			this.current = startRoom;
		} 
				
		return ok;
	}
	
	//Given a command, process (that is: execute) the command.
	protected boolean processCommand(Command command){
		
		boolean exit = false;
		
		if(command.getVerb() == VerbCommands.QUIT){exit = true;
		
		}else if(command.getVerb() == VerbCommands.HELP){
			
			System.out.println(this.parser.getHelp());
		
		}else if(command.getVerb() == VerbCommands.GO){
			
			if(command.getDirection() != Directions.UNKNOWN){
			this.changeRoom(command.getDirection());
				if(this.getCurrentRoom().isExit()){exit = true;}
			}
			
		}
			
			
		
		return exit;
	}
	
	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}


	public Room getCurrent() {
		return current;
	}

	public void setCurrent(Room current) {
		this.current = current;
	}

	//Main loop of the game
	public void runGame(){
		
		boolean exit = false;
		Command command;
		Scanner sc = new Scanner(System.in);		
		
		System.out.println(this.current.getDescription());  //Writes the initial room description
		
		while(!exit){
			
			do{
				System.out.print("> ");
				java.lang.String com = sc.nextLine();
				command = this.parser.nextCommand(com);  //Transforms the string com into a command
				
				if(command.isValid()){
					
					if(command.getVerb() == VerbCommands.QUIT)
						exit = true;
					else if(command.getVerb() == VerbCommands.HELP)
						System.out.println(this.parser.getHelp());
					else {  //If the command is GO
						this.changeRoom(command.getDirection());
						if(this.current.isExit())exit = true;
					}
					
				}else
					System.out.println("What?");
				
			}while(!command.isValid());
			
		}
		
		System.out.println("GAME OVER" + '\n' + "Thank you for playing, goodbye.");  //The end.
		
	}	
		
}