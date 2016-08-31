package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.Parser;
import tp.pr5.commands.Command;

public class GameControllerConsole extends GameController{

	private Console console;
	
	
	
	public GameControllerConsole(Game game) {
		super(game);
		this.console = new Console();
		this.registerGameObserver(this.console);
		this.registerMapObserver(this.console);
		this.registerPlayerObserver(this.console);	
	}

	//This function implements the main loop of the game.
	public void	runGame(){
		
		Scanner sc = new Scanner(System.in);
		if(this.game == null)
			this.game.setOver(true);
		
		while(!this.game.isOver()){
	
			this.game.requestStart();
		
			do{
								
				try{

					this.console.prompt();
					java.lang.String com = sc.nextLine();
					Command command = Parser.parseCommand(com);  //Transforms the string com into a command
					this.game.executeCommand(command);
				}
				
				catch(Exception e){ // Si no ha reconocido el comando.
					if(e.getClass() == tp.pr5.commands.exceptions.WrongCommandFormatException.class){
						this.console.gameError(Constants.MESSAGE_WHAT);
					}
				}				
						
				if(this.game.deadPlayer()){
					this.console.gameOver(false);
				}
				
			}
			while(!this.game.isOver());
			
		}
		
	}
	 
	
}
