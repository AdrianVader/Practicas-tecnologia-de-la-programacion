package tp.pr5;

public interface GameObserver {

	
	//Notifies that the game cannot execute a command
	public void gameError(java.lang.String msg);
	
	//Notifies that the player requests help information
	public void gameHelp();
	
	//Notifies that the game is finished and whether the player wins or is death
	public void gameOver(boolean win);
	
	//Notifies that the player requests to quit the game.
	public void gameQuit();
	
	//Notifies that the game starts.
	public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth);
	
}
