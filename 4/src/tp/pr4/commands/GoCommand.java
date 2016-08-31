package tp.pr4.commands;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tp.pr4.Constants;
import tp.pr4.Directions;
import tp.pr4.Game;
import tp.pr4.Player;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;

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
								//if(this.game.getMainWindow() != null)
									//this.game.getMainWindow().getUndoCommand().saveRoom(this.game.getCurrentMap().getCurrentRoom());
								this.game.getCurrentMap().setCurrentRoom(this.game.getCurrentMap().getDoors().get(i).getTarget()); //Changes the current room to the target room
								this.game.getPlayer().addHealth(Constants.LOST_LIVE);
								System.out.println(Constants.MESSAGE_CHANGE_ROOM(direction));
								
								if(this.game.getMainWindow() != null){
									
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setBackground(Color.GRAY);
									
									if(this.direction.name().equals("NORTH")){
										this.game.getMainWindow().getMapPanel().setRow(this.game.getMainWindow().getMapPanel().getRow()-1);										
									}else if(this.direction.name().equals("SOUTH")){
										this.game.getMainWindow().getMapPanel().setRow(this.game.getMainWindow().getMapPanel().getRow()+1);
									}else if(this.direction.name().equals("EAST")){
										this.game.getMainWindow().getMapPanel().setCol(this.game.getMainWindow().getMapPanel().getCol()+1);
									}else if(this.direction.name().equals("WEST")){
										this.game.getMainWindow().getMapPanel().setCol(this.game.getMainWindow().getMapPanel().getCol()-1);
									}
									
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setBackground(Color.GREEN);
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setRoom(this.game.getCurrentMap().getCurrentRoom());
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setText(this.game.getCurrentMap().getCurrentRoom().getName());
									this.game.getMainWindow().getPlayerPanel().updatePlayerHealth(this.game.getPlayer().getHealth());
									this.game.getMainWindow().getMapPanel().getText().setText(this.game.getCurrentMap().getCurrentRoom().getDescription());
								}
								
								if(!this.game.getCurrentMap().getCurrentRoom().isExit()){
									this.game.showCurrentRoom();
									System.out.println(Constants.MESSAGE_PLAYER_TOSTRING(this.game.getPlayer()));
								}
								else
									this.game.requestQuit();
							}
							else{ //If the door is closed
								if(this.game.getMainWindow() != null){
									//JOptionPane exception = new JOptionPane();
									JOptionPane.showMessageDialog(new JButton("Aceptar"), "There is a door in the " + this.direction + ", but it is closed.");
								}
								throw new CommandExecutionException("There is a door in the " + this.direction + ", but it is closed.");
							}
						}
						else { //The door is bidirectional and the player is in the target room
									
							if(this.game.getCurrentMap().getDoors().get(i).isOpen()){
								
								//this.game.getMainWindow().getUndoCommand().saveRoom(this.game.getCurrentMap().getCurrentRoom());
								this.game.getCurrentMap().setCurrentRoom(this.game.getCurrentMap().getDoors().get(i).getSource()); //Changes the current room to the source room
								this.game.getPlayer().addHealth(Constants.LOST_LIVE);
								System.out.println(Constants.MESSAGE_CHANGE_ROOM(this.direction));
								
								if(this.game.getMainWindow() != null){
									
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setBackground(Color.GRAY);
									
									if(this.direction.name().equals("NORTH")){
										this.game.getMainWindow().getMapPanel().setRow(this.game.getMainWindow().getMapPanel().getRow()-1);										
									}else if(this.direction.name().equals("SOUTH")){
										this.game.getMainWindow().getMapPanel().setRow(this.game.getMainWindow().getMapPanel().getRow()+1);
									}else if(this.direction.name().equals("EAST")){
										this.game.getMainWindow().getMapPanel().setCol(this.game.getMainWindow().getMapPanel().getCol()+1);
									}else if(this.direction.name().equals("WEST")){
										this.game.getMainWindow().getMapPanel().setCol(this.game.getMainWindow().getMapPanel().getCol()-1);
									}
									
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setBackground(Color.GREEN);
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setRoom(this.game.getCurrentMap().getCurrentRoom());
									this.game.getMainWindow().getMapPanel().getCells()[this.game.getMainWindow().getMapPanel().getRow()][this.game.getMainWindow().getMapPanel().getCol()].setText(this.game.getCurrentMap().getCurrentRoom().getName());
									this.game.getMainWindow().getPlayerPanel().updatePlayerHealth(this.game.getPlayer().getHealth());
									this.game.getMainWindow().getMapPanel().getText().setText(this.game.getCurrentMap().getCurrentRoom().getDescription());
								}
								
								if(!this.game.getCurrentMap().getCurrentRoom().isExit()){
									System.out.println(this.game.getCurrentMap().getCurrentRoom().getDescription());
									System.out.println(this.game.getPlayer().toString());
								}else
									this.game.requestQuit();
							}
							else{ //If the door is closed
								if(this.game.getMainWindow() != null){
									//JOptionPane exception = new JOptionPane();
									JOptionPane.showMessageDialog(new JButton("Aceptar"), "There is a door in the " + this.direction + ", but it is closed.");
								}
								throw new CommandExecutionException("There is a door in the " + this.direction + ", but it is closed.");
							}
						}					
					}
					else{ //The door isn't in the current room
						if(this.game.getMainWindow() != null){
							//JOptionPane exception = new JOptionPane();
							JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_DOOR);
						}
						throw new CommandExecutionException(Constants.MESSAGE_DOOR);
					}
				}
			}
			if(!encontrado){ //There is no door in that direction in the current room
				if(this.game.getMainWindow() != null){
					//JOptionPane exception = new JOptionPane();
					JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_WALL(direction));
				}
				throw new CommandExecutionException(Constants.MESSAGE_WALL(direction));
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
