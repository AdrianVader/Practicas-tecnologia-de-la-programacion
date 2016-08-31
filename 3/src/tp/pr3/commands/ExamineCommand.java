package tp.pr3.commands;

import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;

public class ExamineCommand implements Command{
	
	
	private Game game;
	
	
	//Constructor for an examine command
	public ExamineCommand(){
		this.game = null;
	}

	@Override
	public void execute() throws CommandExecutionException {

		System.out.println(this.game.getCurrentMap().getCurrentRoom().getDescription());
		//System.out.println(this.game.getPlayer().toString());
	}

	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "EXAMINE|EXAMINAR";
	}

	
	//Parses the String returning an ExamineCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{

		
		ExamineCommand examineCommand = new ExamineCommand();
		String words[] = cad.split(" ");
	
		if((words[0].toUpperCase().equals("EXAMINE") || words[0].toUpperCase().equals("EXAMINAR"))){
			if(words.length != 1)
				throw new WrongCommandFormatException();					
			else
				examineCommand.game = execContext;
		}
		else
			throw new WrongCommandFormatException();	

		
		return examineCommand;
	}
	

}
