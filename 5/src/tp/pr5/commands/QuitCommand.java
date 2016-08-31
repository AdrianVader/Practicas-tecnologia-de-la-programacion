package tp.pr5.commands;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class QuitCommand implements Command{

	private Game game;
	@SuppressWarnings("unused")
	private Player player;
	@SuppressWarnings("unused")
	private Map map;
	
	
	//Constructor for a quit command
	public QuitCommand(){
		this.game = null;
		this.player = null;
		this.map = null;
	}
	
	
	
	@Override
	public void configureContext(Game g, Map m, Player p) {
		
		this.game = g;
		this.map = m;
		this.player = p;
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
	public Command parse(String cad) throws WrongCommandFormatException{
		
		QuitCommand quitCommand = new QuitCommand();
		String words[] = cad.split(" ");

			if((words[0].toUpperCase().equals("QUIT") || words[0].toUpperCase().equals("SALIR"))){
				if(words.length != 1)
					throw new WrongCommandFormatException();
			}
			else
				throw new WrongCommandFormatException();	
			

		return quitCommand;		
	}

	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	

}