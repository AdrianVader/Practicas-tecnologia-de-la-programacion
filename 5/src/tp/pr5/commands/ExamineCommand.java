package tp.pr5.commands;

import tp.pr5.Map;
import tp.pr5.Game;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class ExamineCommand implements Command{
	
	
	@SuppressWarnings("unused")
	private Game game;
	@SuppressWarnings("unused")
	private Player player;
	private Map map;
	
	
	//Constructor for an examine command
	public ExamineCommand(){
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
	public void execute() throws CommandExecutionException {
		this.map.playerExamineRoom();
	}

	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "EXAMINE|EXAMINAR";
	}

	
	//Parses the String returning an ExamineCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{

		
		ExamineCommand examineCommand = new ExamineCommand();
		String words[] = cad.split(" ");
	
		if((words[0].toUpperCase().equals("EXAMINE") || words[0].toUpperCase().equals("EXAMINAR"))){
			if(words.length != 1)
				throw new WrongCommandFormatException();
		}
		else
			throw new WrongCommandFormatException();	

		
		return examineCommand;
	}
	

}
