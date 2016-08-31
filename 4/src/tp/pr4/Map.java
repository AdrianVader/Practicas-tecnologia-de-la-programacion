package tp.pr4;

import java.util.ArrayList;

import tp.pr4.gui.MapPanel;


public class Map {

	private ArrayList<Door> doors;
	private Room currentRoom;
	private MapPanel mapPanel;

	
	
	
	//Default constructor of Map.
	public Map(Room initialRoom){
		
		this.currentRoom = initialRoom;
		this.doors = new ArrayList<Door>();
		//this.mapPanel = new MapPanel();
		//this.mapPanel.getCells()[this.mapPanel.getRow()][this.mapPanel.getCol()].setRoom(this.currentRoom);
	}
	
	
		
		
	
	//Adds a created door to the map
	public void addDoor(Door d){
		
		this.doors.add(d);
	}
		
		
	//Creates a new door between the rooms given as parameters, adds it to the map and returns it.
	public Door addDoor(Room var_source, Directions direction, Room var_target){
		
		Door door = new Door(var_source, direction, var_target, false);
		if(!this.doors.add(door))
			door = null;
		return door;
	}
	
	
	//Similar to addDoor method but creating a door that may be crossed in both directions.
	public Door addBidirectionalDoor(Room var_source, Directions direction, Room var_target){
		
		Door door = new Door(var_source, direction, var_target, true);
		if(!this.doors.add(door))
			door = null;
		return door;
	}
	
	
	//Returns the door that is in a Direction of a Room.
	public Door getDoor(Room room, Directions dir){
		Door door = null;
		
		for (int i = 0;i < this.doors.size(); i++){
			if (this.doors.get(i).isInRoom(room, dir))
				door = this.doors.get(i);
			
		}
		return door;
	}
	
	public ArrayList<Door> getDoors(){
		return doors;
	}

	public void setDoors(ArrayList<Door> doors){
		this.doors = doors;
	}
	
	//Returns a reference to the room where the player is currently located.
	public Room getCurrentRoom(){	
		return this.currentRoom;
	}
		
	//Sets the current room
	public void setCurrentRoom(Room current) {
		this.currentRoom = current;
	}
	
	//Establishes the panel that will show the map on the Swing interface
	public void setMapPanel(MapPanel mapPanel){
		this.mapPanel = mapPanel;
	}
	
}
