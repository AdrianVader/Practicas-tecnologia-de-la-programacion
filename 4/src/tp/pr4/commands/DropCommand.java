package tp.pr4.commands;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tp.pr4.Game;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;



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
							if(this.game.getMainWindow() != null){
								//JOptionPane exception = new JOptionPane();
								JOptionPane.showMessageDialog(new JButton("Aceptar"), "Ya hay uno igual en la habitación!");
							}
							throw new CommandExecutionException("Ya hay uno igual en la habitación!");//---no sé cual es el mensaje correcto---
						}
						else{								
							this.game.getPlayer().removeItem(this.idItem);//remove the item from the player's inventory.
							if(this.game.getMainWindow() != null)
								this.game.getMainWindow().getPlayerPanel().updateInventory(this.game.getPlayer().getInventory());
						}
					}
				}
			}
				
			
			if (!encontrado){
				if(this.game.getMainWindow() != null){
					//JOptionPane exception = new JOptionPane();
					JOptionPane.showMessageDialog(new JButton("Aceptar"), "Ya hay uno igual en la habitación!");
				}
				throw new CommandExecutionException("You do not have any " + this.idItem + ".");
			}
			if(this.game.getMainWindow() != null){
				this.game.getMainWindow().getMapPanel().getText().setText(this.game.getCurrentMap().getCurrentRoom().getDescription());
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
