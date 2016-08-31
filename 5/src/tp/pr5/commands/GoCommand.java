package tp.pr5.commands;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class GoCommand implements Command{

	
	private Directions direction;
	private Game game;
	private Player player;
	private Map map;
	
	
	
	//Constructor for a go command
	public GoCommand(){
		
		this.direction = Directions.UNKNOWN;
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
	//Moves from the Games current Room to the Room on the given Direction, provided in the constructor. An opened Door must exist between both Doors to be moved
	public void execute() throws CommandExecutionException {
		
		boolean encontrado = false;
			
			for(int i = 0;(i < this.map.getDoors().size()) && (!encontrado); i++){
				if(this.map.getDoors().get(i).isInRoom(this.map.getCurrentRoom(), this.direction)){ //If there is a door in that direction in the current room
					encontrado = true;
							
					if(this.map.getDoors().get(i).isInRoom(this.map.getCurrentRoom())){ //If the door is in the current room
								
						if(this.map.getDoors().get(i).getSource() == this.map.getCurrentRoom()){
									
							if(map.getDoors().get(i).isOpen()){
										
								this.map.setCurrentRoom(this.map.getDoors().get(i).getTarget(), this.direction); //Changes the current room to the target room
								
								
								this.player.addHealth(Constants.LOST_LIVE);
								
								
								if(!this.map.getCurrentRoom().isExit()){
								}
								else{
									
									this.game.setOver(true);
								}
							}
							else{ //If the door is closed
								
								this.game.requestError(Constants.MESSAGE_DOOR(this.direction));
								throw new CommandExecutionException();
							}
						}
						else { //The door is bidirectional and the player is in the target room
									
							if(this.map.getDoors().get(i).isOpen()){
								this.map.setCurrentRoom(this.map.getDoors().get(i).getSource(), this.direction); //Changes the current room to the source room.
								
								this.player.addHealth(Constants.LOST_LIVE);
								
								if(!this.map.getCurrentRoom().isExit()){
									
								}else{
									this.game.setOver(true);
								}
							}
							else{ //If the door is closed
								
								this.game.requestError(Constants.MESSAGE_DOOR(this.direction));
								
								throw new CommandExecutionException();
							}
						}					
					}
					else{ //The door isn't in the current room
						
						this.game.requestError(Constants.MESSAGE_UNDIRECTIONAL_DOOR);
						
						throw new CommandExecutionException();
					}
				}
			}
			if(!encontrado){ //There is no door in that direction in the current room
				
				this.game.requestError(Constants.MESSAGE_WALL(this.direction));
				
				throw new CommandExecutionException();
			}
			if(this.player.dead()){
				
				this.game.gameOver(false);
				
				throw new CommandExecutionException();
			}
	}

	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {
		
		return "GO|IR { NORTH|EAST|SOUTH|WEST }";
	}

	
	//Parses the String returning a GoCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{
		
		GoCommand goCommand = new GoCommand();
		String words[] = cad.split(" ");

			
			if (words[0].toUpperCase().equals("GO") || words[0].toUpperCase().equals("IR")){
				if (words.length != 2)
					throw new WrongCommandFormatException();
				else if (words[1].toUpperCase().equals(Directions.NORTH.name()))
					goCommand.direction = Directions.NORTH;
				else if (words[1].toUpperCase().equals(Directions.SOUTH.name()))
					goCommand.direction = Directions.SOUTH;
				else if (words[1].toUpperCase().equals(Directions.EAST.name()))
					goCommand.direction = Directions.EAST;
				else if (words[1].toUpperCase().equals(Directions.WEST.name()))
					goCommand.direction = Directions.WEST;
				else throw new WrongCommandFormatException();
			}
			else throw new WrongCommandFormatException();
		
			
		return goCommand;
	}

	public Directions getDirection() {
		return this.direction;
	}
	
	public void setDirection(Directions var_direction) {
		this.direction = var_direction;
	}
	
	public Game getGame(){
		return this.game;
	}
	
	public void setGame(Game var_game){
		this.game = var_game;
	}

	
}
