package tp.pr5;

public abstract class GameController{

		
	protected Game game;
	
	
	
	public GameController(Game game){		
		this.game = game;
	}

	
	
	//Registers a GameObserver to the model
	public void registerGameObserver(GameObserver gameObserver){
		this.game.addGameObserver(gameObserver);
	}
	
	//Registers a MapObserver to the model
	public void registerMapObserver(MapObserver mapObserver){
		this.game.addMapObserver(mapObserver);
	}
	
	//Registers a PlayerObserver to the model
	public void registerPlayerObserver(PlayerObserver playerObserver){
		this.game.addPlayerObserver(playerObserver);
	}
	
	//Abstract method that runs the game. 
	public abstract void runGame();

	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	
}
