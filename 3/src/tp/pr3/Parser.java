package tp.pr3;

import tp.pr3.commands.*;
import tp.pr3.commands.exceptions.WrongCommandFormatException;

public class Parser {
	
	public Parser(){
	}
	
	
	//Returns information about all the commands that the game understands
	public static java.lang.String getHelp(){
		
		return "You are lost. You are alone. You wander around\nYour command words are:\n" + new ExamineCommand().getHelp() + new GoCommand().getHelp() + new HelpCommand().getHelp() + new LookCommand().getHelp() + new PickCommand().getHelp() + new DropCommand().getHelp() + new QuitCommand().getHelp() + new UseCommand().getHelp();
		
	}
	
	
	//Parse the next command according to the user input. It returns the corresponding command
	public static Command parseCommand(java.lang.String line, Game executionContext) throws WrongCommandFormatException{
		
		
		try{
			return new GoCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		try{
			return new QuitCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		try{
			return new LookCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		try{
			return new ExamineCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		try{
			return new PickCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		try{
			return new DropCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		try{
			return new HelpCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		try{
			return new UseCommand().parse(line,  executionContext);
		}
		catch (WrongCommandFormatException e){}
		
		throw new WrongCommandFormatException("What?"); // If "line" doesn't match any of the commands
	}
	
}
