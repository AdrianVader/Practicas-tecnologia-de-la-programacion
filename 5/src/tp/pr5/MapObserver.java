package tp.pr5;


public interface MapObserver {

	//Notifies that the player examined a room
	public void playerHasExaminedRoom(RoomInfo r);
	
	//Notifies that the player entered a room coming from a given direction 
	public void roomEntered(Directions direction, RoomInfo targetRoom);
	
	//Notifies that the room inventory has changed
	public void updateRoomInventory(RoomInfo room);

	// Notifies that now you have not visited the room.
	public void forgetRoom();

	// Notifies that now the player is in that room.
	public void changeRoom(Directions direction, RoomInfo targetRoom);
	
}
