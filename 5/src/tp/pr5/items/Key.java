package tp.pr5.items;

import tp.pr5.Door;
import tp.pr5.Player;
import tp.pr5.Room;


public class Key extends PersistentItem{

	private Door opensDoor;
	
	
	
	//Default constructor of key.
	public Key(java.lang.String id, java.lang.String description, Door door){
		
		super(id, description);
		this.opensDoor = door;
	}
	
	
	
	
	//Toggle the state of the door (open-closed / closed-open).
	public boolean use(Player who, Room where){
		boolean used = true;
		
		if((where != null) && (this.opensDoor != null) && (this.opensDoor.isInRoom(where))){
			
			if (this.opensDoor.isOpen())
				this.opensDoor.close();
			else
				this.opensDoor.open();
		}
		else
			used = false;
		
		return used;
	}




	public Door getOpensDoor() {
		return opensDoor;
	}




	public void setOpensDoor(Door opensDoor) {
		this.opensDoor = opensDoor;
	}
	
}
