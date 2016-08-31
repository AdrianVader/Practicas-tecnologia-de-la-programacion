package tp.pr5.commands;

import tp.pr5.Map;
import tp.pr5.Game;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;


public interface Command{
	
	//Set the execution context.
	void configureContext(Game g, Map m, Player p); 
	 
	//Executes the command. It must be implemented in every non abstract subclass
	public void execute() throws CommandExecutionException;
	
	//Returns a description of the command syntax
	public java.lang.String getHelp();
	
	//Parses the String returning an instance its corresponding subclass if the string fits the command's syntax
	public Command parse(java.lang.String cad) throws WrongCommandFormatException;
	
}


