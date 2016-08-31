package tp.pr4.commands;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tp.pr4.Constants;
import tp.pr4.Game;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.items.Item;

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
			
			if(!encontrado){
				if(this.game.getMainWindow() != null){
					//JOptionPane exception = new JOptionPane();
					JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_PICK_ERROR);
				}
				throw new CommandExecutionException(Constants.MESSAGE_PICK_ERROR + '\n');//???????????????????????????????????????????????????????
				
			}
			
			
			else if(!(this.game.getPlayer().addItem(item))){
				if(this.game.getMainWindow() != null){
					//JOptionPane exception = new JOptionPane();
					JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_ITEM_IS_IN_INVENTARY(this.idItem));
				}
				throw new CommandExecutionException(Constants.MESSAGE_ITEM_IS_IN_INVENTARY(this.idItem));
			}
				
			else{
				this.game.getCurrentMap().getCurrentRoom().getItems().remove(item);
				if(this.game.getMainWindow() != null)
					this.game.getMainWindow().getPlayerPanel().updateInventory(this.game.getPlayer().getInventory());
			}
			if(this.game.getMainWindow() != null){
				this.game.getMainWindow().getMapPanel().getText().setText(this.game.getCurrentMap().getCurrentRoom().getDescription());
			}
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
