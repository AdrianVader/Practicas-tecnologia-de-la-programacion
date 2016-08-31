package tp.pr4;

public class Constants {
	
	public static final int NUM_CELLS = 11;// Pensé que podía ser útil añadir el número de celdas por si hay que redimensionarlo en algún momento.
	public static final int DEFAULT_EXPIRATION_LIVE = 1;
	public static final int INITIAL_LIVE = 100;
	public static final int INITIAL_SCORE = 0;
	public static final java.lang.String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final int LOST_LIVE = -5;
	public static final java.lang.String MESSAGE_POOR = "You are poor, you have not got any item (yet).";
	public static final java.lang.String MESSAGE_CHANGE_ROOM(Directions direction){ return "...moving to " + direction.name();}
	public static final java.lang.String MESSAGE_CHANGES = "Something changes ...";
	public static final java.lang.String MESSAGE_CHANGES_ERROR = "I did not go to TP classes last week, I do not know how to use it...";
	public static final java.lang.String MESSAGE_DIE = "You are dead.";
	public static final java.lang.String MESSAGE_DOOR = "Impossible to go through the door from this side.";
	public static final java.lang.String MESSAGE_DROP_ERROR(String idItem){ return "The "+ idItem + " is already in the room.";}
	public static final java.lang.String MESSAGE_EMPTY(String idItem){ return "The " + idItem + " is empty. It is deleted from the inventory.";}
	public static final java.lang.String MESSAGE_FIN = "GAME OVER" + LINE_SEPARATOR + "Thank you for playing, goodbye.";
	public static final java.lang.String MESSAGE_ITEM_IS_IN_INVENTARY(String idItem){ return  "You have another " + idItem + " in your inventory.";}
	public static final java.lang.String MESSAGE_ITEM_IS_IN_ROOM(String idItem){ return  "No puedes tirar " + idItem + "ya hay uno igual.";}//---------------------------------------------------
	public static final java.lang.String MESSAGE_ITEMS = "My items are:";
	public static final java.lang.String MESSAGE_ITEMS_ROOM = "It contains the following items:";
	public static final java.lang.String MESSAGE_NO_ITEM(String idItem){ return "There is no " + idItem + " in your inventory.";}
	public static final java.lang.String MESSAGE_NO_ITEMS = "This room is empty";
	public static final java.lang.String MESSAGE_NO_ROOM_TO_EXAMINE = "No hay habitación que examinar.";//------------------------------------------------
	public static final java.lang.String MESSAGE_PICK_ERROR = "Do you get to pick what you dream about?";
	public static final java.lang.String MESSAGE_PLAYER_TOSTRING(Player player){ return "HEALTH = " + player.getHealth() + ", SCORE =" + player.getScore();}// Writes the player's status.
	public static final java.lang.String MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS(String idItem){ return "Someone stole your " + idItem + ".";}
	public static final java.lang.String MESSAGE_UNDIRECTIONAL_DOOR = "Esta puerta no se abre desde este lado.";//------------------------------------------------
	public static final java.lang.String MESSAGE_WALL(Directions direction){ return "What the hell am I supposed to do going to " + direction.name() + "?";}
	public static final java.lang.String MESSAGE_WHAT = "What?";
	public static final java.lang.String PROMT = ">";
	
}
