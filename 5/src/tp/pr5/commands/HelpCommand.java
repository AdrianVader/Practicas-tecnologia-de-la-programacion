package tp.pr5.commands;


import tp.pr5.Map;
import tp.pr5.Game;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class HelpCommand implements Command{
	
	private Game game;
	@SuppressWarnings("unused")
	private Player player;
	@SuppressWarnings("unused")
	private Map map;
	
	
	
	//Constructor for a help command
	public HelpCommand(){
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
	public void execute() throws CommandExecutionException{
		this.game.requestHelp();
	}
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "HELP|AYUDA";
	}

	
	//Parses the String returning a HelpCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{

		HelpCommand helpCommand = new HelpCommand();
		
		String words[] = cad.split(" ");

			if((words[0].toUpperCase().equals("HELP") || words[0].toUpperCase().equals("AYUDA"))){
				if(words.length != 1)
					throw new WrongCommandFormatException();
			}
			else
				throw new WrongCommandFormatException();	
				
		return helpCommand;
	}
	

}
