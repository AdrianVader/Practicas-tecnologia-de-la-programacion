package tp.pr4.items;

public abstract class PersistentItem extends Item{
	
	//Default constructor for a persistent item
	public PersistentItem(String id, java.lang.String description){
		
		super(id, description);
	}

	//The persistent items can be used always, thus this method returns always true.
	public boolean canBeUsed(){
		
		return true;
	}
	
}
