package tp.pr4.commands;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tp.pr4.Constants;
import tp.pr4.Directions;
import tp.pr4.Game;
import tp.pr4.Room;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;

public class UndoCommand implements Command{


	private ArrayList<Command> gameStack;
	private ArrayList<Room> roomStack;
	private Game currentGame;
	
	
	public UndoCommand(){
		
		this.gameStack = new ArrayList<Command>();
		this.roomStack = new ArrayList<Room>();
		this.currentGame = null;
	}
	
	
	public void execute() throws CommandExecutionException{
		
		if(this.gameStack.size() <= 0){
			if(this.currentGame.getMainWindow().getFrame() != null){
				//JOptionPane exception = new JOptionPane();
				JOptionPane.showMessageDialog(new JButton("Aceptar"), "No hay movimientos anteriores.");
			}
			else{
				throw new CommandExecutionException("No hay movimientos anteriores.");
			}
		}else{// Según lo que hayas hecho deshazlo =) (reconociendo comandos)
			// Si el commando es examine, look, help o quit no hay que deshacer ningún cambio.
			// Debemos restablecer el estado anterior si es go, drop, pick o use.

			Command c = this.gameStack.get(this.gameStack.size()-1);

			if(tp.pr4.commands.PickCommand.class == c.getClass()){
				DropCommand drop = new DropCommand();
				drop.setIdItem(((tp.pr4.commands.PickCommand) c).getIdItem());
				drop.setGame(((tp.pr4.commands.PickCommand) c).getGame());
				drop.execute();
			}else if(tp.pr4.commands.DropCommand.class == c.getClass()){
				PickCommand pick = new PickCommand();
				pick.setIdItem(((tp.pr4.commands.DropCommand) c).getIdItem());
				pick.setGame(((tp.pr4.commands.DropCommand) c).getGame());
				pick.execute();
			}else if(tp.pr4.commands.GoCommand.class == c.getClass()){

				Room r = this.currentGame.getMap().getCurrentRoom();

				this.currentGame.getMap().setCurrentRoom(this.roomStack.get(this.roomStack.size() - 1));
				this.currentGame.getPlayer().setHealth(this.currentGame.getPlayer().getHealth() - Constants.LOST_LIVE);
				this.roomStack.remove(this.roomStack.size() - 1);
				if(this.currentGame.getMainWindow() != null){
					if(this.roomStack.contains(r)){
						this.currentGame.getMainWindow().getMapPanel().getCells()[this.currentGame.getMainWindow().getMapPanel().getRow()][this.currentGame.getMainWindow().getMapPanel().getCol()].setBackground(Color.GRAY);
						if(((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.NORTH){
							this.currentGame.getMainWindow().getMapPanel().setRow(this.currentGame.getMainWindow().getMapPanel().getRow()-1);
						}
						else if(((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.SOUTH){
							this.currentGame.getMainWindow().getMapPanel().setRow(this.currentGame.getMainWindow().getMapPanel().getRow()+1);
						}
						else if(((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.EAST){
							this.currentGame.getMainWindow().getMapPanel().setCol(this.currentGame.getMainWindow().getMapPanel().getCol()+1);
						}
						else if (((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.WEST){
							this.currentGame.getMainWindow().getMapPanel().setCol(this.currentGame.getMainWindow().getMapPanel().getCol()-1);
						}
						this.currentGame.getMainWindow().getMapPanel().getCells()[this.currentGame.getMainWindow().getMapPanel().getRow()][this.currentGame.getMainWindow().getMapPanel().getCol()].setBackground(Color.GREEN);
						this.currentGame.getMainWindow().getMapPanel().getText().setText(this.currentGame.getMap().getCurrentRoom().getDescription());
						this.currentGame.getMainWindow().getPlayerPanel().updatePlayerHealth(this.currentGame.getPlayer().getHealth());
					}
					else{
						this.currentGame.getMainWindow().getMapPanel().getCells()[this.currentGame.getMainWindow().getMapPanel().getRow()][this.currentGame.getMainWindow().getMapPanel().getCol()].setBackground(new javax.swing.plaf.ColorUIResource(238, 238, 238));
						this.currentGame.getMainWindow().getMapPanel().getCells()[this.currentGame.getMainWindow().getMapPanel().getRow()][this.currentGame.getMainWindow().getMapPanel().getCol()].setText("");
						this.currentGame.getMainWindow().getMapPanel().getCells()[this.currentGame.getMainWindow().getMapPanel().getRow()][this.currentGame.getMainWindow().getMapPanel().getCol()].setRoom(null);
						if(((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.NORTH){
							this.currentGame.getMainWindow().getMapPanel().setRow(this.currentGame.getMainWindow().getMapPanel().getRow()-1);
						}
						else if(((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.SOUTH){
							this.currentGame.getMainWindow().getMapPanel().setRow(this.currentGame.getMainWindow().getMapPanel().getRow()+1);
						}
						else if(((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.EAST){
							this.currentGame.getMainWindow().getMapPanel().setCol(this.currentGame.getMainWindow().getMapPanel().getCol()+1);
						}
						else if (((GoCommand) c).getDirection().opposite(((GoCommand) c).getDirection()) == Directions.WEST){
							this.currentGame.getMainWindow().getMapPanel().setCol(this.currentGame.getMainWindow().getMapPanel().getCol()-1);
						}
						this.currentGame.getMainWindow().getMapPanel().getCells()[this.currentGame.getMainWindow().getMapPanel().getRow()][this.currentGame.getMainWindow().getMapPanel().getCol()].setBackground(Color.GREEN);
						this.currentGame.getMainWindow().getMapPanel().getText().setText(this.currentGame.getMap().getCurrentRoom().getDescription());
						this.currentGame.getMainWindow().getPlayerPanel().updatePlayerHealth(this.currentGame.getPlayer().getHealth());
					}
				}
				
			}else if(tp.pr4.commands.UseCommand.class == c.getClass()){
				
				if(((tp.pr4.commands.UseCommand) c).getItem().getClass() == tp.pr4.items.Food.class){
					this.currentGame.getPlayer().setHealth(this.currentGame.getPlayer().getHealth() - ((tp.pr4.items.Food) ((tp.pr4.commands.UseCommand) c).getItem()).getGivesHealth());
					
					if(this.currentGame.getPlayer().getInventory().contains(((tp.pr4.commands.UseCommand) c).getItem())){
						for(int i = 0; i < this.currentGame.getPlayer().getInventory().size(); i++){
							if(this.currentGame.getPlayer().getInventory().get(i) == ((tp.pr4.commands.UseCommand) c).getItem())
								((tp.pr4.items.Food) this.currentGame.getPlayer().getInventory().get(i)).setTimes(this.currentGame.getPlayer().getInventory().get(i).getTimes() + 1);
						}
					}else{
						this.currentGame.getPlayer().getInventory().add(((tp.pr4.commands.UseCommand) c).getItem());
						for(int i = 0; i < this.currentGame.getPlayer().getInventory().size(); i++){
							if(this.currentGame.getPlayer().getInventory().get(i) == ((tp.pr4.commands.UseCommand) c).getItem())
								((tp.pr4.items.ExpirationItem) this.currentGame.getPlayer().getInventory().get(i)).setTimes(1);
						}
					}
					if(this.currentGame.getMainWindow() != null){
						this.currentGame.getMainWindow().getPlayerPanel().updateInventory(this.currentGame.getPlayer().getInventory());
						this.currentGame.getMainWindow().getPlayerPanel().updatePlayerHealth(this.currentGame.getPlayer().getHealth());
					}
					
				}else if(((tp.pr4.commands.UseCommand) c).getItem().getClass() == tp.pr4.items.Valuable.class){// Los valuables solo tienen un uso, por definición...
					this.currentGame.getPlayer().setScore(this.currentGame.getPlayer().getScore() - ((tp.pr4.items.Valuable) ((tp.pr4.commands.UseCommand) c).getItem()).getGivesScore());
					this.currentGame.getPlayer().getInventory().add(((tp.pr4.commands.UseCommand) c).getItem());
					
					for(int i = 0; i < this.currentGame.getPlayer().getInventory().size(); i++){
						if(this.currentGame.getPlayer().getInventory().get(i) == ((tp.pr4.commands.UseCommand) c).getItem())
							((tp.pr4.items.ExpirationItem) this.currentGame.getPlayer().getInventory().get(i)).setTimes(this.currentGame.getPlayer().getInventory().get(i).getTimes()+1);
					}
					
					if(this.currentGame.getMainWindow() != null){
						this.currentGame.getMainWindow().getPlayerPanel().updateInventory(this.currentGame.getPlayer().getInventory());
						this.currentGame.getMainWindow().getPlayerPanel().updatePlayerScore(this.currentGame.getPlayer().getScore());
					}
					
				}else if(((tp.pr4.commands.UseCommand) c).getItem().getClass() == tp.pr4.items.Key.class){// Solo hay que volver a usar la llave, ya que así la cerramos.
					//si estamos en una habitación donde no podemos usar la llave va a saltar el mensaje de I did not go to tp...
					if(((tp.pr4.items.Key)((tp.pr4.commands.UseCommand) c).getItem()).getOpensDoor().isInRoom(this.currentGame.getMap().getCurrentRoom()))
						c.execute();
				}
				
			}
		
		}
		this.gameStack.remove(this.gameStack.size()-1);
	}
	
	
	public String getHelp(){
		
		return "UNDO|DESHACER";
	}
	
	
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{
		
		UndoCommand undoCommand = new UndoCommand();
		String[] words = cad.split(" ");
		
		if(words[0].toUpperCase().equals("UNDO") || words[0].toUpperCase().equals("DESHACER")){
			if(words.length != 1)
				throw new WrongCommandFormatException();
			else
				this.currentGame = execContext;
		}
		else throw new WrongCommandFormatException();
		
		return undoCommand;
	}
	
	
	public void saveGame(Command command){		
		this.gameStack.add(command);
	}
	public Command loadGame(){
		Command top = this.gameStack.get(this.gameStack.size()-1);
		this.gameStack.remove(this.gameStack.size()-1);
		return top;
		
	}
	public ArrayList<Command> getGameStack() {
		return this.gameStack;
	}
	public void setGameStack(ArrayList<Command> gameStack) {
		this.gameStack = gameStack;
	}
	public void saveRoom(Room room){
		this.roomStack.add(room);
	}
	public Game getCurrentGame(){
		return this.currentGame;
	}
	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}
	
	
}
