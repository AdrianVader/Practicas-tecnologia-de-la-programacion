package tp.pr5.commands;

import tp.pr5.Constants;
import tp.pr5.Map;
import tp.pr5.Game;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;



public class DropCommand implements Command{

	private Game game;
	private Player player;
	private Map map;
	private String idItem;
	
	
	
	//Constructor for a drop command
	public DropCommand(){
		this.game = null;
		this.player = null;
		this.map = null;
		this.idItem = "";
	}
	

	
	@Override
	public void configureContext(Game g, Map m, Player p) {
		
		this.game = g;
		this.map = m;
		this.player = p;
	}
	
	//The execution tries to drop the item contained in the player inventory in the current room
	@Override
	public void execute() throws CommandExecutionException {

		boolean encontrado = false;
		
			if(this.player.getInventory().size() > 0){
				
				for(int i = 0; i < this.player.getInventory().size(); i++){
					
					if(this.player.getInventory().get(i).getId().equalsIgnoreCase(this.idItem)){
						encontrado = true;
						
						if(!this.map.getCurrentRoom().addItem(this.player.getInventory().get(i))){//add the item to the current room the player is in.
							
							this.game.requestError(Constants.MESSAGE_DROP_ERROR(this.idItem));
							
							throw new CommandExecutionException();
							
						}
						else{								
							this.player.removeItem(this.idItem);//remove the item from the player's inventory.
							
							this.map.requestRoomInventoryUpdate();
							
						}
					}
				}
			}
				
			
			if (!encontrado){
				
				this.game.requestError(Constants.MESSAGE_NO_ITEM(this.idItem));
				
				throw new CommandExecutionException();
			}
	}

	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "DROP|SOLTAR <<id>>";
	}
	
	
	//Parses the String returning an DropCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{

		DropCommand dropCommand = new DropCommand();
		String words[] = cad.split(" ");

			if (words[0].toUpperCase().equals("DROP") || words[0].toUpperCase().equals("SOLTAR")){
				if(words.length != 2)
					throw new WrongCommandFormatException();
				else{
					dropCommand.idItem = words[1];
				}
			}
			else throw new WrongCommandFormatException();
		
		return dropCommand;
		
	}
	
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	
	
}
