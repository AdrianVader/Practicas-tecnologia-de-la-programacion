package tp.pr1;
import tp.pr1.Directions;

public class Room {

	private boolean exit;
	private java.lang.String description;
	private Room[] rooms;
	



	//Constructors.
	public Room(boolean var_exit, java.lang.String var_description){
		
		this.exit = var_exit;
		this.description = var_description;
		this.rooms = new Room[4]; // 0-> North, 1-> South, 2-> East, 3-> West
		for(int i = 0; i < 4;i++){
			this.rooms[i] = null;
		}
		
	}
	
	
	//Methods.
	
	//Is it an exit room?
	public boolean isExit() {
		return this.exit;
	}

	//Sets the value of exit
	public void setExit(boolean var_exit) {
		this.exit = var_exit;
	}

	//Returns the room description
	public java.lang.String getDescription() {
		return this.description;
	}
	
	//Sets the description
	public void setDescription(String var_description) {
		this.description = var_description;
	}
	
	//Returns the next room in a given direction
	public Room getNextRoom(Directions dir){
		
		Room target = null;
		
		switch(dir){
		
		case NORTH:
			target = this.rooms[0];
			break;
		
		case SOUTH:
			target = this.rooms[1];
			break;
		
		case EAST:
			target = this.rooms[2];
			break;
		
		case WEST:
			target = this.rooms[3];
			break;
			
			default:break;//If the direction is UNKNOWN
		}
	
		return target;
	}
	
	//Sets the adjacent room in a given direction
	public void setRoom(Directions dir, Room room){
	
		switch(dir){
		
		case NORTH:
			rooms[0] = room;
			break;
		
		case SOUTH:
			rooms[1] = room;
			break;
		
		case EAST:
			rooms[2] = room;
			break;
		
		case WEST:
			rooms[3] = room;
			break;
		}			
	}
	
	
	public Room[] getRooms() {
		return rooms;
	}


	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
	
}
