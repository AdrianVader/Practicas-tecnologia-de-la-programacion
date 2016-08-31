package tp.pr5.console;

import java.util.List;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.GameObserver;
import tp.pr5.MapObserver;
import tp.pr5.PlayerObserver;
import tp.pr5.RoomInfo;
import tp.pr5.items.Item;

public class Console implements GameObserver, PlayerObserver, MapObserver{

	
	
	public Console(){		
		//No hace nada ya que la consola informa a traves de system.out		
	}
	
	
	
	
	
	
	public void prompt(){
		System.out.print(Constants.PROMPT);
	}
	
	@Override
	public void roomEntered(Directions direction, RoomInfo targetRoom){
		
		System.out.println(Constants.MESSAGE_CHANGE_ROOM(direction));
		System.out.println(targetRoom.getDescription());
	}

	@Override
	public void playerHasExaminedRoom(RoomInfo r) {
		System.out.println(r.getDescription());
	}

	@Override
	public void inventoryUpdate(List<Item> inventory) {
		System.out.println(Constants.MESSAGE_CHANGES);
	}

	@Override
	public void itemEmpty(String itemName) {
		System.out.println(Constants.MESSAGE_EMPTY(itemName));
	}

	@Override
	public void itemLooked(String description) {
		System.out.println(description);
	}

	@Override
	public void itemUsed(String itemName) {
		System.out.println(Constants.MESSAGE_CHANGES);
	}

	@Override
	public void playerDead() {
		System.out.println(Constants.MESSAGE_DIE + '\n' + Constants.MESSAGE_FIN);
	}

	@Override
	public void playerLookedInventory(List<Item> inventory) {
		if(inventory.size() <= 0){
			System.out.println(Constants.MESSAGE_POOR);
		}else{
			System.out.println(Constants.MESSAGE_ITEMS);
			for(int i = 0;i < inventory.size(); i++)
				System.out.println(inventory.get(i).toString());
		}
	}

	@Override
	public void playerUpdate(int newHealth, int newScore) {
		System.out.println(Constants.MESSAGE_PLAYER_TOSTRING(newHealth, newScore));
	}

	@Override
	public void gameError(String msg) {
		System.out.println(msg);
	}

	@Override
	public void gameHelp() {
		System.out.println(Constants.HELP);
	}

	@Override
	public void gameOver(boolean win) {
		if(win){
			System.out.println(Constants.MESSAGE_WIN);
			System.out.println(Constants.MESSAGE_FIN);
		}else{
			System.out.println(Constants.MESSAGE_LOSE);
			System.out.println(Constants.MESSAGE_FIN);
		}
	}

	@Override
	public void gameQuit() {
		System.out.println(Constants.MESSAGE_FIN);
	}

	@Override
	public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth) {
		System.out.println(initialRoom.getDescription());
		System.out.println(Constants.MESSAGE_PLAYER_TOSTRING(playerHealth, playerPoints));
	}

	@Override
	public void updateRoomInventory(RoomInfo room) {
		
		//no hace nada
	}
	
	@Override
	public void forgetRoom() {}
	

	@Override
	public void changeRoom(Directions direction, RoomInfo targetRoom) {
		this.roomEntered(direction, targetRoom);
	}
	
}
