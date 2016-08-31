package tp.pr5.commands;


import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.items.Item;

public class PickCommand implements Command{
	
	private String idItem;
	private Game game;
	private Player player;
	private Map map;
	
	
	
	//Constructor for a pick command
	public PickCommand(){
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

		boolean encontrado = false;
		
		Item item = null;
		for(int i = 0; i < this.map.getCurrentRoom().getItems().size(); i++){
			if(this.map.getCurrentRoom().getItems().get(i).getId().equalsIgnoreCase(this.idItem)){
				item = this.map.getCurrentRoom().getItems().get(i);
				encontrado = true;
			}
		}
		
		if(!encontrado){
			this.game.requestError(Constants.MESSAGE_PICK_ERROR);
			throw new CommandExecutionException();
			
		}
		else if(!(this.player.addItem(item))){
			this.game.requestError(Constants.MESSAGE_ITEM_IS_IN_INVENTORY(this.idItem));
			throw new CommandExecutionException();
			
		}
		else{
			this.map.getCurrentRoom().getItems().remove(item);
			this.player.requestInventoryUpdate();
			this.map.requestRoomInventoryUpdate();
		}
	}

	
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "PICK|COGER <<id>>";
	}

	
	//Parses the String returning a PickCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{

		PickCommand pickCommand = new PickCommand();
		String words[] = cad.split(" ");

			if (words[0].toUpperCase().equals("PICK") || words[0].toUpperCase().equals("COGER")){
				if(words.length != 2)
					throw new WrongCommandFormatException();
				else
				pickCommand.idItem = words[1];
			}
			else throw new WrongCommandFormatException();
		
		return pickCommand;
	}
	

	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
}
