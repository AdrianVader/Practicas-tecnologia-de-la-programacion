package tp.pr1;
import tp.pr1.Directions;
import tp.pr1.VerbCommands;



public class Command {

	private VerbCommands verb;
	private Directions dir;
	
	
	
	//Default constructor
	public Command (){
		
		this.verb = VerbCommands.UNKNOWN;
		this.dir = Directions.UNKNOWN;
	}
	
	
	//Creates a new command based on a verb
	public Command(VerbCommands verbCommand){
		
		this.verb = verbCommand;
		this.dir = Directions.UNKNOWN;
	}
	
	
	//Checks if the command is valid
	public boolean isValid(){
	
		boolean valid = false;
		if((this.verb == VerbCommands.GO)&& (this.dir == Directions.NORTH))
			valid = true;
		else if((this.verb == VerbCommands.GO)&& (this.dir == Directions.SOUTH))
			valid = true;
		else if((this.verb == VerbCommands.GO)&& (this.dir == Directions.EAST))
			valid = true;
		else if((this.verb == VerbCommands.GO)&& (this.dir == Directions.WEST))
			valid = true;
		else if(this.verb == VerbCommands.HELP)
			valid = true;
		else if(this.verb == VerbCommands.QUIT)
			valid = true;

		return valid;
	}
	
	
	//Returns the verb contained in the command
	public VerbCommands getVerb(){
		
		return this.verb;
	}
	
	
	//Returns the direction contained in the command
	public Directions getDirection(){
		
		return this.dir;
	}
	
	public void setVerb(VerbCommands verbCommand){
		
		this.verb = verbCommand;
	}
	
	
	//Sets the command direction
	public void setDirection(Directions direction){
		
		this.dir = direction;
	}

}
