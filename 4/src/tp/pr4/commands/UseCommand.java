package tp.pr4.commands;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tp.pr4.Constants;
import tp.pr4.Game;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.items.Item;

public class UseCommand implements Command{

	
	private String idItem;
	private Game game;
	private Item item;
	
	
	//Constructor for a use command
	public UseCommand(){
		
		this.idItem = "";
		this.game = null;
		this.item = null;
	}
	
	
	
	@Override
	public void execute() throws CommandExecutionException {

		boolean encontrado = false;

		if(this.game.getPlayer().getInventory().size() > 0){
				
			for(int i = 0; i < this.game.getPlayer().getInventory().size(); i++){
					
				if(this.game.getPlayer().getInventory().get(i).getId().equalsIgnoreCase(this.idItem)){
					encontrado = true;						
					this.item = this.game.getPlayer().getInventory().get(i);
					
					if (!(this.game.getPlayer().getInventory().get(i).use(this.game.getPlayer(), this.game.getCurrentMap().getCurrentRoom())))
						if(this.game.getMainWindow() != null){
							//JOptionPane exception = new JOptionPane();
							JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_CHANGES_ERROR);
						}
						else{
							this.item = null;
							throw new CommandExecutionException(Constants.MESSAGE_CHANGES_ERROR);
						}
					if(this.game.getMainWindow() != null){
						this.game.getMainWindow().getPlayerPanel().updateInventory(this.game.getPlayer().getInventory());
						this.game.getMainWindow().getPlayerPanel().updatePlayerHealth(this.game.getPlayer().getHealth());
						this.game.getMainWindow().getPlayerPanel().updatePlayerScore(this.game.getPlayer().getScore());
					}
				}				
			}
		}
			
		if (!encontrado){
			if(this.game.getMainWindow() != null){
				//JOptionPane exception = new JOptionPane();
				JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS(this.idItem));
			}else{
				throw new CommandExecutionException(Constants.MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS(this.idItem));
			}
		}
		if(this.game.getPlayer().dead()){
			if(this.game.getMainWindow() != null){
				//JOptionPane dead = new JOptionPane();
				JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_DIE);
			}
			this.game.requestQuit();
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
