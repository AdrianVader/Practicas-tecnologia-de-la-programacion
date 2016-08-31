package tp.pr5;

public class Door {

	private Room source;
	private Directions doorDir;
	private Room target;
	private boolean bidirectional;
	private boolean isOpen;
	
	
	
	//Constructor for a closed bidirectional door
	public Door(Room source, Directions direction, Room target, boolean bidirectional){
		this.source = source;
		this.doorDir = direction;
		this.target = target;
		this.bidirectional = bidirectional;
		this.isOpen = false;
		
	}
	
	
	//Constructor for a bidirectional door
	public Door(Room source, Directions direction, Room target, boolean bidirectional, boolean isOpen){
		this.source = source;
		this.doorDir = direction;
		this.target = target;
		this.bidirectional = bidirectional;
		this.isOpen = isOpen;	
		
	}
	
	//Returns true if the door belongs to the input Room room . Let us note that if the door is bidirectional then it checks that room == source || room == target.
	public boolean isInRoom(Room room){
		return (this.source == room) || ((this.bidirectional) && (this.target == room));
	}
	
	//Returns true if the door belongs to the input Room room, in the provided direction.
	public boolean isInRoom(Room room, Directions where){		
		return (((this.source == room) && (this.doorDir == where)) || ((this.target == room) && (this.doorDir.opposite(this.doorDir) == where)));
	}
	
	//This function opens a door.
	public void open(){
		this.isOpen = true;	
	}
	
	//This function closes a door.
	public void close(){
		this.isOpen = false;
	}
	
	//Returns the room on the other side of whereAmI if it is possible (even if the door is closed).
	public Room nextRoom(Room whereAmI){
	
		if(this.source == whereAmI)
			return this.target;
		else if((this.target == whereAmI) && (this.bidirectional))
			return this.source;
		else
			return null;
				
	}
	
	//Returns true if this door links roomA and roomB and we can go from roomA to roomB. Let us note that the door might be closed.
	public boolean connect(Room roomA, Room roomB){
		boolean conected = false;
		
		if((this.source == roomA) && (this.target == roomB) && (this.isOpen))
			conected = true;
		else if((this.bidirectional) && (this.source == roomB) && (this.target == roomA) && (this.isOpen))
			conected = true;
		
		return conected;
	}
	
	//true if the door is open.
	public boolean isOpen(){
		return this.isOpen;
	}	
	
	public Room getSource() {
		return source;
	}
	
	public void setSource(Room source) {
		this.source = source;
	}

	public Directions getDoorDir() {
		return doorDir;
	}

	public void setDoorDir(Directions doorDir) {
		this.doorDir = doorDir;
	}

	public Room getTarget() {
		return target;
	}

	public void setTarget(Room target) {
		this.target = target;
	}

	public boolean isBidirectional() {
		return bidirectional;
	}

	public void setBidirectional(boolean bidirectional) {
		this.bidirectional = bidirectional;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}
