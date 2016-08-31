package tp.pr4.commands;

import tp.pr4.Constants;
import tp.pr4.Game;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;

public class LookCommand implements Command{

	
	
	private String idItem;
	private Game game;
	
	
	
	//Constructor for a look command
	public LookCommand(){
		
		this.idItem = "";
		this.game = null;
	}
	
	
	
	
	
	@Override
	public void execute() throws CommandExecutionException {

		if (this.idItem.equals("")) //Look
			
			if(this.game.getPlayer().getInventory().size() <= 0)
				System.out.println(Constants.MESSAGE_POOR);
				
			else{ //The player has at least one item in the inventory
					
				System.out.println(Constants.MESSAGE_ITEMS);
				for(int i = 0;i < this.game.getPlayer().getInventory().size(); i++)
					System.out.println(this.game.getPlayer().getInventory().get(i).toString());
				
			}
			
			//this.game.getPlayer().showItems();
					
			
		else //Look + <item>
			if(this.game.getPlayer().getInventory().size() <= 0)
				throw new CommandExecutionException("There is no " + this.idItem.toLowerCase() + " in your inventory.");
					
			else{
				boolean encontrado = false;
				for(int i = 0; i < this.game.getPlayer().getInventory().size(); i++){
						
					if(this.game.getPlayer().getInventory().get(i).getId().equalsIgnoreCase(this.idItem)){							
						System.out.println(this.game.getPlayer().getInventory().get(i).toString());
						encontrado = true;
							
					}
							
				}
				if(!encontrado)
					throw new CommandExecutionException("There is no " + this.idItem.toLowerCase() + " in your inventory.");
			}
	}

	
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "LOOK|MIRA [<<id>>]";
	}

	
	//Parses the String returning a LookCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{

		LookCommand lookCommand = new LookCommand();
		String words[] = cad.split(" ");
		if (words.length == 1){
			if (!(words[0].toUpperCase().equals("LOOK") || words[0].toUpperCase().equals("MIRA")))
				throw new WrongCommandFormatException();
			else
					lookCommand.game = execContext;
		}
		else if (words.length == 2){
			if (!(words[0].toUpperCase().equals("LOOK") || words[0].toUpperCase().equals("MIRA")))
				throw new WrongCommandFormatException();
			else{
					lookCommand.game = execContext;
					lookCommand.idItem = words[1];
			}
		}
		else
			throw new WrongCommandFormatException();
		return lookCommand;
	}

}
