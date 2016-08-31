package tp.pr3.commands;
import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;



public interface Command{
	
	
	//Executes the command. It must be implemented in every non abstract subclass
	public void execute() throws CommandExecutionException;
	
	
	//Returns a description of the command syntax
	public java.lang.String getHelp();
	
	
	//Parses the String returning an instance its corresponding subclass if the string fits the command's syntax
	public Command parse(java.lang.String cad, Game execContext) throws WrongCommandFormatException;
	
}


