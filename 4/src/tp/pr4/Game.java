package tp.pr4;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tp.pr4.Parser;
import tp.pr4.Player;
//import tp.pr4.commands.UndoCommand;
import tp.pr4.gui.MainWindow;


public class Game {


	private Map map;
	private Player player;
	private boolean quit;
	private MainWindow mainWindow;
	//private UndoCommand undoCommand;
	
	
	
	//Default constructor. Creates a new game
	public Game(Map var_map){

		this.map = var_map;
		this.player = new Player();
		this.quit = false;
		//this.mainWindow = null;
		//this.undoCommand = new UndoCommand();
	}
	
	
	
	//Prints information about the current room
	public void showCurrentRoom(){
		
		System.out.println(this.map.getCurrentRoom().getDescription());
	}
	
	//Prints information when the game is finished
	public void showGameOver(){
		if(this.mainWindow != null){
			if(this.player.dead()){
				//JOptionPane gameOver = new JOptionPane();
				JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_FIN + this.player.toString());
				System.exit(0);
			}else if(this.map.getCurrentRoom().isExit()){
				//JOptionPane gameOver = new JOptionPane();
				JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_FIN + this.player.toString());
				System.exit(0);
			}
			//JOptionPane ask = new JOptionPane();
			int answer = JOptionPane.showConfirmDialog(this.mainWindow.getFrame(), "Are you sure ?", "Quit", JOptionPane.YES_NO_OPTION);
			if(answer == 0){
				//JOptionPane gameOver = new JOptionPane();
				JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_FIN + this.player.toString());
				System.exit(0);
			}
		}
		//this.showCurrentRoom();
		
		System.out.println(Constants.MESSAGE_FIN);
		System.out.println(this.player.toString()); //The end.
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
	public Map getMap(){
		return this.map;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean isQuit() {
		return quit;
	}
	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	public MainWindow getMainWindow() {
		return mainWindow;
	}
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	

	//Main loop of the game
	public void runGame(){
		
		//UndoCommand undoCommand = new UndoCommand();
		//undoCommand.setCurrentGame(this);
		Scanner sc = new Scanner(System.in);
		
		if(this.map == null) // If the map is null the game will be over
			this.quit = true;
		
		while(!this.quit){
				
			showCurrentRoom();  //Writes the initial room description
			System.out.println(this.player.toString()); //Writes the initial player information
			
			
			do{
				
				
				try{
					
					System.out.print("> ");
					java.lang.String com = sc.nextLine();
					//this.undoCommand.saveGame(Parser.parseCommand(com, this));
					Parser.parseCommand(com, this).execute();  //Transforms the string com into a command
					
				}
				
				catch(Exception e){}				
						
				if(this.player.dead()){
					this.requestQuit();
				}
				
			}
			while(!this.quit);
			
		}
		
	}
	
	
	//Requests the game to quit
	public void requestQuit(){
		this.quit = true;
		showGameOver(); //The end.
	}
		
}