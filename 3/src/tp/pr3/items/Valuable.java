package tp.pr3.items;
import tp.pr3.Player;
import tp.pr3.Constants;
import tp.pr3.Room;

public class Valuable extends ExpirationItem{
	
	private int givesScore;
	
	
	//Default constructor of a Valuable Item.
	public Valuable (java.lang.String id, java.lang.String description, int score){
		
		super(id, description, Constants.DEFAULT_EXPIRATION_LIVE);
		this.givesScore = score;
	}
	
	
	
	
	//The points are added to the player. Once it has been used, a valuable item is deleted.
	@Override
	public boolean use (Player who, Room where){
		
		if (this.canBeUsed()){
			who.addPoints(this.givesScore);
			System.out.println(Constants.MESSAGE_CHANGES);
			this.setTimes(0);
			who.removeItem(this.getId());
			System.out.println("The " + this.getId() + Constants.MESSAGE_EMPTY);
			return true;
		}else{
			who.removeItem(this.getId());
			System.out.println(Constants.MESSAGE_EMPTY);
			return false;
		}
	}
	
}
