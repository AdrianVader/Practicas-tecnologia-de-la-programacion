package tp.pr1;

public class Main {

	//Entrance point of the game. It creates the game and runs it.
	
	public Main(){}
	
	/**
	 * Create the rooms and configure the initial current room.
	 * @return The room where the player starts the game
	 */
	private static Room createRooms(){
		Room []_rooms = new Room[10];
		 String LINE_SEPARATOR = System.getProperty("line.separator");
		// 0: Main Entrance
		_rooms[0] = new Room(false ,"MAIN ENTRANCE" + LINE_SEPARATOR +
				"You are at the School of Computer Science, at Complutense University of Madrid. " +
				"Completely desperate due to the first Java assignment that you have to finish tomorrow, " + LINE_SEPARATOR +
				"you are looking for one of the lecturers who can help you with it." + LINE_SEPARATOR +
				"They are your last hope for completing the assignment on time!");
		// 1: Bulletin board
		_rooms[1] = new Room(false, "BULLETIN BOARD" + LINE_SEPARATOR +
			"Mmmh... it seems that most of the lecturers work on the 4th floor.");

		// 2: Library
		_rooms[2] = new Room(false, "LIBRARY ENTRANCE" + LINE_SEPARATOR +
			"Firstly, you can visit the library. Maybe you can find a good book about Java instead of disturbing the lecturers." + LINE_SEPARATOR +
			"Wait! What the f..., it is their work! isn't it?... No way, you decide to talk with them.");

		// 3: Bus (END)
		_rooms[3] = new Room(true, "BUS STOP" + LINE_SEPARATOR +
			"The bus is coming... and you think: these lecturers are very rude and they are not going to help me" + LINE_SEPARATOR +
			"Finally, you decide to go back home.");
		
		// 4: Hall 
		_rooms[4] = new Room(false, "HALL" + LINE_SEPARATOR +
			"You have to go to the 4th floor. So you can take the lift... but using the stairs will be better for reducing your belly." + LINE_SEPARATOR +
			"Another option could be eating something at the canteen: you have a lot of questions for them and should be ready.");
		
		// 5: Lift 
		_rooms[5] = new Room(false, "LIFT" + LINE_SEPARATOR +
			"Buf! You're tired and prefer to take the lift." + LINE_SEPARATOR +
			"CRAAK ... CRAAK ... CRAAK ... CRAAK" + LINE_SEPARATOR +
			"The doors are opened. You have arrived at the 4th floor");
		
		// 6: Stairs 
		_rooms[6] = new Room(false, "STAIRS" + LINE_SEPARATOR +
			"You think: It's true! I'am fat! I'll take the stairs..." + LINE_SEPARATOR +
			"1, 2, 3 ... 134, ... 135, ... 136, ..." +
			"Buf! At last! You have arrived at the 4th floor. Mental note: this is the last time you take the stairs.");
		
		// 7: Canteen (END) 
		_rooms[7] = new Room(true, "CANTEEN" + LINE_SEPARATOR +
			"You decide to eat something first." + LINE_SEPARATOR +
			"When you go into the canteen, your friends are there... AND THEY ARE PLAYING THE LAST FIFA!!!" + LINE_SEPARATOR +
			"Ok... only one match before going upstairs. You need some relax..." + LINE_SEPARATOR +
			"(3 hours later...) Oh sh..!!! It is too late! You run upstairs but all the offices are closed." + LINE_SEPARATOR +
			"Definetly, you are not going to finish the assignment on time :-(");
		
		// 8: Fourth floor (END)
		_rooms[8] = new Room(true, "4th FLOOR" + LINE_SEPARATOR +
			"Ok, you're here and it seems that som of the lecturers are still here. You drink some water, first (really thirsty!)." + LINE_SEPARATOR +
			"Well, hopefully the lecturers will help you."+ LINE_SEPARATOR +
			"(1 hour later)... (Lecturer) and, finally, create the Main object and initialize the rooms with THIS source code. It is easy! :-)" + LINE_SEPARATOR +
			"Oh... no, thanks a lot :-) (Cool!!! It is done! My first assignment will be on time!)");

		
		_rooms[0].setRoom(Directions.NORTH, _rooms[2]);
		_rooms[0].setRoom(Directions.SOUTH, _rooms[3]);
		_rooms[0].setRoom(Directions.EAST, _rooms[1]);
		_rooms[0].setRoom(Directions.WEST, _rooms[4]);

		_rooms[1].setRoom(Directions.WEST, _rooms[0]);
		
		_rooms[2].setRoom(Directions.SOUTH, _rooms[0]);
		
		_rooms[4].setRoom(Directions.NORTH, _rooms[5]);
		_rooms[4].setRoom(Directions.SOUTH, _rooms[6]);
		_rooms[4].setRoom(Directions.EAST, _rooms[0]);
		_rooms[4].setRoom(Directions.WEST, _rooms[7]);
		
		_rooms[5].setRoom(Directions.WEST, _rooms[8]);
		_rooms[6].setRoom(Directions.WEST, _rooms[8]);
		
		// Initial room is room 0
		return _rooms[0];
	}
	
	//Main method
	public static void main(java.lang.String[] args){
		
		Game g = new Game(); //Creates a new Game object
		Room initRoom = createRooms();  //Sets the initial room and creates the map
		if(g.initGame(initRoom)){ //If the room isn't null
			g.runGame();
		}
		return;
	}
	
	
}