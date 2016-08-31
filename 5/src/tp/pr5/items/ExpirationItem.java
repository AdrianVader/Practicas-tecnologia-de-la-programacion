package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.Constants;

public class ExpirationItem extends Item{
	
	private int times;
	
	
	
	
	//Constructor for an expiration item based on an id, a description and the number of times it can be used
	public ExpirationItem(java.lang.String var_id, java.lang.String  var_description, int var_times){
		
		super(var_id, var_description);
		this.times = var_times;
	}
	
	//Constructor for an expiration item based on an id and a description, with the default number of times it can be used
	public ExpirationItem(java.lang.String var_id, java.lang.String var_description){
		
		super(var_id, var_description);
		this.times = Constants.DEFAULT_EXPIRATION_LIVE;
	}
	
	
	//Returns true if the item can be used (the number of times is >0)
	public boolean canBeUsed(){
		
		boolean itCan = false;
		if (this.times > 0)
			itCan = true;
		return itCan;
	}
	
	
	//Uses an expiration item (reduces the number of times it can be used by one)
	public boolean use(Player who, Room where){
		
		this.times -= 1;
		return true;
	}
	
	
	//Returns the item information
	@Override
	public java.lang.String toString(){
		
		java.lang.String info;
		info = "--item[" + this.getId() + "]=" + this.getDescription() + "//" + this.times;
		return info;
	}

	public int getTimes() {
		return this.times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	
}
