package tp.pr5.gui;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import tp.pr5.Directions;
import tp.pr5.GameObserver;
import tp.pr5.MapObserver;
import tp.pr5.PlayerObserver;
import tp.pr5.RoomInfo;
import tp.pr5.items.Item;

public class InfoPanel extends javax.swing.JPanel implements GameObserver, MapObserver, PlayerObserver{

	

	private static final long serialVersionUID = 1L;
	
	
	
	private JLabel label;
	
	
	
	public InfoPanel(){
		this.setBorder(BorderFactory.createTitledBorder("Info"));
		this.label = new JLabel();
		this.add(this.label);
	}

	@Override
	//Notifies that the player inventory has changed
	public void inventoryUpdate(List<Item> inventory) { 
		if(inventory.size() == 1)
			this.label.setText("Your inventory now contains " + inventory.size() + " item.");
		else if(inventory.size() == 0)
			this.label.setText("Your inventory is now empty");
		else 
			this.label.setText("Your inventory now contains " + inventory.size() + " items.");
	}

	@Override
	//Notifies that an item is empty
	public void itemEmpty(String itemName) {
		this.label.setText("Your " + itemName + " is empty.");
	}

	@Override
	//Notifies that the player wants to examine an item
	public void itemLooked(String description) {
		this.label.setText("Looking at an item.");
	}

	@Override
	//Notifies that an item has been used
	public void itemUsed(String itemName) {
		this.label.setText("You have used " + itemName + ".");
	}

	@Override
	//Notifies the player's death
	public void playerDead() {
		this.label.setText("You are dead...");
	}

	@Override
	//Notifies that the player looked the inventory
	public void playerLookedInventory(List<Item> inventory) {
		this.label.setText("Looking at the inventory.");
	}

	@Override
	//Notifies that the player attributes (health and score) have changed 
	public void playerUpdate(int newHealth, int newScore) {
		this.label.setText("Your attributes have been updated: (" + newHealth + ", " + newScore + ")");
	}

	@Override
	//Notifies that the player examined a room
	public void playerHasExaminedRoom(RoomInfo r) {
		this.label.setText("You have examined the " + r.getName() + ".");
	}

	@Override
	//Notifies that the player entered a room coming from a given direction 
	public void roomEntered(Directions direction, RoomInfo targetRoom) {
		this.label.setText("You have entererd the " + targetRoom.getName() + " from the " + direction.opposite(direction).toString() + ".");
	}

	@Override
	//Notifies that the game cannot execute a command
	public void gameError(String msg) {
		this.label.setText(msg);
	}

	@Override
	//Notifies that the player requests help information
	public void gameHelp() {
		this.label.setText("Help requested.");
	}

	@Override
	//Notifies that the game is finished and whether the player wins or is death
	public void gameOver(boolean win) {
		if(win)
			this.label.setText("GAME OVER." + '\n' + "You win.");
		else
			this.label.setText("GAME OVER." + '\n' + "You lose.");
	}

	@Override
	//Notifies that the player requests to quit the game.
	public void gameQuit() {
		this.label.setText("Quit.");
	}

	@Override
	//Notifies that the game starts.
	public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth) {
		this.label.setText("Game starts.");
	}

	@Override
	public void updateRoomInventory(RoomInfo room) {
		
	}

	@Override
	public void forgetRoom() {}

	@Override
	public void changeRoom(Directions direction, RoomInfo targetRoom) {}

	
}
