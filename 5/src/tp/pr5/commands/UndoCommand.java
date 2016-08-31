package tp.pr5.commands;

import java.util.ArrayList;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class UndoCommand implements Command{


	private ArrayList<Command> gameStack;
	private ArrayList<Room> roomStack;
	private Game currentGame;
	private Player player;
	private Map map;
	
	
	
	public UndoCommand(){
		
		this.gameStack = new ArrayList<Command>();
		this.roomStack = new ArrayList<Room>();
		this.currentGame = null;
		this.player = null;
		this.map = null;
	}

	
	
	@Override
	public void configureContext(Game g, Map m, Player p) {
		
		this.currentGame = g;
		this.map = m;
		this.player = p;
		this.gameStack = g.getUndoCommand().getGameStack();
		this.roomStack = g.getUndoCommand().getRoomStack();
	}
	
	public void execute() throws CommandExecutionException{
		
		if(this.gameStack.size() <= 0){
			this.currentGame.requestError(Constants.MESSAGE_UNDO_ERROR);
		}
		else{
			// Según lo que hayas hecho deshazlo =) (reconociendo comandos)
			// Si el commando es examine, look, help o quit no hay que deshacer ningún cambio.
			// Debemos restablecer el estado anterior si es go, drop, pick o use.

			Command c = this.gameStack.get(this.gameStack.size()-1);

			if(tp.pr5.commands.PickCommand.class == c.getClass()){
				DropCommand drop = new DropCommand();
				drop.setIdItem(((tp.pr5.commands.PickCommand) c).getIdItem());
				drop.configureContext(this.currentGame, this.map, this.player);
				drop.execute();
			}else if(tp.pr5.commands.DropCommand.class == c.getClass()){
				PickCommand pick = new PickCommand();
				pick.setIdItem(((tp.pr5.commands.DropCommand) c).getIdItem());
				pick.configureContext(this.currentGame, this.map, this.player);
				pick.execute();
			}else if(tp.pr5.commands.GoCommand.class == c.getClass()){
				
				Room r = this.map.getCurrentRoom();

				this.player.addHealth(- Constants.LOST_LIVE); // Avisamos a los observadores más tarde para que por consola salga en el orden deseado.
				
				
				if(this.roomStack.contains(r)){ // Ya habías estado antes en la habitación de la que vienes.
					
					this.map.setCurrentRoom(this.roomStack.get(this.roomStack.size() - 1), Directions.UNKNOWN.opposite(((tp.pr5.commands.GoCommand) c).getDirection()));
					this.roomStack.remove(this.roomStack.size() - 1);
					
				}
				else{ // Es la primera vez que visitabas la habitación.
					this.map.forget();
					this.map.enterRoom(this.roomStack.get(this.roomStack.size() - 1), Directions.UNKNOWN.opposite(((tp.pr5.commands.GoCommand) c).getDirection()));
					this.roomStack.remove(this.roomStack.size() - 1);
					
				}
				
				this.player.requestPlayerUpdate();
				
			}else if(tp.pr5.commands.UseCommand.class == c.getClass()){
				
				if(((tp.pr5.commands.UseCommand) c).getItem().getClass() == tp.pr5.items.Food.class){
					this.player.setHealth(this.player.getHealth() - ((tp.pr5.items.Food) ((tp.pr5.commands.UseCommand) c).getItem()).getGivesHealth());
					
					if(this.player.getInventory().contains(((tp.pr5.commands.UseCommand) c).getItem())){ // Si tiene el objeto.
						for(int i = 0; i < this.player.getInventory().size(); i++){
							if(this.player.getInventory().get(i) == ((tp.pr5.commands.UseCommand) c).getItem())
								((tp.pr5.items.Food) this.player.getInventory().get(i)).setTimes(this.player.getInventory().get(i).getTimes() + 1);
						}
					}else{ // Si no tiene el objeto.
						this.player.getInventory().add(((tp.pr5.commands.UseCommand) c).getItem());
						for(int i = 0; i < this.player.getInventory().size(); i++){
							if(this.player.getInventory().get(i) == ((tp.pr5.commands.UseCommand) c).getItem())
								((tp.pr5.items.ExpirationItem) this.player.getInventory().get(i)).setTimes(1);
						}
					}
					
					this.player.requestInventoryUpdate();
					this.player.requestPlayerUpdate();
					
				}else if(((tp.pr5.commands.UseCommand) c).getItem().getClass() == tp.pr5.items.Valuable.class){	// Los valuables solo tienen un uso, por definición.
					this.player.setScore(this.player.getScore() - ((tp.pr5.items.Valuable) ((tp.pr5.commands.UseCommand) c).getItem()).getGivesScore());
					this.player.getInventory().add(((tp.pr5.commands.UseCommand) c).getItem());
					
					for(int i = 0; i < this.player.getInventory().size(); i++){
						if(this.player.getInventory().get(i) == ((tp.pr5.commands.UseCommand) c).getItem())
							((tp.pr5.items.ExpirationItem) this.player.getInventory().get(i)).setTimes(this.player.getInventory().get(i).getTimes()+1);
					}
					
					this.player.requestInventoryUpdate();
					this.player.requestPlayerUpdate();
					
				}else if(((tp.pr5.commands.UseCommand) c).getItem().getClass() == tp.pr5.items.Key.class){// Solo hay que volver a usar la llave, ya que así la cerramos.
						c.configureContext(this.currentGame, this.map, this.player);
						c.execute();
				}
				
			}
			this.gameStack.remove(this.gameStack.size()-1);
		}
	}
	
	
	public String getHelp(){
		
		return "UNDO|DESHACER";
	}
	
	@Override
	public Command parse(String cad) throws WrongCommandFormatException{
		
		UndoCommand undoCommand = new UndoCommand();
		String[] words = cad.split(" ");
		
		if(words[0].toUpperCase().equals("UNDO") || words[0].toUpperCase().equals("DESHACER")){
			if(words.length != 1)
				throw new WrongCommandFormatException();
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
	public void removeRoom() {
		this.roomStack.remove(this.roomStack.size() - 1);
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
	public ArrayList<Room> getRoomStack() {
		return roomStack;
	}
	public void setRoomStack(ArrayList<Room> roomStack) {
		this.roomStack = roomStack;
	}
	
	
}
