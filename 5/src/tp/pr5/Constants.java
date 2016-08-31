package tp.pr5;

import tp.pr5.Directions;

public class Constants {
	
	public static final int NUM_CELLS = 11;
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
	public static final java.lang.String MESSAGE_DOOR(Directions direction) {return "There is a door in the " + direction + ", but it is closed.";}
	public static final java.lang.String MESSAGE_DROP_ERROR(String idItem){ return "The "+ idItem + " is already in the room.";}
	public static final java.lang.String MESSAGE_EMPTY(String idItem){ return "The " + idItem + " is empty. It is deleted from the inventory.";}
	public static final java.lang.String MESSAGE_WIN = "YOU WIN";
	public static final java.lang.String MESSAGE_LOSE = "YOU LOSE";
	public static final java.lang.String MESSAGE_FIN = "GAME OVER" + LINE_SEPARATOR + "Thank you for playing, goodbye.";
	public static final java.lang.String MESSAGE_ITEM_IS_IN_INVENTORY(String idItem){ return  "You have another " + idItem + " in your inventory.";}
	public static final java.lang.String MESSAGE_ITEM_IS_IN_ROOM(String idItem){ return  "You can't drop " + idItem + ", it's already in the room.";}
	public static final java.lang.String MESSAGE_ITEMS = "My items are:";
	public static final java.lang.String MESSAGE_ITEMS_ROOM = "It contains the following items:";
	public static final java.lang.String MESSAGE_NO_ITEM(String idItem){ return "There is no " + idItem + " in your inventory.";}
	public static final java.lang.String MESSAGE_NO_ITEMS = "This room is empty";
	public static final java.lang.String MESSAGE_NO_ROOM_TO_EXAMINE = "There's no room to examine.";
	public static final java.lang.String MESSAGE_PICK_ERROR = "Do you get to pick what you dream about?";
	public static final java.lang.String MESSAGE_PLAYER_TOSTRING(int health, int score){ return "HEALTH = " + health + ", SCORE = " + score;}
	public static final java.lang.String MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS(String idItem){ return "Someone stole your " + idItem + ".";}
	public static final java.lang.String MESSAGE_UNDIRECTIONAL_DOOR = "Impossible to go through the door from this side.";
	public static final java.lang.String MESSAGE_WALL(Directions direction){ return "What the hell am I supposed to do going to " + direction.name() + "?";}
	public static final java.lang.String MESSAGE_WHAT = "What?";
	public static final java.lang.String PROMPT = ">";
	public static final java.lang.String HELP = "You are lost. You are alone. You wander around. Your command words are:" + '\n' + '\t' + "EXAMINE|EXAMINAR" + '\n' + '\t' + "GO|IR { NORTH|EAST|SOUTH|WEST }" + '\n' + '\t' + "HELP|AYUDA" + '\n' + '\t' + "LOOK|MIRA [<<id>>]" + '\n' + '\t' + "PICK|COGER <<id>>" + '\n' + '\t' + "DROP|SOLTAR <<id>>" + '\n' + '\t' + "QUIT|SALIR" + '\n' + '\t' + "USE|USAR <<id>>";
	public static final java.lang.String MESSAGE_UNDO_ERROR = "You can not undo.";
	
}
