package tp.pr5.commands;

import tp.pr5.Map;
import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class LookCommand implements Command{

	
	private String idItem;
	private Game game;
	private Player player;
	@SuppressWarnings("unused")
	private Map map;
	
	
	
	//Constructor for a look command
	public LookCommand(){
		
		this.idItem = "";
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

		if (this.idItem.equals("")){ //Look
			
			
			this.player.requestLookInventory();
			
		}
		else //Look + <item>
			if(this.player.getInventory().size() <= 0){
				this.game.requestError(Constants.MESSAGE_NO_ITEM(this.idItem));
				throw new CommandExecutionException();
			}		
			else{
				boolean encontrado = false;
				for(int i = 0; i < this.player.getInventory().size(); i++){
						
					if(this.player.getInventory().get(i).getId().equalsIgnoreCase(this.idItem)){
						this.player.lookItem(this.player.getInventory().get(i).toString());
						encontrado = true;
							
					}
							
				}
				if(!encontrado){
					this.game.requestError(Constants.MESSAGE_NO_ITEM(this.idItem));
					throw new CommandExecutionException();
				}
			}
	}

	
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "LOOK|MIRA [<<id>>]";
	}

	
	//Parses the String returning a LookCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{

		LookCommand lookCommand = new LookCommand();
		String words[] = cad.split(" ");
		if (words.length == 1){
			if (!(words[0].toUpperCase().equals("LOOK") || words[0].toUpperCase().equals("MIRA")))
				throw new WrongCommandFormatException();
		}
		else if (words.length == 2){
			if (!(words[0].toUpperCase().equals("LOOK") || words[0].toUpperCase().equals("MIRA")))
				throw new WrongCommandFormatException();
			else{
					lookCommand.idItem = words[1];
			}
		}
		else
			throw new WrongCommandFormatException();
		return lookCommand;
	}


}
