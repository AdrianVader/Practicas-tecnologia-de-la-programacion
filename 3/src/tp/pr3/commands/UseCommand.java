package tp.pr3.commands;

import tp.pr3.Constants;
import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;

public class UseCommand implements Command{

	
	private String idItem;
	private Game game;
	
	
	
	//Constructor for a use command
	public UseCommand(){
		
		this.idItem = "";
		this.game = null;
	}
	
	
	
	@Override
	public void execute() throws CommandExecutionException {

		boolean encontrado = false;

		if(this.game.getPlayer().getInventory().size() > 0){
				
			for(int i = 0; i < this.game.getPlayer().getInventory().size(); i++){
					
				if(this.game.getPlayer().getInventory().get(i).getId().equalsIgnoreCase(this.idItem)){
					encontrado = true;						
						
					//if(this.game.getPlayer().getInventory().get(i).use(this.game.getPlayer(), this.game.getMap().getCurrentRoom())){
						//if(this.game.getPlayer().dead())
							//exit = true; // *********************************************************************************************************************qué hago con esto?????
							
					//}
					if (!(this.game.getPlayer().getInventory().get(i).use(this.game.getPlayer(), this.game.getCurrentMap().getCurrentRoom())))
						throw new CommandExecutionException(Constants.MESSAGE_CHANGES_ERROR);
					//else 
						//throw new CommandExecutionException(Constants.MESSAGE_CHANGES_ERROR);
				}					
			}
		}
			
		if (!encontrado){
			throw new CommandExecutionException(Constants.MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS + this.idItem.toLowerCase() + ".");
		}
	}

	
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "USE|USAR <<id>>";
	}

	
	//Parses the String returning a UseCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{

		UseCommand useCommand = new UseCommand();
		String words[] = cad.split(" ");
		if (words[0].toUpperCase().equals("USE") || words[0].toUpperCase().equals("USAR")){
			if(words.length != 2)
				throw new WrongCommandFormatException();
			else{
				useCommand.idItem = words[1];
				useCommand.game = execContext;
			}
		}
		else throw new WrongCommandFormatException();
		return useCommand;
	}

}
