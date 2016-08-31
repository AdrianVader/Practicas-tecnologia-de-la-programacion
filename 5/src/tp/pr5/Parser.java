package tp.pr5;

import java.util.ArrayList;

import tp.pr5.commands.*;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class Parser {
	
	public Parser(){
	}
	
	
	//Returns information about all the commands that the game understands
	public static java.lang.String getHelp(){
		
		return Constants.HELP;
		
	}
	
	
	//Parse the next command according to the user input. It returns the corresponding command
	public static Command parseCommand(java.lang.String line) throws WrongCommandFormatException{
		
		ArrayList<Command> commands = new ArrayList<Command>();
		
		commands.add(new GoCommand());
		commands.add(new QuitCommand());
		commands.add(new LookCommand());
		commands.add(new ExamineCommand());
		commands.add(new PickCommand());
		commands.add(new DropCommand());
		commands.add(new HelpCommand());
		commands.add(new UseCommand());
		commands.add(new UndoCommand());
		
		for(int i = 0; i < commands.size();i++){
			try{
				return commands.get(i).parse(line);
			}
			catch (WrongCommandFormatException e){}
		}

		throw new WrongCommandFormatException(); // If "line" doesn't match any of the commands
		
	}
	
}
