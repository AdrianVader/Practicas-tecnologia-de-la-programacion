package tp.pr5.commands;


import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.items.Item;

public class UseCommand implements Command{

	
	private String idItem;
	private Game game;
	private Player player;
	private Map map;
	private Item item;
	
	
	
	//Constructor for a use command
	public UseCommand(){
		
		this.idItem = "";
		this.game = null;
		this.player = null;
		this.map = null;
		this.item = null;
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

		if(this.player.getInventory().size() > 0){
				
			for(int i = 0; i < this.player.getInventory().size(); i++){
					
				if(this.player.getInventory().get(i).getId().equalsIgnoreCase(this.idItem)){
					encontrado = true;						
					this.item = this.player.getInventory().get(i);
					
					if (!(this.player.getInventory().get(i).use(this.player, this.map.getCurrentRoom()))){
						
						this.game.requestError(Constants.MESSAGE_CHANGES_ERROR);
						
						throw new CommandExecutionException();
					}
					
					if(!this.player.getInventory().contains(this.item)){
						this.player.itemEmpty(this.item.getId());
					}
					this.player.requestInventoryUpdate();
					this.player.requestPlayerUpdate();
					this.player.itemUsed(this.item.getId());
				}				
			}
		}
		
		if (!encontrado){
			this.game.requestError(Constants.MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS(this.idItem));			
			throw new CommandExecutionException();
			
		}
		if(this.player.dead()){
			this.game.gameOver(false);
		}			
	}
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "USE|USAR <<id>>";
	}

	//Parses the String returning a UseCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{

		UseCommand useCommand = new UseCommand();
		String words[] = cad.split(" ");
		if (words[0].toUpperCase().equals("USE") || words[0].toUpperCase().equals("USAR")){
			if(words.length != 2)
				throw new WrongCommandFormatException();
			else{
				useCommand.idItem = words[1];
			}
		}
		else throw new WrongCommandFormatException();
		return useCommand;
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
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
