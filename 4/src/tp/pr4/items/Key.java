package tp.pr4.items;
import tp.pr4.Door;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.Constants;


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
			System.out.println(Constants.MESSAGE_CHANGES);
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
