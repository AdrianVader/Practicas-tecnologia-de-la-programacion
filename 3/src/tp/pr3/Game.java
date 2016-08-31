package tp.pr3;
import java.util.Scanner;
import tp.pr3.Parser;
import tp.pr3.Player;




public class Game {


	private Map map;
	private Player player;
	private boolean quit;
	
	
	
	//Default constructor. Creates a new player
	public Game(Map var_map){

		this.map = var_map;
		this.player = new Player();
		this.quit = false;
	}
	
	
	
	
	
	
	// Access to the player
	public Player getPlayer(){
		
		return this.player;
	}
	
	//Access to the map
	public Map getCurrentMap(){
		
		return this.map;
	}
	

	public void setMap(Map map) {
		this.map = map;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	


	
	
	//Main loop of the game
	public void runGame(){
		
		Scanner sc = new Scanner(System.in);
		
		if(this.map == null) // If the map is null the game will be over
			this.quit = true;
		
		while(!this.quit){
				
			
			try{
				
					System.out.println(this.map.getCurrentRoom().getDescription());  //Writes the initial room description
					System.out.println("HEALTH = " + this.player.getHealth() + ", SCORE =" + this.player.getScore()); //Writes the initial player information
			
			}
			catch(Exception e){
				
				this.requestQuit();
				
			}
			
			
			do{
				
				
				try{
					
					System.out.print("> ");
					java.lang.String com = sc.nextLine();
					Parser.parseCommand(com, this).execute();  //Transforms the string com into a command
					if(this.player.dead())
						this.quit = true;
				}
				
				catch(Exception e){
					
				}
				
									
			}
			while(!this.quit);
			
		}
		
		System.out.println("GAME OVER " + '\n' + " Thank you for playing, goodbye.");
		System.out.println("HEALTH = " + this.player.getHealth() + ", SCORE =" + this.player.getScore()); //The end.
				
	}	
	
	
	//Requests the game to quit
	public void requestQuit(){
		
		this.quit = true;
		
	}
		
}