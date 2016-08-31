package tp.pr5;

import java.util.ArrayList;

import tp.pr5.commands.Command;
import tp.pr5.commands.UndoCommand;
import tp.pr5.commands.exceptions.CommandExecutionException;



//The controllers are responsible for asking if the game has finished.
public class Game extends tp.pr5.Observable<GameObserver>{	//tiene un array de gameObservers


	private Map map;
	private Player player;
	private boolean over;
	private UndoCommand undoCommand;
	


	//Default constructor. Creates a new game
	public Game(Map var_map){
		
		super();
		this.map = var_map;
		this.player = new Player();
		this.over = false;
		this.undoCommand = new UndoCommand();
	}

	
	
	
		
	//Registers a GameObserver to the model
	public void addGameObserver(GameObserver observer) {
		this.addObserver(observer);
	}
	
	 
	//Registers a MapObserver to the map contained in model
	public void addMapObserver(MapObserver observer){
		this.map.addObserver(observer);
	}
	
	
	//Registers a PlayerObserver to the player contained in model.
	public void addPlayerObserver(PlayerObserver observer){
		this.player.addObserver(observer);
	}
	
	
	//It executes a command and informs the observers whether the game has finished after executing the command.
	public void executeCommand(Command c) {
		c.configureContext(this, this.map, this.player);
		//try {
		if(c.getClass() == tp.pr5.commands.GoCommand.class){
			this.saveRoom();
		}
			try {
				c.execute();
				if(c.getClass() == tp.pr5.commands.GoCommand.class || c.getClass() == tp.pr5.commands.DropCommand.class || c.getClass() == tp.pr5.commands.PickCommand.class || c.getClass() == tp.pr5.commands.UseCommand.class){
					this.saveGame(c);
				}
			} catch (CommandExecutionException e) {
				if(c.getClass() == tp.pr5.commands.GoCommand.class){
					this.removeRoom();
				}
			}
		if(this.player.dead()){
			for(int i = 0; i < this.observadores.size();i++){
				this.observadores.get(i).gameOver(false);
			}
		}
		else if(this.map.getCurrentRoom().isExit()){
			for(int i = 0; i < this.observadores.size();i++){
				this.observadores.get(i).gameOver(true);
			}
			
		}
	}
	 
	
	//Removes a GameObserver attached to the model
	public void removeGameObserver(GameObserver observer){
		this.observadores.remove(observer);
	}
	
	 
	
	//Removes a MapObserver attached to the map contained in the model
	public void removeMapObserver(MapObserver observer){
		this.map.removeObserver(observer);
	}
	 
	
	//Removes a PlayerObserver attached to the player contained in the model
	public void removePlayerObserver(PlayerObserver observer){
		this.player.removeObserver(observer);
	}
	 
	
	//Requests the game to inform the observers that an error has ocurred
	public void  requestError(java.lang.String msg){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).gameError(msg);
		}
	}
	
	
	//Requests the game to inform the observers about the help information.
	public void requestHelp(){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).gameHelp();
		}
	} 
	
	//Requests the game to inform the observers that the game starts 
	public void requestStart(){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).gameStart(this.map.getCurrentRoom(), this.player.getPoints(), this.player.getHealth());
		}
	}	
	
	
	//Requests the game to quit
	public void requestQuit(){
		this.over = true;
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).gameQuit();
		}
		//The end.
	}
	
	public void gameOver(boolean win){
		this.over = true;
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).gameOver(false);
		}
	}
	
	//Saves the last command in the command stack in undoCommand
	public void saveGame(Command com){
		this.undoCommand.saveGame(com);
	}
	
	//Saves the last room in the room stack in undoCommand
	public void saveRoom(){
		this.undoCommand.saveRoom(this.map.getCurrentRoom());
	}

	//Removes the last room from the room stack in undoCommand
	public void removeRoom() {
		this.undoCommand.removeRoom();
	}
	//Checks if the player is dead
	public boolean deadPlayer(){
		if (this.player.dead())
			return true;
		else
			return false;
	}
	
	
	public void setOver(boolean quit) {
		if(quit){
			for(int j = 0; j < this.observadores.size();j++){
				this.observadores.get(j).gameOver(true);
			}
		}
		this.over = quit;
	}	
	public UndoCommand getUndoCommand() {
		return undoCommand;
	}
	public void setUndoCommand(UndoCommand undoCommand) {
		this.undoCommand = undoCommand;
	}
	
	//Checks if the game is finished
	public boolean isOver(){		
		if(this.over){
			for(int j = 0; j < this.observadores.size();j++){
				this.observadores.get(j).gameOver(true);
			}
		}
		return this.over;
	}
		
	//Get the ArrayList of GameObservers.
	public ArrayList<GameObserver> getObservadores(){
		return this.observadores;
	}
	
	//Set the ArrayList of GameObservers.
	public void setObservadores(ArrayList<GameObserver> obs){
		this.observadores = obs;
	}
	
	
}