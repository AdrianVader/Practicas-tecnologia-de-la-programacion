package tp.pr5;

public interface RoomInfo {

	
	//Returns the room description and a list with the ids of the items and their description that belongs to this room.
	public java.lang.String	getDescription();
	
	//Returns the room name
	public java.lang.String	getName();
	
	//Is it an exit room? 
	public boolean isExit();
		
	
}
