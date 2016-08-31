package tp.pr5.gui;

import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.UndoCommand;
import tp.pr5.commands.UseCommand;

public class GameControllerGUI extends GameController{
	
	
	//Constructor that uses the model
	public GameControllerGUI(Game game){
		super(game);
	}
	
	
	//Executes a DROP command
	public void	executeDropAction(java.lang.String item){
		DropCommand drop = new DropCommand();
		drop.setIdItem(item);
		this.game.executeCommand(drop);
	}
	
	 
	
	//Executes a PICK command
	public void	executePickAction(java.lang.String item){
		
		PickCommand pick = new PickCommand();
		pick.setIdItem(item);
		this.game.executeCommand(pick);
	}
	
	 
	public void executeUndoAction(){
		
		UndoCommand undo = new UndoCommand();
		this.game.executeCommand(undo);
		
	}
	
	
	//Executes a QUIT command
	public void executeQuitAction(){
		
		QuitCommand quit = new QuitCommand();
		this.game.executeCommand(quit);
	}
	
	 
	
	//Executes an USE command
	public void executeUseAction(java.lang.String itemName){
		
		UseCommand use = new UseCommand();
		use.setIdItem(itemName);
		this.game.executeCommand(use);
	}
	
	//Executes an GO command
	public void executeGoAction(Directions direction){
		
		GoCommand go = new GoCommand();
		go.setDirection(direction);
		this.game.executeCommand(go);
		
	}
	
	 
	
	//This method only requests the game to start
	public void	runGame(){
		this.game.requestStart();
	}

		
}
