package tp.pr3.commands;

import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;

public class QuitCommand implements Command{

	private Game game;
	
	
	
	//Constructor for a quit command
	public QuitCommand(){
		this.game = null;
	}
	
	@Override
	public void execute()  throws CommandExecutionException {
		
		this.game.requestQuit();
		
	}

	
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "QUIT|SALIR";
	}

	
	//Parses the String returning a QuitCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{
		
		QuitCommand quitCommand = new QuitCommand();
		String words[] = cad.split(" ");

			if((words[0].toUpperCase().equals("QUIT") || words[0].toUpperCase().equals("SALIR"))){
				if(words.length != 1)
					throw new WrongCommandFormatException();
				else
					quitCommand.game = execContext;
			}
			else
				throw new WrongCommandFormatException();	
			

		return quitCommand;		
	}

}