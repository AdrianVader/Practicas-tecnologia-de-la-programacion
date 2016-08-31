package tp.pr5;

import java.util.ArrayList;

import tp.pr5.gui.MapPanel;


public class Map extends tp.pr5.Observable<MapObserver>{	//tiene un array de mapObservers

	private ArrayList<Door> doors;
	private Room currentRoom;
	@SuppressWarnings("unused")
	private MapPanel mapPanel;

	
	
	//Default constructor of Map.
	public Map(Room initialRoom){
		super();
		this.currentRoom = initialRoom;
		this.doors = new ArrayList<Door>();
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
	
	//Requests the player wants to examine a room 
	public void playerExamineRoom(){
		
		for(int i = 0; i < this.observadores.size(); i++){			
			this.observadores.get(i).playerHasExaminedRoom(this.currentRoom);			
		}
		
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
	public void setCurrentRoom(Room current, Directions dir) {
		this.currentRoom = current;
		for(int j = 0; j < this.observadores.size();j++){
			this.observadores.get(j).roomEntered(dir, current);
		}

	}
	
	//Establishes the panel that will show the map on the Swing interface
	public void setMapPanel(MapPanel mapPanel){
		this.mapPanel = mapPanel;
	}
	
	//Set the ArrayList of MapObservers.
	public ArrayList<MapObserver> getObservadores(){
		return this.observadores;
	}
		
	//Get the ArrayList of MapObservers.
	public void setObservadores(ArrayList<MapObserver> obs){
		this.observadores = obs;
	}

	public void requestRoomInventoryUpdate() {
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).updateRoomInventory(this.currentRoom);
		}		
	}



	public void enterRoom(Room room, Directions dir) {
		this.currentRoom = room;
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).changeRoom(dir, room);
		}
	}



	public void forget() {
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).forgetRoom();
		}
	}
	
	
}
