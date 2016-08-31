package tp.pr3.commands;

import tp.pr3.Constants;
import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;
import tp.pr3.items.Item;

public class PickCommand implements Command{
	
	
	private String idItem;
	private Game game;
	
	
	
	//Constructor for a pick command
	public PickCommand(){
		this.idItem = "";
		this.game = null;
	}
	
	

	@Override
	public void execute() throws CommandExecutionException {

		boolean encontrado = false;
		
		Item item = null;
			for(int i = 0; i < this.game.getCurrentMap().getCurrentRoom().getItems().size(); i++){
				if(this.game.getCurrentMap().getCurrentRoom().getItems().get(i).getId().equalsIgnoreCase(this.idItem)){
					item = this.game.getCurrentMap().getCurrentRoom().getItems().get(i);
					encontrado = true;
				}
			}
			
			/*if(!this.game.getMap().getCurrentRoom().existsItem(this.idItem.toLowerCase()))
				throw new CommandExecutionException(Constants.MESSAGE_PICK_ERROR + '\n');*/
			
			if(!encontrado){
				throw new CommandExecutionException(Constants.MESSAGE_PICK_ERROR + '\n');//???????????????????????????????????????????????????????
			}
			
			
			else if(!(this.game.getPlayer().addItem(item)))
				throw new CommandExecutionException("You have another " + this.idItem + " in your inventory.\n");
				
			else
				this.game.getCurrentMap().getCurrentRoom().getItems().remove(item);
	}

	
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "PICK|COGER <<id>>";
	}

	
	//Parses the String returning a PickCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{

		PickCommand pickCommand = new PickCommand();
		String words[] = cad.split(" ");

			if (words[0].toUpperCase().equals("PICK") || words[0].toUpperCase().equals("COGER")){
				if(words.length != 2)
					throw new WrongCommandFormatException();
				else
				pickCommand.idItem = words[1];
				pickCommand.game = execContext;
			}
			else throw new WrongCommandFormatException();
		
		return pickCommand;
	}

}
