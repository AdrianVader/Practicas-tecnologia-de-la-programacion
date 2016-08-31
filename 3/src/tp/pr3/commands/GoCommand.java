package tp.pr3.commands;

import tp.pr3.Constants;
import tp.pr3.Directions;
import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;

public class GoCommand implements Command{

	
	private Directions direction;
	private Game game;
	
	
	
	//Constructor for a go command
	public GoCommand(){
		
		this.direction = Directions.UNKNOWN;
		this.game = null;
	}
	
	
	
	
	@Override
	//Moves from the Games current Room to the Room on the given Direction, provided in the constructor. An opened Door must exist between both Doors to be moved
	public void execute() throws CommandExecutionException {

				
		boolean encontrado = false;
			
			for(int i = 0;i < this.game.getCurrentMap().getDoors().size(); i++){
				if(this.game.getCurrentMap().getDoors().get(i).isInRoom(this.game.getCurrentMap().getCurrentRoom(), this.direction)){ //If there is a door in that direction in the current room
					encontrado = true;
							
					if(this.game.getCurrentMap().getDoors().get(i).isInRoom(this.game.getCurrentMap().getCurrentRoom())){ //If the door is in the current room
								
						if(this.game.getCurrentMap().getDoors().get(i).getSource() == this.game.getCurrentMap().getCurrentRoom()){
									
							if(game.getCurrentMap().getDoors().get(i).isOpen()){
									
								this.game.getCurrentMap().setCurrentRoom(this.game.getCurrentMap().getDoors().get(i).getTarget()); //Changes the current room to the target room
								this.game.getPlayer().addHealth(Constants.LOST_LIVE);
								System.out.println(Constants.MESSAGE_CHANGE_ROOM + direction.name());
								if(!this.game.getCurrentMap().getCurrentRoom().isExit())
									System.out.println(this.game.getCurrentMap().getCurrentRoom().getDescription());
								if(!this.game.getCurrentMap().getCurrentRoom().isExit())
									System.out.println("HEALTH = " + this.game.getPlayer().getHealth() + ", SCORE =" + this.game.getPlayer().getScore());
								else
									this.game.requestQuit();
							}
							else //If the door is closed
								throw new CommandExecutionException("There is a door in the " + this.direction + ", but it is closed.");
								
						}
						else { //The door is bidirectional and the player is in the target room
									
							if(this.game.getCurrentMap().getDoors().get(i).isOpen()){
										
								this.game.getCurrentMap().setCurrentRoom(this.game.getCurrentMap().getDoors().get(i).getSource()); //Changes the current room to the source room
								this.game.getPlayer().addHealth(Constants.LOST_LIVE);
								System.out.println(Constants.MESSAGE_CHANGE_ROOM + this.direction.name());
								System.out.println(this.game.getCurrentMap().getCurrentRoom().getDescription());
								if(!this.game.getCurrentMap().getCurrentRoom().isExit())
									System.out.println("HEALTH = " + this.game.getPlayer().getHealth() + ", SCORE =" + this.game.getPlayer().getScore());
										
							}
							else //If the door is closed
								throw new CommandExecutionException("There is a door in the " + this.direction + ", but it is closed.");
									
						}					
					}
					else //The door isn't in the current room
						throw new CommandExecutionException(Constants.MESSAGE_DOOR);
				}
			}
			if(!encontrado) //There is no door in that direction in the current room
				throw new CommandExecutionException(Constants.MESSAGE_WALL + direction.name() + " ?");
		
	}

	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {
		
		return "GO|IR { NORTH|EAST|SOUTH|WEST }";
	}

	
	//Parses the String returning a GoCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{
		
		GoCommand goCommand = new GoCommand();
		String words[] = cad.split(" ");

			//if (words.length != 2)
				//throw new WrongCommandFormatException("What?");
			if (words[0].toUpperCase().equals("GO") || words[0].toUpperCase().equals("IR")){
				goCommand.game = execContext;
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
