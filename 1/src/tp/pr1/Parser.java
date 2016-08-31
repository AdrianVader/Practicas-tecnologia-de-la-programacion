package tp.pr1;
import tp.pr1.Command;

public class Parser {

	
	private Command command = new Command();
	
	
	
	public Parser (){
		
		this.command.setVerb(VerbCommands.UNKNOWN);
		this.command.setDirection(Directions.UNKNOWN);
	}
	
	//Returns information about all the commands that the game understands
	public java.lang.String getHelp(){
		
		java.lang.String help = "You are lost. You are alone. You wander around." + '\n' + "Your command words are:\n  HELP\n  GO { NORTH | SOUTH | EAST | WEST }\n  QUIT\n";
		return help;
	}
	
	
	//Parses the next command in the String. It returns a command
	public Command nextCommand(java.lang.String line){
		
		String [] words = line.split(" ");  //Splits the string in words
		
		if (words.length <= 2){
			if ((words[0].toUpperCase().equals(VerbCommands.GO.name()) && (words.length == 2))){
				
				this.command.setVerb(VerbCommands.GO);
				
				if(words[1].toUpperCase().equals(Directions.NORTH.name()))
					this.command.setDirection(Directions.NORTH);
				
				else if(words[1].toUpperCase().equals(Directions.SOUTH.name()))
					this.command.setDirection(Directions.SOUTH);
			
				else if(words[1].toUpperCase().equals(Directions.EAST.name()))
					this.command.setDirection(Directions.EAST);
			
				else if(words[1].toUpperCase().equals(Directions.WEST.name()))
					this.command.setDirection(Directions.WEST);
				else
					this.command.setDirection(Directions.UNKNOWN);  //If the second (and last) word isn't a valid direction

			}
			
			else if (words.length == 1){
				
				if ((words[0].toUpperCase().equals(VerbCommands.GO.name())))
					this.command.setVerb(VerbCommands.GO);
				else if(words[0].toUpperCase().equals(VerbCommands.HELP.name()))
					this.command.setVerb(VerbCommands.HELP);
		
				else if(words[0].toUpperCase().equals(VerbCommands.QUIT.name()))
					this.command.setVerb(VerbCommands.QUIT);
				else
					this.command.setVerb(VerbCommands.UNKNOWN); //If the word isn't a valid verb command
				this.command.setDirection(Directions.UNKNOWN);  //In this case the direction is always unknown
			}

			else{  //If there are two words but the first one isn't GO
				this.command.setVerb(VerbCommands.UNKNOWN);
				this.command.setDirection(Directions.UNKNOWN);}
		}			
		else{  //If there are more than two words (or something is wrong)
			this.command.setVerb(VerbCommands.UNKNOWN);
			this.command.setDirection(Directions.UNKNOWN);}
		return this.command;
	}
}
