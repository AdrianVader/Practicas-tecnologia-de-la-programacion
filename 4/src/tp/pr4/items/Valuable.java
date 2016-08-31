package tp.pr4.items;
import tp.pr4.Player;
import tp.pr4.Constants;
import tp.pr4.Room;

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
			this.setTimes(this.getTimes()-1);
			who.removeItem(this.getId());
			System.out.println(Constants.MESSAGE_EMPTY(this.getId()));
			return true;
		}else{
			who.removeItem(this.getId());
			System.out.println(Constants.MESSAGE_EMPTY(this.getId()));
			return false;
		}
	}


	public int getGivesScore() {
		return givesScore;
	}
	public void setGivesScore(int givesScore) {
		this.givesScore = givesScore;
	}
	
	
}
