package tp.pr5;


//Enum that provides all possible directions
public enum Directions {
	
	NORTH,
	EAST,
	SOUTH,
	WEST,
	UNKNOWN;
	
	//Returns the opposite logic direction.
	public Directions opposite(Directions direction){
		 
		if(direction == EAST){
			return WEST;
		}
		else if(direction == NORTH){
			return SOUTH;
		}
		else if(direction == SOUTH){
			return NORTH;
		}
		else if(direction == WEST){
			return EAST;
		}
		else{
			return UNKNOWN;
		}		
		
	}
}
