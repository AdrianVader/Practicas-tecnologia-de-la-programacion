package tp.pr3.commands;

import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;



public class DropCommand implements Command{

	private Game game;
	private String idItem;
	
	
	
	//Constructor for a drop command
	public DropCommand(){
		this.game = null;
		this.idItem = "";
	}
	
	
	
	//The execution tries to drop the item contained in the player inventory in the current room
	@Override
	public void execute() throws CommandExecutionException {

		boolean encontrado = false;
		
			if(this.game.getPlayer().getInventory().size() > 0){
				
				for(int i = 0; i < this.game.getPlayer().getInventory().size(); i++){
					
					if(this.game.getPlayer().getInventory().get(i).getId().equalsIgnoreCase(this.idItem)){
						encontrado = true;
						
						if(!this.game.getCurrentMap().getCurrentRoom().addItem(this.game.getPlayer().getInventory().get(i))){//add the item to the current room the player is in.
							throw new CommandExecutionException("Ya hay uno igual en la habitación!");//---no sé cual es el mensaje correcto---
						}
						else								
							this.game.getPlayer().removeItem(this.idItem);//remove the item from the player's inventory.						
					}
				}
			}
				
			
			if (!encontrado){
				throw new CommandExecutionException("You do not have any " + this.idItem + ".");//*****************someone stole your
			}
		
	}

	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "DROP|SOLTAR <<id>>";
	}

	
	
	//Parses the String returning an DropCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{

		DropCommand dropCommand = new DropCommand();
		String words[] = cad.split(" ");

			if (words[0].toUpperCase().equals("DROP") || words[0].toUpperCase().equals("SOLTAR")){
				if(words.length != 2)
					throw new WrongCommandFormatException();
				else{
					dropCommand.idItem = words[1];
					dropCommand.game = execContext;
				}
			}
			else throw new WrongCommandFormatException();
		
		return dropCommand;
		
	}

	/*public static void main(String[] args){
		
		try{
			DropCommand drop = new DropCommand();
			drop.parse("drop", null).execute();
		}
		catch(Exception e){}
	}*/
	
}
